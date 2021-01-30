package com.projectbox.jpaentityrelationships.repositories;

import com.projectbox.jpaentityrelationships.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
