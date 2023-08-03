package slack.lite.controller;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.Message;
import slack.lite.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

class MessageLite {
	public UUID thread;
	public String content;
}

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {
	@Autowired
	private MessageService service;

	@GetMapping
	public ResponseEntity<Set<Message>> load(@RequestParam("thread") UUID id, @RequestParam("offset") Integer offset) {
		return ResponseEntity.ok(service.load(id, offset));
	}

	@PostMapping
	public ResponseEntity<Message> create(@RequestHeader("Authorization") UUID id, @RequestBody MessageLite ml) {
		return ResponseEntity.of(service.save(id, ml.thread, ml.content));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Message> delete(@PathVariable("id") UUID id) {
		return ResponseEntity.of(service.delete(id));
	}
}