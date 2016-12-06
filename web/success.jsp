<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/6 0006
  Time: 下午 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>成功购买</title>
</head>
<body>
<div align="center">
  <img src="<%=request.getContextPath()%>/images/add_cart_success.jpg"/>
  <hr>
  <%
    String id = request.getParameter("id");
    String num = request.getParameter("num");
  %>
  您成功购买了<%=num%>件商品编号为<%=id%>的商品&nbsp;&nbsp;&nbsp;&nbsp;
  <br>
  <br>
  <br>
</div>
</body>
</html>
