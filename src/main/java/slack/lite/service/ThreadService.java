package slack.lite.service;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.Thread;
import slack.lite.repository.ThreadRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ThreadService {
	@Autowired
	ThreadRepository repository;

	public Thread save(Thread thread) {
		return repository.save(thread);
	}

	public Set<Thread> load() {
		return repository.findAllByOrderByLabelAsc();
	}

	public void delete(UUID id) {
		// SOON : check if thread is locked before lock
		repository.deleteById(id);
	}
}