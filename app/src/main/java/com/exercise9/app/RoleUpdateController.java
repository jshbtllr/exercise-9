package com.exercise9.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;
import com.exercise9.core.model.Roles;
import com.exercise9.core.service.RoleCrudServiceImpl;

public class RoleUpdateController extends SimpleFormController{
	private static final Integer sortById = new Integer(1);
	private static final Boolean ascending = true;

	private RoleCrudServiceImpl roleService;

	public void setRoleService(RoleCrudServiceImpl roleService) {
		this.roleService = roleService;
	}

	public RoleUpdateController() {
		setCommandClass(Roles.class);
		setCommandName("roles");
	}

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {
		ModelAndView modelAndView = new ModelAndView("roleupdateform");
		Long roleId = Long.parseLong(request.getParameter("roleId"));
		Roles role = roleService.get(roleId);
		modelAndView.addObject("role", role);
		return modelAndView;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
		Long roleId = Long.parseLong(request.getParameter("roleId"));
		String roleCode = request.getParameter("roleCode");
		String roleName = request.getParameter("roleName");
		Roles role = new Roles(roleName, roleCode);
		role.setId(roleId);

		role = roleService.update(role);

		//RedirectView redirectView = new RedirectView("/roles");
		ModelAndView modelAndView = new ModelAndView("roles");
		//modelAndView.setView("/roles");
		if(role.getRoleName() == " ") {
			modelAndView.addObject("message", "Role Code already exists<br/>Role update unsuccessful<br/> Role was not updated");
		} else {
			modelAndView.addObject("message", "Role successfully updated");
		}

		List <Roles> roleList = roleService.read(sortById, ascending);
		modelAndView.addObject("roles", roleList);

		return modelAndView;
	}
}