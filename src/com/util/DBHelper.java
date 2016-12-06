package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class DBHelper {
    private static final String driver   = "com.mysql.jdbc.Driver";
    private static final String url      = "jdbc:mysql://127.0.0.1:3306/Test";
    private static final String username = "root";
    private static final String password = "";
    private static Connection conn       = null;

    //静态代码块负责加载驱动
    static {
        try {
            Class.forName(driver);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //单例模式返回数据库链接对象,整个链接对象只有一份拷贝
    public static Connection getConnection() throws Exception{
        if (conn == null){
            conn = DriverManager.getConnection(url,username,password);
            return conn;
        }
        return conn;
    }
}

