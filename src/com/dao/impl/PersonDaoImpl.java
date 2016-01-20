package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import com.dao.PersonDao;
import com.domain.Person;

public class PersonDaoImpl implements PersonDao{
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void delete(Long id) {
		this.sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(Person.class, id));
	}

	@Override
	public Person findById(Long id) {
		return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
	}

	@Override
	public void save(Person person) {
		sessionFactory.getCurrentSession().persist(person);
		
	}

	@Override
	public void update(Person person) {
		sessionFactory.getCurrentSession().merge(person);
	}

	@Override
	public List<Person> findAllPerson() {
		return sessionFactory.getCurrentSession().createQuery("from Person").list();
	}

}
