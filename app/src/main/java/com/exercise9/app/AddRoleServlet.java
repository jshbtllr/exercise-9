package com.exercise9.app;

import com.exercise9.core.model.Roles;
import com.exercise9.core.service.RoleService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddRoleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
 		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	            
	    try { 
	        out.println("<html>");
	        out.println("<head>");      
	        out.println("<title>Add Role</title>");    
	        out.println("</head>");
	        out.println("<h3>Add Role</h3>");
	        out.println("<body>");
	        out.println("<form action=\"/roles/add\" method=\"POST\"/>");
	        out.println("Role Code: <input type=\"text\" name=\"roleCode\" maxlength=255/><br/>");
	        out.println("Role Name: <input type=\"text\" name=\"roleName\" maxlength=255/><br/>");
	        out.println("<input type=\"submit\" value=\"Add Role\"/>");
        	out.println("</body>");
        	out.println("</html>");
    	} finally {       
        	out.close();
    	}
    }    	

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
 		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    String roleCode = request.getParameter("roleCode");
	    String roleName = request.getParameter("roleName");
	    Roles role = new Roles(roleName, roleCode);
	    Integer success = null;
	            
	    try { 
	    	success = RoleService.addRoles(role);
	        out.println("<html>");
	        out.println("<head>");      
	        out.println("<title>Add Role</title>");    
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<center>");
	        if (success == 1) {
	        	out.println("<h3>Role successfully added</h3>");
	        } else {
	        	out.println("<h3>Role Code already exists</h3><h4>Role was not added</h4>");
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