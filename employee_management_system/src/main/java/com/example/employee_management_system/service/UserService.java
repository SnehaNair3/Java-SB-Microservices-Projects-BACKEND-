package com.example.employee_management_system.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.employee_management_system.dto.UserRegistrationDto;
import com.example.employee_management_system.model.User;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);
}
