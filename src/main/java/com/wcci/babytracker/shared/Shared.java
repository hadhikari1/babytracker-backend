package com.wcci.babytracker.shared;

import com.wcci.babytracker.Repositories.UserRepository;
import com.wcci.babytracker.authorization.JwtUtil;
import com.wcci.babytracker.pojos.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Share or known as common file which can be extends to other service file
 * or Inject when needed
 * Created By Hari Adhikari April, 2022
 */
@Service
public class Shared {

    @Inject
    private UserRepository userRepository;

    @Inject
    private JwtUtil jwtUtil;

    /**
     * checkUserByUsername
     * @param username
     * @return
     * @throws Exception
     */
    public User checkUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("Something Went Wrong");
        }
        return user;
    }

    /**
     * By the http header it will have email in the JWT Token, which can be useful
     * UI doesn't have to pass the email specifically
     * @param request
     * @return
     */
    public String getEmailByToken(HttpServletRequest request){
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        String email = null;
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = jwtUtil.extractUsername(jwt);
        }

        return email;
    }
}
