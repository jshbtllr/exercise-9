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

public class RoleController extends SimpleFormController{
	private Logger logger = Logger.getLogger(RoleController.class);
	private RoleCrudServiceImpl roleService;
	private static final Integer sortById = new Integer(1);
	private static final Integer sortByCode = new Integer(2);
	private static final Integer sortByName = new Integer(3);
	private static final Boolean ascending = true;
	private static final Boolean descending = false;

	public void setRoleService(RoleCrudServiceImpl roleService) {
		this.roleService = roleService;
	}

	public RoleController() {
		setCommandClass(Roles.class);
		setCommandName("roles");
	}

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {
		logger.info("Role showForm()");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		Integer sortType = null;
		Boolean orderType = null;

		if((sort == null) || (sort.equals("id"))) {
			sortType = sortById;
		} else if(sort.equals("code")) {
			sortType = sortByCode;
		} else if(sort.equals("name")) {
			sortType = sortByName;
		} 

		if((order == null) || (order.equals("ascending"))) {
			orderType = ascending;
		} else {
			orderType = descending;
		}

		ModelAndView modelAndView = new ModelAndView("roles");
		List <Roles> roleList = roleService.read(sortType, orderType);
		modelAndView.addObject("roles", roleList);

		return modelAndView;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
		logger.info("Role onSubmit()");
		Long roleId = Long.parseLong(request.getParameter("roleId"));
		Roles role = roleService.delete(roleId);

		ModelAndView modelAndView = new ModelAndView("roles");
		if(role.getRoleCode() == null) {
			modelAndView.addObject("message","Role still assigned to employee<br/>Role unsuccessfully deleted");
		} else {
			modelAndView.addObject("message","Role successfully deleted");
		}

		List <Roles> roleList = roleService.read(sortById, ascending);
		modelAndView.addObject("roles", roleList);

		return modelAndView;
	}
}