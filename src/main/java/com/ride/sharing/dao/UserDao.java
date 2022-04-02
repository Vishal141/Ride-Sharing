package com.ride.sharing.dao;

import com.ride.sharing.models.User;

public interface UserDao {
    public User login(User user);
    public User signIn(User user);
}
