package com.exercise9.app;

import com.exercise9.core.model.Roles;
import com.exercise9.core.model.Address;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.model.Employee;
import com.exercise9.core.model.Name;
import com.exercise9.core.dao.RoleDAO;
import com.exercise9.util.InputUtil;
import com.exercise9.core.service.EmployeeService;
import com.exercise9.core.service.EmployeeRoleService;
import com.exercise9.core.service.ContactInfoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
 		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    Integer success = new Integer(1);
		Set <ContactInfo> contacts = new HashSet <ContactInfo>();
		Set <Roles> role = new HashSet <Roles>();
		ContactInfo info = null;
		Address address = null;
		Name name = null;
		Employee employee = null;
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
	    String employmentStatus = request.getParameter("employed");
	    Boolean employed = Boolean.parseBoolean(request.getParameter("employed"));
	    String birth = request.getParameter("birthdate");
	    String hire = request.getParameter("hireDate");
	    String grade = request.getParameter("grade");
	    String infoType = request.getParameter("infoType");
	    String infoDetail = request.getParameter("infoDetail");	    
	    Float gradeWeightAverage = null;
	    Date birthdate = null;
	    Date hireDate = null;
	    List <String> addedRole = Arrays.asList(request.getParameterValues("roles"));

	    try {
	    	gradeWeightAverage = Float.parseFloat(grade);
	    } catch (NumberFormatException nfe) {
	    	success = 2;
	    }

	    if(success != 2) {
	    	if(!InputUtil.checkGrade(gradeWeightAverage)) {
	    		success = 2;
	    	}
	    }

	    if(employed) {
	    	if(InputUtil.checkDate(hire)) {
	    		hireDate = InputUtil.getDate(hire);
	    	} else {
	    		success = 3;
	    	}
	    } else {
	    	hireDate = InputUtil.getDate("31/12/9999");
	    }

	    if(InputUtil.checkDate(birth)) {
	    	birthdate = InputUtil.getDate(birth);
	    } else {
	    	success = 4;
	    }

	    info = new ContactInfo(infoType, infoDetail);
	    info = ContactInfoService.checkInfo(info);

	    if(info.getInfoType().equals(" ")) {
	    	success = 5;
	    }


	    if(success == 1) {
	    	name = new Name(firstName, lastName, middleName, suffix, title);
	    	address = new Address(streetNumber, barangay, city, country, zipcode);
	    	contacts.add(info);
	    	for(String add : addedRole) {
	    		Long id = Long.parseLong(add);
	    		Roles in = RoleDAO.get(Roles.class, id);
	    		role.add(in);
	    	}
	    	employee = new Employee(name, address, birthdate, gradeWeightAverage, hireDate, employed, 
					contacts, role);
	    }
	    
	    try { 
	        out.println("<html>");
	        out.println("<head>");      
	        out.println("<title>Add Employee</title>");    
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<center>");
	        if(success == 1) {
	        	EmployeeService.createEmployee(employee);
            	out.println("<h3>Employee Successfully updated</h3>");
            } else {
            	out.println("<h3>Employee not updated</h3>");
            	if(success == 2) {
            		out.println("<h3>Invalid GWA input</h3>");
            	} else if(success == 3) {
            		out.println("<h3>Invalid Hire Date input</h3>");
            	} else if(success == 4) {
            		out.println("<h3>Invalid Birthday input</h3>");
            	} else if (success == 5) {
            		out.println("<h3>Invalid Contact Info input</h3>");
            	}
            }
            out.println("<a href=/employee>Back to Employee Management</a>");
	        out.println("</center>");
        	out.println("</body>");
        	out.println("</html>");
    	} finally {       
        	out.close();
    	}		
 	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
 		response.sendRedirect("/notallowed");
	   } 	
}