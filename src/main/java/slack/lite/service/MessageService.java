package slack.lite.service;

import java.util.List;

import slack.lite.entity.Message;
import slack.lite.repository.MessageRepository;

@Service
public class MessageService {

	private final MessageRepository messageRepository;

	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	};

	public List<Message> get20MessagesFromIndex(Integer index) {
		List<Message> Messages;
		if(index == null) {
			// Si index est null, je change le comportement du repository ici ??

			messages = messageRepository.findByThreadByIndex();
			            // Ici je récupére tous les messages
			messages = messageRepository.findAll();
		}else {
			messages = messageRepository.findAll(PageRequest.of(index,20)).getContent();
		}
	}return messages
}
