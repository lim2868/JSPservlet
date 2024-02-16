<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서정보</title>
</head>
<body>
	<h2>도서정보</h2> <a href="/bookstore/book">새로고침</a>
	<table border=1>
		<tr><th>도서번호</th><th>도서제목</th><th>작가이름</th><th>가격</th><th>삭제버튼</th></tr>
		<c:forEach items="${books}" var="b">
			<tr>
				<td>${b.bookNo}</td><td>${b.bookName}</td><td>${b.author}</td><td>${b.price}</td>
				<td><a href="/bookstore/book?action=delete&bookNo=${b.bookNo}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<h2>도서추가</h2>
	<hr>
	<form method="post" action="/bookstore/book?action=register">
		<label>도서제목</label>
		<input type="text" name="bookName"><br>
		<label>작가이름</label>
		<input type="text" name="author"><br>
		<label>가격</label>
		<input type="text" name="price"><br>
		<button type="submit">등록</button>
	</form>
</body>
</html>