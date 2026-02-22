package com.example.service;

import com.example.dto.APIResponse;
import com.example.dto.UserDto;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
   private UserRepository  userRepository;
   private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
   public APIResponse<String> register(UserDto userDto){
       APIResponse<String> response = new APIResponse<>();
        if(userRepository.existsByUsername(userDto.getUsername())){

            response.setMessage("Registration Failed");
            response.setStatus(409);
            response.setData("UserName already exists");
        }
        if (userRepository.existsByEmail(userDto.getEmail())){
            response.setMessage("Registration Failed");
            response.setStatus(409);
            response.setData("email already exists");
       }
       UserEntity userEntity = new UserEntity();
       BeanUtils.copyProperties(userDto, userEntity);
       userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
       userRepository.save(userEntity);

       response.setMessage("Registration Success");
       response.setStatus(200);
       response.setData("UserEntity Registration Done");

       return response;

   }

}
