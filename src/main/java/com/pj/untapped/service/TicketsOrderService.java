package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Order;
import com.pj.untapped.domain.Ticket;
import com.pj.untapped.domain.TicketsOrder;
import com.pj.untapped.dtos.TicketsOrderDTO;
import com.pj.untapped.repositories.OrderRepository;
import com.pj.untapped.repositories.TicketRepository;
import com.pj.untapped.repositories.TicketsOrderRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class TicketsOrderService {

    @Autowired
    private TicketsOrderRepository ticketsOrderRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private TicketRepository ticketRepository;
    
    public TicketsOrder findById(Integer id) {
        Optional<TicketsOrder> newObj = ticketsOrderRepository.findById(id);
        return newObj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + TicketsOrder.class.getName()));
    }
    
    public List<TicketsOrder> findAll() {
        return ticketsOrderRepository.findAll();
    }

    public TicketsOrder create(@Valid TicketsOrderDTO objDTO) {
        TicketsOrder newObj = new TicketsOrder(
            null,
            objDTO.getPrice(),
            objDTO.getQuantity()
        );
        
        Optional<Ticket> ticket = ticketRepository.findById(objDTO.getTicket().getId());
        if(ticket.isPresent()) {
            newObj.setTicket(ticket.get());
        }
        
        Optional<Order> order = orderRepository.findById(objDTO.getOrder().getId());
        if(order.isPresent()) {
            newObj.setOrder(order.get());
        }
        return ticketsOrderRepository.save(newObj);
    }

    public TicketsOrder update(Integer id, @Valid TicketsOrderDTO objDTO) {
        TicketsOrder oldObj = findById(id);
        dataUpdate(oldObj, objDTO);
        return ticketsOrderRepository.save(oldObj);
    }

    public void delete(Integer id) {
        findById(id);
        ticketsOrderRepository.deleteById(id);
    }

    private void dataUpdate(TicketsOrder newObj, TicketsOrderDTO objDTO) {
        Optional<Ticket> ticket = ticketRepository.findById(objDTO.getTicket().getId());
        if(ticket.isPresent()) {
            newObj.setTicket(ticket.get());
        }
        
        Optional<Order> order = orderRepository.findById(objDTO.getOrder().getId());
        if(order.isPresent()) {
            newObj.setOrder(order.get());
        }
        
        newObj.setPrice(objDTO.getPrice());
        newObj.setQuantity(objDTO.getQuantity());
    }
}
