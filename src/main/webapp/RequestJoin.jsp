<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 2024-06-27
  Time: 오후 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
  <h2> 회원가입 </h2>
  <form action="RequestJoinProc.jsp" method="post">
  <table width="500" border="1">
    <tr height="50">
      <td width="150" align = "center"> 아이디</td>
      <td width="350" align = "center"> <input type="text" name="id" maxlength="40"></td>

    <tr height="50">
      <td width="150" align = "center"> 비밀번호</td>
      <td width="350" align = "center"> <input type="password" name="pwd1" maxlength="40"></td>

    <tr height="50">
      <td width="150" align = "center"> 비밀번호확인</td>
      <td width="350" align = "center"> <input type="password" name="pwd2" maxlength="40"></td>

    <tr height="50">
      <td width="150" align = "center"> 이메일</td>
      <td width="350" align = "center"> <input type="email" name="email" maxlength="40"></td>

    <tr height="50">
      <td width="150" align = "center"> 전화번호</td>
      <td width="350" align = "center"> <input type="text" name="phone" maxlength="40"></td>
    </tr>

    <tr height="50">
      <td width="150" align = "center"> 관심분야</td>
      <td width="350" align = "center">
        <input type="checkbox" name="hobby" value="캠핑"> 캠핑 &nbsp; &nbsp;
        <input type="checkbox" name="hobby" value="캠핑"> 등산 &nbsp; &nbsp;
        <input type="checkbox" name="hobby" value="캠핑"> 영화 &nbsp; &nbsp;
        <input type="checkbox" name="hobby" value="캠핑"> 독서 &nbsp; &nbsp;
      </td>
    </tr>

    <tr height="50">
      <td width="150" align = "center"> 직업</td>
      <td width="350" align = "center">
        <select name="job">
          <option value="교사">교사</option>
          <option value="변호사">변호사</option>
          <option value="의사">의사</option>
          <option value="개발자">개발자</option>
        </select>
      </td>
    </tr>

    <tr height="50">
      <td width="150" align = "center"> 연령대</td>
      <td width="350" align = "center">
        <input type="radio" name="age" value="10"> 10 &nbsp; &nbsp;
        <input type="radio" name="age" value="20"> 20 &nbsp; &nbsp;
        <input type="radio" name="age" value="30"> 30 &nbsp; &nbsp;
        <input type="radio" name="age" value="40"> 40 &nbsp; &nbsp;
      </td>
    </tr>

    <tr height="50">
      <td width="150" align="center"> 하고싶은말 </td>
      <td width="350" align="center">
        <textarea rows="5" cols="40" name="info"></textarea>
      </td>
    </tr>

    <tr height="50">
      <td colspan="2" align="center">
        <input type="submit" value="회원가입">
        <input type="reset" value="취소">
      </td>
    </tr>

  </table>
  </form>
</center>

</body>
</html>
