package com.pj.untapped.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

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
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Street is required")
	private String street;
	
	@NotEmpty(message = "District is required")
	private String district;
	
	private String addressComplement;
	
	private String addressNumber;
	
	@NotEmpty(message = "Cep is required")
	private String cep;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	@NotEmpty(message = "State is required")
	private String state;
	
	@NotEmpty(message = "Contry is required")
	private String contry;
	
	private Double latitude;
	
	private Double longitude;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

	public Address(Integer id, String street, String district, String cep, String city, String state, String contry) {
		super();
		this.id = id;
		this.street = street;
		this.district = district;
		this.cep = cep;
		this.city = city;
		this.state = state;
		this.contry = contry;
	}
}
