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
	
	<h2>ajax 통신하기</h2>
	<h4><button class="btn btn-outline-primary" onclick="basicAjax();">기본 ajax처리</button></h4>
	<p><button class="btn btn-outline-success" onclick="convertAjax();">json 받기</button></p>
	<p><button class="btn btn-outline-warning" onclick="basic2();">jsp 받아오기</button></p>
	<p><button class="btn btn-outline-danger" onclick="fn_memberAll();">전체 멤버 가져오기</button></p>
	<p><button class="btn btn-outline-dark" onclick="insertData();">데이터 저장하기</button></p>
	<div id="ajaxContainer"></div>
</section>
<script>
	const insertData=()=>{
		/* $.post("${pageContext.request.contextPath}/ajax/insertData.do",
				{userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"},
				data=>{
					console.log(data);
				}); */
		/* const data={userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"};
		$.ajax({
			url:"${pageContext.request.contextPath}/ajax/insertData.do",
			type:"post",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			success:data=>{
				console.log(data);
			}
		}); */
		
		//js가 제공하는 fetch함수
		
		
	}
	const basicAjax=()=>{
		//console.log("basicAjax 실행");
		$.get('${pageContext.request.contextPath}/ajax/basicTest.do',(data)=>{
			console.log(data);
			$("#ajaxContainer").html("<h2>"+data+"</h2>");
		});
	}
	
	const convertAjax=()=>{
		$.get("${pageContext.request.contextPath}/ajax/converter",data=>{
			console.log(data);
		});
	}
	
	const basic2=()=>{
		$.get("${pageContext.request.contextPath}/ajax/basic2",data=>{
			console.log(data);
			$("#ajaxContainer").html(data);
		});
	}
	
	const fn_memberAll=()=>{
		$.get("${pageContext.request.contextPath}/ajax/selectMemberAll",data=>{
			console.log(data);
			const table=$("<table>").attr("class","table");
			const header=["아이디","이름","나이","성별","이메일","전화번호","주소","취미","가입일"];
			const tr=$("<tr>");
			header.forEach(e=>{
				const th=$("<th>").text(e);
				tr.append(th);
			});
			table.append(tr);
			data.forEach(e=>{
				const bodyTr=$("<tr>");
				const id=$("<td>").text(e.userId);
				const name=$("<td>").text(e.userName);
				const age=$("<td>").text(e.age);
				const gender=$("<td>").text(e.gender);
				const email=$("<td>").text(e.email);
				const phone=$("<td>").text(e.phone);
				const addr=$("<td>").text(e.address);
				const hobby=$("<td>").html(e.hobby.toString());
				const enrollDate=$("<td>").text(new Date(e.enrolldate));
				bodyTr.append(id).append(name).append(age).append(gender).append(email).append(phone)
					.append(addr).append(hobby).append(enrollDate);
				table.append(bodyTr);
			});
			$("#ajaxContainer").html(table);
		});
	}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>