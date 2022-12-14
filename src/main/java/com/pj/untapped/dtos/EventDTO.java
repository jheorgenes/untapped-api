package com.pj.untapped.dtos;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.Categories;
import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.Ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {

	private Integer id;
	
	@NotEmpty(message = "Title is required")
	private String title;
	
	@NotEmpty(message = "subTitle is required")
	private String subTitle;

	@NotNull(message = "DateEntry is required")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateEntry;

	@NotNull(message = "DeadLine is required")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime deadline;
	
	private String photos;
	
	private String media;
	
	private String frontCover;
	
	@OneToOne(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	@Valid
	private Address address;
	
	@NotNull(message = "Capacity is required")
	private Integer capacity;
	
	@NotEmpty(message = "Title is required")
	private String description;
	
	private Integer userId;
	
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ticket> tickets;
	
	@ManyToMany
    @JoinTable(name = "events_category", 
        joinColumns = { @JoinColumn(name = "category_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "event_id") })
	private List<Categories> categories;

	public EventDTO(Event obj) {
		super();
		this.id = obj.getId();
		this.title = obj.getTitle();
		this.subTitle = obj.getSubTitle();
		this.dateEntry = obj.getDateEntry();
		this.deadline = obj.getDeadline();
		this.frontCover = obj.getFrontCover();
		this.capacity = obj.getCapacity();
		this.address = obj.getAddress();
		this.description = obj.getDescription();
		this.tickets = obj.getTickets();
		this.categories = obj.getCategories();
		this.userId = obj.getUserId();
	}
}
