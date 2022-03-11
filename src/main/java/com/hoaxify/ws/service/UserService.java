package com.hoaxify.ws.service;

import com.hoaxify.ws.dto.UserRequestDto;
import com.hoaxify.ws.dto.UserResponseDto;
import com.hoaxify.ws.entity.User;
import com.hoaxify.ws.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        String encryptedPassword = this.passwordEncoder.encode(userRequestDto.getPassword());
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setDisplayName(userRequestDto.getDisplayName());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return new UserResponseDto("user created");
    }
}
