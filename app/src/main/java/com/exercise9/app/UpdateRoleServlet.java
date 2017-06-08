package com.exercise9.app;

import com.exercise9.core.model.Roles;
import com.exercise9.core.service.RoleService;
import com.exercise9.core.dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateRoleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
 		response.setContentType("text/html;charset=UTF-8");
	    Boolean stopExecuting = false;
	    PrintWriter out = response.getWriter();
	    String input = request.getParameter("roleId");
	    Long roleId = null;
	    Roles role = null;
	    try {
	    	roleId = Long.parseLong(input);
	    } catch (NumberFormatException nfe) {
	    	stopExecuting = true;
	    	response.sendRedirect("/notallowed");
	    }

	   	try {
	    	role = RoleDAO.get(Roles.class, roleId);
	    } catch (IndexOutOfBoundsException iobe) {
	    	stopExecuting = true;
	    	response.sendRedirect("/notfound");
	    }	 

	    if(!stopExecuting) {	            
		    try { 
		        out.println("<html>");
		        out.println("<head>");      
		        out.println("<title>Role Update</title>");    
		        out.println("</head>");
		        out.println("<h3>Role Update</h3>");
		        out.println("<body>");
		        out.println("<form action=\"/roles/update\" method=\"POST\"/>");
		        out.println("Role Code: <input type=\"text\" name=\"roleCode\" value=\"" + role.getRoleCode() + "\" maxlength=255/><br/>");
		        out.println("Role Name: <input type=\"text\" name=\"roleName\" value=\"" + role.getRoleName() + "\" maxlength=255/><br/>");
		        out.println("<input type=\"hidden\" name=\"roleId\" value=\"" + role.getId() + "\"/>");
		        out.println("<input type=\"submit\" value=\"Update Role\"/>");
	        	out.println("</body>");
	        	out.println("</html>");
	    	} finally {       
	        	out.close();
	    	}
	    }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
 		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    String roleCode = request.getParameter("roleCode");
	    String roleName = request.getParameter("roleName");
	    String input = request.getParameter("roleId");
	    Long roleId = Long.parseLong(input);
	    Roles role = new Roles(roleName, roleCode);
	    role.setId(roleId);
	    Integer success = null;
	    
	    try { 
	    	success = RoleService.updateRoles(role);
	        out.println("<html>");
	        out.println("<head>");      
	        out.println("<title>Role Update</title>");    
	        out.println("</head>");
	        out.println("<h3>Role Update</h3>");
	        out.println("<body>");
	        out.println("<center>");
	        if (success == 1) {
	        	out.println("<h4>Role successfully updated</h4>");
	        } else {
	        	out.println("<h4>Role Code already exists</h4><h5>Role not updated</h5>");
	        }

	        out.println("<a href=/roles>Back to Role Management</a>");
	        out.println("</center>");	        
        	out.println("</body>");
        	out.println("</html>");
    	} finally {       
        	out.close();
    	}
	}
}








