package com.service;

import java.util.List;

import com.domain.Person;

public interface PersonService {
	List<Person> findAllPerson();
	Person findPerson(Long id);
	void deletePerson(Long id);
	void updatePerson(Person person);
	void savePerson(Person person);
}
