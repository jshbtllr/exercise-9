package com.exercise9.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import java.util.List;
import com.exercise9.core.model.Roles;
import com.exercise9.core.service.RoleCrudServiceImpl;

public class FileUploadController extends SimpleFormController{

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {

		ModelAndView modelAndView = new ModelAndView("uploadform");
		return modelAndView;
	}

	public FileUploadController() {
		setCommandClass();
		setCommandName("fileUpload");
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
		Long roleId = Long.parseLong(request.getParameter("roleId"));
		String roleCode = request.getParameter("roleCode");
		String roleName = request.getParameter("roleName");
		Roles role = new Roles(roleName, roleCode);
		role.setId(roleId);

		role = roleService.update(role);

		ModelAndView modelAndView = new ModelAndView("message");
		if(role.getRoleName() == " ") {
			modelAndView.addObject("message", "Role Code already exists<br/>Role update unsuccessful<br/> Role was not updated");
		} else {
			modelAndView.addObject("message", "Role successfully updated");
		}
		modelAndView.addObject("redirectView", "http://localhost:8080/roles");

		return modelAndView;
	}
}