package com.exercise9.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.Arrays;
import com.exercise9.core.model.Employee;
import com.exercise9.core.model.Name;
import com.exercise9.core.model.Address;
import com.exercise9.core.model.Roles;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.service.EmployeeCrudServiceImpl;
import com.exercise9.core.service.RoleCrudServiceImpl;
import com.exercise9.core.service.EmployeeRoleServiceImpl;
import com.exercise9.core.service.ContactInfoServiceImpl;
import com.exercise9.util.InputUtil;
import org.apache.log4j.Logger;

public class EmployeeAddController extends SimpleFormController{
	private Logger logger = Logger.getLogger(EmployeeAddController.class);
	private EmployeeCrudServiceImpl employeeService;
	private RoleCrudServiceImpl roleService;
	private EmployeeRoleServiceImpl employeeRoleService;
	private ContactInfoServiceImpl contactInfoService;
	private InputUtil inputUtil;
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

	public void setContactInfoService(ContactInfoServiceImpl contactInfoService) {
		this.contactInfoService = contactInfoService;
	}

	public void setInputUtil(InputUtil inputUtil) {
		this.inputUtil = inputUtil;
	}	

	public EmployeeAddController() {
		setCommandClass(Employee.class);
		setCommandName("employee");
	}

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {
		logger.info("Add Controller showForm()");
		List <Roles> roleList = roleService.read(sortById, ascending);
		ModelAndView modelAndView = new ModelAndView("employeeaddform");
		modelAndView.addObject("roleList", roleList);

		return modelAndView;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
		logger.info("Add Controller onSubmit()");
		Set <ContactInfo> contacts = new HashSet <ContactInfo>();
		Set <Roles> role = new HashSet <Roles>();
		Address address = null;
		Name name = null;
		Employee employee = null;
		ContactInfo info = null;		
		String title = request.getParameter("title");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String suffix = request.getParameter("suffix");
		String streetNumber = request.getParameter("streetNumber");
		String barangay = request.getParameter("barangay");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String zipcode = request.getParameter("zipcode");
		Boolean employed = Boolean.parseBoolean(request.getParameter("employed"));
		String birth = request.getParameter("birthday");
		String hire = request.getParameter("hireDate");
	    Float gradeWeightAverage = null;
	    Date birthdate = null;
	    Date hireDate = null;
		String infoType = request.getParameter("infoType");
	    String infoDetail = request.getParameter("infoDetail");	   
	    List <String> addedRole = Arrays.asList(request.getParameterValues("roles"));
		Boolean gradeFlag = false;
		Boolean hireFlag = false;
		Boolean successFlag = true;
		Boolean birthFlag = false;
		Boolean contactFlag = false;		
		StringBuilder message = new StringBuilder();    

	    try {
	    	gradeWeightAverage = Float.parseFloat(request.getParameter("gwa"));
	    } catch (NumberFormatException nfe) {
	    	gradeFlag = true;
	    	successFlag = false;
	    }

	    if(gradeFlag != true) {
	    	if(!inputUtil.checkGrade(gradeWeightAverage)) {
	    		gradeFlag = true;
	    		successFlag = false;
	    	}
	    }

	    if(employed) {
	    	if(inputUtil.checkDate(hire)) {
	    		hireDate = inputUtil.getDate(hire);
	    	} else {
	    		hireFlag = true;
	    		successFlag = false;
	    	}
	    } else {
	    	hireDate = inputUtil.getDate("31/12/9999");
	    }

	    if(inputUtil.checkDate(birth)) {
	    	birthdate = inputUtil.getDate(birth);
	    } else {
	    	birthFlag = true;
	    	successFlag = false;
	    }

	    info = new ContactInfo(infoType, infoDetail);
	    if(!info.getInfoDetail().equals("")) {
	    	info = contactInfoService.checkInfo(info);
		    if(info.getInfoType().equals(" ")) {
		    	contactFlag = true;
		    	successFlag = false;
		    } else {
		 	   contacts.add(info);    	
			}
	    }

	    if(successFlag == true) {
	    	name = new Name(firstName, lastName, middleName, suffix, title);
	    	address = new Address(streetNumber, barangay, city, country, zipcode);
	    	for(String add : addedRole) {
	    		Long roleId = Long.parseLong(add);
	    		Roles in = roleService.get(roleId);
	    		role.add(in);
	    	}
	    	employee = new Employee(name, address, birthdate, gradeWeightAverage, hireDate, employed, 
					contacts, role);

	    	employeeService.create(employee);
	    	message.append("Employee Successfully added");
	    } else {
	    	if(gradeFlag) {
	    		message.append("Invalid GWA input<br/>");
	    	}
	    	if (hireFlag) {
	    		message.append("Invalid Hire Date<br/>");
	    	}
	    	if(birthFlag) {
	    		message.append("Invalid Birthdate<br/>");
	    	}
	    	if(contactFlag) {
	    		message.append("Invalid Contact Information<br/>");
	    	}

	    	message.append("Employee not added");
	    }

		ModelAndView modelAndView = new ModelAndView("message");
		modelAndView.addObject("message", message);
		modelAndView.addObject("redirectView", "http://localhost:8080/employee");

		return modelAndView;
	}
}