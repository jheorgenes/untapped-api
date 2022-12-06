package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.Ticket;
import com.pj.untapped.domain.enuns.StatusTicket;
import com.pj.untapped.dtos.TicketDTO;
import com.pj.untapped.repositories.EventRepository;
import com.pj.untapped.repositories.TicketRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class TicketService {

    @Autowired
    public TicketRepository ticketRepository;
    
    @Autowired
    public EventRepository eventRepository;

    public Ticket findById(Integer id) {
        Optional<Ticket> newObj = ticketRepository.findById(id);
        return newObj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Ticket.class.getName()));
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket create(@Valid TicketDTO objDTO) {
        Ticket newObj = new Ticket();
        return fromDTO(newObj, objDTO);
    }

    public Ticket update(Integer id, @Valid TicketDTO objDTO) {
        Ticket oldObj = findById(id);
        return fromDTO(oldObj, objDTO);
    }

    public void delete(Integer id) {
        findById(id);
        ticketRepository.deleteById(id);
    }

    private Ticket fromDTO(Ticket newObj, TicketDTO objDTO) {
        newObj.setId(objDTO.getId());
        newObj.setDescription(objDTO.getDescription());
        newObj.setExpirationDate(objDTO.getExpirationDate());
        newObj.setTicketClassification(objDTO.getTicketClassification());
        newObj.setValueTicket(objDTO.getValueTicket());
        newObj.setNumberOfTicketsPerRating(objDTO.getNumberOfTicketsPerRating());
        newObj.setStatusTicket(StatusTicket.toEnum(objDTO.getStatusTicket().getCod()));
        
        Optional<Event> event = eventRepository.findById(objDTO.getEventId());
        if(event.isPresent()) {
            newObj.setEvent(event.get());
        }
        
        return ticketRepository.save(newObj);
    }
}
