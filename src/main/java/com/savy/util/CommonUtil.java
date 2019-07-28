package com.savy.util;

import java.util.Calendar;
import java.util.Random;

//公共工具类
public class CommonUtil {


    //生成随机文件名，防止上传文件后文件名重复
    public static String generateRandomFilename(){
        String RandomFilename = "";
        Random rand = new Random();//生成随机数
        int random = rand.nextInt();

        Calendar calCurrent = Calendar.getInstance();
        int intDay = calCurrent.get(Calendar.DATE);
        int intMonth = calCurrent.get(Calendar.MONTH) + 1;
        int intYear = calCurrent.get(Calendar.YEAR);
        String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" +
                String.valueOf(intDay) + "_";

        RandomFilename = now + String.valueOf(random > 0 ? random : ( -1) * random);

        return RandomFilename;
    }
}
