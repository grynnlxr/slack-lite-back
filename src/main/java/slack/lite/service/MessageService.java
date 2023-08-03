package slack.lite.service;

import java.util.Set;
import java.util.UUID;
import java.util.Optional;
import slack.lite.entity.User;
import slack.lite.entity.Thread;
import slack.lite.entity.Message;
import slack.lite.repository.UserRepository;
import slack.lite.repository.ThreadRepository;
import org.springframework.stereotype.Service;
import slack.lite.repository.MessageRepository;
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
			msg.setContent(content);
			msg.setAuthor(author.get());
			msg.setThread(thread.get());
			return Optional.of(messageRepo.save(msg));
		}
		return Optional.empty();
	}

	public Set<Message> load(UUID id, Integer offset) {
		OffsetScrollPosition o = ScrollPosition.offset(offset);
		return messageRepo.findTop20ByThreadIdOrderByDateDesc(id, o);
	}

	public Optional<Message> delete(UUID id) {
		Optional<Message> msg = messageRepo.findById(id);
		if (msg.isPresent()) {
			messageRepo.deleteById(msg.get().getId());
		}
		return msg;
	}
}