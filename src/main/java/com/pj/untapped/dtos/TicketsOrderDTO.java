package com.pj.untapped.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pj.untapped.domain.Order;
import com.pj.untapped.domain.Ticket;
import com.pj.untapped.domain.TicketsOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketsOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonIgnoreProperties
    private Order order;
    
    private Ticket ticket;
    @NotNull(message = "Price is required")
    private Double price;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    
    private String qrCode;
    private Integer validateTicket = 0;
    
    public TicketsOrderDTO(TicketsOrder obj) {
        this.id = obj.getId();
        this.order = obj.getOrder();
        this.ticket = obj.getTicket();
        this.price = obj.getPrice();
        this.quantity = obj.getQuantity();
        this.qrCode = obj.getQrCode();
        this.validateTicket = obj.getValidateTicket();
    }
    
    public TicketsOrderDTO(Integer id, Double price, Integer quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }
    
    public TicketsOrderDTO(Integer id, Order order, Ticket ticket,
            @NotNull(message = "Price is required") Double price,
            @NotNull(message = "Quantity is required") Integer quantity) {
        this.id = id;
        this.order = order;
        this.ticket = ticket;
        this.price = price;
        this.quantity = quantity;
    }
}
