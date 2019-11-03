package com.word.asmide;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class FileHelper {

    private String currentPath;
    static final String STORAGE_DIR=  Environment.getExternalStorageDirectory().toString();


    FileHelper(String currentPath) {
        this.currentPath = currentPath;
	    formatCurrentPath();
    }

    void gotoDir(String dirName) {
       formatCurrentPath();
       setCurrentPath(getCurrentPath()+dirName);
    }

    void setCurrentPath(String currentPath) {
        formatCurrentPath();
        this.currentPath = currentPath;
    }

    String getCurrentPath() {
        formatCurrentPath();
        return currentPath;
    }

    //检查当前路径最后一个字符是否为/，如果不是则加上/，以此来标准化当前路径
    private void formatCurrentPath() {
        if(currentPath.charAt(currentPath.length() - 1) != '/') {
            this.currentPath = currentPath + "/";
        }
    }

    String readWithName(String name) throws IOException {
        File currentFile = new File(currentPath+name);
        if (currentFile.isFile()&&currentFile.canRead())
            return readWithPath(currentPath+name);
        else
            return null;
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

    public ArrayList<String> list() {
        ArrayList<String> list = new ArrayList<>();
        String[] filelist = new File(currentPath).list();
        for (String content:filelist) {
            list.add(content);
        }
        return list;
    }

    public void gotoParent() {
        formatCurrentPath();
        this.currentPath = new File(currentPath).getParent();
        formatCurrentPath();
    }
}
