package com.oguzkurtcebe.crud.service;

import java.util.List;

import com.oguzkurtcebe.crud.model.Person;

public interface RestService {

	Person findPerson(Long id);
	List<Person>findPersons();
	List<Person>findPersons(String lastName);
	void createPerson(Person person);
	void updatePerson(Person person);
	void deletePerson(Long id);
}
