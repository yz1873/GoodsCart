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
        String id = request.getParameter("id");
        String number = request.getParameter("num");
        Items item = idao.getItemsById(Integer.parseInt(id));
        //是否是第一次给购物车添加商品，需要给session添加一个新的购物车对象(开辟一块新的内存用来存储cart)
        if (request.getSession().getAttribute("cart")==null){
            Cart cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        return (cart.addGoodsInCart(item,Integer.parseInt(number)));
    }

    public boolean deleteFromCart(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
        String id = request.getParameter("id");
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Items item = idao.getItemsById(Integer.parseInt(id));
        return (cart.removeGoodsFromCart(item));
    }
}
