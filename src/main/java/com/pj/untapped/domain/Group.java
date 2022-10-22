package com.pj.untapped.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "GROUPS")
@Getter 
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Name is required")
	private String name;
	private String description;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "listGroups")
	private List<User> listUsers = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "groups_permissions", 
		joinColumns = {@JoinColumn(name = "group_id")}, 
		inverseJoinColumns = {@JoinColumn(name = "permission_id")})
	private List<Permission> listPermissions = new ArrayList<>();

	public Group(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
}
