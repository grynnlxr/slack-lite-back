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

	public Optional<Message> save(UUID id, UUID tid, String content) {
		Optional<User> author = userRepo.findById(id);
		Optional<Thread> thread = threadRepo.findById(tid);
		if (author.isPresent() && thread.isPresent() && content.trim().length() > 0) {
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
			return Optional.of(messageRepo.save(msg));
		}
		return Optional.empty();
	}

	public Set<Message> load(UUID id, Integer offset) {
		OffsetScrollPosition o = ScrollPosition.offset(offset);
		Set<Message> messages = messageRepo.findTop20ByThreadIdOrderByDateDesc(id, o);
		for (Message message : messages) {
			String content = message.getContent();
			content = Pattern.compile("[{]d(4|6|8|10|12|20|100):([0-9]+)[}]").matcher(content)
					.replaceAll("<span class=\"dice-$1\">$2</span>");
			content = Pattern.compile("[*]{2}(.*?)[*]{2}").matcher(content).replaceAll("<b>$1</b>");
			content = Pattern.compile("[/]{2}(.*?)[/]{2}").matcher(content).replaceAll("<em>$1</em>");
			content = Pattern.compile("[-]{2}(.*?)[-]{2}").matcher(content).replaceAll("<del>$1</del>");
			content = Pattern.compile("[_]{2}(.*?)[_]{2}").matcher(content)
					.replaceAll("<span class=\"underline\">$1</span>");
			content = Pattern.compile("\n\n").matcher(content).replaceAll("</p><p>");
			content = "<p>" + content + "</p>";
			content = Pattern.compile("\n").matcher(content).replaceAll("<br/>");
			content = Pattern.compile("^(https://tenor.com/[^\\S])$").matcher(content).replaceAll("<img src=\"$1\">");
			message.setContent(content);
		}
		return messages;
	}

	public Optional<Message> delete(UUID id) {
		Optional<Message> msg = messageRepo.findById(id);
		if (msg.isPresent()) {
			messageRepo.deleteById(msg.get().getId());
		}
		return msg;
	}
}