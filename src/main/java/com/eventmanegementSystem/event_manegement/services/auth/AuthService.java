package com.eventmanegementSystem.event_manegement.services.auth;


import com.eventmanegementSystem.event_manegement.DTO.SignUpRequest;
import com.eventmanegementSystem.event_manegement.DTO.UserDto;
import com.eventmanegementSystem.event_manegement.Entity.User;

import java.util.List;

public interface AuthService {
    UserDto createdCustomer(SignUpRequest signuprequest);
    boolean hascustomerwithemail(String email);

    List<User> getAllUsers();

    List<UserDto> getAllStudents();




}
