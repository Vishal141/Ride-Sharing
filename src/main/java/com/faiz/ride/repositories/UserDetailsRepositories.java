package com.faiz.ride.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faiz.ride.models.UserDetails;

public interface UserDetailsRepositories extends JpaRepository<UserDetails, Integer>{

}
