package com.ecomindo.onboarding.sampleauth.dao;

import java.util.Optional;

import com.ecomindo.onboarding.sampleauth.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserDao extends JpaRepository<User, Integer> { 
    public Optional<User> findByUsername(String username);
}

