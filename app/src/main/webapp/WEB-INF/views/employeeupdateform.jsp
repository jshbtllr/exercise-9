<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
	<head>
		<title>Employee Management</title>
	</head>
	<body>
		<h3>Update Employee</h3>
		<a href="/employee"> Back to Employee <br/> Management </a><br/>
		<form action="employee/update" method="GET">
			<table align="left" cellpadding="8" width=100%>
				<tr>
					<td> Name </td>
				</tr>
				<tr>
					<td align="center" width=20%> 
						<input type="text" name="title" value="${employee.name.title}" maxlength="255"/>
					</td>
					<td align="center" width=20%>
						<input type="text" name="firstName" value="${employee.name.firstName}" maxlength="255" required/>
					</td>
					<td align="center" width=20%>
						<input type="text" name="middleName" value="${employee.name.middleName}" maxlength="255" required/>
					</td>
					<td align="center" width=20%>
						<input type="text" name="lastName" value="${employee.name.lastName}" maxlength="255" required/>
					</td>
					<td align="center" width=20%>
						<input type="text" name="suffix" value="${employee.name.suffix}" maxlength="255"/>
					</td>
				</tr>
				<tr>
					<td align="center" width=20%> Title </td>
					<td align="center" width=20%> First Name </td>
					<td align="center" width=20%> Middle Name </td>
					<td align="center" width=20%> Last Name </td>
					<td align="center" width=20%> Suffix </td>
				</tr>								
				<tr>
					<td> Address </td>
				</tr>
				<tr>
					<td align="center" width=20%> 
						<input type="text" name="streetNumber" value="${employee.address.streetNumber}" maxlength="255" required/>
					</td>
					<td align="center" width=20%>
						<input type="text" name="barangay" value="${employee.address.barangay}" maxlength="255" required/>
					</td>
					<td align="center" width=20%>
						<input type="text" name="city" value="${employee.address.city}" maxlength="255" required/>
					</td>
					<td align="center" width=20%>
						<input type="text" name="country" value="${employee.address.country}" maxlength="255" required/>
					</td>
					<td align="center" width=20%>
						<input type="text" name="zipcode" value="${employee.address.zipcode}" maxlength="255" required/>
					</td>
				</tr>
				<tr>
					<td align="center" width=20%> Street Number </td>
					<td align="center" width=20%> Barangay </td>
					<td align="center" width=20%> City </td>
					<td align="center" width=20%> Country </td>
					<td align="center" width=20%> Zipcode </td>
				</tr>				
				<tr>
					<td align="left" width=20%> Birthday </td>
					<td align="left" width=20%>
						<input type="text" name="birthday" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${employee.birthday}"/>" maxlength="10" required/>
					</td>
					<td width=20%/>
					<td align="left" width=20%> Grade </td>
					<td align="left" width=20%>
						<input type="text" name="gwa" value="${employee.gradeWeightAverage}" maxlength="6" required/>
					</td>
				</tr>
				<tr>
					<td align="left" width=20%> Employed? </td>
					<td align="center" width=20%>
						<input type="radio" name="employed" value="true" onclick="document.getElementById('hiredate').disable = false;" required
						<core:if test="${employee.employed}">
								checked
						</core:if>
						> Yes </input>
						<input type="radio" name="employed" value="false" onclick="document.getElementById('hiredate').disable = true;" required
						<core:if test="${not employee.employed}">
								checked
						</core:if>
						> No </input>
					</td>
					<td width=20%/>
					<td width=20% align="left"> Hire Date </td>
					<td width=20% align="left">
						<input type="text" name="hireDate" id="hiredate" maxlength="10" value=
						<core:choose>
							<core:when test="${employee.employed}">
								"<fmt:formatDate pattern="dd/MM/yyyy" value="${employee.hireDate}"/>"
							</core:when>
							<core:otherwise>
								""
							</core:otherwise>
						</core:choose>
						/>
					</td>
				</tr>
				<tr>
					<td align="left"> Update Current Contact Info </td><br/>
					<core:forEach var="contact" items="${contacts}">
						<td>
							<select name="infoType">
								<option value="email"
									<core:if test="${contact.infoType == 'email'}">
										selected
									</core:if>
									> email 
								</option>
								<option value="telephone"
									<core:if test="${contact.infoType == 'telephone'}">
										selected
									</core:if>
									> telephone 
								</option>
								<option value="cellphone"
									<core:if test="${contact.infoType == 'cellphone'}">
										selected
									</core:if>
									> cellphone 
								</option>
							</select>
						</td>
						<td>
							<input type="text" name="infoDetail" value="${contact.infoDetail}" maxlength=255/>
						</td>
					</core:forEach>
				</tr>
				<tr width=50% align="left">
					<td align="left"> Update Current Roles </td><br/>
					<table width=50% border="1" align="center">
						<thead>
							<tr>
								<th> Role Code </th>
								<th> Role Name </th>
							</tr>
						</thead>
						<tbody>
							<core:forEach var="role" items="${roleList}">
								<tr>
									<td align="left">
										<input type="checkbox" name="roles" value="${role.id}"
											<core:forEach var="emprole" items="${currentRoles}">
												<core:if test="${role.id == emprole.id}">
													checked
												</core:if>
											</core:forEach>
										> 
										${role.roleCode}
									</td>
									<td>${role.roleName}</td>
								</tr>
							</core:forEach>
						</tbody>
					</table>
				</tr>
			</table>
		</form>
	</body>
</html>