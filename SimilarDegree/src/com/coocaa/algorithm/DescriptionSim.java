package com.coocaa.algorithm;

/**
 * Created by jiahuiyu on 2017/8/21.
 */

import com.coocaa.movie.MovieModel;
import com.coocaa.movie.MovieUtil;
import com.coocaa.util.sqlConnecttion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

/**
 * 连接数据库，获得文本信息
 */
public class DescriptionSim{
    public MovieModel getFieldItems(){
        try {
            Connection conn = sqlConnecttion.getConn();
            String sql = "select v_id,focus,description from movies";//要执行的SQL语句
            PreparedStatement pstmt = conn.prepareStatement(sql);//创建一个Statement对象
            //数据库查询语句
            ResultSet rs = pstmt.executeQuery(sql);//创建数据对象
            MovieModel descriptionitem= MovieUtil.convertList(rs);
            sqlConnecttion.closeAll(conn,pstmt,rs);
            return descriptionitem;
        } catch (SQLException se) {
            System.out.println("数据库连接失败！");
            printStackTrace();
        }catch (ClassNotFoundException cn){
            System.out.println("数据库连接失败！");
            printStackTrace();
        }
        return getFieldItems();
    }

    /**
     * @input :textInformation
     * @output :textSimilarity
     * @param :
     */
    /**
     * 分词
     */
    public static class WordSegment{

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
