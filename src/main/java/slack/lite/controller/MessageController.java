package slack.lite.controller;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.Message;
import slack.lite.service.MessageService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

class LoadingParameters {
	public UUID id;
	public Integer offset;
}

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {
	@Autowired
	MessageService service;

	@GetMapping
	public Set<Message> load(@RequestBody LoadingParameters params) {
		return service.load(params.id, params.offset);
	}

	@PostMapping
	public Message create(@RequestBody String content) {
		Message msg = new Message();
		msg.setContent(content);
		// TODO : define author via http authentication header
		return service.save(msg);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") UUID id) {
		service.delete(id);
	}
}