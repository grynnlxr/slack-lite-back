package slack.lite.service;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.User;
import slack.lite.entity.Thread;
import slack.lite.entity.Message;
import org.springframework.stereotype.Service;
import slack.lite.repository.MessageRepository;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MessageService {
	@Autowired
	MessageRepository repository;

	public Message save(UUID id, UUID tid, String content) {
		User author = new User();
		author.setId(id);
		Thread thread = new Thread();
		thread.setId(tid);
		Message msg = new Message();
		msg.setContent(content);
		msg.setAuthor(author);
		msg.setThread(thread);
		return repository.save(msg);
	}

	public Set<Message> load(UUID id, Integer offset) {
		OffsetScrollPosition o = ScrollPosition.offset(offset);
		return repository.findTop20ByThreadIdOrderByDateDesc(id, o);
	}

	public boolean delete(UUID id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (IllegalArgumentException e) {
		}
		return false;
	}
}