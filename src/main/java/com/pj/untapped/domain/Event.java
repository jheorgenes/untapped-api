package com.pj.untapped.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

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
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Title is required")
	private String title;
	
	@NotEmpty(message = "SubTitle is required")
	private String subTitle;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateEntry;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime deadline;
	
	private String photos; 
	private String media;
	private String frontCover;
	private Integer capacity;
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)//Permite excluir todos os endereços vinculados
	private Address address;
	
//	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
//	private List<Ticket> listTickets;

	public Event(Integer id, String title, String subTitle, LocalDateTime dateEntry, LocalDateTime deadline, String frontCover, Integer capacity, Address address, String description) {
		super();
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.dateEntry = dateEntry;
		this.deadline = deadline;
		this.frontCover = frontCover;
		this.capacity = capacity;
		this.address = address;
		this.description = description;
	}
	
	public Event(Integer id, String title, String subTitle, LocalDateTime dateEntry, LocalDateTime deadline, String frontCover, Integer capacity, String description) {
        super();
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.dateEntry = dateEntry;
        this.deadline = deadline;
        this.frontCover = frontCover;
        this.capacity = capacity;
        this.description = description;
    }
}
