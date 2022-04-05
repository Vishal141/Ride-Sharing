package com.faiz.ride.repositories;

import com.faiz.ride.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepositories extends JpaRepository<UserDetails,Integer> {
}
