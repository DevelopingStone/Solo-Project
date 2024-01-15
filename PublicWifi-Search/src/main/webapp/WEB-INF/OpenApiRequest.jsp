<%--
  Created by IntelliJ IDEA.
  User: gomlj
  Date: 2023-06-02
  Time: 오전 6:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공공 와이파이 데이터 로딩 결과</title>
</head>
<body>
<p><%= request.getAttribute("numberOfTotalData") + "개의 데이터 로딩 성공!"%></p>
<a href="index.jsp" onclick="window.close();"><%="홈으로"%></a>
</body>
</html>
