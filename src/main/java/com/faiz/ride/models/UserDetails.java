package com.faiz.ride.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userDetails")
@Getter @Setter @NoArgsConstructor
public class UserDetails {

	@Id
	private int id;
	private String name;

	public UserDetails(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
