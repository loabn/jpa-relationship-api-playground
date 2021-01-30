package com.projectbox.jpaentityrelationships.services;

import com.projectbox.jpaentityrelationships.entities.Person;
import com.projectbox.jpaentityrelationships.exceptions.PersonNotFoundException;
import com.projectbox.jpaentityrelationships.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAllPeople() {
        return StreamSupport
                .stream(personRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        Optional<Person> optionalPerson = personRepository.findById((id));

        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException("No person with that id found");
        }

        Person updatedPerson = optionalPerson.get();

        updatedPerson.setName(person.getName());
        updatedPerson.getAddress().setAddress(person.getAddress().getAddress());
        updatedPerson.getAddress().setPostcode(person.getAddress().getPostcode());

        personRepository.save(updatedPerson);

        return updatedPerson;
    }

    @Override
    public void deletePerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException("No Person with that id found");
        }

        Person person = optionalPerson.get();

        personRepository.delete(person);
    }

}
