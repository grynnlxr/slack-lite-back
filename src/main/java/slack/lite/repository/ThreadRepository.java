package slack.lite.repository;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread, UUID> {
	public Set<Thread> findAllByOrderByLabelAsc();
}