package com.coocaa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

import static java.nio.charset.Charset.forName;

/**
 * Created by jiahuiyu on 2017/8/11.
 */
public class sqlConnecttion {
        private static final String DRIVER="com.mysql.jdbc.Driver";
        private static final String URL = "jdbc:mysql://localhost:3306/iqiyi_recource";
        private static final String USERNAME="aa";
        private static final String PASSWORD="abcdefgjhy123321";

        /**
         * 连接数据库
         * @return 数据库连接对象
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public static Connection getConn()throws ClassNotFoundException,SQLException{
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return conn;
        }
        /**
         * 释放资源
         * @param conn
         * @param pstmt
         * @param rs
         * @throws SQLException
         */
        public static void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs)throws SQLException{
            if(rs!=null){
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }

        /**
         * 执行SQL语句，可以进行增、删、改的操作
         * @param sql
         * @return 影响条数
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public int executeSQL(String sql)throws ClassNotFoundException,SQLException{
            Connection conn = this.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            int number = pstmt.executeUpdate();
            this.closeAll(conn, pstmt, null);
            return number;
        }
}

