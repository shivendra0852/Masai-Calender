package com.masaicalender.service;

import org.w3c.dom.events.EventException;

import com.masaicalender.exception.AuthorizationException;
import com.masaicalender.model.Event;

public interface EventService {
	public Event createEvent(Event event, String uniqueId) throws AuthorizationException;
	
	public Event updateEvent(Event event, String uniqueId) throws EventException, AuthorizationException;
	
	public Event deleteEvent(Integer eventId, String uniqueId) throws EventException, AuthorizationException;
}
