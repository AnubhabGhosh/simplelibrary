<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Insert Book</title>
	</head>
	<body>
		<img src="/simplelibrary/resources/images/logo.png" alt="Wiley Logo" style="width:372px;height:113px;">
		<div>
			<c:if test="${not empty logInMessage}">
				<h4>${logInMessage}</h4>
			</c:if>
		</div>
		<h2>Insert a new book:</h2>
		<form:form action="insert" method="post">
	 			ID:<input id="bookId" type="text" name="id">
			<br />
	 			Title :<input id="title" type="text" name="title" />
			<br />
	 			Author :<input id="author" type="text" name="author" />
			<br />
	 			Price :<input id="price" type="text" name="price" />
			<br />
			<input id="insertBook" value="Add book" type="submit" />
		</form:form>
	</body>
</html>