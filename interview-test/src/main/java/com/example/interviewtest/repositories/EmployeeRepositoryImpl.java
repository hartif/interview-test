package com.example.interviewtest.repositories;

import com.example.interviewtest.domain.Employee;
import com.example.interviewtest.exceptions.EtBadRequestException;
import com.example.interviewtest.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final String SQL_FIND_ALL = "SELECT E.EMPLOYEE_ID, E.USER_ID, E.JOB_TITLE, U.FIRST_NAME " +
             "FROM ET_EMPLOYEES E INNER JOIN ET_USERS U ON U.USER_ID = E.USER_ID " +
             "ORDER BY U.FIRST_NAME ASC";
    private static final String SQL_FIND_BY_ID = "SELECT E.EMPLOYEE_ID, E.USER_ID, E.JOB_TITLE, U.FIRST_NAME " +
            "FROM ET_EMPLOYEES E INNER JOIN ET_USERS U ON U.USER_ID = E.USER_ID " +
            "WHERE E.USER_ID = ? AND E.EMPLOYEE_ID = ?";
    private static final String SQL_CREATE = "INSERT INTO ET_EMPLOYEES (EMPLOYEE_ID, USER_ID, JOB_TITLE) VALUES(NEXTVAL('et_employees_seq'), ?, ?)";
    private static final String SQL_UPDATE = "UPDATE ET_EMPLOYEES SET JOB_TITLE = ? " +
            "WHERE USER_ID = ? AND EMPLOYEE_ID = ?";
    private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM ET_EMPLOYEES WHERE USER_ID = ? AND EMPLOYEE_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findAll(Integer page, Integer size) {
        int offset = (page - 1) * size;
        String sql = SQL_FIND_ALL + " LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{size, offset}, employeeRowMapper);
    }

    @Override
    public Integer countUsers() {
        String sql = "SELECT COUNT(*) FROM ET_EMPLOYEES";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Employee findById(Integer userId, Integer employeeId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, employeeId}, employeeRowMapper);
        }catch (Exception e) {
            throw new EtResourceNotFoundException("Employee not found");
        }
    }

    @Override
    public Integer create(Integer userId, String jobTitle) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, jobTitle);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("EMPLOYEE_ID");
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request: " + e.getMessage());
        }
    }

    @Override
    public void update(Integer userId, Integer employeeId, Employee employee) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{employee.getJobTitle(), userId, employeeId});
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request: " + e.getMessage());
        }
    }

    @Override
    public void removeById(Integer userId, Integer employeeId) {
        jdbcTemplate.update(SQL_DELETE_EMPLOYEE, new Object[]{userId, employeeId});
    }

    private RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        return new Employee(
            rs.getInt("EMPLOYEE_ID"),
            rs.getInt("USER_ID"),
            rs.getString("JOB_TITLE"),
            rs.getString("FIRST_NAME")
        );
    };
}
