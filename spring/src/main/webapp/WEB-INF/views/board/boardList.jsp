<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="게시판"/>
</jsp:include>
<section id="board-container" class="container">
        <p>총 ${totalData }건의 게시물이 있습니다.</p>
        
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty boards }">
	            <c:forEach var="b" items="${boards }">
	            	<tr>
	            		<td>${b.boardNo }</td>
	            		<td>${b.boardTitle }</td>
	            		<td>${b.boardWriter }</td>
	            		<td>${b.boardDate }</td>
	            		<td>
	            			<%-- <c:if test="${b.file!=null }">
	            			<img alt="" src="">
	            			</c:if> --%>
	            		</td>
	            		<td>${b.boardReadCount }</td>
	            	</tr>
	            </c:forEach>
            </c:if>
        </table> 
</section>
<div>
	${pageBar }
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>