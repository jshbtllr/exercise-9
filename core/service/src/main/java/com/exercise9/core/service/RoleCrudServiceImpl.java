package com.exercise9.core.service;
import com.exercise9.core.model.Roles;
import com.exercise9.util.InputUtil;
import com.exercise9.core.dao.RoleDAO;
import java.util.List;

public class RoleCrudServiceImpl implements CrudServiceInterface <Roles> {

	private RoleDAO roleDao;

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public Roles create(Roles role) {
		if (roleDao.checkDuplicateRole(role, 1)) {
			role.setRoleName(" ");
			return role;
		} else {
			role = roleDao.add(role);
			return role;
		}
	}

	public List <Roles> read(Integer sortRule, Boolean ascending) {
		List <Roles> list = roleDao.showRoles(sortRule, ascending);
		return list;
	}	

	public Roles update(Roles role) {
		if(roleDao.checkDuplicateRole(role, 2)) {
			role.setRoleName(" ");
			return role;
		} else {
			role = roleDao.update(role);
			return role;
		}
	}		

	public Roles delete(Long roleId) {
		Roles role = new Roles();
		role.setId(roleId);
		if (!(roleDao.checkDuplicateRole(role, 3))) {
			role = roleDao.get(Roles.class, roleId);
			role = roleDao.delete(role);
			return role;
		} else {
			return role;
		}
	}

	public Roles get(Long roleId) {
		Roles role = new Roles();
		role = roleDao.get(Roles.class, roleId);
		return role;
	}
}	