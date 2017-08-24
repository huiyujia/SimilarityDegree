package com.coocaa.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hadoop on 2017/3/30.
 */
abstract class AbstractSimilary {

    Map<String, int[]> vectorMap = new HashMap<String, int[]>();

    protected abstract String[] SpriteMethod(String spriteSpring);

    protected abstract  Map<String, int[]> SplitString(String splitString);

}
