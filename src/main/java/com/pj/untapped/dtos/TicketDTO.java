package com.pj.untapped.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.untapped.domain.Ticket;
import com.pj.untapped.domain.enuns.StatusTicket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketDTO {

    private Integer id;
    
    private String description;

    @NotNull(message = "ValueTicket is required")
    private Double valueTicket;

    @NotEmpty(message = "TicketClassification is required")
    private String ticketClassification;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime expirationDate;
    
    @NotNull(message = "Number of tickets per Classification is required")
    private Integer numberOfTicketsPerRating;
    
    private Integer statusTicket;
    
    private Integer eventId;

    public TicketDTO(Ticket obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
        this.valueTicket = obj.getValueTicket();
        this.ticketClassification = obj.getTicketClassification();
        this.expirationDate = obj.getExpirationDate();
        this.numberOfTicketsPerRating = obj.getNumberOfTicketsPerRating();
        this.statusTicket = obj.getStatusTicket().getCod();
        this.eventId = obj.getEvent().getId();
    }
    
    public StatusTicket getStatusTicket() {
        return StatusTicket.toEnum(this.statusTicket);
    }

    public void setStatusTicket(StatusTicket statusTicket) {
        this.statusTicket = statusTicket.getCod();
    }
}
