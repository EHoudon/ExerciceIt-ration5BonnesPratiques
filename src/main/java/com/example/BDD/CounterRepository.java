package com.example.BDD;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CounterRepository extends MongoRepository<Counter, String> {

}
