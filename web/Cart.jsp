<%@ page import="com.po.Cart" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.po.Items" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/28 0028
  Time: 下午 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>查看购物车</title>
  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/Cart.css" />

  <script language="javascript">
    function delcfm() {  //再删除时提示对话框
      if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
      }
    }
  </script>

</head>
<body>
<h1>我的购物车</h1>
<a href="<%=request.getContextPath()%>/GoodsView.jsp">首页</a> >> <a href="<%=request.getContextPath()%>/GoodsView.jsp">商品列表</a>
<hr>
<div id="shopping">
  <form action="" method="">
    <table>
      <tr>
        <th>商品名称</th>
        <th>商品单价</th>
        <th>商品价格</th>
        <th>购买数量</th>
        <th>操作</th>
      </tr>
      <%
        if (request.getSession().getAttribute("cart")!=null){  //查看session是否存在cart属性
          Cart cart = (Cart)request.getSession().getAttribute("cart");   //得到其cart对象
          HashMap<Items,Integer> goods = cart.getGoods();    //得到cart中的goods
          Set<Items> items = goods.keySet();
          Iterator<Items> it = items.iterator();
          while (it.hasNext()){
            Items item = it.next();
      %>

      <tr name="products" id="product_id_1">
        <td class="thumb"><img src="<%=request.getContextPath()%>/images/<%=item.getPicture()%>" /><%=item.getName()%></td>
        <td class="number"><%=item.getPrice()%></td>
        <td class="price" id="price_id_1">
          <span><%=item.getPrice()*goods.get(item)%></span>
          <input type="hidden" value="" />
        </td>
        <td class="number">
          <%=goods.get(item)%>
        </td>
        <td class="delete">
          <a href="<%=request.getContextPath()%>/com/servlet/CartServlet?action=delete&id=<%=item.getId()%>" onclick="delcfm();">删除</a>
        </td>
      </tr>
      <%
        }
      %>
    </table>
    <div class="total"><span id="total">总计：<%=cart.getTotalPrice()%>￥</span></div>
    <%
      }
    %>
    <div class="button"><input type="submit" value="" /></div>
  </form>
</div>
</body>
</html>

