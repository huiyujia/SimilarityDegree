package com.coocaa.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by jiahuiyu on 2017/8/22.
 */
public class StopWord {
    private static final Logger LOGGER = LoggerFactory.getLogger(StopWord.class);
    private static final Set<String> stopwords = new HashSet<>();

    /**static {
        reload();
    }

    public static void reload() {
        AutoDetector.loadAndWatch(new ResourceLoader() {

            @Override
            public void clear() {
                stopwords.clear();
            }

            @Override
            public void load(List<String> lines) {
                LOGGER.info("初始化停用词");
                for (String line : lines) {
                    if (!isStopChar(line)) {
                        stopwords.add(line);
                    }
                }
                LOGGER.info("停用词初始化完毕，停用词个数：" + stopwords.size());
            }

            @Override
            public void add(String line) {
                if (!isStopChar(line)) {
                    stopwords.add(line);
                }
            }

            @Override
            public void remove(String line) {
                if (!isStopChar(line)) {
                    stopwords.remove(line);
                }
            }

        }, WordConfTools.get("stopwords.path", "classpath:stopwords.txt"));
    }
*/
    /**
     * 如果词的长度为一且不是中文字符和数字，则认定为停用词
     *
     * @param word
     * @return
     */
    private static boolean isStopChar(String word) {
        if (word.length() == 1) {
            char _char = word.charAt(0);
            if (_char < 48) {
                return true;
            }
            if (_char > 57 && _char < 19968) {
                return true;
            }
            if (_char > 40869) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一个词是否是停用词
     *
     * @param word
     * @return
     */
    public static boolean is(String word) {
        if (word == null) {
            return false;
        }
        word = word.trim();
        return isStopChar(word) || stopwords.contains(word);
    }

    /**
     * 停用词过滤，删除输入列表中的停用词
     *
     * @param words 词列表
     */
    public static void filterStopWords(List<Word> words) {
        Iterator<Word> iter = words.iterator();
        while (iter.hasNext()) {
            Word word = iter.next();
            if (is(word.getText())) {
                //去除停用词
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("去除停用词：" + word.getText());
                }
                iter.remove();
            }
        }
    }
}
