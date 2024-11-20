package com.plannerapp.service;

import com.plannerapp.model.dto.UserRegisterDTO;

public interface UserService {

    boolean register(UserRegisterDTO userRegisterDTO);
}
