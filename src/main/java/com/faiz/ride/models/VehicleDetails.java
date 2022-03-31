package com.faiz.ride.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class VehicleDetails {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String vehcileName;
	private String modelNumber;
	
	
	public VehicleDetails() {
		super();
	}

	public VehicleDetails(int id, String name, String vehcileName, String modelNumber) {
		super();
		this.id = id;
		this.name = name;
		this.vehcileName = vehcileName;
		this.modelNumber = modelNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVehcileName() {
		return vehcileName;
	}

	public void setVehcileName(String vehcileName) {
		this.vehcileName = vehcileName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
	
	
}
