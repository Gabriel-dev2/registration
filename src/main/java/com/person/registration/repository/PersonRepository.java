package com.person.registration.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.person.registration.entities.Person;

public interface PersonRepository extends MongoRepository<Person, String>{
    
@Query("{name: /?0/}")
    List<Person> findPersonByName(String name);
}
