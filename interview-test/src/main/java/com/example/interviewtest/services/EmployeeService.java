package com.example.interviewtest.services;

import com.example.interviewtest.domain.Employee;
import com.example.interviewtest.exceptions.EtBadRequestException;
import com.example.interviewtest.exceptions.EtResourceNotFoundException;

import org.springframework.data.domain.Page;
import java.util.List;

public interface EmployeeService {

    Page<Employee> fetchAllEmployees(Integer page, Integer size);

    Employee fetchEmployeeById(Integer userId, Integer employeeId) throws EtResourceNotFoundException;

    Employee addEmployee(Integer userId, String jobTitle) throws EtBadRequestException;

    void updateEmployee(Integer userId, Integer employeeId, Employee employee) throws EtBadRequestException;

    void removeEmployeeWithAllTransactions(Integer userId, Integer employeeId) throws EtResourceNotFoundException;

}
