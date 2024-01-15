<%--
  Created by IntelliJ IDEA.
  User: gomlj
  Date: 2023-06-11
  Time: 오전 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private static final String PUBLIC_WIFI_DETAIL_FEATURE = "publicWifiDetailFeature";
    private static final String PUBLIC_WIFI_DETAIL = "publicWifiDetail";
    private static final String BOOKMARK_GROUP_NAME_BUNDLE = "bookmarkGroupNameJson";
    private String publicWifiDetailFeature;
    private String publicWifiDetail;
    private String bookmarkGroupNameBundle;

    public void mappingRequestParameter(HttpServletRequest request){
        if(request.getAttribute(PUBLIC_WIFI_DETAIL_FEATURE)!=null && request.getAttribute(PUBLIC_WIFI_DETAIL)!=null){
            publicWifiDetailFeature = request.getAttribute(PUBLIC_WIFI_DETAIL_FEATURE).toString();
            publicWifiDetail = request.getAttribute(PUBLIC_WIFI_DETAIL).toString();
        }
        if(request.getAttribute(BOOKMARK_GROUP_NAME_BUNDLE)!=null){
            bookmarkGroupNameBundle = request.getAttribute(BOOKMARK_GROUP_NAME_BUNDLE).toString();
            System.out.println(bookmarkGroupNameBundle);
        }
    }
%>
<html>
<head>
    <title>와이파이 상세 페이지</title>
</head>

<body>
<h1><%= "와이파이 정보 구하기" %></h1>
<br/>
<jsp:include page="Header.jsp"></jsp:include>
<form id="bookmark-form">

    <label for="bookmark-selector">
        <select id="bookmark-selector" >
            <option value="bookmark-group-name" selected disabled>북마크 그룹 이름 선택</option>
        </select>
    </label>

    <button id="add-bookmark-button" type="button">즐겨찾기 추가하기</button>

</form>

<%
    mappingRequestParameter(request);
%>
<div id="publicWifiDetail"></div>

</body>
<script type="text/javascript" src="../js/tableMaker.js"></script>
<script type="text/javascript" src="../js/bookmark/BookmarkSaveRequest.js"></script>
<script type="text/javascript" src="../js/bookmarkGroup/BookmarkGroupNameSelector.js"></script>
<script>
    renderBookmarkGroupSelector(<%=bookmarkGroupNameBundle%>);
    renderPublicWifiDetailTable(<%=publicWifiDetail%>, <%=publicWifiDetailFeature%>, "publicWifiDetail");

</script>
</html>
