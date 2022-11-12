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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Reserves implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;
    
    @NotNull(message = "Total value is required")
    private Double totalValue;
    
    @OneToMany(mappedBy = "reserve", cascade = CascadeType.ALL)
    private List<Payment> payment;
    
    @OneToMany(mappedBy = "reserve", cascade = CascadeType.ALL)
    private List<ReservedTickets> reservedTickets;
    
}
