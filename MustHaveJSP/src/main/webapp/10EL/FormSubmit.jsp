<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어(EL) 폼값 처리</title>
</head>
<body>
<h2>폼값 전송하기</h2>
<form name="frm" method="post" action="FormResult.jsp">
이름:<input type="text" name="name"/><br/>
gender:<input type="radio" name="gender" value="Man"> Man
		<input type="radio" name="gender" value="Woman"> Woman
grade:
<select name="grade">
<option value="ele">ele</option>
<option value="mid">mid</option>
<option value="high">high</option>
<option value="uni">uni</option>
</select> <br/>
inter:
	<input type="checkbox" name="inter" value="pol"/> pol
	<input type="checkbox" name="inter" value="eco"/> eco
	<input type="checkbox" name="inter" value="ent"/> ent
	<input type="checkbox" name="inter" value="spo"/> spo <br/>
	<input type="submit" value="submit">
</form>
</body>
</html>