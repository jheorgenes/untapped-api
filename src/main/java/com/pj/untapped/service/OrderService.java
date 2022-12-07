package com.pj.untapped.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.Order;
import com.pj.untapped.domain.TicketsOrder;
import com.pj.untapped.domain.User;
import com.pj.untapped.dtos.OrderDTO;
import com.pj.untapped.repositories.EventRepository;
import com.pj.untapped.repositories.OrderRepository;
import com.pj.untapped.repositories.TicketsOrderRepository;
import com.pj.untapped.repositories.UserRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private TicketsOrderRepository ticketsOrderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    public Order findById(Integer id) {
        Optional<Order> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Order.class.getName()));
    }
    
    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    
    public Order create(@Valid OrderDTO objDTO) {
        Order newOrder = new Order();
        newOrder.setId(null);
        newOrder.setDate(objDTO.getDate());
        
        Optional<User> user = userRepository.findById(objDTO.getUserId());
        if(user.isPresent()) {
            newOrder.setUser(user.get());
        }
        
        Optional<Event> event = eventRepository.findById(objDTO.getEventId());
        if(event.isPresent()) {
            newOrder.setEvent(event.get());
        }
        
        Order orderSave = orderRepository.save(newOrder);
        
        Double priceTotal = 0.0;
        List<TicketsOrder> tickets = new ArrayList<>();
        for (TicketsOrder ticketOrder : objDTO.getTicketsOrder()) {
            TicketsOrder newTicketOrder = new TicketsOrder();
            newTicketOrder.setId(null);
            newTicketOrder.setPrice(ticketOrder.getPrice());
            newTicketOrder.setQuantity(ticketOrder.getQuantity());
            newTicketOrder.setOrder(orderSave);
            newTicketOrder.setTicket(ticketOrder.getTicket());
            priceTotal += (newTicketOrder.getPrice() * newTicketOrder.getQuantity());
            newTicketOrder.setQrCode(generateRandomDigits(12));
            tickets.add(ticketsOrderRepository.save(newTicketOrder));
        }
        orderSave.setTotalValue(priceTotal);
        orderSave.setTicketsOrder(tickets);
        orderRepository.save(orderSave);
        return orderSave;
    }
    
    public Order update(Integer id, @Valid OrderDTO objDTO) {
        Order oldObj = findById(id);
        dataUpdates(objDTO, oldObj);
        return orderRepository.save(oldObj); //Grava a segunda mudança e já retorna a Entidade
    }
    
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
    
    private void dataUpdates(OrderDTO objDTO, Order oldObj) { 
        oldObj.setDate(objDTO.getDate());
        
        Optional<User> user = userRepository.findById(objDTO.getUserId());
        if(user.isPresent()) {
            oldObj.setUser(user.get());
        }
        
        Optional<Event> event = eventRepository.findById(objDTO.getEventId());
        if(event.isPresent()) {
            oldObj.setEvent(event.get());
        }
        
        Order orderSave = orderRepository.save(oldObj); //Grava a primeira mudança
        Double priceTotal = 0.0;
        List<TicketsOrder> tickets = new ArrayList<>();
        for (TicketsOrder ticketOrder : objDTO.getTicketsOrder()) {
            TicketsOrder newTicketOrder = new TicketsOrder();
            newTicketOrder.setId(null);
            newTicketOrder.setPrice(ticketOrder.getPrice());
            newTicketOrder.setQuantity(ticketOrder.getQuantity());
            newTicketOrder.setOrder(orderSave);
            newTicketOrder.setTicket(ticketOrder.getTicket());
            priceTotal += (newTicketOrder.getPrice() * newTicketOrder.getQuantity());
            tickets.add(ticketsOrderRepository.save(newTicketOrder));
        }
        orderSave.setTotalValue(priceTotal);
        orderSave.setTicketsOrder(tickets);
    }
    
    private static String generateRandomDigits(int numberOfDigits) {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < numberOfDigits; i++) {
            text.append(random.nextInt(10)); // gerar um número aleatório entre 0 e 9
        }
        return text.toString();
    }
}
