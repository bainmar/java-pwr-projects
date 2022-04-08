package com.bartoszek.bilboard;

import java.time.Duration;

public class Advertisement {
    private final String advertisement;
    private final Duration duration;
    private final int id;
    private Duration displayedTime;

    public Advertisement(String advertisement, Duration duration, int id) {
        this.advertisement = advertisement;
        this.duration = duration;
        this.id = id;
        this.displayedTime = Duration.ZERO;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public Duration getDuration() {
        return duration;
    }

    public Duration getDisplayedTime() {
        return displayedTime;
    }

    public void setDisplayedTime(Duration displayedTime) {
        this.displayedTime = displayedTime;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "|------------ Reklama ----------:\n" +
                advertisement +
                "\n--------------------------------";
    }
}
