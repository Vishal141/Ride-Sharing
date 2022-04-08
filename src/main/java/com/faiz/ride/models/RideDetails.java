package com.faiz.ride.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rideDetails")
@Getter @Setter @NoArgsConstructor
public class RideDetails {

	@Id
	private int id;
	private int availableSeats;
	private String name;
	private String vehicleName;
	private String origin;
	private String destination;

	public RideDetails(int id, int availableSeats, String name, String vehicleName, String origin, String destination) {
		this.id = id;
		this.availableSeats = availableSeats;
		this.name = name;
		this.vehicleName = vehicleName;
		this.origin = origin;
		this.destination = destination;
	}
}
