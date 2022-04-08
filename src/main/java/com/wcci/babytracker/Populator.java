package com.wcci.babytracker;

import com.wcci.babytracker.Repositories.BabyRepository;
import com.wcci.babytracker.Repositories.UserRepository;
import com.wcci.babytracker.pojos.Baby;
import com.wcci.babytracker.pojos.User;
import com.wcci.babytracker.resourses.Diaper;
import com.wcci.babytracker.resourses.Feed;
import com.wcci.babytracker.resourses.Nap;
import com.wcci.babytracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Populator implements CommandLineRunner {
    @Autowired
    private BabyRepository babyRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {
        List<User> getAllUser = (List<User>) userRepo.findAll();
        if (getAllUser.size() == 0) {
            User user1 = new User("User1", "user1@gmail.com", "password");
            userService.saveUserInDatabase(user1);

            User user2 = new User("User2", "user2@gmail.com", "password");
            userService.saveUserInDatabase(user2);

            Nap nap1 = new Nap("Nap",LocalDateTime.now(),LocalDateTime.now());
            Diaper diaper1 = new Diaper("wet",LocalDateTime.now());
            Feed feed1 = new Feed("solid",1,LocalDateTime.now());
            Baby bab1 = new Baby(user1,"David Smith",1,22,"male","Latex","https://i.pinimg.com/originals/66/89/bb/6689bbd69162c7770ba266bac6006d1b.jpg",nap1);
            bab1.loadDiapers(diaper1);
            bab1.loadFeeds(feed1);
            babyRepo.save(bab1);


            Baby baby2 = new Baby(user2,"Dia Singh",2, 35, "Female", "Egg","https://images.unsplash.com/photo-1583086762675-5a88bcc72548?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8OHx8fGVufDB8fHx8&w=1000&q=80");
            babyRepo.save(baby2);
            Baby baby3 = new Baby(user1,"John Doe",2, 35, "Male", "penicillin","https://media.istockphoto.com/photos/portrait-of-african-baby-toddler-smiling-sitting-on-bed-indoor-picture-id1311426758?b=1&k=20&m=1311426758&s=170667a&w=0&h=mFGdHj1PELUVWfSTMkRyfYXOpAlXZMALkPmYGbD7rdc=");
            babyRepo.save(baby3);
        }
    }

}
