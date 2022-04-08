package com.faiz.ride.services;

import com.faiz.ride.models.*;
import com.faiz.ride.repositories.RideDetailsRepositories;
import com.faiz.ride.repositories.UserDetailsRepositories;
import com.faiz.ride.repositories.VehicleDetailsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Component
public class RideSharingService {
    @Autowired
    private RideDetailsRepositories rideDetailsRepositories;

    @Autowired
    private UserDetailsRepositories userDetailsRepositories;

    @Autowired
    private VehicleDetailsRepositories vehicleDetailsRepositories;

    public VehicleDetails addVehicle(VehicleDetails vehicleDetails){
        vehicleDetailsRepositories.save(vehicleDetails);
        return vehicleDetails;
    }

    public RideDetails offerRide(int vehicleId, UserLocationDetails userLocationDetails) throws Exception{
        try {
            VehicleDetails vehicleDetails = vehicleDetailsRepositories.findById(vehicleId).orElse(null);
            String userName = vehicleDetails.getName();
            System.out.println(userLocationDetails.getSeats());
            RideDetails rideDetails = new RideDetails(vehicleId, userLocationDetails.getSeats(), userName,vehicleDetails.getVehicleName(), userLocationDetails.getSource(), userLocationDetails.getDestination());
            rideDetailsRepositories.save(rideDetails);
            return rideDetails;
        }
        catch(Exception e){
             return null;
        }
    }

    public RideDetails selectRide(UserSelectedRideDetails userSelectedRideDetails){
        try{
            RideDetails selectedRideDetails;
            if(userSelectedRideDetails.getSeats()==0 && userSelectedRideDetails.getPreferredVehicle().isEmpty())
            {
                selectedRideDetails = rideDetailsRepositories.findByLocation(userSelectedRideDetails.getSource(), userSelectedRideDetails.getDestination()).orElseThrow(null);
            }
            else if(userSelectedRideDetails.getSeats()==0) {
                selectedRideDetails = rideDetailsRepositories.findByVehicleName(userSelectedRideDetails.getSource(), userSelectedRideDetails.getDestination(), userSelectedRideDetails.getPreferredVehicle()).orElseThrow(null);
            }
            else {
                selectedRideDetails = rideDetailsRepositories.findBySeats(userSelectedRideDetails.getSource(), userSelectedRideDetails.getDestination(), userSelectedRideDetails.getSeats()).orElseThrow(null);
            }

            return selectedRideDetails;
        }
        catch(Exception e){
            return null;
        }
    }

    public List<RideDetails> getAllRides(){
        List<RideDetails> list = rideDetailsRepositories.findAll();
        return list;
    }
}
