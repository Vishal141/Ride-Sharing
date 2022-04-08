package com.faiz.ride.repositories;

import com.faiz.ride.models.RideDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RideDetailsRepositories extends JpaRepository<RideDetails,Integer> {

    @Query(value = "SELECT * FROM ride_details R WHERE R.origin = ?1 AND R.destination = ?2",
    nativeQuery = true)
    Optional<RideDetails> findByLocation(String origin, String destination);

    @Query(value = "SELECT * FROM ride_details R WHERE R.origin = ?1 AND R.destination = ?2 And R.vehicle_name = ?3",
            nativeQuery = true)
    Optional<RideDetails> findByVehicleName(String source, String destination,String preferredVehicle);

    @Query(value = "SELECT * FROM ride_details R WHERE R.origin = ?1 AND R.destination = ?2 And R.available_Seats = ?3",
            nativeQuery = true)
    Optional<RideDetails> findBySeats(String origin, String destination, int seats);

}
