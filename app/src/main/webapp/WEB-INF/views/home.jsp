<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
	<head>
		<title>Employee Management</title>
	</head>
	<body>
		<h3>Employee Management</h3>
		<h4>Current Employees</h4>
		<table width=100%>
			<td width=30% align="left">
				<a href="/employee/add"> Add Employee </a>
			</td>
			<td width=30% align="center">
				<a href="/roles"> Role Management </a>
			</td>
			<td width=40% align="right">
				<form action="employee" method="GET">
					Sort by: 
					<select name="sort">
						<option value="lastname"> Last Name </option>
						<option value="gwa"> Grade </option>
						<option value="hiredate"> Hire Date </option>
					</select>
					<select name="order">
						<option value="ascending"> Ascending </option>
						<option value="descending"> Descending </option>
					</select>
					<input type="submit" value="sort"/>
				</form>
			</td>
		</table>
		<div style="clear:both;"></div><br/>
		<table border="1" align="left">
			<thead>
				<tr>
					<th>ID</th>
					<th width=15%>Full Name</th>
					<th width=15%>Address</th>
					<th>Birthdate</th>
					<th>Grade</th>
					<th>Employed</th>
					<th>Hire Date</th>
					<th width=20%>Contact Info</th>
					<th width=15%>Roles</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<core:forEach var="employee" items="${employees}">
				<tr>
					<td align="center">${employee.id}</td>
					<td align="center">${employee.name.title} ${employee.name.firstName} ${employee.name.middleName} ${employee.name.lastName} ${employee.name.suffix}</td>
					<td align="center">${employee.address.streetNumber} ${employee.address.barangay} ${employee.address.city} ${employee.address.country} ${employee.address.zipcode}</td>
					<td align="center"><fmt:formatDate pattern="yyyy/MM/dd" value="${employee.birthday}"/></td>
					<td align="center">${employee.gradeWeightAverage}</td>
					<core:choose>
						<core:when test="${employee.employed}">
							<td align="center">Yes</td>
							<td align="center"><fmt:formatDate pattern="yyyy/MM/dd" value="${employee.hireDate}"/></td>
						</core:when>
						<core:otherwise>
							<td align="center">No</td>
							<td align="center">N/A</td>
						</core:otherwise>
					</core:choose>
					<core:choose>
						<core:when test="${empty employee.contactInfo}">
							<td align="center">No Contacts Available</td>
						</core:when>
						<core:otherwise>
							<td align="center">
								<core:forEach var="contact" items="${employee.contactInfo}">
									${contact.infoType}: ${contact.infoDetail} <br/>
								</core:forEach>
							</td>
						</core:otherwise>
					</core:choose>
					<core:choose>
						<core:when test="${empty employee.role}">
							<td align="center">No Roles Available</td>
						</core:when>
						<core:otherwise>
							<td align="center">
								<core:forEach var="emprole" items="${employee.role}">
									${emprole.roleName}<br/>
								</core:forEach>
							</td>
						</core:otherwise>
					</core:choose>
					<td align="center">
						<form action="/employee" method="POST">
							<input type="hidden" name="employeeId" value="${employee.id}"/>
							<input type="submit" value="delete"/>
						</form>
						<form action="/employee/update" method="GET">
							<input type="hidden" name="employeeId" value="${employee.id}"/>
							<input type="submit" value="update"/>
						</form>
					</td>
				</tr>
				</core:forEach>
			</tbody>
		</table>
	</body>
</html>