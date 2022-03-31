package com.faiz.ride.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faiz.ride.models.RideDetails;

@Repository
public interface RideDetailsRepositories extends JpaRepository<RideDetails, Integer> {

}
