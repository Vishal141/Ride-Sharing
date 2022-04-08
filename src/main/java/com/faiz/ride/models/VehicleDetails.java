package com.faiz.ride.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicleDetails")
@Getter @Setter @NoArgsConstructor
public class VehicleDetails {

	@Id
	private int id;
	private String name;
	private String vehicleName;

	public VehicleDetails(int id, String name, String vehicleName) {
		super();
		this.id = id;
		this.name = name;
		this.vehicleName = vehicleName;
	}
}
