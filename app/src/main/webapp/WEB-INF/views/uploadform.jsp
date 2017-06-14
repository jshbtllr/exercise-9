<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix = "spring" uri = "http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
	<head>
		<title><spring:message code="file.upload"/></title>
	</head>
	<body>
		<h3><spring:message code="file.upload"/></h3>
		<table width=50% align="left">
			<tr>
				<td align="left">
					<a href="/employee"> <spring:message code="back"/> </a>
				</td>
			</tr>
			<tr>
				<td align="center">
					<form action="/fileupload" method="POST" enctype="multipart/form-data">
						<spring:message code="choose.file"/>: <input type="file" name="file"/>
						<input type="submit" value="<spring:message code="submit"/>"/>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>