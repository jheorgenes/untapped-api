package com.pj.untapped.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

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

	@NotEmpty(message = "DateEntry is required")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateEntry;

	@NotEmpty(message = "DeadLine is required")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime deadline;
	
	private MultipartFile[] photos;
	
	private String media;
	
	private String frontCover;
	
	private Address address;
	
	@NotEmpty(message = "Capacity is required")
	private Integer capacity;

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
	}
}
