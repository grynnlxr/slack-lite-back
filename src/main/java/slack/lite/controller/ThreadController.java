package slack.lite.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import slack.lite.entity.Thread;
import slack.lite.service.ThreadService;

@RestController
@RequestMapping("api/v1/threads")
public class ThreadController {
	
	@Autowired
	ThreadService threadService;

	// Poster un thread
	@PostMapping
	public void addThread(@RequestBody Thread thread) {
		threadService.add(thread);
	}

	// Modifier un thread
	@PutMapping("{id}")
		public ResponseEntity<String> updateThread(@RequestBody Thread thread, @PathVariable("id") UUID id){
		boolean ok = threadService.update(thread, id);
        if(ok){
            return ResponseEntity.ok("update OK");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	// Afficher un thread
	@GetMapping
	public List<Thread> getAllThread() {
		return threadService.getAll();
	}

	// Supprimer un thread
	@DeleteMapping("{id}")
	public void deleteThread(@PathVariable("id") UUID id) {
		threadService.delete(id);
	}
}
