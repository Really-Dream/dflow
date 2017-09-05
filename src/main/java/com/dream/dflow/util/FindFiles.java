package com.dream.dflow.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 查询文件夹下的所有的文件
 */
public class FindFiles {

    public static List<String> list(String path){
        String basePath = FindFiles.class.getResource("/").getPath();
        basePath = basePath.substring(1,basePath.length());
        String[] s = new File(basePath+File.separator+path).list();
        List<String> list = Arrays.asList(s);
        return list;
    }

}
