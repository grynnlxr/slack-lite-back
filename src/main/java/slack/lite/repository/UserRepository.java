package slack.lite.repository;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
	public Set<User> findAllByName(String name);
}