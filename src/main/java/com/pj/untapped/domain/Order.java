package com.pj.untapped.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
    
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;
    
    //@NotNull(message = "Total value is required")
    private Double totalValue;
    
    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payments;
    
    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<TicketsOrder> ticketsOrder;

    public Order(Integer id, LocalDateTime date, User user, Event event) {
        this.id = id;
        this.date = (date != null) ? date : LocalDateTime.now();
        this.user = user;
        this.event = event;
    }
}
