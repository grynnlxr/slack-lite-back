package slack.lite.repository;

import java.util.List;
import java.util.UUID;
import slack.lite.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, UUID> {

	List<Message> findByThreadByIndex();
}