package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import com.dto.UsersPojo;
import com.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/API/EMPLOYEE/")
public class ProController {

    @Autowired
    UsersService empService;

    //admin role
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/saveEmp")
    public String saveEmp(@RequestBody UsersPojo empPojo) {
        empService.saveEmp(empPojo);
        return "emp saved";
    }


    //admin role
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/welcome")
    public String welcome(HttpServletRequest req) {
        return "welcome to spring security" + " " + req.getSession().getId();
    }

    //user role
    //admin role
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/employee/findAllEmp")
    public List<UsersPojo> fetchEmpList() {

        List<UsersPojo> op = empService.fetchEmpList();
        System.out.println("AFTER EXECUTION ");
        return op;
    }

    // by pathvariable
    // user role
    //admin role
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/fetchEmpByIdByPV/{id}")
    public UsersPojo getEmployeeByIdByPV(@PathVariable int id) {
        UsersPojo op = empService.fetchEmpByIdByPV(id);
        return op;
    }

    // by request param
    // user role
    //admin role
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/fetchEmpByIdByRP")
    public UsersPojo getEmployeeByIdByRP(@RequestParam(value = "userId", defaultValue = "0") int userId) {
        return empService.fetchEmpByIdByRP(userId);
    }

    //admin role
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("deleteEmpById/{id}")
    public void deleteEmpById(@PathVariable int id) {
        empService.deleteById(id);

    }

    //admin role
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/updateEmpById/{id}")
    public String updateEmpById(@RequestBody UsersPojo empPojo, @PathVariable int id) {
        empService.updateEmpById(empPojo, id);
        return "employee Details updated";
    }

}
