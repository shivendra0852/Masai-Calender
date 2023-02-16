package com.masaicalender.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masaicalender.model.Event;
import com.masaicalender.service.EventService;

@RestController
@RequestMapping("/masaicalender")
public class EventController {
	@Autowired
	private EventService eventService;
	
	@PostMapping("/event/{uid}")
	public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, @PathVariable("uid") String uniqueId){
		
		Event createdEvent = eventService.createEvent(event, uniqueId);
		
		return new ResponseEntity<Event>(createdEvent, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/event/{id}/{uid}")
	public ResponseEntity<Event> deleteEvent(@PathVariable("id") Integer eventId, @PathVariable("uid") String uniqueId){
		
		Event deletedEvent = eventService.deleteEvent(eventId, uniqueId);
		
		return new ResponseEntity<Event>(deletedEvent,HttpStatus.OK);
	}
}
