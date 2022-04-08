package com.wcci.babytracker.Controllers;

import com.wcci.babytracker.Repositories.UserRepository;
import com.wcci.babytracker.pojos.User;
import com.wcci.babytracker.service.UserService;
import com.wcci.babytracker.shared.AuthenticationResponse;
import com.wcci.babytracker.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://127.0.0.1:5501/")
public class UserController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BabyController babyRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<User> getUsers(){
        return userRepo.findAll();
    }

    @PostMapping("/users/{id}")
    public User  addUser(@RequestBody User user, @PathVariable Long id){
        userRepo.save(user);
        return userRepo.findById(id).get();
    }

    @PostMapping("/user/sign-up")
    public AuthenticationResponse signupUser(@RequestBody User user){
        User newUser = userService.saveUser(user);
        if(newUser != null){
            return new AuthenticationResponse(Response.SUCCESS);
        }
        return new AuthenticationResponse(Response.USER_EXIST);
    }

    @PostMapping("/user/login")
    public AuthenticationResponse userLogin(@RequestBody User userLogin){
        //checking if the username and password match
        AuthenticationResponse user = userService.loginUserWithPassword(userLogin);
        if(user != null){
            return user;
        }
        return new AuthenticationResponse(Response.WRONG_USER_OR_PASSWORD);
    }

    @GetMapping("/user/logout")
    public AuthenticationResponse userLogout(){
        return new AuthenticationResponse(Response.SUCCESS);
    }
}
