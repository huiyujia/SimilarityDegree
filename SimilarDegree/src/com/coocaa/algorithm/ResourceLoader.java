package com.coocaa.algorithm;

import java.util.List;

/**
 * Created by jiahuiyu on 2017/8/22.
 */
public interface ResourceLoader {
    /**
     * 清空数据
     */
    public void clear();
    /**
     * 初始加载全部数据
     * @param lines 初始全部数据
     */
    public void load(List<String> lines);
    /**
     * 动态增加一行数据
     * @param line 动态新增数据（一行）
     */
    public void add(String line);
    /**
     * 动态移除一行数据
     * @param line 动态移除数据（一行）
     */
    public void remove(String line);
}
