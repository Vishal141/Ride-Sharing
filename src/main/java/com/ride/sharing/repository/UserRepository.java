package com.ride.sharing.repository;

import com.ride.sharing.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends JpaRepository<User,String> {
}
