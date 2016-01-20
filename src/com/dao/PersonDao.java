package com.dao;

import java.util.List;

import com.domain.Person;

public interface PersonDao {
	void save(Person person);
	void delete(Long id);
	void update(Person person);
	Person findById(Long id);
	List<Person> findAllPerson();
}
