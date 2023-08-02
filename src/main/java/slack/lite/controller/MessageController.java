package slack.lite.controller;

import org.aspectj.bridge.Message;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import slack.lite.service.MessageService;

@RestController
// @RequestMapping("??")
public class MessageController {

	@Autowired
	MessageService messageService;

	@PostMapping("messages")
	public void create(@RequestBody Message message){
		messageService.addMessage(message);
	}

	@GetMapping("messages")
	public List<Message> get20MessagesFromIndex(){
		return messageService.get20MessagesFromIndex();
	}
}
