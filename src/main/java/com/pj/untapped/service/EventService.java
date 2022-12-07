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
	        objDTO.getDescription());
	    
	    Address address = addNewAddress(objDTO, newEvent);
	    newEvent.setAddress(address);
	    
	    setCategoriesRegisteredInTheCategoriesListOfThisEvent(objDTO, newEvent);
	    Event eventSave = eventRepository.save(newEvent);

	    List<Ticket> tickets = new ArrayList<>();
	    for (Ticket ticket : objDTO.getTickets()) {
	        insertNewTicketInEventTicketList(eventSave, tickets, ticket);
        }
	    return eventSave;
	}

	public Event update(Integer id, @Valid EventDTO objDTO) {
		Event oldEvent = findById(id);
		dataUpdates(objDTO, oldEvent);
		return eventRepository.save(oldEvent);
	}
	
	public void delete(Integer id) {
		eventRepository.deleteById(id);
	}

	@Transactional
	private void dataUpdates(EventDTO eventDTO, Event oldEvent) {
		oldEvent.setTitle(eventDTO.getTitle());
		oldEvent.setSubTitle(eventDTO.getSubTitle());
		oldEvent.setDateEntry(eventDTO.getDateEntry());
		oldEvent.setDeadline(eventDTO.getDeadline());
		oldEvent.setFrontCover(eventDTO.getFrontCover());
		oldEvent.setCapacity(eventDTO.getCapacity());
		
		updateOrInsertAddressInEvent(eventDTO, oldEvent);
        updateListTicketsByEvent(eventDTO, oldEvent);	
		setCategoriesRegisteredInTheCategoriesListOfThisEvent(eventDTO, oldEvent);
	}
	
	private Address addNewAddress(EventDTO eventDTO, Event event) {
        Address address = new Address(
            null, 
            eventDTO.getAddress().getTitle(),
            eventDTO.getAddress().getStreet(), 
            eventDTO.getAddress().getDistrict(), 
            eventDTO.getAddress().getCep(), 
            eventDTO.getAddress().getCity(), 
            eventDTO.getAddress().getState(), 
            eventDTO.getAddress().getCountry());  
        address.setAddressComplement(eventDTO.getAddress().getAddressComplement());
        address.setAddressNumber(eventDTO.getAddress().getAddressNumber());
        address.setLatitude(eventDTO.getAddress().getLatitude());
        address.setLongitude(eventDTO.getAddress().getLongitude());
        //Salva Endereço sem relacionar com Evento (Relacionamento será feito no evento)
        addressRepository.save(address); 
        address.setEvent(event); 
        return address;
    }

    private void setCategoriesRegisteredInTheCategoriesListOfThisEvent(EventDTO eventDTO, Event event) {
        List<Categories> categoriesList = new ArrayList<>();
		for (Categories category : eventDTO.getCategories()) {
		    Optional<Categories> categoryFound = categoriesRepository.findById(category.getId());
		    if(categoryFound.isPresent()) {
		        categoriesList.add(categoryFound.get());
		    }
		}
		event.setCategories(categoriesList);
    }

    private void updateOrInsertAddressInEvent(EventDTO eventDTO, Event event) {
        if(eventDTO.getAddress() != null) {
		    if(eventDTO.getAddress().getId() != null) {
		        eventDTO.getAddress().setEvent(event);
		        AddressDTO addressDTO = new AddressDTO(eventDTO.getAddress());
		        addressService.update(eventDTO.getAddress().getId(), addressDTO);
		    } else {
		        Address address = addNewAddress(eventDTO, event);
		        event.setAddress(address);
		    }
		}
    }

    private void updateListTicketsByEvent(EventDTO eventDTO, Event event) {
        if (eventDTO.getTickets() != null) {
            List<Ticket> tickets = new ArrayList<>();
            for (Ticket ticket : eventDTO.getTickets()) {
                if (ticket.getId() != null) {
                    ticket.setEvent(event);
                    TicketDTO ticketDTO = new TicketDTO(ticket);
                    ticketService.update(ticket.getId(), ticketDTO);
                } else {
                    insertNewTicketInEventTicketList(event, tickets, ticket);
                }
            }
        }
    }
    
    private void insertNewTicketInEventTicketList(Event event, List<Ticket> tickets, Ticket ticket) {
        Ticket newTicket = new Ticket(
            null, 
            ticket.getDescription(), 
            ticket.getValueTicket(), 
            ticket.getTicketClassification(), 
            ticket.getExpirationDate(), 
            ticket.getNumberOfTicketsPerRating(), 
            ticket.getStatusTicket(),
            event);
        Ticket ticketSave = ticketRepository.save(newTicket);
        tickets.add(ticketSave);
    }
}
