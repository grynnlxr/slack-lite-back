package slack.lite.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import slack.lite.entity.Thread;
import slack.lite.repository.ThreadRepository;

@Service
public class ThreadService {
	
    @Autowired
    ThreadRepository threadRepository;
   
	// Ajout d'un tread
    public void add(Thread thread) {
		threadRepository.save(thread);
	}
	
	// Récupérer tous les threads
	public List<Thread> getAll() {
		return threadRepository.findAll(); 
    }
	
	// Mise à jour d'un thread
    public boolean update(Thread thread, UUID id) {
    	Optional<Thread> optional = threadRepository.findById(id);
			if(optional.isPresent()) {
				threadRepository.save(thread);
				return true;
			} else {
				return false;
			}
    }

	// Supprimer un thread
    public void delete(UUID id) {
        threadRepository.deleteById(id);
    }    
}