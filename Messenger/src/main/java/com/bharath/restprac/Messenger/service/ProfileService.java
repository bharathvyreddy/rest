package com.bharath.restprac.Messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bharath.restprac.Messenger.database.DatabaseClass;
import com.bharath.restprac.Messenger.model.Profile;


public class ProfileService {
	
	private Map<String, Profile> profiles=DatabaseClass.getProfiles();
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1L);
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty())
			return null;
		else {
			profiles.put(profile.getProfileName(), profile);
			return profile;
		}
	}
	
	public void deleteProfile(String profileName) {
		profiles.remove(profileName);
	}

}
