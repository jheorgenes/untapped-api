package com.pj.untapped.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.User2;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "UserName is required")
	private String userName;
	
	@NotEmpty(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotEmpty(message = "Password is required")
	private String password;
	
	@CPF
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdAt;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> adresses = new ArrayList<>();

	public UserDTO() {
		super();
	}

	public UserDTO(User2 obj) {
		super();
		this.id = obj.getId();
		this.userName = obj.getUserName();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.cpf = obj.getCpf();
		this.birthDate = obj.getBirthDate();
		this.updatedAt = obj.getUpdatedAt();
		this.createdAt = obj.getCreatedAt();
		this.adresses = obj.getAdresses();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Address> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Address> adresses) {
		this.adresses = adresses;
	}
}
