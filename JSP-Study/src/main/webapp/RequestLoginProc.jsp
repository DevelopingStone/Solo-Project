<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- RequestLogin 에서 넘어온 ID, PW 확인 --%>

<h2> 로그인페이지 작성</h2>

<%
    //사용자의 정보가 저장되어있는 객체 request 의 getParameter() 사용자 정보를 추출
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
%>

<h2>
당신의 아이디는 <%=id%> 이고 패스워드는 <%=pwd%> 입니다.
</h2>

<a href="RequestForward.jsp"> 다음페이지</a>
</body>
</html>
