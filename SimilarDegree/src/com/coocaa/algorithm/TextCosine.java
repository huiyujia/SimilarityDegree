package com.coocaa.algorithm;

/**
 * Created by jiahuiyu on 2017/8/22.
 */

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 文本相似度计算
 * 判定方式：余弦相似度，通过计算两个向量的夹角余弦值来评估他们的相似度
 * 余弦夹角原理：
 * 向量a=(x1,y1),向量b=(x2,y2)
 * similarity=a.b/|a|*|b|
 * a.b=x1x2+y1y2
 * |a|=根号[(x1)^2+(y1)^2],|b|=根号[(x2)^2+(y2)^2]
 */
public class TextCosine extends TextSimilarity {
    /**
     * @param words1 词列表1
     * @param words2 词列表2
     * @return 相似度分值
     */
    @Override
    protected double scoreImpl(List<Word> words1, List<Word> words2) {
        //用词频来标注词的权重
        taggingWeightWithWordFrequency(words1, words2);
        //构造权重快速搜索容器
        Map<String, Float> weights1 = toFastSearchMap(words1);
        Map<String, Float> weights2 = toFastSearchMap(words2);
        //所有的不重复词
        Set<Word> words = new HashSet<>();
        words.addAll(words1);
        words.addAll(words2);
        //向量的维度为words的大小，每一个维度的权重是词频
        //a.b
        AutoFloat ab = new AutoFloat();
        //|a|的平方
        AutoFloat aa = new AutoFloat();
        //|b|的平方
        AutoFloat bb = new AutoFloat();
        //计算
        words
                .parallelStream()
                .forEach(word -> {
                    Float x1 = weights1.get(word.getText());
                    Float x2 = weights2.get(word.getText());
                    if (x1 != null && x2 != null) {
                        //x1x2
                        float oneOfTheDimension = x1 * x2;
                        //+
                        ab.addAndGet(oneOfTheDimension);
                    }
                    if (x1 != null) {
                        //(x1)^2
                        float oneOfTheDimension = x1 * x1;
                        //+
                        aa.addAndGet(oneOfTheDimension);
                    }
                    if (x2 != null) {
                        //(x2)^2
                        float oneOfTheDimension = x2 * x2;
                        //+
                        bb.addAndGet(oneOfTheDimension);
                    }
                });
        //|a|
        double aaa = Math.sqrt(aa.doubleValue());
        //|b|
        double bbb = Math.sqrt(bb.doubleValue());
        //使用BigDecimal保证精确计算浮点数
        //|a|*|b|
        //double aabb = aaa * bbb;
        BigDecimal aabb = BigDecimal.valueOf(aaa).multiply(BigDecimal.valueOf(bbb));
        //similarity=a.b/|a|*|b|
        //double cos = ab.get() / aabb.doubleValue();
        double cos = BigDecimal.valueOf(ab.get()).divide(aabb, 9, BigDecimal.ROUND_HALF_UP).doubleValue();
        return cos;
    }
}
