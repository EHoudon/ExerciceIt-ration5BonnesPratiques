package com.example.BDD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counters")
public class CounterController {

    private final CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/{counterId}")
    public int getCounter(@PathVariable String counterId) {
        return counterService.getCounterValue(counterId);
    }

    @PostMapping("/{counterId}")
    public int addToCounter(@PathVariable String counterId, @RequestBody int value) {
        return counterService.addToCounter(counterId, value);
    }
}
