package com.word.asmide.code;

import androidx.annotation.Nullable;

import com.word.asmide.Values;

//此Token表示换行符
public class LineToken extends Token {
    public String content;

    public LineToken(@Nullable String content) {
        super(content);
        this.content = Values.TYPE_REGULAR.LINE_TOKEN;
    }
}
