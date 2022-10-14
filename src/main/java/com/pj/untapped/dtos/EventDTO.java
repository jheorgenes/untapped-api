package com.pj.untapped.dtos;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.Event;

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
	
	@Valid
	private Address address;
	
	@NotNull(message = "Capacity is required")
	private Integer capacity;
	
	@NotEmpty(message = "Title is required")
	private String description;

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
	}
}
