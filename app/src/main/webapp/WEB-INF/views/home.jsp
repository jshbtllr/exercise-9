<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix = "spring" uri = "http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
	<head>
		<title><spring:message code="employee.management"/></title>
	</head>
	<body>
		<h3><spring:message code="employee.management"/></h3>
		<h4><spring:message code="current.employee"/></h4>
		<table width=100%>
			<td width=20% align="left">
				<a href="/employee/add"> <spring:message code="employee.add"/> </a>
			</td>
			<td width=20% align="left">
				Language: <a href="?language=en"> <spring:message code="english"/> </a> | <a href="?language=de"> <spring:message code="german"/> </a>
			</td>
			<td width=20% align="left">
				<a href="/roles"> <spring:message code="role.title"/> </a>
			</td>
			<td width=40% align="right">
				<form action="employee" method="GET">
					<spring:message code="sortby"/>
					<select name="sort">
						<option value="lastname"> <spring:message code="lastname"/> </option>
						<option value="gwa"> <spring:message code="grade"/> </option>
						<option value="hiredate"> <spring:message code="hiredate"/> </option>
					</select>
					<select name="order">
						<option value="ascending"> <spring:message code="ascending"/> </option>
						<option value="descending"> <spring:message code="descending"/> </option>
					</select>
					<input type="submit" value="<spring:message code="sort"/>"/>
				</form>
			</td>
		</table>
		<div style="clear:both;"></div><br/>
		<table border="1" align="left">
			<thead>
				<tr>
					<th>ID</th>
					<th width=15%>Name</th>
					<th width=15%><spring:message code="address"/></th>
					<th><spring:message code="birthday"/></th>
					<th><spring:message code="grade"/></th>
					<th><spring:message code="employment.status"/></th>
					<th><spring:message code="hiredate"/></th>
					<th width=20%><spring:message code="contact.info"/></th>
					<th width=15%><spring:message code="role.head"/></th>
					<th><spring:message code="action"/></th>
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
							<td align="center"><spring:message code="yes"/></td>
							<td align="center"><fmt:formatDate pattern="yyyy/MM/dd" value="${employee.hireDate}"/></td>
						</core:when>
						<core:otherwise>
							<td align="center"><spring:message code="no"/></td>
							<td align="center">N/A</td>
						</core:otherwise>
					</core:choose>
					<core:choose>
						<core:when test="${empty employee.contactInfo}">
							<td align="center"><spring:message code="no.contacts"/></td>
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
							<td align="center"><spring:message code="no.roles"/></td>
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
							<input type="submit" value="<spring:message code="delete"/>"/>
						</form>
						<form action="/employee/update" method="GET">
							<input type="hidden" name="employeeId" value="${employee.id}"/>
							<input type="submit" value="<spring:message code="update"/>"/>
						</form>
					</td>
				</tr>
				</core:forEach>
			</tbody>
		</table>
	</body>
</html>