package com.coocaa.algorithm;

/**
 * Created by jiahuiyu on 2017/8/22.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
/**
        * 中文分词工厂类
        * 根据指定的分词算法返回分词实现
        * @author 杨尚川
        */
public class SegmentationFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(SegmentationFactory.class);
    private static final Map<String, Segmentation> pool = new HashMap<>();
    private SegmentationFactory(){};
    public static synchronized Segmentation getSegmentation(SegmentationAlgorithm segmentationAlgorithm){
        String clazz = "org.apdplat.word.segmentation.impl."+segmentationAlgorithm.name();
        Segmentation segmentation = pool.get(clazz);
        if(segmentation == null){
            LOGGER.info("构造分词实现类："+clazz);
            try {
                segmentation = (Segmentation)Class.forName(clazz).newInstance();
                pool.put(clazz, segmentation);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                LOGGER.info("构造分词实现类失败：", ex);
            }
        }
        return segmentation;
    }
}
