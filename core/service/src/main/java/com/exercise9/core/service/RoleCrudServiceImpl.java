package com.exercise9.core.service;
import com.exercise9.core.model.Roles;
import com.exercise9.util.InputUtil;
import com.exercise9.core.dao.RoleDAO;
import java.util.List;

public class RoleCrudServiceImpl implements CrudeServiceInterface <Roles> {
	public Roles create(Roles role) {
		if (RoleDAO.checkDuplicateRole(role, 1)) {
			return role;
		} else {
			role = RoleDAO.add(role);
			return role;
		}
	}

	public List <Roles> read(Integer sortRule, Boolean ascending) {
		List <Roles> list = RoleDAO.showRoles(sortRule, ascending);
		return list;
	}	

	public Roles update(Roles role) {
		if(RoleDAO.checkDuplicateRole(role, 2)) {
			role.setRoleName(" ");
			return role;
		} else {
			role = RoleDAO.update(role);
			return role;
		}
	}		

	public Roles delete(Long roleId) {
		Roles role = new Roles();
		role.setId(roleId);
		if (!(RoleDAO.checkDuplicateRole(role, 3))) {
			role = RoleDAO.get(Roles.class, roleId);
			role = RoleDAO.delete(role);
			return role;
		} else {
			return role;
		}
	}
}	