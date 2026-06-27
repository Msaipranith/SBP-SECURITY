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

import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for managing Employee (User) operations.
 * Protected by method-level security depending on roles (Admin/User).
 */
@Slf4j
@RestController
//@RequestMapping("/API/EMPLOYEE/")
public class ProController {

    @Autowired
    UsersService empService;

    /**
     * Saves a new employee. Requires ADMIN role.
     * @param empPojo the employee data to save
     * @return a success message
     */
    //admin role
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/saveEmp")
    public String saveEmp(@RequestBody UsersPojo empPojo) {
        log.info("Received request to save employee with username: {}", empPojo.getUsername());
        empService.saveEmp(empPojo);
        log.info("Successfully saved employee: {}", empPojo.getUsername());
        return "emp saved";
    }


    /**
     * A simple welcome endpoint to verify session/auth state. Requires ADMIN role.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/welcome")
    public String welcome(HttpServletRequest req) {
        log.info("Received welcome request. Session ID: {}", req.getSession().getId());
        return "welcome to spring security" + " " + req.getSession().getId();
    }

    /**
     * Fetches a list of all employees. Accessible by ADMIN and USER roles.
     * @return List of UsersPojo
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/employee/findAllEmp")
    public List<UsersPojo> fetchEmpList() {
        log.info("Received request to fetch all employees.");
        List<UsersPojo> op = empService.fetchEmpList();
        log.info("Fetched {} employees.", op.size());
        return op;
    }

    /**
     * Fetches a specific employee by ID using a PathVariable. Accessible by ADMIN and USER.
     * @param id the employee ID
     * @return UsersPojo
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/fetchEmpByIdByPV/{id}")
    public UsersPojo getEmployeeByIdByPV(@PathVariable int id) {
        log.info("Received request to fetch employee by ID (PathVariable): {}", id);
        UsersPojo op = empService.fetchEmpByIdByPV(id);
        log.info("Successfully fetched employee with ID: {}", id);
        return op;
    }

    /**
     * Fetches a specific employee by ID using a RequestParam. Accessible by ADMIN and USER.
     * @param userId the employee ID
     * @return UsersPojo
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/fetchEmpByIdByRP")
    public UsersPojo getEmployeeByIdByRP(@RequestParam(value = "userId", defaultValue = "0") int userId) {
        log.info("Received request to fetch employee by ID (RequestParam): {}", userId);
        UsersPojo result = empService.fetchEmpByIdByRP(userId);
        log.info("Successfully fetched employee with RequestParam ID: {}", userId);
        return result;
    }

    /**
     * Deletes an employee by their ID. Requires ADMIN role.
     * @param id the employee ID to delete
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("deleteEmpById/{id}")
    public void deleteEmpById(@PathVariable int id) {
        log.info("Received request to delete employee with ID: {}", id);
        empService.deleteById(id);
        log.info("Successfully deleted employee with ID: {}", id);
    }

    /**
     * Updates an employee's details by their ID. Requires ADMIN role.
     * @param empPojo the updated employee details
     * @param id the employee ID to update
     * @return a success message
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/updateEmpById/{id}")
    public String updateEmpById(@RequestBody UsersPojo empPojo, @PathVariable int id) {
        log.info("Received request to update employee with ID: {}", id);
        empService.updateEmpById(empPojo, id);
        log.info("Successfully updated employee with ID: {}", id);
        return "employee Details updated";
    }

}
