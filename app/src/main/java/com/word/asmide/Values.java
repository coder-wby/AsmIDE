package com.word.asmide;

import android.Manifest;
import android.graphics.Color;

public class Values {
    //权限相关
    static final String[] Permission = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    static int REQUEST_CODE = 2;
    //代码高亮相关
    public static final int TYPE_TEXT_COLOR = Color.BLACK;
    //不同类型代码的正则表达式
    public static class TYPE_REGULAR {
        public static final String LINE_TOKEN = "\n";
        public static final String INSTRUCTION_TOKEN = "MOV|ADD|SUB|JMP|PUSH|POP";
//        public static final String = ";"
    }
    //CodeEdtor相关
    //CodeEditor单行高度
    public static final int LINE_HEIGH = 10;
}
