<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 2024-06-26
  Time: 오후 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> 구구단 시작</h2>

<%
    for (int i = 1; i < 10; i++) {
        for (int j = 1; j < 10; j++) {
            out.write(i + "*" + j + "=" + i * j);
        }
    }
%>

</body>
</html>
