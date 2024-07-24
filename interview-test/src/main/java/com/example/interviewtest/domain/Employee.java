package com.example.interviewtest.domain;

public class Employee {

    private Integer employeeId;
    private Integer userId;
    private String jobTitle;
    private String firstName;

    public Employee(Integer employeeId, Integer userId, String jobTitle, String firstName) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.jobTitle = jobTitle;
        this.firstName = firstName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
