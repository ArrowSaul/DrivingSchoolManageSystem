package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    public static Connection con = null;

    public Mysql(String username, String password) {
        //定义了一个数据的账号和密码
        //第一步加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动加载成功");
        }catch (Exception e){
            System.out.println("驱动加载失败");
        }


        String uri="jdbc:mysql://localhost:3306/car?characterEnxcoding=utf-8&useSSL=false";
        try{
            con= DriverManager.getConnection(uri,username,password);
            System.out.println("连接数据库成功");
        }catch (SQLException e){
            System.out.println("连接数据库失败");
        }
    }
}
