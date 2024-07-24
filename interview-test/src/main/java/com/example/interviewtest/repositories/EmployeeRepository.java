package com.example.interviewtest.repositories;

import com.example.interviewtest.domain.Employee;
import com.example.interviewtest.exceptions.EtBadRequestException;
import com.example.interviewtest.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll(Integer page, Integer size);

    Integer countUsers();

    Employee findById(Integer userId, Integer employeeId) throws EtResourceNotFoundException;

    Integer create(Integer userId, String jobTitle) throws EtBadRequestException;

    void update(Integer userId, Integer employeeId, Employee employee) throws EtBadRequestException;

    void removeById(Integer userId, Integer employeeId);

}
