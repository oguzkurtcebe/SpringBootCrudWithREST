package com.oguzkurtcebe.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzkurtcebe.crud.dao.PersonRepository;
import com.oguzkurtcebe.crud.exception.PersonNotFoundException;
import com.oguzkurtcebe.crud.model.Person;
@Transactional
@Service
public class RestServiceImpl implements RestService {
    @Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person findPerson(Long id) {
		Person person = personRepository.findById(id);
		if(person==null) throw new PersonNotFoundException("Not found");
		return person;
	}

	@Override
	public List<Person> findPersons() {
		List<Person> list = personRepository.findAll();
		
		return list;
	}

	@Override
	public List<Person> findPersons(String lastName) {
		List<Person> list = personRepository.findyLastName(lastName);
		return list;
	}

	@Override
	public void createPerson(Person person) {
		personRepository.create(person);

	}

	@Override
	public void updatePerson(Person person) {
		personRepository.update(person);

	}

	@Override
	public void deletePerson(Long id) {
		personRepository.delete(id);

	}

}
