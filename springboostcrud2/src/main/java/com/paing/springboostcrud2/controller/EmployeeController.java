package com.paing.springboostcrud2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.paing.springboostcrud2.model.Employee;
import com.paing.springboostcrud2.service.EmployeeService;

@Controller
public class EmployeeController implements WebMvcConfigurer{

	@Autowired
	private EmployeeService employeeService;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

	@RequestMapping(value = { "/", "/employeeList" }, method = RequestMethod.GET)
	public ModelAndView listEmployee() {
		ModelAndView mav = new ModelAndView("index");
		Employee employee = new Employee();
		mav.addObject("employee", employee);
		List<Employee> employees = employeeService.findAll();
		mav.addObject("employees", employees);
		return mav;
	}

	@RequestMapping(value = { "/addEmployee" }, method = RequestMethod.GET)
	public String showEmployeePage(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee";
	}

	@RequestMapping(value = { "/addEmployee" }, method = RequestMethod.POST)
	public ModelAndView addEmployee(@Validated @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("redirect:/employeeList");
		
		if (bindingResult.hasErrors()) {
			ModelAndView mav1 = new ModelAndView("employee");
			return mav1;
		}
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
		} else {
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

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO Auto-generated method stub
		return null;
	}
}
