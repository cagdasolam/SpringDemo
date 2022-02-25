package com.example.demo.services;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repos.RoleRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.request.AddRoleRequest;
import com.example.demo.request.RoleCreateRequest;
import com.example.demo.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepository;
    private final RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepository, RoleRepo roleRepo) {
        this.userRepository = userRepository;
        this.roleRepo = roleRepo;
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public UserResponse getOneUser(Long userId) {
         User user = userRepository.findById(userId).orElse(null);
         return new UserResponse(user);
    }

    public void addUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()){
            throw new IllegalStateException("user is already exist");
        }
        userRepository.save(user);
    }

    public User updateUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser = user.get();
            foundUser.setEmail(newUser.getEmail());
            foundUser.setPassword(newUser.getPassword());
            return userRepository.save(foundUser);
        }else{
            return null;
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getOneUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Role saveRole(RoleCreateRequest role){
        Optional<Role> roleOptional = Optional.ofNullable(roleRepo.findByName(role.getRoleName()));
        if (roleOptional.isPresent()){
            return null;
        }
        Role roleToSave = new Role();
        roleToSave.setName(role.getRoleName());
        roleRepo.save(roleToSave);
        return roleToSave;
    }

    public Collection<Role> addRoleToUser(AddRoleRequest addRoleRequest) {
        User user = userRepository.findByEmail(addRoleRequest.getEmail());
        Role role = roleRepo.findByName(addRoleRequest.getRoleName());
        user.getRoles().add(role);
        return user.getRoles();
    }

}
