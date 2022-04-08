package com.wcci.babytracker.authorization;

import com.wcci.babytracker.Repositories.UserRepository;
import com.wcci.babytracker.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User newUser = userRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(newUser.getUsername(), newUser.getPassword(), new ArrayList<>());
    }
}
