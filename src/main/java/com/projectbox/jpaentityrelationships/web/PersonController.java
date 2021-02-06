package com.projectbox.jpaentityrelationships.web;

import com.projectbox.jpaentityrelationships.entities.Address;
import com.projectbox.jpaentityrelationships.entities.Person;
import com.projectbox.jpaentityrelationships.exceptions.PersonNotFoundException;
import com.projectbox.jpaentityrelationships.repositories.PersonRepository;
import com.projectbox.jpaentityrelationships.services.PersonService;
import com.projectbox.jpaentityrelationships.services.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/people")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = personService.findAllPeople();

        logger.info("[PersonController] Returning a list of people.");

        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> optionalPerson = personService.findPersonById(id);

        if (optionalPerson.isEmpty()) {
            logger.info("[PersonController] No person was found with the inputted ID.");
            throw new PersonNotFoundException("No person with that is was found");
        }

        Person person = optionalPerson.get();

        logger.info("[PersonController] Returning a person.");

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person newPerson = personService.savePerson(person);

        logger.info("[PersonController] Creating a new person.");
        logger.info(String.format("[PersonController] The phone numbers of the person where: %s", person.getPhoneNumbers()));
        logger.info(String.format("[PersonController] The address of the person passed in %s: ", person.getAddress().toString()));

        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(id, person);

        logger.info("[PersonController] Updating a person.");

        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);

        logger.info(String.format("[PersonController] Person with ID: %d, successfully deleted.", id));

        return new ResponseEntity<>("Person has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}
