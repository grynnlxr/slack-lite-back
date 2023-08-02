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

	public Thread save(String label) {
		Thread th = new Thread();
		th.setLabel(label);
		return repository.save(th);
	}

	public Optional<Thread> save(Thread th) {
		Optional<Thread> op = repository.findById(th.getId());
		if (op.isPresent() && !op.get().isLocked()) {
			Thread thread = op.get();
			thread.setLabel(th.getLabel());
			repository.save(thread);
			return Optional.of(thread);
		}
		return Optional.empty();
	}

	public Set<Thread> load() {
		return repository.findAllByOrderByLabelAsc();
	}

	public boolean delete(UUID id) {
		Optional<Thread> ot = repository.findById(id);
		if (ot.isPresent() && !ot.get().isLocked()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}