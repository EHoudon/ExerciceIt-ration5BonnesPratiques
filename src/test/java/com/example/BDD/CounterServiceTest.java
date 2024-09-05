package com.example.BDD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class CounterServiceTest {
    @Autowired
    CounterService counterService;
    @Test
    void addToCounter() {
        for (int i = 0; i < 10; i++) {
            Thread.startVirtualThread(() -> {
                counterService.addToCounter("102", 1);
            });
        }
        assertEquals(10, counterService.getCounterValue("102"));
    }
}