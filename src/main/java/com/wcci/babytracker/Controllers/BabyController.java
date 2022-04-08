package com.wcci.babytracker.Controllers;

import com.wcci.babytracker.Repositories.BabyRepository;
import com.wcci.babytracker.Repositories.UserRepository;
import com.wcci.babytracker.pojos.Baby;
import com.wcci.babytracker.pojos.User;
import com.wcci.babytracker.resourses.BabyStatus;
import com.wcci.babytracker.util.Common;
import com.wcci.babytracker.util.modal.TimeDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin(value = "http://127.0.0.1:5500/")
public class BabyController {
    @Autowired
    private BabyRepository babyRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private Common common;

    public BabyController(BabyRepository babyRepo, UserRepository userRepo) {
        this.babyRepo = babyRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/babies")
    public Iterable<Baby> getAllBabies(){
        return babyRepo.findAll();
    }

    @GetMapping("/babies/{id}")
    public Baby getBaby(@PathVariable Long id){
        return babyRepo.findById(id).get();
    }

    @GetMapping("/babies/user/{id}")
    public List<Baby> getAllBabiesByUserId(@PathVariable Long id){
        return babyRepo.findAllByUserId(id);
    }
    @PostMapping("/babies/addBaby/user/{id}")
    public List<Baby> addBaby(@RequestBody Baby baby, @PathVariable Long id){
        User user = userRepo.findById(id).get();
        if(user != null) {
            Baby newBaby = new Baby(user,baby.getName(), baby.getAge(),baby.getHeight(),baby.getGender(),baby.getAllergies(),baby.getBabyImgUrl());
            babyRepo.save(newBaby);
            return babyRepo.findAllByUserId(id);
        }
        return Arrays.asList();
    }

    @PostMapping("/testDateTime")
    public TimeDuration testDateTime(@RequestBody LocalDateTime localDateTime) {
        LocalDateTime lt;
        if (localDateTime == null) {
            lt = LocalDateTime.now();
        } else {
            lt = localDateTime;
        }
        // save(lt) to the database
        return common.timeDurationFromNow(localDateTime);
    }

    @PostMapping("/update/baby_status/baby/{id}")
    public Baby updateBabyStatusAndGetBaby(@RequestBody BabyStatus babyStatus, @PathVariable Long id){
        if(babyStatus == null){
            return null;
        }
        Baby newBaby = babyRepo.findById(id).get();
        if(babyStatus.getFeed() != null){
            newBaby.getFeeds().add(babyStatus.getFeed());
            newBaby.setFeeds(newBaby.getFeeds());
        }
        if(babyStatus.getDiaper() != null){
            newBaby.getDiapers().add(babyStatus.getDiaper());
            newBaby.setDiapers(newBaby.getDiapers());
        }
        if(babyStatus.getHealth() != null){
            newBaby.getHealths().add(babyStatus.getHealth());
            newBaby.setHealths(newBaby.getHealths());
        }
        if(babyStatus.getNap() != null){
            newBaby.getNaps().add(babyStatus.getNap());
            newBaby.setNaps(newBaby.getNaps());
        }
        babyRepo.save(newBaby);
        return newBaby;
    }
}
