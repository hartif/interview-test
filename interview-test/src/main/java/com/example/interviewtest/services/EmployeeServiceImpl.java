package com.example.interviewtest.services;

import com.example.interviewtest.domain.Employee;
import com.example.interviewtest.exceptions.EtBadRequestException;
import com.example.interviewtest.exceptions.EtResourceNotFoundException;
import com.example.interviewtest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> fetchAllEmployees(Integer page, Integer size) {
        List<Employee> employees = employeeRepository.findAll(page, size);
        int total = employeeRepository.countUsers();
        return new PageImpl<>(employees, PageRequest.of(page, size), total);
    }

    @Override
    public Employee fetchEmployeeById(Integer userId, Integer employeeId) throws EtResourceNotFoundException {
        return employeeRepository.findById(userId, employeeId);
    }

    @Override
    public Employee addEmployee(Integer userId, String jobTitle) throws EtBadRequestException {
        int employeeId = employeeRepository.create(userId, jobTitle);
        return employeeRepository.findById(userId, employeeId);
    }

    @Override
    public void updateEmployee(Integer userId, Integer employeeId, Employee employee) throws EtBadRequestException {
        employeeRepository.update(userId, employeeId, employee);
    }

    @Override
    public void removeEmployeeWithAllTransactions(Integer userId, Integer employeeId) throws EtResourceNotFoundException {
        this.fetchEmployeeById(userId, employeeId);
        employeeRepository.removeById(userId, employeeId);
    }
}
