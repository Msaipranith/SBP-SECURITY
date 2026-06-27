package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dto.UsersPojo;
import com.entity.Users;
import com.repo.UsersRepo;
import com.service.UsersService;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation handling business logic for Employee/User operations.
 */
@Slf4j
@Component
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepo empRepo;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void saveEmp(UsersPojo empPojo) {
		log.info("Processing request to save new employee: {}", empPojo.getUsername());
		Users emp = new Users();
		BeanUtils.copyProperties(empPojo, emp);
		emp.setPassword(passwordEncoder.encode(empPojo.getPassword()));
		log.debug("Encoded password for user: {}", empPojo.getUsername());
		empRepo.save(emp);
		log.info("Saved employee to database: {}", empPojo.getUsername());
	}

	@Override
	public List<UsersPojo> fetchEmpList() {
		log.info("Fetching all employees from database.");
		List<Users> op = empRepo.findAll();
		log.debug("Found {} employees in database.", op.size());

		return op.stream().map(users -> {
			UsersPojo empPojo = new UsersPojo();
			BeanUtils.copyProperties(users, empPojo);
			return empPojo;
		}).collect(Collectors.toList());
	}

	@Override
	public UsersPojo fetchEmpByIdByPV(int id) {
		log.info("Fetching employee by PathVariable ID: {}", id);
		return empRepo.findById(id).map(data -> {
			UsersPojo empPojo = new UsersPojo();
			BeanUtils.copyProperties(data, empPojo);
			log.debug("Found employee details for ID: {}", id);
			return empPojo;
		}).orElseThrow(() -> {
			log.error("Employee with ID {} not found", id);
			return new RuntimeException("Employee not found");
		});
	}

	@Override
	public UsersPojo fetchEmpByIdByRP(int id) {
		log.info("Fetching employee by RequestParam ID: {}", id);
		return empRepo.findById(id).map(data -> {
			UsersPojo empPojo = new UsersPojo();
			BeanUtils.copyProperties(data, empPojo);
			log.debug("Found employee details for ID: {}", id);
			return empPojo;
		}).orElseGet(() -> {
			log.warn("Employee with ID {} not found", id);
			return new UsersPojo();
		});
	}

	@Override
	public void deleteById(int id) {
		log.info("Deleting employee with ID: {}", id);
		empRepo.deleteById(id);
		log.debug("Successfully deleted employee from database.");
	}

	@Override
	public void updateEmpById(UsersPojo empPojo, int id) {
		log.info("Updating employee with ID: {}", id);
		empRepo.findById(id).map(emp -> {
			emp.setId(id);
			emp.setPassword(passwordEncoder.encode(empPojo.getPassword()));
			emp.setUsername(empPojo.getUsername());
			if (empPojo.getRole() != null) {
				emp.setRole(empPojo.getRole());
			}
			log.debug("Applied updates for employee ID: {}", id);
			empRepo.save(emp);
			log.info("Saved updated employee with ID: {} to database.", id);
			return emp;
		}).orElseGet(() -> {
			log.warn("Employee with ID {} not found for update.", id);
			return null;
		});
	}

}
