package com.projectbox.jpaentityrelationships.web;

import com.projectbox.jpaentityrelationships.entities.PhoneNumber;
import com.projectbox.jpaentityrelationships.exceptions.PersonNotFoundException;
import com.projectbox.jpaentityrelationships.exceptions.PhoneNumberNotFoundException;
import com.projectbox.jpaentityrelationships.services.PhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/phone")
public class PhoneNumberController {

    private final Logger logger = LoggerFactory.getLogger(PhoneNumberController.class);
    private final PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PhoneNumber> getPhoneNumber(@PathVariable Long id) {
        Optional<PhoneNumber> optionalPhoneNumber = phoneNumberService.findPhoneNumberById(id);

        if (optionalPhoneNumber.isEmpty()) {
            logger.info("[PhoneNumberController] No phone number was found with the inputted ID.");
            throw new PhoneNumberNotFoundException("No phone number with that id was found");
        }

        PhoneNumber phoneNumber = optionalPhoneNumber.get();

        return new ResponseEntity<>(phoneNumber, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PhoneNumber> updatePhoneNumber(@PathVariable Long id, @RequestBody PhoneNumber phoneNumber) {
        PhoneNumber updatedPhoneNumber = phoneNumberService.updatePhoneNumber(id, phoneNumber);

        logger.info("[PhoneNumberController] Updating a phone number.");

        return new ResponseEntity<>(updatedPhoneNumber, HttpStatus.OK);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletePhoneNumber(@PathVariable Long id) {
        phoneNumberService.deletePhoneNumber(id);

        logger.info(String.format("[PhoneNumberController] Phone Number with ID: %d, successfully deleted.", id));

        return new ResponseEntity<>("Phone Number has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}
