package com.coocaa.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//连接数据库

/**
 * 字符串相似性匹配算法
 * Created by panther on 15-7-20.
 * 余弦算法，距离公式
 */
public class Similarity extends AbstractSimilary {
    Map<String, int[]> vectorMap = new HashMap<String, int[]>();

    int[] tempArray = null;

    public Similarity(String string1, String string2) {

        String strArray[] = SpriteMethod(string1);
        for (int i = 0; i < strArray.length; ++i) {
            if (vectorMap.containsKey(strArray[i])) {
                vectorMap.get(strArray[i])[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(strArray[i], tempArray);
            }
        }

        strArray = SpriteMethod(string2);
        for (int i = 0; i < strArray.length; ++i) {
            if (vectorMap.containsKey(strArray[i])) {
                vectorMap.get(strArray[i])[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(strArray[i], tempArray);
            }
        }
    }


    // 求余弦相似度
    public double sim() {
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }

    private double sqrtMulti(Map<String, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }

    // 求平方和  
    private double squares(Map<String, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<String> keySet = paramMap.keySet();
        for (String character : keySet) {
            int temp[] = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    // 点乘法  
    private double pointMulti(Map<String, int[]> paramMap) {
        double result = 0;
        Set<String> keySet = paramMap.keySet();
        for (String character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }


    /*
     * 抽象字符分隔方法，为后续使用分词库进行分词做准备
     *
     */
    @Override
    protected String[] SpriteMethod(String spriteSpring) {
        return spriteSpring.split(",");
    }

    /*
    *
    *分词
    *  */
    @Override
    protected Map<String, int[]> SplitString(String splitString) {
        return null;
    }
    public static void main(String[] args) {
        String s1 = "20170213";
        String s2 = "20170213";
        Similarity similarity = new Similarity(s1, s2);
        System.out.println(similarity.sim());
    }
}