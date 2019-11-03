package com.word.asmide.code;

import java.util.ArrayList;

public class CodeAnalysis {

    //需要进行分析的代码
    private String code;
    //代码分析类
    public CodeAnalysis (String code) {
        setCode(code);
    }

//    public ArrayList<Token> analysisCode() {
//    }

    public void setCode(String code) {
        this.code = code;
    }
}
