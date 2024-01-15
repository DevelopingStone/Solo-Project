<%--
  Created by IntelliJ IDEA.
  User: gomlj
  Date: 2023-06-12
  Time: 오전 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private static final String BOOKMARK_GROUP_FEATURE_ATTRIBUTE = "bookmarkGroupFeature";
    private static final String BOOKMARK_GROUP_JSON_ATTRIBUTE = "bookmarkGroupJson";
    private String bookmarkGroupFeature;
    private String bookmarkGroupJson;

%>
<html>
<head>
    <title>북마크 그룹 목록</title>
</head>
<body>
<h1><%= " 북마크 그룹 목록" %></h1>
<br/>
<jsp:include page="Header.jsp"></jsp:include>
<%
    if (request.getAttribute(BOOKMARK_GROUP_FEATURE_ATTRIBUTE)!= null && bookmarkGroupFeature==null){
        bookmarkGroupFeature = request.getAttribute(BOOKMARK_GROUP_FEATURE_ATTRIBUTE).toString();
        System.out.println(bookmarkGroupFeature);
    }
    if(request.getAttribute(BOOKMARK_GROUP_JSON_ATTRIBUTE)!=null){
        bookmarkGroupJson = request.getAttribute(BOOKMARK_GROUP_JSON_ATTRIBUTE).toString();
    }
%>
<form id="bookmark-group-add">
  <button id="bookmark-group-add-button" type="button">
      북마크 이름 추가
  </button>
</form>

<div id="bookmark-group-list">

</div>

</body>
<script type="text/javascript" src="../js/bookmarkGroup/bookmarkGroupTableMaker.js"></script>
<script type="text/javascript" src="../js/bookmarkGroup/BookmarkGroupAdd.js"></script>
<script type="text/javascript" src="../js/bookmarkGroup/BookmarkGroupDeletion.js"></script>
<script type="text/javascript" src="../js/bookmarkGroup/BookmarkGroupUpdateButtonClick.js"></script>

<script>
    renderBookmarkGroupTable(<%=bookmarkGroupJson%>, <%=bookmarkGroupFeature%>, "bookmark-group-list");
</script>
</html>
