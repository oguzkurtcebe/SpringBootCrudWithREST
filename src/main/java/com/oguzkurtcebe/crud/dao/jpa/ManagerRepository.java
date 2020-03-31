package com.oguzkurtcebe.crud.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.oguzkurtcebe.crud.model.Manager;

@Repository("managerRepository")
public interface ManagerRepository extends JpaRepository<Manager, Long> {

	Manager findByUsername(String username);
}
