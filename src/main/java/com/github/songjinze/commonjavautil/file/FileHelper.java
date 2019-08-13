package com.github.songjinze.commonjavautil.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    private static Logger logger = LoggerFactory.getLogger(FileHelper.class);

    public static String getProjectPath() {
        return File.class.getResource("/").getPath();
    }

    public static List<String> readFile(String filePath) {
        List<String> res=new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            res = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                res.add(line);
            }
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
        return res;
    }

    public static List<File> getSubFiles(String rootPath){
        List<File> files=new ArrayList<>();
        File file=new File(rootPath);
        if(file.isDirectory()){

        }else{
            logger.info("Path: \"{}\" is a file not a directory",rootPath);
            files.add(file);
        }
        return files;
    }

    public static boolean isExists(String path){
        File file=new File(path);
        return file.exists();
    }
}
