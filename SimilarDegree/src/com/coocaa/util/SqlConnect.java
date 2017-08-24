package com.coocaa.util;

import java.sql.*;

/**
 * Created by jiahuiyu on 2017/8/9.
 */
public class SqlConnect {
    public static void main(String[] args) {
        try {
            //驱动程序名
            String driver = "com.mysql.jdbc.Driver";
            //URL指向要访问的数据库名mydata
            String url = "jdbc:mysql://localhost:3306/iqiyi_recource";
            //MySQL配置时的用户名
            String user = "root";
            //MySQL配置时的密码
            String password = "abcdefgjhy123321";
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            Connection conn = null;
            //创建Statement对象
            Statement stmt = null;
            try {
                Connection con = DriverManager.getConnection(url, user, password);
                stmt = con.createStatement();//创建一个Statement对象
                System.out.println("成功连接到数据库~");

                //数据库查询语句
                String sql = "select * from moviestxt";//要执行的SQL

                /*在询数据表时，需要用到ResultSet接口，它类似于一个数据表，通过该接口的实例
                 * 可以获得检索结果集，以及对应数据表的接口信息。*/
                ResultSet rs = stmt.executeQuery(sql);//创建数据对象
                System.out.println("v_id" + "\t" + "albumFocus" + "\t" + "tag" + "\t" + "score" + "\t" + "albumName" + "\t" + "albumCtime");
                //遍历查询的结果集
                while (rs.next()) {
                    System.out.print(rs.getInt(1) + "\t");
                    System.out.print(rs.getString(2) + "\t");
                    System.out.print(rs.getString(3) + "\t");
                    System.out.print(rs.getString(4) + "\t");
                    System.out.print(rs.getString(5) + "\t");
                    System.out.print(rs.getString(6) + "\t");
                    System.out.print(rs.getString(7) + "\t");
                    System.out.print(rs.getString(8) + "\t");
                    System.out.print(rs.getString(9) + "\t");
                    System.out.print(rs.getString(10) + "\t");
                    System.out.print(rs.getString(11) + "\t");
                    System.out.println();
                }
                //关闭连接
                if (rs!=null){rs.close();}
                if (stmt!=null){stmt.close();}
                if (conn!=null){conn.close();}
                //rs.close();
                //stmt.close();
                //conn.close();
            } catch (SQLException se) {
                System.out.println("数据库连接失败！");
                se.printStackTrace();
            }
        } catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println(ex.getClass().getName());
        }
    }

}
