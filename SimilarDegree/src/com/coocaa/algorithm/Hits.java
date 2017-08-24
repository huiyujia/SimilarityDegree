package com.coocaa.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 相似度排名列表
 * Created by jiahuiyu on 2017/8/22.
 */
public class Hits {
    private List<Hit> hits = null;

    public Hits(){
        hits = new ArrayList<>();
    }

    public Hits(int size){
        hits = new ArrayList<>(size);
    }

    public int size(){
        return hits.size();
    }

    public List<Hit> getHits() {
        return Collections.unmodifiableList(hits);
    }

    public void addHits(List<Hit> hits) {
        this.hits.addAll(hits);
    }
    public void addHit(Hit hit) {
        this.hits.add(hit);
    }
}
