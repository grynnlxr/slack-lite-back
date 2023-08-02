package slack.lite.controller;

import java.util.Set;
import java.util.UUID;
import slack.lite.entity.Message;
import slack.lite.entity.Response;
import slack.lite.service.MessageService;
import org.springframework.http.HttpStatus;
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
	public String content;
}

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {
	private String bad_id = "cette id (%s) ne correspond à aucun message.";
	private String bad_content = "le message ne peut pas être vide ou ne contenir que des caractéres blanc.";
	private String successfully_deleted = "le message à correctement été supprimer.";

	@Autowired
	private MessageService service;

	@GetMapping
	public ResponseEntity<Response> load(@RequestParam UUID id, @RequestParam Integer offset) {
		Set<Message> msgs = service.load(id, offset);
		Response content = new Response(HttpStatus.OK, msgs);
		return new ResponseEntity<>(content, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Response> create(@RequestHeader("Authorization") UUID id, @RequestBody MessageLite ml) {
		String c = ml.content;
		if (c.trim().length() < 1) {
			Response content = new Response(HttpStatus.BAD_REQUEST, bad_content);
			return new ResponseEntity<>(content, HttpStatus.BAD_REQUEST);
		}
		// TODO : optional pour géré le cas de l'id (Authorization) qui ne correspond à
		// aucun user
		Message newmsg = service.save(id, c);
		Response content = new Response(HttpStatus.OK, newmsg);
		return new ResponseEntity<>(content, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") UUID id) {
		if (service.delete(id)) {
			Response content = new Response(HttpStatus.OK, successfully_deleted);
			return new ResponseEntity<>(content, HttpStatus.OK);
		}
		Response content = new Response(HttpStatus.BAD_REQUEST, String.format(bad_id, id));
		return new ResponseEntity<>(content, HttpStatus.BAD_REQUEST);
	}
}