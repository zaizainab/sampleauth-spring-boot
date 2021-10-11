package com.ecomindo.onboarding.sampleauth.service;

import com.ecomindo.onboarding.sampleauth.dao.UserDao;
import com.ecomindo.onboarding.sampleauth.dto.LoginDTO;
import com.ecomindo.onboarding.sampleauth.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    
    @Override
    public User getUserByUsernameAndPassword(LoginDTO data) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User res = userDao.findAll().stream()
            .filter(x -> x.getUsername().equals(data.getUsername()) && x.getPassword().equals(encoder.encode(data.getPassword()))).findAny().orElse(null);
        
        return res;
    }
    
}
