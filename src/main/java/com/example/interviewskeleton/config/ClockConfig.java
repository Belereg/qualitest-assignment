package com.example.interviewskeleton.config;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ClockConfig {

    public LocalTime getCurrentTime() { //decoupled this from the service so I can test it easier
        return LocalTime.now();
    }

}
