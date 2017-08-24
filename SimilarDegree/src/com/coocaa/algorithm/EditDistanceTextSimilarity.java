package com.coocaa.algorithm;

import java.util.List;

/**
 * Created by jiahuiyu on 2017/8/22.
 */
public class EditDistanceTextSimilarity extends TextSimilarity {
    /**
     * 计算相似度分值
     *
     * @param words1 词列表1
     * @param words2 词列表2
     * @return 相似度分值
     */
    @Override
    protected double scoreImpl(List<Word> words1, List<Word> words2) {
        //文本1
        StringBuilder text1 = new StringBuilder();
        words1.forEach(word -> text1.append(word.getText()));
        //文本2
        StringBuilder text2 = new StringBuilder();
        words2.forEach(word -> text2.append(word.getText()));
        int maxTextLength = Math.max(text1.length(), text2.length());
        if (maxTextLength == 0) {
            //两个空字符串
            return 1.0;
        }
        //计算文本1和文本2的编辑距离
        int editDistance = editDistance(text1.toString(), text2.toString());
        double score = (1 - editDistance / (double) maxTextLength);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("文本1：" + text1.toString());
            LOGGER.debug("文本2：" + text2.toString());
            LOGGER.debug("文本1和文本2的编辑距离：" + editDistance);
            LOGGER.debug("文本1和文本2的最大长度：" + maxTextLength);
            LOGGER.debug("文本1和文本2的相似度分值：1 - " + editDistance + " / (double)" + maxTextLength + "=" + score);
        }
        return score;
    }

    private int editDistance(String text1, String text2) {
        int[] costs = new int[text2.length() + 1];
        for (int i = 0; i <= text1.length(); i++) {
            int previousValue = i;
            for (int j = 0; j <= text2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else if (j > 0) {
                    int useValue = costs[j - 1];
                    if (text1.charAt(i - 1) != text2.charAt(j - 1)) {
                        useValue = Math.min(Math.min(useValue, previousValue), costs[j]) + 1;
                    }
                    costs[j - 1] = previousValue;
                    previousValue = useValue;

                }
            }
            if (i > 0) {
                costs[text2.length()] = previousValue;
            }
        }
        return costs[text2.length()];
    }
}
