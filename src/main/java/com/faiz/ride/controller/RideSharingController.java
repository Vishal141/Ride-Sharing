package com.faiz.ride.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faiz.ride.models.LocationDetails;
import com.faiz.ride.models.RideDetails;
import com.faiz.ride.models.UserDetails;
import com.faiz.ride.models.VehicleDetails;
import com.faiz.ride.repositories.RideDetailsRepositories;
import com.faiz.ride.repositories.UserDetailsRepositories;
import com.faiz.ride.repositories.VehicleDetailsRepositories;

import antlr.collections.impl.Vector;

@RestController
@RequestMapping("/ride")
public class RideSharingController {

	@Autowired
	private RideDetailsRepositories rideDetailsRepositories;
	
	@Autowired
	private UserDetailsRepositories userDetailsRepositories;
	
	@Autowired
	private VehicleDetailsRepositories vehicleDetailsRepositories;
	
	@PostMapping("/adduser")
	public ResponseEntity addUser(@RequestBody UserDetails userDetails) {
		
		userDetailsRepositories.save(userDetails);
		return ResponseEntity.ok(userDetails);
	}
	
	@PostMapping("/addvehcile")
	public ResponseEntity addVehcile(@RequestBody VehicleDetails vehicleDetails) {
		
		vehicleDetailsRepositories.save(vehicleDetails);
		return ResponseEntity.ok(vehicleDetails);
	}
	
	@PostMapping("/offerride/{vehicleId}")
	public ResponseEntity offerRide(@PathVariable(value = "vehicleId") int vehicleId, @RequestBody LocationDetails locationDetails) {
		
		try {
			VehicleDetails vehicleDetails = vehicleDetailsRepositories.findById(vehicleId).orElse(null);
			String userName = vehicleDetails.getName();
			RideDetails rideDetails = new RideDetails(vehicleId, userName,vehicleDetails, locationDetails.getSource(), locationDetails.getDestination());
			rideDetailsRepositories.save(rideDetails);
			return ResponseEntity.ok(rideDetails);
		}
		catch(Exception e){
			return ResponseEntity.internalServerError().body("Error");
		}
	}
	
	
}
