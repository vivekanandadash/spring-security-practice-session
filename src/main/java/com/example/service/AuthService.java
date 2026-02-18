package com.example.service;

import com.example.dto.APIResponse;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.BeanUtils;

public class AuthService {
   private UserRepository  userRepository;

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

       return null;

   }

}
