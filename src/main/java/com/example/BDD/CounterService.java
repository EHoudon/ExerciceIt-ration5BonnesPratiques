package com.example.BDD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class CounterService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CounterRepository counterRepository;

    Lock lock = new ReentrantLock();

    public int getCounterValue(String counterId) {
        Optional<Counter> counter = counterRepository.findById(counterId);
        return counter.map(Counter::getValue).orElse(0);
    }

    public int addToCounter(String counterId, int value) {
        Counter updatedCounter = mongoTemplate.findAndModify(
                query(where("_id").is(counterId)),
                new Update().inc("value", value),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                Counter.class
        );

        if (updatedCounter != null) {
            return updatedCounter.getValue();
        } else {
            throw new RuntimeException("Failed to update the counter.");
        }
    }


//    public int addToCounter(String counterId, int value) {
//        lock.lock();
//        try {
//            Optional<Counter> counterOpt = counterRepository.findById(counterId);
//            Counter counter;
//            if (counterOpt.isPresent()) {
//                counter = counterOpt.get();
//                Thread.sleep(3000);
//            } else {
//                counter = new Counter();
//                counter.setId(counterId);
//                counter.setValue(0);
//            }
//            int newValue = counter.getValue() + value;
//            counter.setValue(newValue);
//            counterRepository.save(counter);
//            return newValue;
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            lock.unlock();
//        }
    }
