package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    
    public TicketsOrder findByQrCode(String qrcode) {
        TicketsOrder newObj = new TicketsOrder();
        Optional<TicketsOrder> newOptional = Optional.ofNullable(ticketsOrderRepository.findByQrCode(qrcode));
        if(newOptional.isPresent()) {
            newObj = newOptional.get();
            if(newObj.getQuantity() != null && newObj.getQuantity() > 0) {
                if(newObj.getValidateTicket() < newObj.getQuantity()) {
                    newObj.setValidateTicket(newObj.getValidateTicket() + 1);
                    ticketsOrderRepository.save(newObj);
                } else {
                    throw new ObjectNotFoundException("Acabaram as entradas para o QRCODE: " + qrcode + ", Tipo: " + TicketsOrder.class.getName());
                }
            }
            return newObj;
        } else {
            throw new ObjectNotFoundException("Objeto não encontrado! QRCODE: " + qrcode + ", Tipo: " + TicketsOrder.class.getName());
        }
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
        
        String generatedQrCode = gerarDigitosAleatorios(12);
        newObj.setQrCode(generatedQrCode);
        
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

    private static String gerarDigitosAleatorios(int digitos) {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < digitos; i++) {
            text.append(random.nextInt(10)); // gerar um número aleatório entre 0 e 9
        }
        return text.toString();
    }
}
