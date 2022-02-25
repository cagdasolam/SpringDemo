package com.example.demo.responses;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.Data;

import java.util.Collection;

@Data
public class UserResponse {

    Long id;
    String email;
    Collection<Role> roles;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
