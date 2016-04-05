<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">
</style>
</head>
<body class="container">

	<div class="panel panel-default" style="margin-top: 10em">
		<div class="panel-heading">
			<h3 class="panel-title">All Player</h3>
		</div>
		<div class="panel-body">

			<table class="table table-hover">
				<tr>
					<td>ID</td>
					<td>Email</td>
					<td>Name</td>
					<td>Phone</td>
					<td>Code</td>
					<td>Status</td>
				</tr>
				<c:forEach items="${list}" var="player">
					<c:if test="${player.status==0}">
						<tr class="danger">
							<td>${player.id}</td>
							<td>${player.email}</td>
							<td>${player.name}</td>
							<td>${player.phone}</td>
							<td>${player.code}</td>
							<td>${player.status}</td>
						</tr>
					</c:if>
					<c:if test="${player.status==1}">
						<tr class="success">
							<td>${player.id}</td>
							<td>${player.email}</td>
							<td>${player.name}</td>
							<td>${player.phone}</td>
							<td>${player.code}</td>
							<td>${player.status}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>