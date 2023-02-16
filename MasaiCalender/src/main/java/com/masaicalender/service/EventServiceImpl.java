package com.masaicalender.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import com.masaicalender.exception.AuthorizationException;
import com.masaicalender.model.CurrentUserSession;
import com.masaicalender.model.Event;
import com.masaicalender.repository.EventDao;
import com.masaicalender.repository.SessionDao;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private SessionDao sessionDao;

	@Override
	public Event createEvent(Event event, String uniqueId) throws AuthorizationException {
		
		CurrentUserSession validUser = sessionDao.findByUniqueId(uniqueId);
		
		if(validUser==null) {
			throw new AuthorizationException("Please login first!");
		}
		else {
			Event newEvent = eventDao.save(event);
			
			newEvent.setEmail(validUser.getEmail());
			
			return newEvent;
		}
	}

	@Override
	public Event updateEvent(Event event, String uniqueId) throws EventException, AuthorizationException {
		
		return null;
	}

	@Override
	public Event deleteEvent(Integer eventId, String uniqueId) throws EventException, AuthorizationException {
		
		CurrentUserSession validUser = sessionDao.findByUniqueId(uniqueId);
		
		if(validUser == null) {
			throw new AuthorizationException("Please enter the valid user details to delete the blog");
		}
		
		Optional<Event> existingEvent = eventDao.findById(eventId);
		
		if(existingEvent.get().getEmail()==validUser.getEmail()) {
			eventDao.delete(existingEvent.get());
			
			return existingEvent.get();
		}
		else {
			throw new AuthorizationException("User can't delete someone's blog");
		}
	}

}
