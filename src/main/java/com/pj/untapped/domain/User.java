package com.pj.untapped.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "USERS")
@Getter 
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "UserName is required")
	private String userName;
	
	@NotEmpty(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotEmpty(message = "Password is required")
	private String password;
	
	private String avatar;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)//Permite excluir todos os endereços vinculados
	private List<Address> adresses = new ArrayList<>();

	@CPF
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate; 
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdAt;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime updatedAt;
	
	@ManyToMany
	@JoinTable(name = "users_groups", 
		joinColumns = {@JoinColumn(name = "user_id")}, 
		inverseJoinColumns = {@JoinColumn(name = "group_id")})
	private List<Group> listGroups = new ArrayList<>();

	public User(Integer id, String userName, String email, String password, String cpf, LocalDate birthDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.birthDate = birthDate; //
		this.createdAt = LocalDateTime.now();
	}
}
