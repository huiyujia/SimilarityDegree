package com.coocaa.algorithm;

import com.coocaa.movie.SimTable;

/**
 * 相似度排名结果
 * Created by jiahuiyu on 2017/8/22.
 */
public class Hit implements Comparable{
    private String text;
    private Double score;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        return ((Hit)o).getScore().compareTo(score);
    }

    @Override
    public String toString() {
        return score + " " +text;
    }
}
