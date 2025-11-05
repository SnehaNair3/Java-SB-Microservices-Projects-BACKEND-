package com.example.employee_management_system.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.employee_management_system.model.Employee;

public interface EmployeeService {
	void saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(long id);

	void deleteEmployeeById(long id);

	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
