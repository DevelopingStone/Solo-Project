<%--
  Created by IntelliJ IDEA.
  User: gomlj
  Date: 2023-06-11
  Time: 오전 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private String searchHistoryFeature;
    private String SearchHistoryJson;

%>
<html>
<head>
    <title>검색 히스토리 조회</title>
</head>
<body>
<h1><%= "위치 히스토리 목록" %></h1>
<br/>

<jsp:include page="Header.jsp"></jsp:include>
<%
    if (request.getAttribute("searchHistoryFeature")!= null && searchHistoryFeature==null){
        searchHistoryFeature = request.getAttribute("searchHistoryFeature").toString();
    }
    if(request.getAttribute("searchHistory")!=null){
        SearchHistoryJson = request.getAttribute("searchHistory").toString();
    }
%>


<div id="searchHistoryTable">

</div>

</body>
<script type="text/javascript" src="../js/tableMaker.js"></script>
<script type="text/javascript" src="../js/searchHistory/searchHistoryDelete.js"></script>
<script>
    renderSearchHistoryTable(<%= SearchHistoryJson %>, <%= searchHistoryFeature%>, "searchHistoryTable");
</script>
</html>
