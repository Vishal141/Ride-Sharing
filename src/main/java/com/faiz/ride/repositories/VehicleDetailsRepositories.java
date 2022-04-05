package com.faiz.ride.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faiz.ride.models.VehicleDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDetailsRepositories extends JpaRepository<VehicleDetails, Integer>{

}
