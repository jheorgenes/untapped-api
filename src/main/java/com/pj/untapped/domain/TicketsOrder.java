package com.pj.untapped.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TicketsOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    
    @NotNull(message = "price is required")
    private Double price;
    @NotNull(message = "quantity is required")
    private Integer quantity;
    
    private String qrCode;
    private Integer validateTicket = 0;
    
    public TicketsOrder(Integer id, Double price, Integer quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }
    
    public TicketsOrder(Integer id, Order order, Ticket ticket, Double price, Integer quantity) {
        this.id = id;
        this.order = order;
        this.ticket = ticket;
        this.price = price;
        this.quantity = quantity;
    }
}
