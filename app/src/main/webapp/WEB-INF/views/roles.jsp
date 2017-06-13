<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
	<head>
		<title>Role Management</title>
	</head>
	<body>
		<h3>Role Management</h3>
		<h4>Current Roles</h4>
		<table width=100%>
			<td width=30% align="left">
				<a href="/employee"> Back to Employee <br/> Management </a>
			</td>
			<td width=30% align="center">
				<a href="/roles/add"> Add Roles </a>
			</td>
			<td width=40% align="right">
				<form action="roles" method="GET">
					Sort by: <select name="sort">
						<option value="id"> Role ID </option>
						<option value="code"> Role Code </option>
						<option value="name"> Role Name </option>
					</select>
					<select name="order">
						<option value="ascending"> Ascending </option>
						<option value="descending"> Descending </option>
					</select>
					<input type="submit" value="sort"/>
				</form>
			</td>
		</table>

		<div style="clear:both"></div><br/>
		<table width="60%" border="1" align="left">
			<thead>
				<tr>
					<th width=8%>ID</th>
					<th width=15%>Role Code</th>
					<th width=25%>Role Name</th>
					<th width=15%>Action</th>
				</tr>
			</thead>
			<tbody>
				<core:forEach var="role" items="${roles}">
					<tr>
						<td align="center">${role.id}</td>
						<td align="center">${role.roleCode}</td>
						<td align="center">${role.roleName}</td>
						<td align="center">
							<form roles="/action" method="POST">
								<input type="hidden" name="roleId" value="${role.id}"/>
								<input type="submit" value="delete"/>
							</form>
							<form action="/roles/update" method="GET">
								<input type="hidden" name="roleId" value="${role.id}"/>
								<input type="submit" value="update"/>
							</form>
						</td>
					</tr>
				</core:forEach>
			</tbody>
		</table>
	</body>
</html>