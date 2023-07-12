<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bs.spring.demo.model.dto.Demo" %>
<%
	Demo d=(Demo)request.getAttribute("demo");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	table#tbl-dev{
		margin:0 auto;
		width:50%;
	}
</style>
<section id="content">
	<table class="table" id="tbl-dev">
		<tr>
			<th scope="col">이름</th>
			<td>${d.devName }</td>
		<tr>
		<tr>
			<th>나이</th>
			<td>${d.devAge }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${d.devEmail }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${d.devGender }</td>
		</tr>
		<tr>
			<th>개발기능언어</th>
			<td>
				<c:forEach var="l" items="${d.devLang }">
					<p>${l }</p>
				</c:forEach>
			</td>
		</tr>
	</table>
</section>