package com.coocaa.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 词性
 * @author 杨尚川
 */
public class PartOfSpeech {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartOfSpeech.class);
    private String pos;
    private String des;

    public PartOfSpeech(String pos, String des) {
        this.pos = pos;
        this.des = des;
    }

    private static class PartOfSpeechMap {
        private static final Map<String, PartOfSpeech> POS = new HashMap<>();

        static {
            init();
        }

        private static void init() {
            AutoDetector.loadAndWatch(new ResourceLoader() {

                @Override
                public void clear() {
                    POS.clear();
                }

                @Override
                public void load(List<String> lines) {
                    LOGGER.info("初始化自定义词性说明");
                    int count = 0;
                    for (String line : lines) {
                        try {
                            String[] attr = line.split("=");
                            POS.put(attr[0], new PartOfSpeech(attr[0], attr[1]));
                            count++;
                        } catch (Exception e) {
                            LOGGER.error("错误的自定义词性说明数据：" + line);
                        }
                    }
                    LOGGER.info("自定义词性说明初始化完毕，数据条数：" + count);
                }

                @Override
                public void add(String line) {
                    try {
                        String[] attr = line.split("=");
                        POS.put(attr[0], new PartOfSpeech(attr[0], attr[1]));
                    } catch (Exception e) {
                        LOGGER.error("错误的自定义词性说明数据：" + line);
                    }
                }

                @Override
                public void remove(String line) {
                    try {
                        String[] attr = line.split("=");
                        POS.remove(attr[0]);
                    } catch (Exception e) {
                        LOGGER.error("错误的自定义词性说明数据：" + line);
                    }
                }

            }, WordConfTools.get("part.of.speech.des.path", "classpath:part_of_speech_des.txt"));
        }

        private static Map<String, PartOfSpeech> getPos() {
            return POS;
        }
    }

    public static PartOfSpeech valueOf(String pos) {
        if (Objects.isNull(pos) || "".equals(pos.trim())) {
            return I;
        }
        PartOfSpeech partOfSpeech = PartOfSpeechMap.getPos().get(pos.toLowerCase());
        if (partOfSpeech == null) {
            //未知词性
            return new PartOfSpeech(pos, "");
        }
        return partOfSpeech;
    }

    public static boolean isPos(String pos) {
        return PartOfSpeechMap.getPos().get(pos.toLowerCase()) != null;
    }

    //未知词性
    public static final PartOfSpeech I = new PartOfSpeech("i", "未知");

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
