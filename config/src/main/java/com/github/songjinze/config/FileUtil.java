package com.github.songjinze.config;

import java.io.File;

public class FileUtil {
    public static boolean isExists(String path){
        File file=new File(path);
        return file.exists();
    }




}
