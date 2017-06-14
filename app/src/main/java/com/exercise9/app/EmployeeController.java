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
import org.apache.log4j.Logger;

public class EmployeeController extends SimpleFormController{
	private Logger logger = Logger.getLogger(EmployeeController.class);
	private EmployeeCrudServiceImpl employeeService;
	private static final Integer sortByName = new Integer(1);
	private static final Integer sortByGrade = new Integer(2);
	private static final Integer sortByHire = new Integer(3);
	private static final Integer sortById = new Integer(4);
	private static final Boolean ascending = true;
	private static final Boolean descending = false;

	public void setEmployeeService(EmployeeCrudServiceImpl employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeController() {
		setCommandClass(Employee.class);
		setCommandName("employee");
	}

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {
		logger.info("Employee Controller showForm()");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		Integer sortType = null;
		Boolean orderType = null;

		if(sort == null) {
			sortType = sortById;
		} else {
			if(sort.equals("lastname")) {
				sortType = sortByName;
			} else if(sort.equals("gwa")) {
				sortType = sortByGrade;
			} else if(sort.equals("hiredate")) {
				sortType = sortByHire;
			}
		}

		if((order == null) || (order.equals("ascending"))) {
			orderType = ascending;
		} else {
			orderType = descending;
		}

		ModelAndView modelAndView = new ModelAndView("home");
		List <Employee> employeeList = employeeService.read(sortType, orderType);
		modelAndView.addObject("employees",employeeList);

		return modelAndView;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
		logger.info("Employee Controller onSubmit()");
		Long employeeId = Long.parseLong(request.getParameter("employeeId"));
		Employee employee = employeeService.delete(employeeId);

		ModelAndView modelAndView = new ModelAndView("home");
		List <Employee> employeeList = employeeService.read(sortById, ascending);
		modelAndView.addObject("employees", employeeList);

		return modelAndView;
	}
}