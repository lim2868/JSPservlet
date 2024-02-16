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
		<tr><th>사원번호</th><th>사원이름</th><th>나이</th><th>부서</th></tr>
		<c:set var="emp" value="${Employee}" />
		<tr>
			<td>${emp.empNo}</td>
			<td>${emp.empName}</td>
			<td>${emp.age}</td>
			<td>${emp.departName}</td>
		</tr>
	</table>
</body>
</html>