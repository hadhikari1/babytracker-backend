package com.wcci.babytracker.pojos;

import com.wcci.babytracker.resourses.Diaper;
import com.wcci.babytracker.resourses.Feed;
import com.wcci.babytracker.resourses.Health;
import com.wcci.babytracker.resourses.Nap;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(schema = "public")
public class Baby {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private int age;
    private String gender;
    private double height;
    private String allergies;
    @Column(length = 10000)
    private String babyImgUrl;
    @ElementCollection
    private List<Diaper> diapers;
    @ElementCollection
    private List<Feed> feeds;
    @ElementCollection
    private List<Nap> naps;
    @ElementCollection
    private List<Health> healths;
    @ManyToOne
    private User user;
    public Baby(User user, String name, int age, double height, String gender, String allergies, String babyImgUrl, Nap... naps) {
        this.user = user;
        this.name = name;
        this.age = age;
        this.height = height;
        this.allergies = allergies;
        this.babyImgUrl = babyImgUrl;
        this.gender = gender;
        this.naps = Arrays.asList(naps);
    }

    public Baby(String name, int age, String gender, double height, String allergies,
                String babyImgUrl, List<Diaper> diapers, List<Feed> feeds, List<Nap> naps,
                List<Health> healths) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.allergies = allergies;
        this.babyImgUrl = babyImgUrl;
        this.diapers = diapers;
        this.feeds = feeds;
        this.naps = naps;
        this.healths = healths;
    }

    public Baby() {
    }

    public User getUser() {
        return user;
    }

    public void loadFeeds(Feed... feeds){
        this.feeds = Arrays.asList(feeds);
    }
    public void loadDiapers(Diaper... diapers){
        this.diapers = Arrays.asList(diapers);
    }
    public void loadHealth(Health healths){
        this.healths = Arrays.asList(healths);
    }

    public Long getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getBabyImgUrl() {
        return babyImgUrl;
    }

    public List<Diaper> getDiapers() {
        return diapers;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public List<Nap> getNaps() {
        return naps;
    }

    public List<Health> getHealths() {
        return healths;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public void setBabyImgUrl(String babyImgUrl) {
        this.babyImgUrl = babyImgUrl;
    }

    public void setDiapers(List<Diaper> diapers) {
        this.diapers = diapers;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public void setNaps(List<Nap> naps) {
        this.naps = naps;
    }

    public void setHealths(List<Health> healths) {
        this.healths = healths;
    }
}
