import java.util.List;
import java.util.Optional;

@Service
public class ThreadService {
	

    @Autowired
    ThreadRepository threadRepository;
;
   
    public void getAllThread(Thread thread) {
        threadRepository.save(thread);
    }

    public void updateThread(Thread thread) {
        threadRepository.save(thread);
    }

    public void deleteThread(Thread thread) {
        threadRepository.save(thread);
    }

    public List<Tigre> findAllThread() {
        return threadRepository.findAll();
    }

    public boolean updateThread(Thread thread, Integer id) {
        Optional<Thread> optional = threadRepository.findById(id);
        if(optional.isPresent()){
            threadRepository.save(thread);
            return true;
        }
        else {
            return false;
        }
    }

	public void deleteThread(int id){
        tjreadRepository.deleteById(id);
    }
}


}
