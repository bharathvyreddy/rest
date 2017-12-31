package com.bharath.restprac.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bharath.restprac.Messenger.database.DatabaseClass;
import com.bharath.restprac.Messenger.exception.DataNotFoundException;
import com.bharath.restprac.Messenger.model.Message;

public class MessageService {
	private Map<Long, Message> messages=DatabaseClass.getMessages();
	
	/*public MessageService(){
		
	}*/
	public Set<Message> getAllMessages(){
		return new HashSet<Message>(messages.values());
	} 
	
	public List<Message> getAllMessagesInAnYear(int year){
		List<Message> yearMessages=new ArrayList<Message>();
		Calendar calendar=Calendar.getInstance();
		for(Message message:messages.values()) {
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR)==year) {
				yearMessages.add(message);
			}
		}
		return yearMessages;
	}
	
	public List<Message> getAllMessagesPaginated(int start,int size){
		List<Message> paginatedMessages = new ArrayList<Message>(messages.values());
		if((start+size)>messages.size()) return null;
		return paginatedMessages.subList(start, start+size);
	}
	public Message getMessage(long id) {
		Message message= messages.get(id);
		if(message==null) {
			throw new DataNotFoundException("Message id: "+id+" not found");
			}
		else
			return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId()<=0)
			return null;
		else {
			messages.put(message.getId(),message);
			return message;
		}
	}
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
