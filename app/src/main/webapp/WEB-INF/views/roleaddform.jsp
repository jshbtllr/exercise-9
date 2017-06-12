<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
	<head>
		<title>Role Management</title>
	</head>
	<body>
		<h3>Add Role</h3>
		<form action="/roles/add" method="POST">
			Role Code: <input type="text" name="roleCode" maxlength=255/><br/>
			Role Name: <input type="text" name="roleName" maxlength=255/><br/>
			<input type="submit" value="add role"/>
		</form>
	</body>
</html>