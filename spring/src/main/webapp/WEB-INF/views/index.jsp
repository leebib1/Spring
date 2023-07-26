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
		const data={userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"};
		/* $.ajax({
			url:"${pageContext.request.contextPath}/ajax/insertData.do",
			type:"post",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			success:data=>{
				console.log(data);
			}
		}); */
		
		//js가 제공하는 fetch함수 
		//fetch는 다른 라이브러리 필요 없이 사용 가능하다.
		//비동기식 통신이라 callback 처리 되어있다.
		//fetch("url",{요청에 대한 옵션 정보}) //Promise 객체를 반환. 옵션 정보 생략시 get방식으로 요청이 넘어간다
		// .then(response=>{response.json()}) //응답 내용 파싱
		// .then(data=>{처리 로직}) //success함수 역할
		/* fetch("${pageContext.request.contextPath}/ajax/memberAll.do",{
			method:"get",
			//headers:{} ->contentType 등 헤더 옵션을 보낼 수 있다
			//body:JSON.stringify(객체) ->전송할 객체를 보낼 수 있다.
		}).then(response=>{
			console.log(response);
			if(!response.ok) throw new Error("요청실패");
			return response.json();
			})
		.then(data=>{console.log(data)})
		.catch(e=>{
			alert(e);
		}); */
		
		fetch("${pageContext.request.contextPath}/ajax/insertData.do",{
			method:"post",
			header:{
				"Content-Type":"application/json"
			},
			body:JSON.stringify(data)
		}).then(response=>{
			console.log(data);
			if(!response.ok) new Error("");
			return response.json();
			//일반문자를 반환하는 경우 response.text() 사용
		}).then(data=>{
			console.log(data);
		}).catch(e=>{
			//error 발생 시 실행 로직
		});
		
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
		$.get("${pageContext.request.contextPath}/ajax/memberAll.do",data=>{
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


	<h1>JPA 테스트</h1>
	<h3><a href="${pageContext.request.contextPath }/jpa/basictest.do">기본 EntityManager 이용</a></h3>
	
	
	
	
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>