package com.pj.untapped.dtos;

import javax.validation.constraints.NotEmpty;

import com.pj.untapped.domain.Address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

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
	
	public AddressDTO(Address obj) {
		this.id = obj.getId();
		this.street = obj.getStreet();
		this.district = obj.getDistrict();
		this.cep = obj.getCep();
		this.city = obj.getCity();
		this.state = obj.getState();
		this.contry = obj.getContry();
	}
}
