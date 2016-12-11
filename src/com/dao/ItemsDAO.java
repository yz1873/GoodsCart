package com.dao;

import com.po.Items;
import com.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

//商品的DAO类
public class ItemsDAO {     //商品dao类
    public ArrayList<Items> getAllItems(){      //得到数据库中所有商品
        ArrayList<Items> list = new ArrayList<Items>();  //商品list初始化
        Connection conn = null;                   //链接初始化
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from items;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPicture(rs.getString("picture"));
                item.setPrice(rs.getInt("price"));
                list.add(item);
            }
            return list;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        finally {
            if(rs!=null){
                try {
                    rs.close();
                    rs = null;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
            if(stmt!=null){
                try {
                    stmt.close();
                    stmt = null;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }

    //    返回商品资料
    public Items getItemsById(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.getConnection();
            String sql = "select * from items where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPicture(rs.getString("picture"));
                item.setPrice(rs.getInt("price"));
                return item;
            }
            else{
                return null;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        finally {
            if(rs!=null){
                try {
                    rs.close();
                    rs = null;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            if(stmt!=null){
                try {
                    stmt.close();
                    stmt = null;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Items> getViewList(String list) {  //传入一个list，返回其对应的商品
        ArrayList<Items> viewlist = new ArrayList<Items>();
        int iCount = 4;//每次返回前4条记录
        if (list != null && list.length() > 0) {
            String[] arr = list.split(",");
            if (arr.length>=4) {
                for (int i = arr.length-1; i >=arr.length-iCount; i--) {
                    Items item = new Items();
                    item = getItemsById(Integer.parseInt(arr[i]));
                    viewlist.add(item);
                }
            }
            else{
                for (int i = arr.length-1; i >=0; i--) {
                    Items item = new Items();
                    item = getItemsById(Integer.parseInt(arr[i]));
                    viewlist.add(item);
                }
            }
            return viewlist;
        }
        else{
            return null;
        }
    }
}

