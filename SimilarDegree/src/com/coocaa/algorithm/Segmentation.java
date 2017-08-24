package com.coocaa.algorithm;

/**
 * Created by jiahuiyu on 2017/8/22.
 */
import java.util.List;

/**
 * 中文分词接口
 * Chinese Word Segmentation Interface
 * @author 杨尚川
 */
public interface Segmentation {
    /**
     * 将文本切分为词
     * @param text 文本
     * @return 词
     */
    public List<Word> seg(String text);
    /**
     * 分词器使用的算法
     * @return 分词算法
     */
    public SegmentationAlgorithm getSegmentationAlgorithm();
}
