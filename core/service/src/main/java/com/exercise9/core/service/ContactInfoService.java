package com.exercise9.core.service;
import com.exercise9.core.model.ContactInfo;
import com.exercise9.core.model.Employee;
import com.exercise9.util.InputUtil;
import com.exercise9.core.dao.EmployeeDAO;
import java.util.Set;
import java.util.Iterator;
import org.apache.commons.validator.routines.EmailValidator;

public class ContactInfoService {
	public static Integer addContactInfo(Long employeeId, ContactInfo addInfo) {
		Employee employee = null;
		Set <ContactInfo> contacts;
		Integer contactCount = null;

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		contacts = employee.getContactInfo();	
		contactCount = contacts.size();

		contacts = addContactSet(contacts, employee, addInfo);

		employee.setContactInfo(contacts);
		EmployeeDAO.update(employee);
		
		if(contacts.size() == contactCount) {
			return 0;
		} else {
			return 1;
		}		
	}


	public static Set <ContactInfo> addContactSet(Set <ContactInfo> contacts, Employee employee, ContactInfo addInfo) {	
		Boolean exist = false;

		addInfo = checkInfo(addInfo);

		if(addInfo.getInfoType().equals(" ")) {
			return contacts;
		} else {

			if(contacts.isEmpty()) {
				contacts.add(addInfo); 			
			} else {
				for(ContactInfo list : contacts) {
					if(list.getInfoDetail().equals(addInfo.getInfoDetail())) {
						exist = true;
					}
				}
				if(!exist) {
					contacts.add(addInfo);		
				}
			}
		}
		return contacts;
	}

	public static ContactInfo checkInfo(ContactInfo info) {
		if(info.getInfoType().equals("email")) {
			if(!EmailValidator.getInstance().isValid(info.getInfoDetail())) {
				info.setInfoType(" ");
			}
		} else if(info.getInfoType().equals("telephone")) {
			if(!info.getInfoDetail().matches("^[1-9]{1}\\d{6}")) {
				info.setInfoType(" ");
			}
		} else if(info.getInfoType().equals("cellphone")) {
			if(!info.getInfoDetail().matches("^09\\d{9}")) {
				info.setInfoType(" ");
			}			
		}

		return info;		
	}

	public static Integer removeContactInfo(Long employeeId, ContactInfo deleteContact) {
		Employee employee;
		Boolean exist = false;
		Set <ContactInfo> contacts = null;
		Iterator <ContactInfo> iterator = null;

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		contacts = employee.getContactInfo();

		iterator = contacts.iterator();
		while(iterator.hasNext()) {
			if(deleteContact.getInfoDetail().equals(iterator.next().getInfoDetail())) {
				exist = true;
				iterator.remove();
			}
		}

		employee.setContactInfo(contacts);
		EmployeeDAO.update(employee);

		if(!exist) {
			return 0;
		}	
		return 1;
	}

	public static Integer updateContactInfo(Long employeeId, ContactInfo updateContact, String newInfoDetail) {
		Employee employee = null;
		Boolean exist = false;
		ContactInfo newInfo = new ContactInfo(updateContact.getInfoType(), newInfoDetail);
		Set <ContactInfo> contacts = null;
		Iterator <ContactInfo> iterator = null;		

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		contacts = employee.getContactInfo();
		
		for(ContactInfo list : contacts) {
			if(updateContact.getInfoDetail().equals(list.getInfoDetail())) {
				newInfo = checkInfo(newInfo);
				if(newInfo.getInfoType().equals(" ")) {
					return 0;
				} else {					
					list.setInfoDetail(newInfoDetail);
				}
			}
		}

		employee.setContactInfo(contacts);
		EmployeeDAO.update(employee);	

		return 1;
	}
}