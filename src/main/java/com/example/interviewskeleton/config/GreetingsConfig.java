package com.example.interviewskeleton.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "greeting")
public class GreetingsConfig {
    private Map<String, Map<String, String>> greetings;
}
