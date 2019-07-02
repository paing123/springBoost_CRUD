package com.paing.springboostcrud2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.stereotype.Service;

import com.paing.springboostcrud2.dao.EmployeeDao;
import com.paing.springboostcrud2.model.Employee;

@Service("employeeService")
public class EmployeeServiceImp implements EmployeeService{

	@Autowired
	private EmployeeDao emplyeeDao;
	
	@Override
	public List<Employee> findAll(){
		return emplyeeDao.findAll();
	}
	
	@Override
	public void save(Employee employee) {
		emplyeeDao.save(employee);
	}
	
	@Override
	public void delete(Integer id) {
		emplyeeDao.delete(id);
	}
	
	@Override
	public void update(Employee employee) {
		emplyeeDao.update(employee);
	}
	
	@Override
	public Employee findById(Integer id) {
		return emplyeeDao.findById(id);
	}
	
	public List<Employee> findByName(String name){
		return emplyeeDao.findByName(name);
	}
}
