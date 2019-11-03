package com.word.asmide.code;

//此Token表示汇编指令
public class InstructionToken extends Token {
    public String content;

    public InstructionToken(String content) {
        super(content);
    }

}
