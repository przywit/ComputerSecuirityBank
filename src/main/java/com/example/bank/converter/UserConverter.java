package com.example.bank.converter;

import com.example.bank.DTO.UserDTO;
import com.example.bank.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertDTOToEntity(UserDTO userDTO){
        return new User(userDTO.getLogin(),userDTO.getPassword(),userDTO.getEmail(), userDTO.getAnswer());
    }

}
