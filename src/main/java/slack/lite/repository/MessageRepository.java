package slack.lite.repository;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.Message;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, UUID> {
	/**
	 * Provide a set of the 20 most recent messages according to offset
	 * 
	 * <pre>
	 * Example 1:
	 * UUID id = UUID.fromString("sample-identifier");
	 * OffsetScrollPosition offset = ScrollPosition.offset((long) 0);
	 * findTop20ByThreadsIdOrderByDateDesc(id, offset);
	 * </pre>
	 * 
	 * @param id     the thread identifier
	 * @param offset number of messages to offset
	 * @return returns a set of the 20 most recent messages according to offset
	 */
	public Set<Message> findTop20ByThreadsIdOrderByDateDesc(UUID id, OffsetScrollPosition offset);
}