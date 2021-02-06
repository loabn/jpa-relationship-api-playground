package com.projectbox.jpaentityrelationships.services;

import com.projectbox.jpaentityrelationships.entities.PhoneNumber;

import java.util.Optional;

public interface PhoneNumberService {

    public Optional<PhoneNumber> findPhoneNumberById(Long id);
    public PhoneNumber updatePhoneNumber(Long id, PhoneNumber phoneNumber);
    public void deletePhoneNumber(Long id);

}
