package com.example.interviewskeleton.service;

import com.example.interviewskeleton.config.ClockConfig;
import com.example.interviewskeleton.config.GreetingsConfig;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class GreetingsService {

    private final GreetingsConfig greetingsConfig;

    private final ClockConfig clockConfig;

    public GreetingsService(ClockConfig clockConfig,
                            GreetingsConfig greetingsConfig) {
        this.clockConfig = clockConfig;
        this.greetingsConfig = greetingsConfig;
    }

    public String getGreetingMessage(String name, String locale) {
        String timeOfDay = getTimeOfDay();
        String greetingTemplate = greetingsConfig.getGreetings().get(timeOfDay).get(locale);

        return greetingTemplate.replace("{name}", name);
    }

    private String getTimeOfDay() {
        LocalTime currentTime = clockConfig.getCurrentTime();
        if (currentTime.isBefore(LocalTime.of(12, 0))) {
            return "morning";
        } else if (currentTime.isBefore(LocalTime.of(18, 0))) {
            return "afternoon";
        } else {
            return "evening";
        }
    }
}

