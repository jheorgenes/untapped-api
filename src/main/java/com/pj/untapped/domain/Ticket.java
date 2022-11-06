package com.pj.untapped.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pj.untapped.domain.enuns.StatusTicket;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
//@Getter 
//@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "ValueTicket is required")
    private BigDecimal valueTicket;

    @NotEmpty(message = "TicketClassification is required")
    private String ticketClassification;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime expirationDate;
    
    @NotNull(message = "Number of tickets per Rating is required")
    private Integer numberOfTicketsPerRating;
    
    private Integer statusTicket;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    
    public Ticket(
            Integer id, 
            @NotNull(message = "ValueTicket is required") BigDecimal valueTicket,
            @NotEmpty(message = "TicketClassification is required") String ticketClassification,
            LocalDateTime expirationDate,
            @NotNull(message = "Number of tickets per Rating is required") Integer numberOfTicketsPerRating,
            StatusTicket status) {
        this.id = id;
        this.valueTicket = valueTicket;
        this.ticketClassification = ticketClassification;
        this.expirationDate = expirationDate;
        this.numberOfTicketsPerRating = numberOfTicketsPerRating;
        this.statusTicket = (status == null) ? 0 : status.getCod();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValueTicket() {
        return valueTicket;
    }

    public void setValueTicket(BigDecimal valueTicket) {
        this.valueTicket = valueTicket;
    }

    public String getTicketClassification() {
        return ticketClassification;
    }

    public void setTicketClassification(String ticketClassification) {
        this.ticketClassification = ticketClassification;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getNumberOfTicketsPerRating() {
        return numberOfTicketsPerRating;
    }

    public void setNumberOfTicketsPerRating(Integer numberOfTicketsPerRating) {
        this.numberOfTicketsPerRating = numberOfTicketsPerRating;
    }

    public StatusTicket getStatusTicket() {
        return StatusTicket.toEnum(this.statusTicket);
    }

    public void setStatusTicket(StatusTicket statusTicket) {
        this.statusTicket = statusTicket.getCod();
    }
}
