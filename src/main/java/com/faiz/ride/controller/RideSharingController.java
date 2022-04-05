package com.faiz.ride.controller;

import com.faiz.ride.models.*;
import com.faiz.ride.repositories.RideDetailsRepositories;
import com.faiz.ride.repositories.UserDetailsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faiz.ride.repositories.VehicleDetailsRepositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ride")
public class RideSharingController {

	@Autowired
	private RideDetailsRepositories rideDetailsRepositories;

	@Autowired
	private UserDetailsRepositories userDetailsRepositories;

	@Autowired
	private VehicleDetailsRepositories vehicleDetailsRepositories;

	private TotalRidesDetails totalRidesDetails = TotalRidesDetails.getInstance();

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
	public ResponseEntity offerRide(@PathVariable(value = "vehicleId") int vehicleId, @RequestBody UserLocationDetails userLocationDetails) {

		try {
			VehicleDetails vehicleDetails = vehicleDetailsRepositories.findById(vehicleId).orElse(null);
			String userName = vehicleDetails.getName();
			RideDetails rideDetails = new RideDetails(vehicleId, userName,vehicleDetails, userLocationDetails.getSource(), userLocationDetails.getDestination());
			rideDetailsRepositories.save(rideDetails);
			RideStatsDetails rideStatsDetails;
			if(totalRidesDetails.getUsers().containsKey(userName)){
				rideStatsDetails = totalRidesDetails.getUsers().get(userName);
				Integer newOffered = rideStatsDetails.getOffered()+1;
				rideStatsDetails.setOffered(newOffered);
			}
			else{
				rideStatsDetails = new RideStatsDetails(1, 0);
			}
			totalRidesDetails.getUsers().put(userName,rideStatsDetails);
			return ResponseEntity.ok(rideDetails);
		}
		catch(Exception e){
			return ResponseEntity.internalServerError().body("Error");
		}
	}

	@PostMapping("/selectride")
	public ResponseEntity selectRide(@RequestBody SelectRideDetails selectRideDetails){

		try{
			Optional<RideDetails> rideDetailsList = rideDetailsRepositories.findByLocation(selectRideDetails.getSource(),selectRideDetails.getDestination());
			String userName = selectRideDetails.getName();
			RideStatsDetails rideStatsDetails;
			if(totalRidesDetails.getUsers().containsKey(userName)){
				rideStatsDetails = totalRidesDetails.getUsers().get(userName);
				Integer newTaken = rideStatsDetails.getTaken()+1;
				rideStatsDetails.setOffered(newTaken);
			}
			else{
				rideStatsDetails = new RideStatsDetails(0, 1);
			}
			totalRidesDetails.getUsers().put(userName,rideStatsDetails);
			return ResponseEntity.ok(rideDetailsList);
		}
		catch(Exception e){
			return ResponseEntity.internalServerError().body("No Rides Available for given location");
		}
	}

	@GetMapping("/ridestats")
	public List<String> rideStats(){
		List<String> list = null;
		for(Map.Entry<String,RideStatsDetails> entry : totalRidesDetails.getUsers().entrySet()){
			String userStats = entry.getKey() + ":" + String.valueOf(entry.getValue().getOffered()) + " " + "Offered" + " " + "," + " " + String.valueOf(entry.getValue().getTaken()) + " " + "Taken";
			list.add(userStats);
		}
		
		return list;
	}
}
