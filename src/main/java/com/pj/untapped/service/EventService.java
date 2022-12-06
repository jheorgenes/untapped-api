package com.pj.untapped.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.Categories;
import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.Ticket;
import com.pj.untapped.dtos.AddressDTO;
import com.pj.untapped.dtos.EventDTO;
import com.pj.untapped.dtos.TicketDTO;
import com.pj.untapped.repositories.AddressRepository;
import com.pj.untapped.repositories.CategoriesRepository;
import com.pj.untapped.repositories.EventRepository;
import com.pj.untapped.repositories.TicketRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CategoriesRepository categoriesRepository;

	public Event findById(Integer id) {
		Optional<Event> obj = eventRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Event.class.getName()));
	}
	
	@Transactional
	public List<Event> searchByTitle(String title){
	    return eventRepository.findByTitleWith(title);
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event create(@Valid EventDTO objDTO) {
	    Event newEvent = new Event(
	        null, 
	        objDTO.getTitle(), 
	        objDTO.getSubTitle(), 
	        objDTO.getDateEntry(), 
	        objDTO.getDeadline(), 
	        objDTO.getFrontCover(), 
	        objDTO.getCapacity(), 
	        objDTO.getDescription()
	    );
	    
	    Address address = addressRepository.save(new Address(
	        null, 
	        objDTO.getAddress().getTitle(),
	        objDTO.getAddress().getStreet(), 
	        objDTO.getAddress().getDistrict(), 
	        objDTO.getAddress().getCep(), 
	        objDTO.getAddress().getCity(), 
	        objDTO.getAddress().getState(), 
	        objDTO.getAddress().getContry())
	    );	    
	    newEvent.setAddress(address);
	    
	    List<Categories> categoriesList = new ArrayList<>();
	    for (Categories category : objDTO.getCategories()) {
	        Optional<Categories> categoryFound = categoriesRepository.findById(category.getId());
	        if(categoryFound.isPresent()) {
	            categoriesList.add(categoryFound.get());
	        }
	    }
	    newEvent.setCategories(categoriesList);
	    
	    Event eventSave = eventRepository.save(newEvent);
	    // Criando os tickets conforme demanda 
	    List<Ticket> tickets = new ArrayList<>();
	    for (Ticket ticket : objDTO.getTickets()) {
	        tickets.add(
	            ticketRepository.save(new Ticket(
	                null, 
	                ticket.getDescription(), 
	                ticket.getValueTicket(), 
	                ticket.getTicketClassification(), 
	                ticket.getExpirationDate(), 
	                ticket.getNumberOfTicketsPerRating(), 
	                ticket.getStatusTicket(),
	                eventSave)
	            )
	        );
        }
	    return eventSave;
	}

	public Event update(Integer id, @Valid EventDTO objDTO) {
		Event oldObj = findById(id);
		dataUpdates(objDTO, oldObj);
		return eventRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		eventRepository.deleteById(id);
	}

	@Transactional
	private void dataUpdates(EventDTO objDTO, Event oldObj) {
		oldObj.setTitle(objDTO.getTitle());
		oldObj.setSubTitle(objDTO.getSubTitle());
		oldObj.setDateEntry(objDTO.getDateEntry());
		oldObj.setDeadline(objDTO.getDeadline());
		oldObj.setFrontCover(objDTO.getFrontCover());
		oldObj.setCapacity(objDTO.getCapacity());
		
		updateEventAddress(objDTO, oldObj);
        updateListTicketsByEvent(objDTO, oldObj);
		
		List<Categories> categoriesList = new ArrayList<>();
		for (Categories category : objDTO.getCategories()) {
		    Optional<Categories> categoryFound = categoriesRepository.findById(category.getId());
		    if(categoryFound.isPresent()) {
		        categoriesList.add(categoryFound.get());
		    }
		}
		oldObj.setCategories(categoriesList);
	}

    private void updateEventAddress(EventDTO objDTO, Event oldObj) {
        if(objDTO.getAddress() != null) {
		    if(objDTO.getAddress().getId() != null) {
		        objDTO.getAddress().setEvent(oldObj);
		        AddressDTO addressDTO = new AddressDTO(objDTO.getAddress());
		        addressService.update(objDTO.getAddress().getId(), addressDTO);
		    } else {
		        Address address = addressRepository.save(new Address(
	                 null, 
	                 objDTO.getAddress().getTitle(),
	                 objDTO.getAddress().getStreet(), 
	                 objDTO.getAddress().getDistrict(), 
	                 objDTO.getAddress().getCep(), 
	                 objDTO.getAddress().getCity(), 
	                 objDTO.getAddress().getState(), 
	                 objDTO.getAddress().getContry())
	            );
		        address.setEvent(oldObj);
		        oldObj.setAddress(address);
		    }
		}
    }

    private void updateListTicketsByEvent(EventDTO objDTO, Event oldObj) {
        if (objDTO.getTickets() != null) {
            List<Ticket> tickets = new ArrayList<>();
            for (Ticket ticket : objDTO.getTickets()) {
                if (ticket.getId() != null) {
                    ticket.setEvent(oldObj);
                    TicketDTO ticketDTO = new TicketDTO(ticket);
                    ticketService.update(ticket.getId(), ticketDTO);
                } else {
                    tickets.add(
                        ticketRepository.save(new Ticket(
                            null,
                            ticket.getDescription(),
                            ticket.getValueTicket(),
                            ticket.getTicketClassification(),
                            ticket.getExpirationDate(),
                            ticket.getNumberOfTicketsPerRating(),
                            ticket.getStatusTicket(),
                            oldObj))
                    );
                }
            }
        }
    }
}
