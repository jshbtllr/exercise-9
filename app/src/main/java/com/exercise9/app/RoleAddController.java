package com.exercise9.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import java.util.List;
import com.exercise9.core.model.Roles;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.service.RoleCrudServiceImpl;
import org.apache.log4j.Logger;

public class RoleAddController extends SimpleFormController{
	private Logger logger = Logger.getLogger(RoleAddController.class);
	private static final Integer sortById = new Integer(1);
	private static final Boolean ascending = true;

	private RoleCrudServiceImpl roleService;

	public void setRoleService(RoleCrudServiceImpl roleService) {
		this.roleService = roleService;
	}

	public RoleAddController() {
		setCommandClass(Roles.class);
		setCommandName("roles");
	}

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {
		logger.info("Add Role showForm()");
		ModelAndView modelAndView = new ModelAndView("roleaddform");
		return modelAndView;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
		logger.info("Add Role onSubmit()");
		String roleCode = request.getParameter("roleCode");
		String roleName = request.getParameter("roleName");
		Roles role = new Roles(roleName, roleCode);

		role = roleService.create(role);

		ModelAndView modelAndView = new ModelAndView("message");
		if(role.getRoleName() == " ") {
			modelAndView.addObject("message", "Role Code already exists<br/>Role was not added");
		} else {
			modelAndView.addObject("message", "Role successfully added");
		}

		modelAndView.addObject("redirectView", "http://localhost:8080/roles");
		return modelAndView;
	}
}