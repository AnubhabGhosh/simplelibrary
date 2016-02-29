<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<img src="/simplelibrary/resources/images/logo.png" alt="devops Logo" style="width:372px;height:113px;">
		<form:form action="validate" method="post">
	   		Enter User Name:<input id="userName" type="text" name="name">
			<br />
	   		Enter Password :<input id="pwd" type="password" name="password" />
			<br />
			<input id="login" value="Log In" type="submit"/>
		</form:form>
		<div>
			<c:if test="${not empty logInMessage}">
				<h4>${logInMessage}</h4>
			</c:if>
		</div>
	</body>
</html>
