<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="게시판"/>
</jsp:include>
<div id="board-container">
        <form:form modelAttribute="board" name="boardFrm" action="${path }/board/insertEndBoard.do" method="post">
            <form:input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" path="boardTitle"/>
            <form:input type="text" class="form-control" placeholder="아이디 (4글자이상)" path="boardWriter" value="${loginMember.userId}" readonly />
            <div class="input-group mb-3" style="padding:0px;">
                <div class="input-group-prepend" style="padding:0px;">
                    <span class="input-group-text">첨부파일1</span>
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="upFile" id="upFile1"/>
                    <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
                </div>
            </div>
            <form:textarea class="form-control" name="boardContent" placeholder="내용" path="boardContent"></form:textarea>
            <br />
            <input type="submit" class="btn btn-outline-success" value="저장" >
        </form:form>
    </div>

<style>
div#board-container {
	width: 400px;
	margin: 0 auto;
	text-align: center;
}

div#board-container input {
	margin-bottom: 15px;
}
</style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>