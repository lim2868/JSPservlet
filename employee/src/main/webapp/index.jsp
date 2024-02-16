<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 정보 검색 페이지</title>
</head>
<body>
	<h1>직원 정보 조회</h1>
	<form action="<%=request.getContextPath() %>/emp">
		<input type="text" name="empNo" placeholder="직원 번호 입력">
		<input type="submit" value="조회">	
	</form>
</body>
</html>