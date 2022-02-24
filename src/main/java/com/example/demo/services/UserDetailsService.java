package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repos.UserRepo;
import com.example.demo.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepo userRepo;

    public UserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        return JwtUserDetails.createUser(user);
    }

//    public UserDetails loadAdminByUsername(String username) throws UsernameNotFoundException{
//        User user = userRepo.findByEmail(username);
//        return JwtUserDetails.createAdmin(user);
//    }

    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id).get();
        return JwtUserDetails.createUser(user);
    }

//    public UserDetails loadAdminById(Long id){
//        User user = userRepo.findById(id).get();
//        return JwtUserDetails.createAdmin(user);
//    }

}
