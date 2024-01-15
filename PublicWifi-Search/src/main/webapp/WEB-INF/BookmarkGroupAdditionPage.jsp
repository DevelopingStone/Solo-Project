<%--
  Created by IntelliJ IDEA.
  User: gomlj
  Date: 2023-06-12
  Time: 오전 5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹 추가</title>
  <link href="../css/bookmarkGroup/addition/additionPage.css" rel="stylesheet" >

</head>
<body>
<h1><%= " 북마크 그룹 추가" %></h1>
<br/>
<jsp:include page="Header.jsp"></jsp:include>

<div id="bookmark-group-addition-container">
  <table id="bookmark-group-addition-table">
    <tr>
      <td id="bookmark-group-name">북마크 이름</td>
      <td id="bookmark-group-name-input-container">
        <label for="bookmark-group-name-input">
          <input id="bookmark-group-name-input" type="text"/>
        </label>
      </td>
    </tr>
    <tr>
      <td id="bookmark-group-order">순서</td>
      <td id="bookmark-group-order-container">
        <label for="bookmark-group-order-input">
          <input id="bookmark-group-order-input" type="text"/>
        </label>
      </td>
    </tr>
  </table>
  <form id="bookmark-group-addition-button-form">
    <button id="bookmark-group-addition-button" type="button" onclick="alert(`북마크 정보를 추가했습니다.`);">추가</button>
  </form>
</div>
</body>
<script type="text/javascript" src="../js/bookmarkGroup/addition/bookmarkGroupSaveRequest.js"></script>
</html>
