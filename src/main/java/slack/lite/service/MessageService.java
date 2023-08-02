package slack.lite.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
// import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import slack.lite.entity.Message;
import slack.lite.repository.*;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	public void addMessage(Message content) {
		messageRepository.save(content);
	}

	public List<Message> findAllMessages() {
		return messageRepository.findAll();
	}

	public void deleteMessage(UUID id) {
		messageRepository.deleteById(id);
	}

	// @Autowired
	/*
	 * public MessageService(MessageRepository messageRepository) {
	 * this.messageRepository = messageRepository;
	 * };
	 */

	/*
	 * public List<Message> get20MessagesFromIndex(Integer index) {
	 * List<Message> messages;
	 * if(index == null) {
	 * // Si index est null, je change le comportement du repository ici ??
	 * 
	 * messages = messageRepository.findByThreadByIndex();
	 * // Ici je récupére tous les messages
	 * messages = messageRepository.findAll();
	 * }else {
	 * messages = messageRepository.findAll(PageRequest.of(index,20)).getContent();
	 * }
	 * return messages;
	 * 
	 * }
	 */
}
