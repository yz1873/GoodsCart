package com.po;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
//购物车类
public class Cart {

    //购买商品的集合
    //map中都为不同商品，改写item的hashCode()和equals()方法可是相同商品统一
    //并改写addGoodsInCart方法
    private HashMap<Items,Integer> goods;

    //购物车的总金额
    private double totalPrice;

    //构造方法
    public Cart(){
        goods = new HashMap<Items, Integer>();
        totalPrice = 0.0;
    }

    public HashMap<Items, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<Items, Integer> goods) {
        this.goods = goods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //添加商品进购物车的方法
    public boolean addGoodsInCart(Items item,int number){
        if (goods.containsKey(item)){
            goods.put(item,number+goods.get(item));
        }
        else {
            goods.put(item,number);
        }
        calTotalPrice();
        return true;
    }
    //删除商品的方法
    public boolean removeGoodsFromCart(Items item){
        goods.remove(item);
        calTotalPrice();
        return true;
    }
    //统计购物车的总金额
    public double calTotalPrice(){
        double sum = 0.0;
        //遍历map类型
        Set<Items> keys = goods.keySet();
        Iterator<Items> it = keys.iterator();
        while(it.hasNext()){
            Items i = it.next();
            sum += i.getPrice()*goods.get(i);
        }
        this.setTotalPrice(sum);
        return this.getTotalPrice();
    }
}
