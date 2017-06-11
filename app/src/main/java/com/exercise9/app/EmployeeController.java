package com.exercise9.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import java.util.List;
import com.exercise9.core.model.Employee;
import com.exercise9.core.model.Name;
import com.exercise9.core.model.Address;
import com.exercise9.core.model.Roles;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.service.EmployeeCrudServiceImpl;

public class EmployeeController extends SimpleFormController{
	private EmployeeCrudServiceImpl employeeService;
	private static final Integer sortByName = new Integer(1);
	private static final Integer sortByGrade = new Integer(2);
	private static final Integer sortByHire = new Integer(3);
	private static final Integer sortById = new Integer(4);
	private Boolean ascending = true;

	public void setEmployeeService(EmployeeCrudServiceImpl employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeController() {
		setCommandClass(Employee.class);
		setCommandName("employee");
	}

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {
		ModelAndView modelAndView = new ModelAndView("home");
		List <Employee> employeeList = employeeService.read(sortById, ascending);
		modelAndView.addObject("employees",employeeList);

		return modelAndView;
	}
}