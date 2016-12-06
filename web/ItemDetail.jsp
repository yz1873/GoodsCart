<%@ page import="com.dao.ItemsDAO" %>
<%@ page import="com.po.Items" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/6 0006
  Time: 下午 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>购物车程序练习</title>
  <link type="text/css" href="css/ItemDetail.css" rel="stylesheet" />
  <script type="text/javascript" src="js/lhgcore.js"></script>
  <script type="text/javascript" src="js/lhgdialog.js"></script>


  <script type="text/javascript">
    function selflog_show(id) {  //点击加入购物车时会执行的js函数，向CartServlet传递商品id和数量
      var num =  document.getElementById("number").value;
      J.dialog.get({id: 'haoyue_creat',title: '购物成功',width: 600,height:400, link: '<%=request.getContextPath()%>/com/servlet/CartServlet?id='+id+'&num='+num+'&action=add', cover:true});
    }
    function add() {
      var num = parseInt(document.getElementById("number").value);
      if(num<100) {
        document.getElementById("number").value = ++num;
      }
    }
    function sub() {
      var num = parseInt(document.getElementById("number").value);
      if(num>1) {
        document.getElementById("number").value = --num;
      }
    }
  </script>


</head>
<body>
<a href="GoodsView.jsp">首页</a> >> <a href="GoodsView.jsp">商品列表</a>
<div class="tabox">
  <%
    ItemsDAO itemsdao = new ItemsDAO();
    Items item = itemsdao.getItemsById(Integer.parseInt(request.getParameter("id")));
    if (item!=null){
  %>
  <div class="bd">
    <div class="p-img">
      <a href="#"><img src="images/<%=item.getPicture()%>"></a>
    </div>
    <div class="p-name">
      <a href="#"><%=item.getName()%></a>
    </div>
    <div class="p-price">优惠价：
      <strong>￥<%=item.getPrice()%></strong>
    </div>
    <div class="p-number">购买数量：
      <span id="sub" onclick="sub();">-</span><input type="text" id="number" name="number" value="1" size="2"/><span id="add" onclick="add();">+</span>
    </div>
    <div id="cart">
      <a href="javascript:selflog_show(<%=item.getId()%>)"><img src="images/in_cart.png"></a><a href="com/servlet/CartServlet?action=show"><img src="images/view_cart.jpg"/></a>
    </div>
  </div>
  <%
    }

    String list = "";
//    从客户端获得cookie集合
    Cookie[] cookies = request.getCookies();
    for (Cookie c:cookies){
      if (c.getName().equals("ListViewCookie")){   //找出ListViewCookie的cookie
        list = c.getValue();
      }
    }
    list+=request.getParameter("id")+",";   //将新的id放入list中
    String arr[] = list.split(",");
    //如果记录超过100则清零
    if(arr!=null && arr.length>100){
      list = "";
    }
    Cookie newcookie = new Cookie("ListViewCookie",list); //创建新的cookie，把list放入
    response.addCookie(newcookie);   //把新的cookie放入response中
  %>
</div>
<div class="rightf">
  <b>您浏览过的商品</b>
  <%
    ItemsDAO itemsdao1 = new ItemsDAO();
    ArrayList<Items> vlistitems = itemsdao1.getViewList(list);
    if(vlistitems!=null && vlistitems.size()>0){
      for(Items i:vlistitems){
  %>
  <div class="br">
    <div class="bimage">
      <img src="images/<%=i.getPicture() %>">
    </div>
    <div class="bname">
      <%=i.getName()%>
    </div>
    <div class="bprice">
      <strong>￥<%=i.getPrice()%></strong>
    </div>
  </div>
  <%
      }
    }
  %>
</div>
</body>
</html>