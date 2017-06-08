package com.exercise9.app;

import com.exercise9.core.model.Roles;
import com.exercise9.core.model.Address;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.model.Employee;
import com.exercise9.core.model.Name;
import com.exercise9.util.InputUtil;
import com.exercise9.core.service.EmployeeService;
import com.exercise9.core.service.RoleService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
 		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    String sortOption = null;
	    String orderOption = null;
	    Integer sort = null;
	    Integer order = null;
	    List <Roles> allRoles = null;	    

	    try { 
		    out.println("<html>");
		    out.println("<head>");      
		    out.println("<title>Role Management</title>");    
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<h3>Role Management</h3><h4>Current Roles</h4>");	        
			out.println("<table width=100%>");
			out.println("<td width=30% align=\"left\">");
			out.println("<a href=/employee> Back to Employee<br/>Management </a></td>");			
			out.println("<td width=30% align=\"left\">");
			out.println("<a href=roles/add> Add roles </a></td>");			
			out.println("<td width=40% align=\"right\">");
			out.println("<form action=\"roles\" method=\"GET\">");
			out.println("Sort by: <select name=\"sort\">");
			out.println("<option value=\"id\"> Role ID </option>");
			out.println("<option value=\"code\"> Role Code </option>");
			out.println("<option value=\"name\"> Role Name </option></select>");
			out.println("<select name=\"order\">");
			out.println("<option value=\"ascending\"> Ascending </option>");
			out.println("<option value=\"descending\"> Descending </option></select>");
			out.println("<input type=\"submit\" value=\"sort\"/>");
			out.println("</form></table>");

			sortOption = request.getParameter("sort");
			orderOption = request.getParameter("order");
			if(sortOption == null) {
				sort = 1;
			} else if(sortOption.equals("id")) {
				sort = 1;
			} else if(sortOption.equals("code")) {
				sort = 2;
			} else if(sortOption.equals("name")) {
				sort = 3;
			}

			if(orderOption == null) {
				order = 1;
			} else if(orderOption.equals("ascending")) {
				order = 1;
			} else if(orderOption.equals("descending")) {
				order = 2;
			}			

			allRoles = RoleService.listRoles(sort, order);

			out.println("<div style=\"clear:both;\"></div><br/>");
			out.println("<table border =\"1\" align=\"left\"><thead><tr>");
			out.println("<th>ID</th><th>Role Code</th>");
			out.println("<th>Role Name</th><th>Action</th>");
			out.println("</tr></thead></tbody>");	

			for(Roles role : allRoles) {
				out.println("<tr><td align=\"center\">" + role.getId() + "</td>");
				out.println("<td align=\"center\">" + role.getRoleCode() + "</td>");
				out.println("<td align=\"center\">" + role.getRoleName() + "</td>");

				out.println("<td align=\"center\">");
				out.println("<form action=\"roles\" method=\"POST\">");
				out.println("<input type=\"hidden\" name=\"roleId\" value=\"" + role.getId() + "\"/>");			
				out.println("<input type=\"submit\" value=\"Delete\"/>");
				out.println("</form><form action=\"roles/update\" method=\"GET\">");
				out.println("<input type=\"hidden\" name=\"roleId\" value=\"" + role.getId() + "\"/>");			
				out.println("<input type=\"submit\" value=\"Update\"/>");
				out.println("</form></td></tr>");				
			}

			out.println("</tbody></table>");
	        out.println("</body>");
	        out.println("</html>");			

    	} finally {       
        	out.close();
    	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
 		PrintWriter out = response.getWriter();
 		response.setContentType("text/html;charset=UTF-8");
 		String sortOption = request.getParameter("sort");
		String orderOption = request.getParameter("order");
		String input = null;
	    Long roleId = null;
	    Integer success = null;

		input = request.getParameter("roleId");
		roleId = Long.parseLong(input);
		success = RoleService.removeRoles(roleId);   
	    
	    try { 
	        out.println("<html>");
	        out.println("<head>");      
	        out.println("<title>Role Deletion</title>");    
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<center>");
	        if(success == 1) {
            	out.println("<h1>Role Successfully deleted</h1><br/><br/>");
            } else {
            	out.println("<h1>Role still associated with an employee<br/>Role not deleted</h1><br/><br/>");
            }
            out.println("<a href=roles>Back to Role Management</a>");
	        out.println("</center>");
        	out.println("</body>");
        	out.println("</html>");
    	} finally {       
        	out.close();
    	}		
 	}        
}