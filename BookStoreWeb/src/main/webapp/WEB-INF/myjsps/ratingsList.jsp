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
	<div class="container">
		<table class="table table-striped table-bordered table-light myfont">
			<thead class="bg-info">
				<tr>
					<th>Book Id</th>
					<th>User Id</th>
					<th>Rating</th>
					<th>Review</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="myuserRating" items="${MyUserRatingList}">
					<tr>
						<td>${myuserRating.bookId}</td>
						<td>${myuserRating.userId}</td>
						<td>${myuserRating.rating}</td>
						<td>${myuserRating.review}</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
	</div>
	<c:import url="myfooter.jsp"></c:import>
</body>
</html>