package com.faiz.ride.controller;

import com.faiz.ride.models.*;
import com.faiz.ride.repositories.RideDetailsRepositories;
import com.faiz.ride.repositories.UserDetailsRepositories;
import com.faiz.ride.services.RideSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faiz.ride.repositories.VehicleDetailsRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ride")
public class RideSharingController {

	@Autowired
	private RideSharingService rideSharingService;

	private TotalRidesDetails totalRidesDetails = TotalRidesDetails.getInstance();

	@PostMapping("/addvehcile")
	public ResponseEntity addVehicle(@RequestBody VehicleDetails vehicleDetails) {

		rideSharingService.addVehicle(vehicleDetails);
		return ResponseEntity.ok(vehicleDetails);
	}
	
	@PostMapping("/offerride/{vehicleId}")
	public ResponseEntity offerRide(@PathVariable(value = "vehicleId") int vehicleId, @RequestBody UserLocationDetails userLocationDetails) {

		try {
			RideDetails rideDetails = rideSharingService.offerRide(vehicleId,userLocationDetails);
			String userName = rideDetails.getName();
			RideStatsDetails rideStatsDetails;
			if(totalRidesDetails.getUsers().containsKey(userName)){
				rideStatsDetails = totalRidesDetails.getUsers().get(userName);
				Integer newOffered = rideStatsDetails.getOffered()+1;
				rideStatsDetails.setOffered(newOffered);
				System.out.println(userName + " " + "newOffered" + " " + newOffered);
			}
			else{
				rideStatsDetails = new RideStatsDetails(1, 0);
				System.out.println(userName + " " + "newOffered" + " " + 1);
			}
			totalRidesDetails.getUsers().put(userName,rideStatsDetails);
			return ResponseEntity.ok(rideDetails);
		}
		catch(Exception e){
			return ResponseEntity.internalServerError().body("Error");
		}
	}

	@PostMapping("/selectride")
	public ResponseEntity selectRide(@RequestBody UserSelectedRideDetails userSelectedRideDetails){

		try{
			RideDetails rideDetails = rideSharingService.selectRide(userSelectedRideDetails);
			String userName = rideDetails.getName();
			RideStatsDetails rideStatsDetails;
			if(totalRidesDetails.getUsers().containsKey(userName)){
				rideStatsDetails = totalRidesDetails.getUsers().get(userName);
				Integer newTaken = rideStatsDetails.getTaken()+1;
				rideStatsDetails.setOffered(newTaken);
				System.out.println(userName + " " + "newTaken" + " " + newTaken);
			}
			else{
				rideStatsDetails = new RideStatsDetails(0, 1);
				System.out.println(userName + " " + "newTaken" + " " + 1);
			}
			totalRidesDetails.getUsers().put(userName,rideStatsDetails);
			return ResponseEntity.ok(userSelectedRideDetails);
		}
		catch(Exception e){
			return ResponseEntity.internalServerError().body("No Rides Available for given location");
		}
	}

	@GetMapping("/allrides")
	public List<RideDetails> getAllRides(){
		List<RideDetails> list = rideSharingService.getAllRides();
		return list;
	}

	@GetMapping("/ridestats")
	public List<String> rideStats(){
		List<String> list = new ArrayList<String>();
		for(Map.Entry<String,RideStatsDetails> entry : totalRidesDetails.getUsers().entrySet()){
			String userStats = entry.getKey() + ":" + String.valueOf(entry.getValue().getOffered()) + " " + "Offered" + " " + "," + " " + String.valueOf(entry.getValue().getTaken()) + " " + "Taken";
			list.add(userStats);
		}
		
		return list;
	}
}
