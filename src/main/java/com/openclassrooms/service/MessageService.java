package com.openclassrooms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.model.Message;
import com.openclassrooms.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

//Verif par id de l'existance d'un message
	public Optional<Message> findById(Long id){
		return messageRepository.findById(id);
	}
	
	public Message createMessage(Message message) {
		messageRepository.save(message);
		
		return message;
	}

}
