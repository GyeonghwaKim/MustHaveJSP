<%@page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL - object paramter</title>
</head>
<body>
<%
request.setAttribute("personObj",new Person("홍길동",33));
request.setAttribute("stringObj", "I am String");
request.setAttribute("integerObj", new Integer(99));

%>

<jsp:forward page="ObjectResult.jsp">
	<jsp:param value="10" name="firstNum"/>
	<jsp:param value="20" name="secondNum"/>
	</jsp:forward>
</body>
</html>