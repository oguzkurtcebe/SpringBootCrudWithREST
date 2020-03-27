package com.oguzkurtcebe.crud.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.oguzkurtcebe.crud.dao.PersonRepository;
import com.oguzkurtcebe.crud.model.Person;
@Repository
@Transactional
public class PersonRepositoryJpaImlp implements PersonRepository {

	@PersistenceContext
	 private EntityManager entityManager;
	
	@Override
	public Person findById(Long id) {
	  return entityManager.find(Person.class, id);
	}

	@Override
	public List<Person> findAll() {
		return entityManager.createQuery("from Person",Person.class).getResultList();
	}

	@Override
	public List<Person> findyLastName(String lastName) {
		return entityManager.createQuery("from Person where lastName = :lastName",Person.class).setParameter("lastName", lastName)
		.getResultList();

	}

	@Override
	public void create(Person person) {
		entityManager.persist(person);

	}

	@Override
	public Person update(Person person) {
		return entityManager.merge(person);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(Person.class, id));

	}

}
