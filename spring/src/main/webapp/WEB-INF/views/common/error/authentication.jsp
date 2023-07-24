<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한 부족 에러 페이지</title>
</head>
<body>
	<h3 style="color:red"><%=exception!=null?exception.getMessage():"권한이 부족합니다." %></h3><!-- el접근 안 됨 -->
	<h4>3초 후 메인화면으로 이동합니다.</h4>
	<script type="text/javascript">
		setTimeout(()=>{
			location.replace("${pageContext.request.contextPath}");
		},3000)
	</script>
</body>
</html>