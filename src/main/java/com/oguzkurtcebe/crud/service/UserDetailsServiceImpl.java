package com.oguzkurtcebe.crud.service;

import com.oguzkurtcebe.crud.dao.jpa.ManagerRepository;

import com.oguzkurtcebe.crud.model.Manager;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ManagerRepository managerRepository;

    public UserDetailsServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerRepository.findByUsername(username);
        if (manager == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(manager.getUsername(), manager.getPassword(), emptyList());
    }
    

}

