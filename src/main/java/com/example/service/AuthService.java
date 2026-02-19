package com.example.service;

import com.example.dto.APIResponse;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {
   private UserRepository  userRepository;
   private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
   public APIResponse<String> register(UserDto userDto){
        if(userRepository.existsByUserName(userDto.getUsername())){
            APIResponse response = new APIResponse();
            response.setMessage("Registration Failed");
            response.setStatus(409);
            response.setData("UserName already exists");
        }
        if (userRepository.existsByEmail(userDto.getEmail())){
            APIResponse response = new APIResponse();
            response.setMessage("Registration Failed");
            response.setStatus(409);
            response.setData("email already exists");
       }
       User user = new User();
       BeanUtils.copyProperties(userDto,user);
       user.setPassword(passwordEncoder.encode(userDto.getPassword()));
       userRepository.save(user);

       return null;

   }

}
