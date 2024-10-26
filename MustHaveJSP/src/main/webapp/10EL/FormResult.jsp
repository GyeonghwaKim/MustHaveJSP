<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL form value process</title>
</head>
<body>
<h3>EL로 폼값 받기</h3>
<ul>
	<li>name : ${param.name }</li>
	<li>gender : ${param.gender }</li>
	<li>grade : ${param.grade }</li>
	<li>inter : ${paramValues.inter[0] }
	${paramValues.inter[1] }
	${paramValues.inter[2] }
	${paramValues.inter[3] }
	</li>
</ul>
</body>
</html>