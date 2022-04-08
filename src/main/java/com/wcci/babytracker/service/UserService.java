package com.wcci.babytracker.service;

import com.wcci.babytracker.Repositories.UserRepository;
import com.wcci.babytracker.authorization.JwtUtil;
import com.wcci.babytracker.authorization.MyUserDetailService;
import com.wcci.babytracker.authorization.PasswordHasher;
import com.wcci.babytracker.pojos.User;
import com.wcci.babytracker.shared.AuthenticationResponse;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordHasher passwordHasher;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;

    public User saveUser(User user){
        //checks if username already exists
        try{
            if(userRepo.findByUsername(user.getUsername()) == null) {
               return saveUserInDatabase(user);
            }
        }
        catch (Exception exception){
            System.out.println(exception);
        }
        return null;
    }

    //Checking to see if stored password matches with use input
    public AuthenticationResponse loginUserWithPassword(User userLogin){
        User user = validateUserWithPassword(userLogin);
        if (user == null) {
            return null;
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = myUserDetailService.loadUserByUsername(user.getUsername().toLowerCase());
        String jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt, user.getId(), user.getFullName());
    }

    private User validateUserWithPassword(User userLogin) {
        User user = userRepo.findByUsername(userLogin.getUsername());
        if(user != null){
            String storedSalt = user.getSalt();
            String storedPassword = user.getPassword();
            String hashedPassword = passwordHasher.computeHash(userLogin.getPassword(),Base64.decode(storedSalt));
            if(storedPassword.equals(hashedPassword)){
                return user;
            }
        }
        return null;
    }

    public User saveUserInDatabase(User user){
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(user.getPassword(), salt);
        String saltString = new String(Base64.encode(salt));
        User newUser = new User(user.getFullName(), user.getUsername().toLowerCase(),hashedPassword, saltString);
        return userRepo.save(newUser);
    }

}
