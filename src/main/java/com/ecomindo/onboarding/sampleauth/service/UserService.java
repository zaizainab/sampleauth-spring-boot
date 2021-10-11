package com.ecomindo.onboarding.sampleauth.service;

import com.ecomindo.onboarding.sampleauth.dto.LoginDTO;
import com.ecomindo.onboarding.sampleauth.entity.User;

public interface UserService {
    public User getUserByUsernameAndPassword(LoginDTO data);
}
