package slack.lite.controller;

import org.aspectj.bridge.Message;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

import slack.lite.service.MessageService;

@RestController
// @RequestMapping("??")
public class MessageController {

	@Autowired
	MessageService messageService;

	@PostMapping("messages")
	public void create(@RequestBody Message content) {
		messageService.addMessage(content);
	}

	@GetMapping("messages")
	public List<Message> getMessages() {
		return messageService.findAllMessages();
	}

	@GetMapping("messages/{id}")
	public void deleteMessage(@PathVariable("id") UUID id) {
		messageService.deleteMessage();
	}
}
