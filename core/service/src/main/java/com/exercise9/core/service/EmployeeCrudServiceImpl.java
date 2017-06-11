package com.exercise9.core.service;
import com.exercise9.core.model.Roles;
import com.exercise9.core.model.Address;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.model.Employee;
import com.exercise9.core.model.Name;
import com.exercise9.util.InputUtil;
import com.exercise9.core.dao.RoleDAO;
import com.exercise9.core.dao.EmployeeDAO;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeCrudServiceImpl implements CrudServiceInterface <Employee> {

	private EmployeeDAO employeeDao;

	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Employee create(Employee employee) {
		return employeeDao.add(employee);
	}

	public List <Employee> read(Integer sortFunction, Boolean ascending) {
		List <Employee> list = employeeDao.showEmployees(sortFunction, ascending);

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
		employee = employeeDao.get(Employee.class, employeeId);
		return employeeDao.delete(employee);
	}

	public Employee update(Employee employee) {
		return employeeDao.update(employee);
	}
}

class gwaComparator implements Comparator <Employee> {
	public int compare(Employee a, Employee b) {
		return a.getGradeWeightAverage() < b.getGradeWeightAverage() ? -1 : a.getGradeWeightAverage() == b.getGradeWeightAverage() ? 0 : 1;
	}
}