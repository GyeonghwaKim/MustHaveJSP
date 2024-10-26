<%@page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% CookieManager.makeCookie(response,"ELCookie","EL좋아요",10); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>쿠키값 읽기</h3>
<li>ELCookie value: ${ cookie.ELCookie.value }</li>

<h3> read http header</h3>
<ul>
<li> host : ${ header.host}</li>
<li> user-agent : ${ header['user-agent'] }</li>
<li> host : ${ header.cookie }</li>
</ul>

<li>OracleDriver : ${initParam.OracleDriver }</li>

<li>${pageContext.request.contextPath }</li>
</body>
</html>