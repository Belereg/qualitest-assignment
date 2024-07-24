package com.example.interviewskeleton.service;

import com.example.interviewskeleton.config.ClockConfig;
import com.example.interviewskeleton.config.GreetingsConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GreetingsServiceTest {

    @Mock
    private ClockConfig clockConfig;

    @Mock
    private GreetingsConfig greetingsConfig;

    @InjectMocks
    private GreetingsService greetingsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        greetingsService = new GreetingsService(clockConfig, greetingsConfig);
        when(greetingsConfig.getGreetings()).thenReturn(buildGreetingsMap());
    }

    @Test
    public void testMorningGreetingMessage() {
        Mockito.doReturn(LocalTime.of(5, 0)).when(clockConfig).getCurrentTime();

        String greetingMessageEN = greetingsService.getGreetingMessage("Qualitest", "en");
        assertEquals("Good morning, Qualitest!", greetingMessageEN);

        String greetingMessageES = greetingsService.getGreetingMessage("Qualitest", "es");
        assertEquals("¡Buenos días, Qualitest!", greetingMessageES);
    }

    @Test
    public void testAfternoonGreetingMessage() {
        Mockito.doReturn(LocalTime.of(13, 0)).when(clockConfig).getCurrentTime();

        String greetingMessageEN = greetingsService.getGreetingMessage("Qualitest", "en");
        assertEquals("Good afternoon, Qualitest!", greetingMessageEN);

        String greetingMessageES = greetingsService.getGreetingMessage("Qualitest", "es");
        assertEquals("¡Buenas tardes, Qualitest!", greetingMessageES);
    }

    @Test
    public void testEveningGreetingMessage() {
        Mockito.doReturn(LocalTime.of(20, 0)).when(clockConfig).getCurrentTime();

        String greetingMessageEN = greetingsService.getGreetingMessage("Qualitest", "en");
        assertEquals("Good evening, Qualitest!", greetingMessageEN);

        String greetingMessageES = greetingsService.getGreetingMessage("Qualitest", "es");
        assertEquals("¡Buenas noches, Qualitest!", greetingMessageES);
    }

    // I created this greetings map for testing purposes
    private Map<String, Map<String, String>> buildGreetingsMap() {
        Map<String, Map<String, String>> greetingsMap = new HashMap<>();

        Map<String, String> morningGreetings = new HashMap<>();
        morningGreetings.put("en", "Good morning, {name}!");
        morningGreetings.put("es", "¡Buenos días, {name}!");
        greetingsMap.put("morning", morningGreetings);

        Map<String, String> afternoonGreetings = new HashMap<>();
        afternoonGreetings.put("en", "Good afternoon, {name}!");
        afternoonGreetings.put("es", "¡Buenas tardes, {name}!");
        greetingsMap.put("afternoon", afternoonGreetings);

        Map<String, String> eveningGreetings = new HashMap<>();
        eveningGreetings.put("en", "Good evening, {name}!");
        eveningGreetings.put("es", "¡Buenas noches, {name}!");
        greetingsMap.put("evening", eveningGreetings);

        return greetingsMap;
    }
}