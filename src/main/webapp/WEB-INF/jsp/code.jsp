<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>What the hell is this</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var key = "${key}";
	var link = "${link}";
	
	var obj = {"javascript":["","","","","","","","","","","",{"data":{"key":key,"link":link}}]};
	console.log(obj);
});
</script>
</head>
<body>
	${code}
</body>
</html>