package com.exercise9.core.service;
import com.exercise9.core.model.Roles;
import com.exercise9.core.model.Employee;
import com.exercise9.util.InputUtil;
import com.exercise9.core.dao.RoleDAO;
import com.exercise9.core.dao.EmployeeDAO;
import com.exercise9.core.service.RoleService;
import java.util.Set;
import java.util.Iterator;

public class EmployeeRoleService {
	public static Integer addRemoveEmployeeRoles(Integer option, Long employeeId, Long roleId) {			/*Option 1 add, Option 2 remove*/
		Employee employee = null;
		Set <Roles> employeeRoles;
		Integer roleCount = null;

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		employeeRoles = employee.getRole();	
		roleCount = employeeRoles.size();

		if(option == 1) {
			employeeRoles = addRoleSet(employeeRoles, roleId);
			if(roleCount.equals(employeeRoles.size())) {
				return 0;
			}
		} else {
			employeeRoles = removeRoleSet(employeeRoles, roleId);
		}

		employee.setRole(employeeRoles);
		EmployeeDAO.update(employee);

		return 1;
	}

	public static Set <Roles> addRoleSet(Set <Roles> roles, Long roleId) {
		Roles newRole = new Roles(" ", " ");
		Boolean exist = false;

		newRole.setId(roleId);

		if(!(RoleDAO.checkDuplicateRole(newRole, 4))) {
			return roles;
		}
		newRole = RoleDAO.get(Roles.class, roleId);
		
		if(roles.isEmpty()) {
			roles.add(newRole);
		} else {
			for(Roles list : roles) {
				if(newRole.getId().equals(list.getId())) {
					exist = true;
				}
			}
			if(!exist) {
				roles.add(newRole);
			}
		}
		return roles;
	}

	public static Set <Roles> removeRoleSet(Set <Roles> roles, Long roleId) {
		Roles deleteRole = new Roles(" ", " ");
		Iterator <Roles> iterator = null;
		Boolean exist = false;

		deleteRole = RoleDAO.get(Roles.class, roleId);
		
		iterator = roles.iterator();
		while(iterator.hasNext()) {
			if(deleteRole.getId().equals(iterator.next().getId())) {
				exist = true;
				iterator.remove();
			}
		}

		return roles;
	}	
}