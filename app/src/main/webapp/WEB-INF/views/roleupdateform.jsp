<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
	<head>
		<title>Role Management</title>
	</head>
	<body>
		<h3>Update Role</h3>
		<form action="/roles/update" method="POST">
			Role Code: <input type="text" name="roleCode" value="${role.roleCode}" maxlength=255/><br/>
			Role Name: <input type="text" name="roleName" value="${role.roleName}" maxlength=255/><br/>
			<input type="hidden" name="roleId" value="${role.id}"/>
			<input type="submit" value="update role"/>
		</form>
	</body>
</html>