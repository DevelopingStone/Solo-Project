<%--
  Created by IntelliJ IDEA.
  User: gomlj
  Date: 2023-06-11
  Time: 오전 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="../css/home/navigator.css?after" rel="stylesheet" >
    <link href="../css/home/locator.css?after" rel="stylesheet" >
    <title></title>
</head>
<body>

<div id="navigator">

    <button type="button" id="homeButton"><%="홈" %></button>

    <button type="button"
            id="history-button">
      <%="위치 히스토리" %>
    </button>

    <button
            type="button"
            id="requestPublicWifiButton"
            onclick=
                    "window.open('JsonRequest', `공공 와이파이 데이터 가져오기 결과`,
                    'width=400,height=150,location=no,status=no,scrollbars=yes')">
        <%="공공 와이파이 정보 갱신" %>
    </button>

    <button
            type="button"
            id="bookmark-button">
        <%="북마크 보기" %>
    </button>

    <button
            type="button"
            id="bookmarkGroup-button">
    <%="북마크 그룹 관리" %>
    </button>

</div>

</body>
<script type="text/javascript" src="../js/searchHistory/SearchHistoryRequest.js"></script>
<script type="text/javascript" src="../js/returnToHome.js"></script>
<script type="text/javascript" src="../js/homepage/BookmarkPage.js"></script>
<script type="text/javascript" src="../js/homepage/BookmarkGroupPage.js"></script>
</html>
