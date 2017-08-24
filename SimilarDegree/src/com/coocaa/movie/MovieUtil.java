package com.coocaa.movie;

import com.coocaa.algorithm.*;
import com.coocaa.util.sqlConnecttion;

import java.sql.*;
import java.util.*;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

/**
 * Created by jiahuiyu hon 2017/8/22.
 * <p/>
 * caculate movie itmes' simiary Value and save them
 */
public class MovieUtil {
    //相似度权重因子，演员，导演，标题，年份，分类（标签）,描述，focus,发布时间
    private static double[] sFactor = new double[]{0.1, 0.2, 0.3, 0.4, 0.4, 0.5, 0.5, 0.5};


    /* @input
    *  @output List<MovieModel>
    * @description 获取需要比较的影片列表
    */
    public static ArrayList getMovieItems() {
        try {
            Connection conn = sqlConnecttion.getConn();
            String sql = "select * from movies";//要执行的SQL语句
            PreparedStatement pstmt = conn.prepareStatement(sql);//创建一个Statement对象
            //数据库查询语句
            ResultSet rs = pstmt.executeQuery(sql);//创建数据对象
            ArrayList movieitems = resultSetToList(rs);
            sqlConnecttion.closeAll(conn, pstmt, rs);
            return movieitems;
        } catch (SQLException se) {
            System.out.println("数据库连接失败！");
            printStackTrace();
        } catch (ClassNotFoundException cn) {
            System.out.println("数据库连接失败！");
            printStackTrace();
        }
        return getMovieItems();
    }

    // TODO 把Resultset转化为list
    /**
     * @input Resultset
        *@ output list
        * 把Resultset转化为list
         */
    public static ArrayList<MovieModel> resultSetToList(ResultSet rs) throws java.sql.SQLException {
        //if (rs == null)
        //  return Collections.EMPTY_LIST;
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
        ArrayList<MovieModel> list = new ArrayList<MovieModel>();
        ArrayList<MovieModel> rowData = new ArrayList<MovieModel>();
        while (rs.next()) {
            rowData = new ArrayList<MovieModel>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
            System.out.println("list:" + list.toString());
        }
        return list;
    }

    /**
     * @param
     * @param
     * @return moviesize
     */
    public static int movieSize() {
        try {
            Connection conn = sqlConnecttion.getConn();
            String sql = "select * from movies";//要执行的SQL语句
            PreparedStatement pstmt = conn.prepareStatement(sql);//创建一个Statement对象
            //数据库查询语句
            ResultSet rs = pstmt.executeQuery(sql);//创建数据对象
            MovieModel list = new MovieModel();
            ResultSetMetaData md = rs.getMetaData();//获取键名
            int columnCount = md.getColumnCount();//获取行的数量
            return columnCount;
        } catch (SQLException se) {
            System.out.println("数据库连接失败！");
            printStackTrace();
        } catch (ClassNotFoundException cn) {
            System.out.println("数据库连接失败！");
            printStackTrace();
        }
        return movieSize();
    }

    /* @input sourceItem ,compareItem
    *  @output  simiaryValue
    * @description "计算两部影片的相似度"
    */
    public static double caculateSimiaryValue(MovieModel item1, MovieModel item2) {
        double simiaryValue = 0.0;
        //演员相似度
        String s1 = item1.getActor();
        String s2 = item2.getActor();
        Similarity similarity = new Similarity(s1, s2);
        double actorSim = similarity.sim();
        //导演相似度
        s1 = item1.getDirector();
        s2 = item2.getDirector();
        similarity = new Similarity(s1, s2);
        double directorSim = similarity.sim();
        //标题相似度
        s1 = item1.getTitle();
        s2 = item2.getTitle();
        similarity = new Similarity(s1, s2);
        double titleSim = similarity.sim();
        //年份相似度
        s1 = item1.getYear();
        s2 = item2.getYear();
        similarity = new Similarity(s1, s2);
        double yearSim = similarity.sim();
        //分类，标签相似度
        s1 = item1.getClassfication();
        s2 = item2.getClassfication();
        similarity = new Similarity(s1, s2);
        double claSim = similarity.sim();
        //TODO 文本相似度计算
        //focus相似度分析
        s1 = item1.getFocus();
        s2 = item2.getFocus();
        TextSimilarity focusSimilarity = new TextCosine();
        double focusSim = focusSimilarity.similarScore(s1, s2);
        //description相似度
        s1 = item1.getDescription();
        s2 = item2.getDescription();
        TextSimilarity descSimilarity = new TextCosine();
        double descSim = descSimilarity.similarScore(s1, s2);

        simiaryValue = actorSim * sFactor[0] + directorSim * sFactor[2] + titleSim * sFactor[1] + yearSim * sFactor[3] + claSim * sFactor[4] + focusSim * sFactor[5] + descSim * sFactor[6];
        System.out.println("相似度" + simiaryValue);
        return simiaryValue;
    }

    // TODO 保存两部影片的相似度
    /* @input sourceItem ,compareItem，simiaryValue
        *  @output saveResult
        * @description "保存两部影片的相似度"
        */
    public static SimTable saveMovieSimiaryValue(MovieModel sourcedata, MovieModel comparedata, double similayValue) {
        SimTable saveResult = new SimTable();
        Integer sourcedata_id = sourcedata.getV_id();
        Integer comparedata_id = comparedata.getV_id();
        similayValue = caculateSimiaryValue(sourcedata, comparedata);
        try {
            Connection conn = sqlConnecttion.getConn();
            String sql = "insert into simTable(id_1,id_2,sim)values" + sourcedata_id + "," + comparedata_id + "," + similayValue;
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, saveResult.getid_1());
            preStmt.setString(2, saveResult.getid_2());
            preStmt.setFloat(3, saveResult.getsim());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cn) {
            printStackTrace();
        }
        return saveResult;
    }

    /**
     * @param id_1
     * @param id_2
     * @param sim
     * @return simlist
     */
    //TODO 保存相似影片_id
    public static Hits saveSimList(SimTable id_1, SimTable id_2, SimTable sim) {
        ArrayList simlist = new ArrayList();
        SimTable saveResult = new SimTable();
        Hits simidlist=new Hits();
        String id1 = saveResult.getid_1();
        String id2 = saveResult.getid_2();
        double simv = saveResult.getsim();
        //将表中的数据存入HashMap中，key为某一影片id,value为相似影片id列表
        HashMap maps = new HashMap();
        //ArrayList id2list = simlist.simmovieslist;
        TextSimilarity textsim = new TextSimilarity() {
            @Override
            protected double scoreImpl(List<Word> words1, List<Word> words2) {
                return 0;
            }
        };
        if (id2 != id1) {
            for (int i = 0; i < movieSize(); i++) {
                simlist.add(id2);
                simidlist = textsim.rank(id1, simlist, 15);
                maps.put(id1, simlist);
            }
        }
        return simidlist;
    }

    /**
     * 将计算得到的数据存入表格
     *
     * @param id1
     * @param simidlist
     * @exceptions SQLException,ClassNotFoundException
     */
    public static void putInTable(String id1, Hits simidlist) {
        SimMovieTable simtable = new SimMovieTable();
        try {
            Connection conn = sqlConnecttion.getConn();
            String sql = "inset into SimMovieTable(id1,simlist)values " + id1 + "," + simidlist;//要执行的SQL语句
            PreparedStatement pstmt = conn.prepareStatement(sql);//创建一个Statement对象
            //数据库查询语句
            ResultSet rs = pstmt.executeQuery(sql);//创建数据对象
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, simtable.getMovie_id());
            preStmt.setString(2, simtable.getsimmovieslist());
            preStmt.executeUpdate();

        } catch (SQLException se) {
            System.out.println("数据库连接失败！");
            printStackTrace();
        } catch (ClassNotFoundException cn) {
            System.out.println("数据库连接失败！");
            printStackTrace();
        }
    }
}
