package com.paing.springboostcrud2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.paing.springboostcrud2.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImp implements EmployeeDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Employee> findAll() {

		String sql = "select * from employee";
		List<Employee> employee = jdbcTemplate.query(sql, new UserMapper());
		return employee;
	}

	class UserMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
			Employee employee = new Employee();
			employee.setId(rs.getInt("id"));
			employee.setName(rs.getString("name"));
			employee.setCity(rs.getString("city"));
			employee.setAge(rs.getInt("age"));
			return employee;
		}
	}
	
	public void save(Employee employee) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO employee(name,age,city) " + "values(?,?,?)", new String[] { "id" });
				ps.setString(1, employee.getName());
				ps.setInt(2, employee.getAge());
				ps.setString(3, employee.getCity());
				return ps;
			}
		});
	}
	
	public void delete(Integer id) {
		jdbcTemplate.update("delete from employee where id=?",id);
	}
	
	public void update(Employee employee) {
		jdbcTemplate.update("update employee set name=?, age=?, city=? where id=?",employee.getName(),
				employee.getAge(),employee.getCity(),employee.getId());
	}
	
	public Employee findById(Integer id){
		String sql = "select * from employee where id=" + id;
		List<Employee> employees = jdbcTemplate.query(sql, new UserMapper());

		if (employees.isEmpty()) {
			return null;
		} else {
			return employees.get(0);//return as an object
		}
	}
	
	public List<Employee> findByName(String name){
		String sql = "select * from employee where name LIKE \'%"+ name+"%\'";
		List<Employee> employees = jdbcTemplate.query(sql, new UserMapper());
		return employees;	
	}

}
