package com.example.interviewtest.services;

import com.example.interviewtest.domain.User;
import com.example.interviewtest.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
