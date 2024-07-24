package com.example.interviewtest.resources;

import com.example.interviewtest.domain.Employee;
import com.example.interviewtest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    public Page<Employee> getAllEmployees(HttpServletRequest request, 
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size) {
        return employeeService.fetchAllEmployees(page, size);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(HttpServletRequest request,
                                                    @PathVariable("employeeId") Integer employeeId) {
        int userId = (Integer) request.getAttribute("userId");
        Employee employee = employeeService.fetchEmployeeById(userId, employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(HttpServletRequest request,
                                                @RequestBody Map<String, Object> employeeMap) {
        int userId = (Integer) request.getAttribute("userId");
        String jobTitle = (String) employeeMap.get("jobTitle");
        Employee employee = employeeService.addEmployee(userId, jobTitle);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Map<String, Boolean>> updateEmployee(HttpServletRequest request,
                                                               @PathVariable("employeeId") Integer employeeId,
                                                               @RequestBody Employee employee) {
        int userId = (Integer) request.getAttribute("userId");
        employeeService.updateEmployee(userId, employeeId, employee);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(HttpServletRequest request,
                                                               @PathVariable("employeeId") Integer employeeId) {
        int userId = (Integer) request.getAttribute("userId");
        employeeService.removeEmployeeWithAllTransactions(userId, employeeId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
