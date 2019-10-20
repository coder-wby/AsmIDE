package com.word.asmide;

import java.util.ArrayList;

@Deprecated
public class HighlightArray{

    //用于标识token的类型，如注释，伪指令等
    private ArrayList<Integer> Element_TYPE;
    //用于描述token的内容
    private ArrayList<String> Element_STRING;
    //用于描述总长度
    private int length;

    public HighlightArray() {
        Element_TYPE = new ArrayList<>();
        Element_STRING = new ArrayList<>();
    }

    public void add(int index,int TYPE,String STRING) {
        Element_TYPE.add(index,TYPE);
        Element_STRING.add(index,STRING);
    }

    public void add(int TYPE, String STRING) {
        Element_TYPE.add(TYPE);
        Element_STRING.add(STRING);
    }

    public int getType(int index) {
        return Element_TYPE.get(index);
    }

    public String getString(int index) {
        return Element_STRING.get(index);
    }

    public int getLength() {
        return length;
    }
}
