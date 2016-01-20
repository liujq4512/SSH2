package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.dao.PersonDao;
import com.domain.Person;
import com.service.PersonService;

@Transactional
public class PersonServiceImpl implements PersonService{
	@Resource
	private PersonDao personDao;
	@Override
	public void deletePerson(Long id) {
		this.personDao.delete(id);
	}

	@Override
	public List<Person> findAllPerson() {
		return this.personDao.findAllPerson();
	}

	@Override
	public Person findPerson(Long id) {
		return this.personDao.findById(id);
	}

	@Override
	public void savePerson(Person person) {
		this.personDao.save(person);
		
	}

	@Override
	public void updatePerson(Person person) {
		this.personDao.update(person);
	}

}
