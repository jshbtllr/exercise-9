<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
				<a href="employee/add"> Add Employee </a>
			</td>
			<td width=30% align="center">
				<a href="roles"> Role Management </a>
			</td>
			<td width=40% aligmn="right">
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
				<core:forEach var="" 

