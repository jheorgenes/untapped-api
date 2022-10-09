package com.pj.untapped.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	
	private String title;
	private String subTitle;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateEntry;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime deadline;
	
	private String photos; 
	private String media;
	private String frontCover;
	private Integer capacity;
	
	@OneToOne(cascade = CascadeType.ALL)//Permite excluir todos os endereços vinculados
	private Address address;

	public Event(Integer id, String title, String subTitle, LocalDateTime dateEntry, LocalDateTime deadline, String frontCover, Integer capacity, Address address) {
		super();
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.dateEntry = dateEntry;
		this.deadline = deadline;
		this.frontCover = frontCover;
		this.capacity = capacity;
		this.address = address;
	}
	
	public Event(Integer id, String title, String subTitle, LocalDateTime dateEntry, LocalDateTime deadline, String frontCover, Integer capacity) {
        super();
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.dateEntry = dateEntry;
        this.deadline = deadline;
        this.frontCover = frontCover;
        this.capacity = capacity;
    }
}
