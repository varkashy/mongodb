package com.varkashy.mongodb;

import com.varkashy.mongodb.model.PersonDetails;
import com.varkashy.mongodb.repository.PersonDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {

	@Autowired
	PersonDetailsRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new PersonDetails("Alice", "Sam"));
        repository.save(new PersonDetails("Bob", "Smith"));

        // fetch all customers
        System.out.println("Persons found with findAll():");
        System.out.println("-------------------------------");
        for (PersonDetails personDetails : repository.findAll()) {
            System.out.println(personDetails);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Persons found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        PersonDetails person =repository.findByLastName("Smith");
            System.out.println(person);

    }
}
