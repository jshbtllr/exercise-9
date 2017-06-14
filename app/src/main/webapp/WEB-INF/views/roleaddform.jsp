<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix = "spring" uri = "http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
	<head>
		<title><spring:message code="role.title"/></title>
	</head>
	<body>
		<h3><spring:message code="role.link"/></h3>
		<table width=100%>
			<td width=50% align="left">
				<a href="/roles"> <spring:message code="back.roles"/> </a><br/>
			</td>
		</table>
		<div style="clear:both;"></div><br/>		
		<form action="/roles/add" method="POST">
			<spring:message code="role.code"/>: <input type="text" name="roleCode" maxlength=255/><br/>
			<spring:message code="role.name"/>: <input type="text" name="roleName" maxlength=255/><br/>
			<input type="submit" value="<spring:message code="role.link"/>"/>
		</form>
	</body>
</html>