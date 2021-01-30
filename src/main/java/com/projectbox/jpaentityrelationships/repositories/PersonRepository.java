package com.projectbox.jpaentityrelationships.repositories;

import com.projectbox.jpaentityrelationships.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
