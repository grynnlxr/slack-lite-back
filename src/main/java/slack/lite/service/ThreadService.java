package slack.lite.service;

import java.util.Set;
import java.util.UUID;
import java.util.Optional;
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

	public boolean delete(UUID id) {
		Optional<Thread> ot = repository.findById(id);
		// TODO : check if thread is locked
		if (ot.isPresent()) { // && !ot.get().isLocked()
			try {
				repository.deleteById(id);
				return true;
			} catch (IllegalArgumentException e) {
			}
		}
		return false;
	}
}