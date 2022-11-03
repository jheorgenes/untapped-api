package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Ticket;
import com.pj.untapped.dtos.TicketDTO;
import com.pj.untapped.repositories.TicketRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class TicketService {

  @Autowired
  public TicketRepository ticketRepository;
  
  public Ticket findById(Integer id) {
      Optional<Ticket> newObj = ticketRepository.findById(id);
      return newObj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Ticket.class.getName()));
  }
  
  public List<Ticket> findAll(){
      return ticketRepository.findAll();
  }
  
  public Ticket create(@Valid TicketDTO objDTO) {
      Ticket newObj = new Ticket(null, objDTO.getValueTicket(), objDTO.getTicketClassification(), objDTO.getExpirationDate(), objDTO.getNumberOfTicketsPerRating());
      return ticketRepository.save(newObj);
  }
  
  public void delete(Integer id) {
      ticketRepository.deleteById(id);
  }
}
