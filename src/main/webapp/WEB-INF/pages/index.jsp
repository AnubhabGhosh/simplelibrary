<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<link href="<c:url value="/resources/css/application.css" />" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<img src="/simplelibrary/resources/images/logo.png" alt="Wiley Logo" style="width:372px;height:113px;">
		<h3><font color="brown">${welcomeMessage}</font></h3>
		<table id="books">
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
			</tr>
			<c:if test="${not empty books}">
				<c:forEach var="book" items="${books}">
					<tr>
						<td>${book.id}</td>
						<td>${book.title}</td>
						<td>${book.author}</td>
						<td>${book.price}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<h4><a id = "addBook" href="/simplelibrary/login">Add a new book: </a></h4>
	</body>
</html>
