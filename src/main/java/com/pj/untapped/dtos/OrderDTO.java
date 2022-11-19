package com.pj.untapped.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.untapped.domain.Order;
import com.pj.untapped.domain.Payment;
import com.pj.untapped.domain.TicketsOrder;
import com.pj.untapped.domain.enuns.StatusPayment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
    private Integer userId;
    private Integer eventId;
    private Double totalValue;
    
    private StatusPayment statusPayment;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payments;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<TicketsOrder> ticketsOrder;
    
    public OrderDTO(Order obj) {
        super();
        this.id = obj.getId();
        this.date = obj.getDate();
        this.userId = obj.getUser().getId();
        this.eventId = obj.getEvent().getId();
        this.ticketsOrder = obj.getTicketsOrder();
        this.totalValue = obj.getTotalValue();
    }

    public OrderDTO(Integer id, LocalDateTime date, Integer userId, Integer eventId, Double totalValue,
           List<Payment> payments, List<TicketsOrder> ticketsOrder) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.eventId = eventId;
        this.totalValue = totalValue;
        this.payments = payments;
        this.ticketsOrder = ticketsOrder;
    }
}
