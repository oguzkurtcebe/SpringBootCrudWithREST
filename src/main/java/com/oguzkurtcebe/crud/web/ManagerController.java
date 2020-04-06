package com.oguzkurtcebe.crud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oguzkurtcebe.crud.dao.jpa.ManagerRepository;
import com.oguzkurtcebe.crud.model.Manager;

@RestController
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private ManagerRepository managerRepository;

	public ManagerController(ManagerRepository managerRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {

		this.managerRepository = managerRepository;
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
	}

	@RequestMapping(value = "sign-up", method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestBody Manager manager) {

		try {
			manager.setPassword(bcryptPasswordEncoder.encode(manager.getPassword()));
			managerRepository.save(manager);
			return ResponseEntity.ok(200);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@RequestMapping(value="sign-In",method=RequestMethod.POST)
	public ResponseEntity<?> signIn(@RequestBody Manager manager) {
		
		return null;

}
	}
