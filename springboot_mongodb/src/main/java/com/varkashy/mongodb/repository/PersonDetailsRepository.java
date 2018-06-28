package com.varkashy.mongodb.repository;

import com.varkashy.mongodb.model.PersonDetails;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PersonDetailsRepository extends MongoRepository<PersonDetails,String> {

    PersonDetails findByFirstName(String firstName);

    PersonDetails findByLastName(String lastName);
}
