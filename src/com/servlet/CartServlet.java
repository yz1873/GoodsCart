package com.servlet;

import com.dao.ItemsDAO;
import com.po.Items;
import com.po.Cart;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
@WebServlet(name = "CartServlet",urlPatterns="/com/servlet/CartServlet")
public class CartServlet extends HttpServlet {
    private String action; //购物车的动作
    private ItemsDAO idao = new ItemsDAO(); //这是商品业务逻辑类的对象

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if (request.getParameter("action")!=null){
            this.action = request.getParameter("action");
            if(action.equals("add")){
                if (addToCart(request,response)){
                    request.getRequestDispatcher("/success.jsp").forward(request,response);
                }
                else {
                    request.getRequestDispatcher("/failure.jsp").forward(request,response);
                }
            }
            if(action.equals("show")){
                request.getRequestDispatcher("/Cart.jsp").forward(request, response);
            }
            if(action.equals("delete")){
                if (deleteFromCart(request,response)){
                    request.getRequestDispatcher("/Cart.jsp").forward(request, response);
                }
                else {
                    request.getRequestDispatcher("/Cart.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }

    private boolean addToCart(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
        String id = request.getParameter("id");  //获取商品id
        String number = request.getParameter("num");   //获取要添加的数量
        Items item = idao.getItemsById(Integer.parseInt(id));    //获取其item
        //是否是第一次给购物车添加商品，需要给session添加一个新的购物车对象(开辟一块新的内存用来存储cart)
        if (request.getSession().getAttribute("cart")==null){    //若session中名为cart的变量不存在
            Cart cart = new Cart();                             //新建一个cart空对象
            request.getSession().setAttribute("cart",cart);     //添加入session中，名为cart
        }
        Cart cart = (Cart)request.getSession().getAttribute("cart");   //获取session中名为cart的object，并强制转化为cart类型
        return (cart.addGoodsInCart(item,Integer.parseInt(number)));   //cart对象中添加对应数量的item商品，返回其是否添加成功
    }

    public boolean deleteFromCart(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
        String id = request.getParameter("id");  //获取商品id
        Cart cart = (Cart)request.getSession().getAttribute("cart");  //获取session中名为cart的object，并强制转化为cart类型
        Items item = idao.getItemsById(Integer.parseInt(id)); //得到要删除的item
        return (cart.removeGoodsFromCart(item));    //cart对象中删除相应商品，并返回删除结果
    }
}
