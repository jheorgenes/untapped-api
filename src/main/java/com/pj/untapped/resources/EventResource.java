package com.pj.untapped.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pj.untapped.domain.Event;
import com.pj.untapped.dtos.EventDTO;
import com.pj.untapped.service.EventService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/events")
public class EventResource {

	@Autowired
	private EventService eventService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EventDTO> findById(@PathVariable Integer id){
		EventDTO objDTO = new EventDTO(eventService.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<EventDTO>> findAll(){
		List<EventDTO> eventList = eventService.findAll().stream().map(obj -> new EventDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(eventList);
	}
	
//	@GetMapping("/search")
//	public ResponseEntity<List<EventDTO>> searchByTitle(@RequestBody String title){
//	    List<EventDTO> eventList = eventService.searchByTitle(title).stream().map(obj -> new EventDTO(obj)).collect(Collectors.toList());
//	    return ResponseEntity.ok().body(eventList);
//	}
	
	@PostMapping
	public ResponseEntity<EventDTO> create(@Valid @RequestBody EventDTO objDTO){
		Event newObj = eventService.create(objDTO);
		EventDTO newDTO = new EventDTO(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EventDTO> update(@PathVariable Integer id, @Valid @RequestBody EventDTO objDTO){
		EventDTO newObj = new EventDTO(eventService.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		eventService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
