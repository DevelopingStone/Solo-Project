<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- RequestLogin 에서 넘어온 ID, PW 확인 --%>

<h2>리퀘스트 포워드 페이지 </h2>



<%

    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
%>

<h2>
    당신의 아이디는 <%=id%> 이고 패스워드는 <%=pwd%> 입니다.
</h2>
</body>
</html>
