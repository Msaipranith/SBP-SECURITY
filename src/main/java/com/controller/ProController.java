package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UsersPojo;
import com.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/API/EMPLOYEE/")
public class ProController {

	@Autowired
	UsersService empService;

	@PostMapping("/saveEmp")
	public String saveEmp(@RequestBody UsersPojo empPojo) {
		empService.saveEmp(empPojo);
		return "emp saved";
	}

	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest req) {
		CsrfToken token = (CsrfToken) req.getAttribute("_csrf");

		return token;
	}

	@GetMapping("/welcome")
	public String welcome(HttpServletRequest req) {
		return "welcome to spring security" + " " + req.getSession().getId();
	}

	@GetMapping("/employee/findAllEmp")
	public List<UsersPojo> fetchEmpList() {

		List<UsersPojo> op = empService.fetchEmpList();
		return op;
	}

	// by pathvariable
	@GetMapping("/fetchEmpByIdByPV/{id}")
	public UsersPojo getEmployeeByIdByPV(@PathVariable int id) {
		UsersPojo op = empService.fetchEmpByIdByPV(id);
		return op;
	}

	// by request param
	@GetMapping("/fetchEmpByIdByRP")
	public UsersPojo getEmployeeByIdByRP(@RequestParam(value = "userId", defaultValue = "0") int userId) {
		UsersPojo op = empService.fetchEmpByIdByRP(userId);
		return op;
	}

	@DeleteMapping("deleteEmpById/{id}")
	public void deleteEmpById(@PathVariable int id) {
		empService.deleteById(id);

	}

	@PutMapping("/updateEmpById/{id}")
	public String updateEmpById(@RequestBody UsersPojo empPojo, @PathVariable int id) {
		empService.updateEmpById(empPojo, id);
		return "employee Details updated";

	}

}
