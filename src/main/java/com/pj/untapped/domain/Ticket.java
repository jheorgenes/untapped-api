package com.pj.untapped.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
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
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "ValueTicket is required")
    private BigDecimal valueTicket;

    @NotEmpty(message = "TicketClassification is required")
    private String ticketClassification;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime expirationDate;
    
    @NotNull(message = "Number of tickets per Rating is required")
    private Integer numberOfTicketsPerRating;

    public Ticket(
            Integer id, 
            @NotNull(message = "ValueTicket is required") BigDecimal valueTicket,
            @NotEmpty(message = "TicketClassification is required") String ticketClassification,
            LocalDateTime expirationDate,
            @NotNull(message = "Number of tickets per Rating is required") Integer numberOfTicketsPerRating) {
        this.id = id;
        this.valueTicket = valueTicket;
        this.ticketClassification = ticketClassification;
        this.expirationDate = expirationDate;
        this.numberOfTicketsPerRating = numberOfTicketsPerRating;
    }
    
    
    
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "event_id")
//    private Event event;
//
//    public Ticket(Integer id, @NotNull(message = "ValueTicket is required") BigDecimal valueTicket,
//                    @NotEmpty(message = "TicketClassification is required") String ticketClassification,
//                    LocalDateTime expirationDate, Integer numberOfTicketsPerRating) {
//        this.id = id;
//        this.valueTicket = valueTicket;
//        this.ticketClassification = ticketClassification;
//        this.expirationDate = expirationDate;
//        this.numberOfTicketsPerRating = numberOfTicketsPerRating;
//    }
}
