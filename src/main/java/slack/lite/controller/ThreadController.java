package slack.lite.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import slack.lite.service.ThreadService;
import slack.lite.entity.Thread;

@RestController
@RequestMapping("api/v1/thread/")
public class ThreadController {
	
	@Autowired
	ThreadService threadService;

	@PostMapping
	public void addThread(@RequestBody Thread thread) {
		threadService.add(thread);
	}

	@GetMapping
	public List<Thread> getAllThread() {
		return threadService.getAll();
	}

	@DeleteMapping("{id}")
	public void deleteThread(@PathVariable("id") UUID id) {
		threadService.delete(id);
	}
}
