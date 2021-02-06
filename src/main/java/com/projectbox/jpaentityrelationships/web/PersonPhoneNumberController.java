package com.projectbox.jpaentityrelationships.web;

import com.projectbox.jpaentityrelationships.entities.Person;
import com.projectbox.jpaentityrelationships.entities.PhoneNumber;
import com.projectbox.jpaentityrelationships.exceptions.PersonNotFoundException;
import com.projectbox.jpaentityrelationships.services.PersonService;
import com.projectbox.jpaentityrelationships.services.PhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/person")
public class PersonPhoneNumberController {

    private final Logger logger = LoggerFactory.getLogger(PersonPhoneNumberController.class);
    private final PersonService personService;

    public PersonPhoneNumberController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/{personId}/phone")
    public ResponseEntity<List<PhoneNumber>> getPersonPhoneNumbers(@PathVariable(name = "personId") Long id) {
        List<PhoneNumber> phoneNumbers = personService.findPersonPhoneNumbers(id);

        return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
    }

    @PostMapping(path = "/{personId}/phone")
    public ResponseEntity<String> addPhoneNumber(@PathVariable(name = "personId") Long id,
                                                      @RequestBody PhoneNumber phoneNumber) {
        Optional<Person> optionalPerson = personService.findPersonById(id);

        if (optionalPerson.isEmpty()) {
            logger.info("[PersonPhoneController] No person was found with the inputted ID.");
            throw new PersonNotFoundException("No person with that id was found");
        }

        Person person = optionalPerson.get();

        /**
         * TODO: If this does not work the create a method on Person to add it.
         * Even if this does work still add a method on the entity to addPhoneNumber(...).
         */
        person.getPhoneNumbers().add(phoneNumber);

        logger.info(
                String.format("[PersonPhoneController] the list of phone numbers after adding: %s",
                        person.getPhoneNumbers()));

        personService.updatePerson(id, person);

        return new ResponseEntity<>("New phone number added", HttpStatus.CREATED);
    }

}
