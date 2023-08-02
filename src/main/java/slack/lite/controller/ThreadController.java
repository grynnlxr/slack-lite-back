package slack.lite.controller;

import java.util.Set;
import java.util.UUID;
import java.util.Optional;
import slack.lite.entity.Thread;
import slack.lite.entity.Response;
import slack.lite.service.ThreadService;
import org.springframework.http.HttpStatus;
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
	private String bad_label = "bad label";
	private String bad_id = "bad id";
	private String not_found = "specified thread not found";
	private String successfully_deleted = "thread deleted";

	@Autowired
	private ThreadService service;

	@GetMapping
	public ResponseEntity<Response> load() {
		Set<Thread> thrds = service.load();
		Response content = new Response(HttpStatus.OK, thrds);
		return new ResponseEntity<>(content, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Response> create(@RequestBody ThreadLite th) {
		String c = th.label;
		if (c.trim().length() < 1) {
			Response content = new Response(HttpStatus.BAD_REQUEST, bad_label);
			return new ResponseEntity<>(content, HttpStatus.BAD_REQUEST);
		}
		Thread saved = service.save(c);
		Response content = new Response(HttpStatus.OK, saved);
		return new ResponseEntity<>(content, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Response> update(@RequestBody Thread th) {
		if (th.getId() == null) {
			Response content = new Response(HttpStatus.BAD_REQUEST, bad_id);
			return new ResponseEntity<>(content, HttpStatus.BAD_REQUEST);
		}
		if (th.getLabel().trim().length() < 1) {
			Response content = new Response(HttpStatus.BAD_REQUEST, bad_label);
			return new ResponseEntity<>(content, HttpStatus.BAD_REQUEST);
		}
		Optional<Thread> saved = service.save(th);
		if (saved.isPresent()) {
			Response content = new Response(HttpStatus.OK, saved.get());
			return new ResponseEntity<>(content, HttpStatus.OK);
		}
		Response content = new Response(HttpStatus.BAD_REQUEST, not_found);
		return new ResponseEntity<>(content, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") UUID id) {
		if (service.delete(id)) {
			Response content = new Response(HttpStatus.OK, successfully_deleted);
			return new ResponseEntity<>(content, HttpStatus.OK);
		}
		Response content = new Response(HttpStatus.BAD_REQUEST, bad_id);
		return new ResponseEntity<>(content, HttpStatus.BAD_REQUEST);
	}
}