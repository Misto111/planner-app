package com.plannerapp.service.impl;

import com.plannerapp.model.dto.UserRegisterDTO;
import com.plannerapp.model.entity.UserEntity;
import com.plannerapp.repository.UserRepository;
import com.plannerapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        UserEntity dbUserEntity = userRepository.findByUsername(userRegisterDTO.getUsername());

        if (dbUserEntity != null) {
            return false;
        }

        UserEntity userEntity = new UserEntity()
                .setUsername(userRegisterDTO.getUsername())
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(userEntity);

        return true;
    }
}
