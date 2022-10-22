package com.pj.untapped.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Name is required")
	private String name;
	private String module;
	private String action;
	
	@ManyToMany(mappedBy = "listPermissions")
	private List<Group> listGroups = new ArrayList<>();
	
	public Permission(Integer id, String name, String module, String action) {
		super();
		this.id = id;
		this.name = name;
		this.module = module;
		this.action = action;
	}
}
