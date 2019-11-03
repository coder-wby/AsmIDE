package com.word.asmide.code;

import com.word.asmide.Values;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    //需要进行分析的代码
    private String path;

    public Lexer(String path) {
        setPath(path);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Token> lex() throws IOException {
        ArrayList<Token> tokens = new ArrayList<Token>();
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedOutputStream = new BufferedInputStream(inputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedOutputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            lexLine(line);
        }
        reader.close();
        return tokens;
    }

    private ArrayList<Token> lexLine(String line) throws IOException {
        ArrayList<Token> tokens = new ArrayList<Token>();
        Pattern pattern = Pattern.compile(Values.TYPE_REGULAR.INSTRUCTION_TOKEN,
                Pattern.CASE_INSENSITIVE //不区分大小写
        );
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            InstructionToken instructionToken = new InstructionToken(matcher.group());
            tokens.add(instructionToken);
        }
        tokens.add(new LineToken(null));
        return tokens;
    }
}



