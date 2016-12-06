package com.po;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
public class Items {
    private int id;
    private String name;
    private int price;
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int hashCode() {
        //改写hashCode，使得相同的商品的hashCode相同。（相同字符串的hashcode相同）
        return this.getId()+this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //用（==）进行比较的时候，比较的是他们在内存中的存放地址
        if(this==obj){
            return true;
        }
        if(obj instanceof Items){
            Items i = (Items)obj;
            //字符串比较相同用equals方法
            return (this.getId()==i.getId() && this.getName().equals(i.getName()));
        }
        else {
            return false;
        }
    }
}
