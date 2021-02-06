package com.projectbox.jpaentityrelationships.services;

import com.projectbox.jpaentityrelationships.entities.Person;
import com.projectbox.jpaentityrelationships.entities.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public List<Person> findAllPeople();
    public Optional<Person> findPersonById(Long id);
    public Person savePerson(Person person);
    public Person updatePerson(Long id, Person person);
    public void deletePerson(Long id);

    public List<PhoneNumber> findPersonPhoneNumbers(Long id);

}
