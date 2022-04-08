package com.wcci.babytracker.resourses;

public class BabyStatus {
    private Diaper diaper;
    private Feed feed;
    private Health health;
    private Nap nap;

    public Diaper getDiaper() {
        return diaper;
    }

    public void setDiaper(Diaper diaper) {
        this.diaper = diaper;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Nap getNap() {
        return nap;
    }

    public void setNap(Nap nap) {
        this.nap = nap;
    }
}
