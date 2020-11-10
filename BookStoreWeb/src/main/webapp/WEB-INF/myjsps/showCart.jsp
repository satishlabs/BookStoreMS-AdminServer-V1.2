<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>JLC Bookstore</title>
<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="mycss/bookstore.css" rel="stylesheet">
</head>
<body>
	<div class="card">
		<c:import url="myheader.jsp" />
	</div>
<body>
	<div class="container">
		<table class="table table-striped table-bordered table-light myfont">
			<thead class="bg-info">
				<tr>
					<th>Book ID</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Publications</th>
					<th>Category</th>
					<th><a href="continueShopping" class="btn btn-danger">
							Continue Shopping</a></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="6" align="center">${CartEmptyMSG}</td>
				</tr>
				<c:forEach var="mybook" items="${MyCartItems}">
					<tr>
						<td>${mybook.bookId }</td>
						<td>${mybook.bookName }</td>
						<td>${mybook.author }</td>
						<td>${mybook.publications }</td>
						<td>${mybook.category }</td>
						<td><form:form action="removeFromCart" method="post">
								<input type="hidden" name="bookId" value="${mybook.bookId }" />
								<input class="btn btn-primary" type="submit"
									value=" Remove from My Cart " />
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" align="center"><a href="placeOrder"
						class="btn btn-danger"> Place My Order</a></td>
				</tr>
			</tfoot>
		</table>
	</div>
	<br />
	<br />
	<br />
	<c:import url="myfooter.jsp" />
</body>
</html>
