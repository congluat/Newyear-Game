<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style type="text/css">
.centered {
	position: fixed;
	top: 50%;
	left: 50%;
	width: 30em;
	height: 12em;
	margin-top: -9em; /*set to a negative number 1/2 of your height*/
	margin-left: -15em; /*set to a negative number 1/2 of your width*/
	border: 1px solid #ccc;
	background-color: #f3f3f3;
}
</style>

</head>
<body>
	<div class="centered panel panel-default ">
		<div class="panel-heading" align="center">Welcome</div>
		<div class="panel-body">

			<form:form action="start" method="post"
				modelAttribute="player" cssClass="form-horizontal">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">Your Email</label>
					<div class="col-sm-8">
						<form:input type="email" cssClass="form-control" path="email"
							placeholder="Your email" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-7">
						<input type="submit" class="btn btn-success" value="Start" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
