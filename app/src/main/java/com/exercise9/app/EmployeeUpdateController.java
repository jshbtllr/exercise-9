package com.exercise9.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import java.util.List;
import java.util.Set;
import com.exercise9.core.model.Employee;
import com.exercise9.core.model.Name;
import com.exercise9.core.model.Address;
import com.exercise9.core.model.Roles;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.service.EmployeeCrudServiceImpl;
import com.exercise9.core.service.RoleCrudServiceImpl;
import com.exercise9.core.service.EmployeeRoleServiceImpl;

public class EmployeeUpdateController extends SimpleFormController{
	private EmployeeCrudServiceImpl employeeService;
	private RoleCrudServiceImpl roleService;
	private EmployeeRoleServiceImpl employeeRoleService;
	private static final Integer sortById = new Integer(1);
	private static final Boolean ascending = true;

	public void setEmployeeService(EmployeeCrudServiceImpl employeeService) {
		this.employeeService = employeeService;
	}

	public void setRoleService(RoleCrudServiceImpl roleService) {
		this.roleService = roleService;
	}

	public void setEmployeeRoleService(EmployeeRoleServiceImpl employeeRoleService) {
		this.employeeRoleService = employeeRoleService;
	}

	public EmployeeUpdateController() {
		setCommandClass(Employee.class);
		setCommandName("employee");
	}

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {
		Long employeeId = Long.parseLong(request.getParameter("employeeId"));
		Employee employee = employeeService.get(employeeId);
		Set <Roles> currentRoles = employeeRoleService.getCurrentRoles(employeeId);
		List <Roles> roleList = roleService.read(sortById, ascending);

		ModelAndView modelAndView = new ModelAndView("employeeupdateform");
		modelAndView.addObject("employee", employee);
		modelAndView.addObject("currentRoles", currentRoles);
		modelAndView.addObject("roleList", roleList);

		return modelAndView;
	}

/*	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
		Long employeeId = Long.parseLong(request.getParameter("employeeId"));
		Employee employee = employeeService.delete(employeeId);

		ModelAndView modelAndView = new ModelAndView("home");
		List <Employee> employeeList = employeeService.read(sortById, ascending);
		modelAndView.addObject("employees", employeeList);

		return modelAndView;
	}*/
}