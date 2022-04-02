package com.ride.sharing.controllers;

import com.ride.sharing.dao.UserDao;
import com.ride.sharing.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ride-sharing/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/sighIn")
    public ResponseEntity<User> signIn(@RequestBody User user){
        User user1 = userDao.signIn(user);
        return ResponseEntity.ok(user1);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User user1 = userDao.login(user);
        return ResponseEntity.ok(user1);
    }

}
