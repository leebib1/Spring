<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메인화면"/>
</jsp:include>
<%
	request.setAttribute("test",new String[]{"1","2","3","4"});
%>    
<section id="content">
	<h2>Hello Spring</h2>
	${fn:join(test,",").contains("2")}
	${test }
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>