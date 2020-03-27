package com.oguzkurtcebe.crud.dao;

import java.util.List;

import com.oguzkurtcebe.crud.model.Person;

public interface PersonRepository {

	Person findById(Long id);
    List<Person> findAll();
    List<Person>findyLastName(String lastName);
    void create(Person person);
    Person update(Person person);
    void delete(Long id);
}
