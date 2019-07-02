package com.paing.springboostcrud2.dao;

import java.util.List;

import com.paing.springboostcrud2.model.Employee;

public interface EmployeeDao {
	
	List<Employee> findAll();
	
	void save(Employee employee);
	
	void delete(Integer id);
	
	void update(Employee employee);
	
	Employee findById(Integer id);
	
	List<Employee> findByName(String name);
}