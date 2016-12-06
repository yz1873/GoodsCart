<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/3 0003
  Time: 下午 6:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dao.ItemsDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.po.Items" %>
<html>
<head>
  <title>购物车程序练习</title>
  <link type="text/css" href="css/GoodsView.css" rel="stylesheet" />
  <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
</head>
<body>
<h1>当前热卖商品</h1>
<div class="tabox">
  <div class="bd">
    <ul class="lh" style="display: none;">
      <%
        ItemsDAO itemsdao = new ItemsDAO();
        ArrayList<Items> list = itemsdao.getAllItems();
        for (Items item:list){
      %>
      <li>
        <div class="p-img ld">
          <a href="ItemDetail.jsp?id=<%=item.getId()%>"><img src="images/<%=item.getPicture() %>"></a>
        </div>
        <div class="p-name">
          <a href="ItemDetail.jsp?id=<%=item.getId()%>"><%=item.getName()%></a></div>
        <div class="p-price">优惠价：
          <strong>￥<%=item.getPrice()%></strong></div>
      </li>
      <%
        }
      %>
    </ul>
  </div>
</div>

<script type="text/javascript">
  jQuery(".tabox").slide({delayTime: 0});
</script>

</body>
</html>