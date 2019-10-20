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
    static class TYPE_REGULAR {
        public static final String NEXT_LINE = "\n";
//        public static final String = ";"
    }
}
