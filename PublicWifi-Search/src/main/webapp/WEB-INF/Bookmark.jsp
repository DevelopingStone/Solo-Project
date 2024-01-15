<%--
  Created by IntelliJ IDEA.
  User: gomlj
  Date: 2023-06-12
  Time: 오전 6:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private static final String BOOKMARK_FEATURE_ATTRIBUTE = "bookmarkFeature";
    private static final String BOOKMARK_JSON_ATTRIBUTE = "bookmarkJson";
    private String bookmarkFeature;
    private String bookmarkJson;

%>
<html>
<head>
    <title>북마크 목록</title>
</head>
<body>
<h1><%= "내 주변 와이파이 탐색" %></h1>
<br/>
<jsp:include page="Header.jsp"></jsp:include>
<%
    if (request.getAttribute(BOOKMARK_FEATURE_ATTRIBUTE)!= null && bookmarkFeature==null){
        bookmarkFeature = request.getAttribute(BOOKMARK_FEATURE_ATTRIBUTE).toString();
    }
    if(request.getAttribute(BOOKMARK_JSON_ATTRIBUTE)!=null){
        bookmarkJson = request.getAttribute(BOOKMARK_JSON_ATTRIBUTE).toString();
        System.out.println(bookmarkJson);
    }
%>

<div id="bookmark-list">

</div>


</body>
<script type="text/javascript" src="../js/bookmark/bookmarkTableMaker.js"></script>
<script type="text/javascript" src="../js/bookmark/BookmarkDeletionRequest.js"></script>
<script>
    renderBookmarkTable(<%=bookmarkJson%>, <%=bookmarkFeature%>, "bookmark-list");
</script>
</html>
