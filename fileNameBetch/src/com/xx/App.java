package com.xx;

import java.io.File;

public class App {
    public static void main(String[] args) {
        String[] sourceName = {"RJ-001","XJ-001"};
        String[] distName = {"RJ-002","XJ-002"};
        File sourceDir = new File("D:\\dist_source");
        File distDir = new File("D:\\dist");
        x(sourceName, distName, sourceDir, distDir);
    }
    private static void x(String[] sourceName, String[] distName, File sourceDir, File distDir) {
        if (sourceDir.isDirectory()) {
            File[] files = sourceDir.listFiles();
            for (File oldFile : files) {
                String absolutePath = oldFile.getAbsolutePath();
                String replace = absolutePath.replace(sourceDir.getAbsolutePath(), distDir.getAbsolutePath());
                for (int i = 0; i < sourceName.length; i++) {
                    replace=replace.replaceAll(sourceName[i],distName[i]);
                }
                File newFile = new File(replace);
                if (oldFile.isDirectory()) {
                    newFile.mkdirs();
                    x(sourceName,distName,oldFile,newFile);
                } else {
                    FileUtil.copy(oldFile, newFile, true);
                }
            }
        }
    }
}
