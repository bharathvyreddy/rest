package com.bharath.restprac.Messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.bharath.restprac.Messenger.model.Message;
import com.bharath.restprac.Messenger.model.Profile;

public class DatabaseClass {
	private static Map<Long, Message> messages=new HashMap<>();
	private static Map<String, Profile> profiles=new HashMap<>();
	static {
		messages.put(1L,new Message(1L, "First Message", "Bharath"));
		messages.put(2L,new Message(2L, "Second Message", "Vikram"));
		profiles.put("bharath", new Profile(1L, "bharath", "bh", "yr"));
		profiles.put("vikram", new Profile(2L, "vikram", "vi", "br"));
	}
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
	

}
