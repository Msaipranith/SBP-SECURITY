package com.service;

import java.util.List;

import com.dto.UsersPojo;

public interface UsersService {

	void saveEmp(UsersPojo empPojo);

	List<UsersPojo> fetchEmpList();

	UsersPojo fetchEmpByIdByPV(int id);



	UsersPojo fetchEmpByIdByRP(int id);

	void deleteById(int id);

	void updateEmpById(UsersPojo empPojo, int id);

	

}
