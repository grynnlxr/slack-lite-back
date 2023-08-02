package slack.lite.service;

import java.util.Set;
import java.util.UUID;
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

	public Message save(Message msg) {
		return repository.save(msg);
	}

	public Set<Message> load(UUID id, Integer offset) {
		OffsetScrollPosition o = ScrollPosition.offset(offset);
		return repository.findTop20ByThreadsIdOrderByDateDesc(id, o);
	}

	public void delete(UUID id) {
		repository.deleteById(id);
	}
}