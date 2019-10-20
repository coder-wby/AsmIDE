package com.word.asmide.code;

public class Token {

    private int index;
    private int type;
    private String text;

    public Token(int index,int type,String text) {
        this.index = index;
        this.type = type;
        this.text = text;
    }
}
