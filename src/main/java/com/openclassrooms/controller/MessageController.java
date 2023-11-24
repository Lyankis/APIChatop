 package com.openclassrooms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.model.Message;
import com.openclassrooms.service.MessageService;


@RestController
@RequestMapping("api/")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("messages")
	public ResponseEntity<Message> createMessage(@RequestBody Message message){
		try {
			Message _message = messageService.createMessage(message);
			return new ResponseEntity<>(_message, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
