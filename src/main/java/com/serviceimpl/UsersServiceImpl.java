package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.dto.UsersPojo;
import com.entity.Users;
import com.repo.UsersRepo;
import com.service.UsersService;

@Component
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepo empRepo;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Override
	public void saveEmp(UsersPojo empPojo) {
		// TODO Auto-generated method stub

		Users emp = new Users();
		BeanUtils.copyProperties(empPojo, emp);
		// System.out.println(empPojo);
//		System.out.println();

		emp.setPassword(encoder.encode(empPojo.getPassword()));
		System.out.println(emp);
		empRepo.save(emp);

	}

	@Override
	public List<UsersPojo> fetchEmpList() {
		// Fetch all Users entities
		List<Users> op = empRepo.findAll();

		// Create a list to hold UsersPojo objects
		List<UsersPojo> empPojoList = new ArrayList<>();

		// Iterate through the Users list and copy properties to UsersPojo objects
		for (Users Users : op) {
			// Create a new UsersPojo object
			UsersPojo empPojo = new UsersPojo();

			// Copy properties from Users to UsersPojo
			BeanUtils.copyProperties(Users, empPojo);

			// Add the UsersPojo to the list
			empPojoList.add(empPojo);
		}

		// Return the list of UsersPojo objects
		return empPojoList;
	}

	@Override
	public UsersPojo fetchEmpByIdByPV(int id) {
		// TODO Auto-generated method stub
		UsersPojo empPojo = new UsersPojo();
		Users data = empRepo.findById(id).get();
		BeanUtils.copyProperties(data, empPojo);
		return empPojo;
	}

	@Override
	public UsersPojo fetchEmpByIdByRP(int id) {
		UsersPojo empPojo = new UsersPojo();
		Optional<Users> dataOptional = empRepo.findById(id);
		if (dataOptional.isPresent()) {
			Users data = dataOptional.get();
			BeanUtils.copyProperties(data, empPojo);
		}
		return empPojo;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		empRepo.deleteById(id);

	}

	@Override
	public void updateEmpById(UsersPojo empPojo, int id) {
		// TODO Auto-generated method stub
		Users emp = empRepo.findById(id).get();
		if (emp != null) {

			emp.setId(id);
			emp.setPassword(empPojo.getPassword());
			emp.setUsername(empPojo.getUsername());

			// emp.setAddress(empPojo.getAddress());

		}
		empRepo.save(emp);

	}

}
