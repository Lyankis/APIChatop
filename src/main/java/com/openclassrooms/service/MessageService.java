package com.openclassrooms.service;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.model.Message;
import com.openclassrooms.repository.MessageRepository;;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	
	//Verif par id de l'existance d'un message
	public Optional<Message> findById(Long id){
		return messageRepository.findById(id);
	}
	
	//Création du message 
	public Message createMessage(Message message) {
		
		Long time = Date.from(Instant.now()).getTime();

		Message newMessage = new Message();
		
		newMessage.setRental_id(message.getRental_id());
		newMessage.setUser_id(message.getUser_id());
		
		newMessage.setMessage(message.getMessage());
		
		newMessage.setCreated_at(new Date(time));
		newMessage.setUpdated_at(new Date(time));
		
       return messageRepository.save(newMessage);
	}

}
