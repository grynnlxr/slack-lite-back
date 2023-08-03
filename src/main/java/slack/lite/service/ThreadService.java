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

	public Optional<Thread> save(String label) {
		if (label.trim().length() < 1) {
			return Optional.empty();
		}
		return Optional.of(repository.save(new Thread(label)));
	}

	public Optional<Thread> save(Thread thread) {
		String label = thread.getLabel();
		if (label.trim().length() < 1) {
			return Optional.empty();
		}
		Optional<Thread> original = repository.findById(thread.getId());
		if (original.isPresent() && !original.get().isLocked()) {
			original.get().setLabel(label);
			repository.save(original.get());
		}
		return original;
	}

	public Set<Thread> load() {
		return repository.findAllByOrderByLabelAsc();
	}

	public Optional<Thread> delete(UUID id) {
		Optional<Thread> original = repository.findById(id);
		if (original.isPresent() && !original.get().isLocked()) {
			repository.deleteById(id);
			return original;
		}
		return Optional.empty();
	}
}