package slack.lite.controller;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.Thread;
import slack.lite.service.ThreadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

class ThreadLite {
	public String label;
}

@RestController
@RequestMapping("api/v1/threads")
public class ThreadController {
	@Autowired
	private ThreadService service;

	@GetMapping
	public ResponseEntity<Set<Thread>> load() {
		return ResponseEntity.ok(service.load());
	}

	@PostMapping
	public ResponseEntity<Thread> create(@RequestBody ThreadLite th) {
		return ResponseEntity.of(service.save(th.label));
	}

	@PutMapping
	public ResponseEntity<Thread> update(@RequestBody Thread th) {
		return ResponseEntity.of(service.save(th));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Thread> delete(@PathVariable("id") UUID id) {
		return ResponseEntity.of(service.delete(id));
	}
}