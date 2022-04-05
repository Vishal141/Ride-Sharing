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
	private String name;

	@OneToOne
	@JoinColumn
	private VehicleDetails vehicleDetails;
	private String origin;
	private String destination;

	public RideDetails(int id, String name, VehicleDetails vehicleDetails, String origin,
			String destination) {
		super();
		this.id = id;
		this.name = name;
		this.vehicleDetails = vehicleDetails;
		this.origin = origin;
		this.destination = destination;
	}
}
