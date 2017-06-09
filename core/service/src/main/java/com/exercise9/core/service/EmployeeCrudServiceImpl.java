package com.exercise9.core.service;
import com.exercise9.core.model.Roles;
import com.exercise9.core.model.Address;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.model.Employee;
import com.exercise9.core.model.Name;
import com.exercise9.util.InputUtil;
import com.exercise9.core.dao.RoleDAO;
import com.exercise9.core.dao.EmployeeDAO;
import com.exercise9.core.service.EmployeeRoleService;
import com.exercise9.core.service.ContactInfoService;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeCrudServiceImpl implements CrudServiceInterface <Employee> {
	public Employee create(Employee employee) {
		return EmployeeDAO.add(employee);
	}

	public List <Employee> read(Integer sortFunction, Boolean ascending) {
		List <Employee> list = EmployeeDAO.showEmployees(sortFunction, orderFunction);
		Set <Roles> roles;
		Set <ContactInfo> contacts;

		if(!list.isEmpty()) {
			if(sortFunction == 2) {
				Collections.sort(list, new gwaComparator());

				if(ascending == false) {
					Collections.sort(list, Collections.reverseOrder(new gwaComparator()));						
				}
			}
		}

		return list;
	}	

	public Employee delete(Long employeeId) {
		Employee employee = new Employee();
		employee = EmployeeDAO.get(Employee.class, employeeId);
		return EmployeeDAO.delete(employee);
	}

	public Employee update(Employee employee) {
		return EmployeeDAO.update(employee);
	}
}

class gwaComparator implements Comparator <Employee> {
	public int compare(Employee a, Employee b) {
		return a.getGradeWeightAverage() < b.getGradeWeightAverage() ? -1 : a.getGradeWeightAverage() == b.getGradeWeightAverage() ? 0 : 1;
	}
}