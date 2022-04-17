package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.request.AddRoleRequest;
import com.example.demo.request.RoleCreateRequest;
import com.example.demo.request.UserPasswordUpdateRequest;
import com.example.demo.responses.UserResponse;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUser();
    }

    @GetMapping("/{userId}")
    public UserResponse getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }


    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserPasswordUpdateRequest newUser){
        return userService.updateUserPassword(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PostMapping("/role/save")
    public Role saveRole(@RequestBody RoleCreateRequest role){
        return userService.saveRole(role);
    }

    @PostMapping("/role/addToUser")
    public Collection<Role> addRoleToUser(@RequestBody AddRoleRequest addRoleRequest){
        return userService.addRoleToUser(addRoleRequest);
    }
}