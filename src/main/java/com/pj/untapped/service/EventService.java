package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Event;
import com.pj.untapped.dtos.EventDTO;
import com.pj.untapped.repositories.EventRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;

	public Event findById(Integer id) {
		Optional<Event> obj = eventRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Event.class.getName()));
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event create(@Valid EventDTO objDTO) {
		return eventRepository.save(new Event(null, objDTO.getTitle(), objDTO.getSubTitle(), objDTO.getDateEntry(), objDTO.getDeadline(), objDTO.getFrontCover(), objDTO.getCapacity()));
	}

	public Event update(Integer id, @Valid EventDTO objDTO) {
		Event oldObj = findById(id);
		dataUpdates(objDTO, oldObj);
		return eventRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		eventRepository.deleteById(id);
	}

	private void dataUpdates(EventDTO objDTO, Event oldObj) {
		oldObj.setTitle(objDTO.getTitle());
		oldObj.setSubTitle(objDTO.getSubTitle());
		oldObj.setDateEntry(objDTO.getDateEntry());
		oldObj.setDeadline(objDTO.getDeadline());
		oldObj.setFrontCover(objDTO.getFrontCover());
		oldObj.setCapacity(objDTO.getCapacity());
	}
}
