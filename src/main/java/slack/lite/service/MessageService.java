package slack.lite.service;

import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.Optional;
import slack.lite.entity.User;
import slack.lite.entity.Thread;
import slack.lite.entity.Message;
import slack.lite.repository.UserRepository;
import slack.lite.repository.ThreadRepository;
import org.springframework.stereotype.Service;
import slack.lite.repository.MessageRepository;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MessageService {
	@Autowired
	MessageRepository messageRepo;
	@Autowired
	ThreadRepository threadRepo;
	@Autowired
	UserRepository userRepo;

	private Message format(Message message) {
		String content = message.getContent();
		content = "<p>" + content + "</p>";
		String[][] replace = {
				{ "[{]d(4|6|8|10|12|20|100):([0-9]+)[}]", "<span class=\"dice-$1\">$2</span>" },
				{ "[*]{2}(.*?)[*]{2}", "<b>$1</b>" },
				{ "[/]{2}(.*?)[/]{2}", "<em>$1</em>" },
				{ "[-]{2}(.*?)[-]{2}", "<del>$1</del>" },
				{ "[_]{2}(.*?)[_]{2}", "<span class=\"underline\">$1</span>" },
				{ "\n\n", "</p><p>" },
				{ "\n", "<br/>" },
				{ "<p>(https://tenor.com/[a-zA-Z0-9]+\\.gif)</p>", "<img src=\"$1\">" }
		};
		for (String[] rule : replace) {
			content = Pattern.compile(rule[0]).matcher(content).replaceAll(rule[1]);
		}
		message.setContent(content);
		return message;
	}

	public Optional<Message> save(UUID id, UUID tid, String content) {
		Optional<User> author = userRepo.findById(id);
		Optional<Thread> thread = threadRepo.findById(tid);
		content = content.trim();
		if (author.isPresent() && thread.isPresent() && content.length() > 0) {
			Message msg = new Message();
			content = StringEscapeUtils.escapeHtml4(content);
			content = Pattern.compile("[{]d(4|6|8|10|12|20|100):[0-9]+[}]").matcher(content).replaceAll("{d$1}");
			content = Pattern.compile("[{]d(4|6|8|10|12|20|100)[}]").matcher(content).replaceAll(match -> {
				Integer max = Integer.parseInt(match.group(1));
				Integer rand = (int) Math.floor(Math.random() * max) + 1;
				return "{d" + max + ":" + rand + "}";
			});
			msg.setContent(content);
			msg.setAuthor(author.get());
			msg.setThread(thread.get());
			msg = messageRepo.save(msg);
			return load(msg.getId());
		}
		return Optional.empty();
	}

	public Optional<Message> load(UUID id) {
		Optional<Message> o = messageRepo.findById(id);
		if (o.isPresent()) {
			Message msg = o.get();
			msg = format(msg);
			return Optional.of(msg);
		}
		return Optional.empty();
	}

	public Set<Message> load(UUID id, Integer offset) {
		OffsetScrollPosition o = ScrollPosition.offset(offset);
		Set<Message> messages = messageRepo.findTop20ByThreadIdOrderByDateDesc(id, o);
		for (Message message : messages) {
			format(message);
		}
		return messages;
	}

	public Optional<Message> delete(UUID id) {
		try {
			messageRepo.deleteById(id);
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
		Message msg = new Message();
		msg.setId(id);
		return Optional.of(msg);
	}
}