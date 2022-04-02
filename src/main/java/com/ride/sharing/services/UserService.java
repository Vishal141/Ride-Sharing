package com.ride.sharing.services;

import com.ride.sharing.dao.UserDao;
import com.ride.sharing.models.User;
import com.ride.sharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(User user) {
        Optional<User> optional = userRepository.findById(user.getUsername());
        if(optional.isEmpty())
            return null;
        User user1 = optional.get();
        if(user1.getPassword() == getMd5(user.getPassword())){
            user1.setPassword("");
            return user1;
        }
        return null;
    }

    @Override
    public User signIn(User user) {
        user.setPassword(getMd5(user.getPassword()));
        User user1 = userRepository.save(user);
        user1.setPassword("");
        return user1;
    }

    public String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
