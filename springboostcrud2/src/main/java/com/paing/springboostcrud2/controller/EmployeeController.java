package com.paing.springboostcrud2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.paing.springboostcrud2.model.Employee;
import com.paing.springboostcrud2.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = {"/","/employeeList"}, method = RequestMethod.GET)
	public ModelAndView listEmployee(){
		ModelAndView mav = new ModelAndView("index");
		List<Employee> employees = employeeService.findAll();
		mav.addObject("employees",employees);
		Employee employee = new Employee();
		mav.addObject("employee", employee);
		return mav;
	}
	
	@RequestMapping(value = { "/addEmployee" }, method = RequestMethod.GET)
	public String showEmployeePage(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee";
	}

	
	@RequestMapping(value = {"/addEmployee"}, method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute("employee")Employee employee){
		ModelAndView mav = new ModelAndView("redirect:/employeeList");
		employeeService.save(employee);
		return mav;
	}
	
	@RequestMapping(value = { "/searchEmployee" }, method = RequestMethod.GET)
	public String SearchEmployee(@ModelAttribute("employee") Employee employee, Model model) {
		String name = employee.getName();

		if (name != null) {
			List<Employee> employees = employeeService.findByName(name);
			model.addAttribute("employees", employees);
			return "index";
		}
		else {
			return "index";
		}

	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteEmployee(@ModelAttribute("id") Integer id) {
		ModelAndView mav = new ModelAndView("redirect:/employeeList");
		employeeService.delete(id);
		return mav;
	}
	
	
	@GetMapping("/update/{id}")
	public ModelAndView showUpdateEmployee(@ModelAttribute("id") int id) {
		Employee employee = employeeService.findById(id);
		ModelAndView mav = new ModelAndView("employeeEdit");
		mav.addObject("updateEmployee", employee);
		return mav;
	}

	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ModelAndView UpdateEmployee(@ModelAttribute("updateEmployee") Employee employee) {
		employeeService.update(employee);
		ModelAndView mav = new ModelAndView("redirect:/employeeList");
		return mav;
	}
}
