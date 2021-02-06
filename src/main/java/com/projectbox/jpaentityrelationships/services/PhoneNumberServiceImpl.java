package com.projectbox.jpaentityrelationships.services;

import com.projectbox.jpaentityrelationships.entities.Person;
import com.projectbox.jpaentityrelationships.entities.PhoneNumber;
import com.projectbox.jpaentityrelationships.exceptions.PhoneNumberNotFoundException;
import com.projectbox.jpaentityrelationships.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Override
    public Optional<PhoneNumber> findPhoneNumberById(Long id) {
        return phoneNumberRepository.findById(id);
    }

    @Override
    public PhoneNumber updatePhoneNumber(Long id, PhoneNumber phoneNumber) {
        Optional<PhoneNumber> optionalPhoneNumber = phoneNumberRepository.findById(id);

        if (optionalPhoneNumber.isEmpty()) {
            throw new PhoneNumberNotFoundException("No Phone Number with that id found");
        }

        PhoneNumber updatedPhoneNumber = optionalPhoneNumber.get();
        updatedPhoneNumber.setPhoneNumberType(phoneNumber.getPhoneNumberType());
        updatedPhoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());

        phoneNumberRepository.save(updatedPhoneNumber);

        return updatedPhoneNumber;
    }

    @Override
    public void deletePhoneNumber(Long id) {
        Optional<PhoneNumber> optionalPhoneNumber = phoneNumberRepository.findById(id);

        if (optionalPhoneNumber.isEmpty()) {
            throw new PhoneNumberNotFoundException("No Phone Number with that id found");
        }

        PhoneNumber phoneNumber = optionalPhoneNumber.get();

        phoneNumberRepository.delete(phoneNumber);
    }
}
