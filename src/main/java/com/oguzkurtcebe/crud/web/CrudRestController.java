package com.oguzkurtcebe.crud.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oguzkurtcebe.crud.exception.PersonNotFoundException;
import com.oguzkurtcebe.crud.model.Person;
import com.oguzkurtcebe.crud.service.RestService;

@RestController
@RequestMapping("/rest")
public class CrudRestController {
	@Autowired
	private RestService restService;


	@RequestMapping(method=RequestMethod.PUT,value="/person/{id}")
	public ResponseEntity<?>updatePerson(@PathVariable("id") Long id,@RequestBody Person person){
		try {
			person.setId(id);
			restService.updatePerson(person);
			return ResponseEntity.ok(200);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@RequestMapping(value="/person/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?>updatePerson(@PathVariable("id") Long id){
		try {
			restService.deletePerson(id);;
			return ResponseEntity.ok(200);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/person")
	public ResponseEntity<?>createPerson(@RequestBody Person person){
		try {
			restService.createPerson(person);
			return ResponseEntity.ok(200);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> allpersons() {
		List<Person> persons = restService.findPersons();
		return ResponseEntity.ok(persons);
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPersons(@PathVariable("id") Long id) {
		Person person = restService.findPerson(id);
		return ResponseEntity.ok(person);
	}

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getpersons(@RequestParam("ln") String lastName) {
      
	try {
		List<Person> list = restService.findPersons(lastName);
		return ResponseEntity.ok(list);
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	}
}
