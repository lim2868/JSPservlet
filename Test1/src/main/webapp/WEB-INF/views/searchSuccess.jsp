<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회 성공</title>
</head>
<body>
	<h2>회원정보</h2>
	<table border="1">
		<tr><th>회원번호</th><th>회원아이디</th><th>회원이름</th><th>회원나이</th>
		<c:set var="user" value="${User}" />
		<tr>
			<td>${user.userNo}</td>
			<td>${user.userId}</td>
			<td>${user.userName}</td>
			<td>${user.userAge}</td>
	</table>
	<a href="<%=request.getContextPath()%>/user">메인페이지로 돌아가기</a>
</body>
</html>