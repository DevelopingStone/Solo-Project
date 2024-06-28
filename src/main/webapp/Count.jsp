<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 2024-06-26
  Time: 오후 5:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" buffer="8kb"
         autoFlush="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2> 1-10 까지의 숫자를 화면에 표시 </h2>

<%
    for (int i = 0; i < 11; i++) {
%>

<%=i%> <br>

<%
    }
%>

</body>
</html>