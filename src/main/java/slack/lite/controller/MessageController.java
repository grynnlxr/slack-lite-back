package slack.lite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import slack.lite.entity.Message;
import slack.lite.service.MessageService;


@RestController
@RequestMapping("api/v1/messages/")
public class MessageController {

	@Autowired
	MessageService messageService;

	@PostMapping
	public void create(@RequestBody Message content) {
		messageService.addMessage(content);
	}

	@GetMapping
	public List<Message> getMessages() {
		return messageService.findAllMessages();
	}

	@GetMapping("{id}")
	public void deleteMessage(@PathVariable("id") UUID id) {
		messageService.deleteMessage();
	}
}
