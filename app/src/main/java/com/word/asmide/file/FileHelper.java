package com.word.asmide.file;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileHelper {

    private String currentPath;
    public static final String STORAGE_DIR=  Environment.getExternalStorageDirectory().toString();

    public FileHelper(String currentPath) {
        this.currentPath = currentPath;
	    formatCurrentPath();
    }

    public void gotoDir(String dirName) {
       formatCurrentPath();
       setCurrentPath(getCurrentPath()+dirName);
    }

    public void setCurrentPath(String currentPath) {
        formatCurrentPath();
        this.currentPath = currentPath;
    }

    public String getCurrentPath() {
        formatCurrentPath();
        return currentPath;
    }

    //检查当前路径最后一个字符是否为/，如果不是则加上/，以此来标准化当前路径
    private void formatCurrentPath() {
        if(currentPath.charAt(currentPath.length() - 1) != '/') {
            this.currentPath = currentPath + "/";
        }
    }

    public String readWithName(String name) throws IOException {
        return readWithPath(name);
    }

    /***
     *
     * @param path 文件绝对路径
     * @return 文件内容
     * @throws IOException
     */

    private static String readWithPath(String path) throws IOException {
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedOutputStream = new BufferedInputStream(inputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedOutputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append("\n");
        }
        return  content.toString();
    }

    public String[] list() {
        return new File(currentPath).list();
    }

    public void gotoParent() {
        formatCurrentPath();
        new File(currentPath).getParent();
        formatCurrentPath();
    }
}
