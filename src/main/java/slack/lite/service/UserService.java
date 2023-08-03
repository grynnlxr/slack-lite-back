package slack.lite.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import slack.lite.entity.User;
import slack.lite.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;

	public Optional<User> save(String name) {
		if (name.trim().length() < 1) {
			return Optional.empty();
		}
		return Optional.of(repository.save(new User(name)));
	}

	public Optional<User> login(String name) {
		Set<User> users = repository.findAllByName(name);
		if (!users.isEmpty()) {
			return Optional.of(users.iterator().next());
		}
		return Optional.empty();
	}
}