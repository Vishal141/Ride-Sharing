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
@Table(name = "vehicleDetails")
@Getter @Setter @NoArgsConstructor
public class VehicleDetails {

	@Id
	private int id;
	private String name;
	private String vehcileName;
	private String modelNumber;

	public VehicleDetails(int id, String name, String vehcileName, String modelNumber) {
		super();
		this.id = id;
		this.name = name;
		this.vehcileName = vehcileName;
		this.modelNumber = modelNumber;
	}
}
