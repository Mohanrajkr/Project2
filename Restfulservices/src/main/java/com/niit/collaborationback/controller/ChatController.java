package com.niit.collaborationback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationback.dao.ChatDAO;
import com.niit.collaborationback.model.Chat;



@RestController
public class ChatController {
	@Autowired
	private ChatDAO chatDAO;
	
	public ChatDAO getChatDAO() {
		return chatDAO;
	}

	public void setChatDAO(ChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}

	@GetMapping("/chat")
	public ResponseEntity<List<Chat>> getChat() {
		List<Chat> listchat = chatDAO.list();
		return new ResponseEntity<List<Chat>>(listchat, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/chat/{chatId}")
	public ResponseEntity getByChatid(@PathVariable("chatId") int chatId) {

		Chat chat = chatDAO.getByCid(chatId);
		if (chat == null) {
			return new ResponseEntity("No Chat found for ID " + chatId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(chat, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/chat")
	public ResponseEntity createChat(@RequestBody Chat chat) {

		chatDAO.create(chat);

		return new ResponseEntity(chat, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/chat/{chatId}")
	public ResponseEntity deleteChat(@PathVariable int chatId) {
		Chat chat=chatDAO.getByCid(chatId);
 		if (chat==null) {
			return new ResponseEntity("No Chat found for ID " + chatId, HttpStatus.NOT_FOUND);
		}
 		chatDAO.delete(chatId);
		return new ResponseEntity(chatId, HttpStatus.OK);
   }
	
	@SuppressWarnings("unchecked")
	@PutMapping("/chat/{chatId}")
	public ResponseEntity saveorupdateChat(@PathVariable int chatId, @RequestBody Chat chat) {

		chat = chatDAO.saveOrUpdate(chat);
		if (null == chat) {
			return new ResponseEntity("No Chat found for ID " + chatId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(chat, HttpStatus.OK);
	}

}
