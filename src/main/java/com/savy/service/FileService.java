package com.savy.service;


import com.alibaba.druid.util.StringUtils;
import com.savy.util.CommonUtil;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

@Service
public class FileService {


    //多文件上传
    public String uploadFile(MultipartFile files, String childFiled) {
        String fileUrl;//文件网络地址
        String classPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        try {
            classPath = URLDecoder.decode(classPath,"utf-8");//解决字符不识别的乱码问题
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("class所在目录为："+classPath);
        String projectPath=StringUtils.subString(classPath,"","savy");//获取项目所在目录
        System.out.println("项目路径为："+projectPath);
        String filePathString=projectPath+File.separator+"SavyFile"+File.separator+childFiled;//定义文件的真正保存目录
        File myFileDirectory = new File(filePathString);
        System.out.println(myFileDirectory.isDirectory()?filePathString+"是目录":filePathString+"是文件");
        if(!myFileDirectory.exists()){//文件目录不存在时进行创建（当给定路径没有后缀时，系统无法判断是否为文件夹）
            System.out.println("创建文件目录:"+myFileDirectory);
            boolean flag = myFileDirectory.mkdirs();
            if(!flag){
                System.out.println("目录创建失败！");
            }
        }else{
            System.out.println("文件已存在！本次不创建目录！");
        }
        String fileOriginalName = files.getOriginalFilename();   //初始文件名
//             String fileOriginalName = "1.png";   //初始文件名
        String fileSuffix = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));//文件后缀名称
        String  finalFileName = CommonUtil.generateRandomFilename()+fileSuffix;//生成随机文件名
        //String filePath = "E:/test_load/";
        String  finalFilePath = filePathString.toString()+File.separator+ finalFileName;//最终要保存的文件名
        System.out.println("最终保存的文件名为："+finalFilePath);
        File  finalFile = new File( finalFilePath);
        try {
            files.transferTo(finalFile);// 将传入的文件保存到指定位置
        } catch (IOException e) {
            e.printStackTrace();
        }
        finalFilePath =  finalFilePath.substring(1);//windows环境中去除地址前面的
        System.out.println("文件的绝对地址为："+finalFilePath);//图片的实际保存位置
        //将图片的绝对地址更改为相对地址,用于图片在浏览器中显示
//        finalFilePath = finalFilePath.replace("D:/WorkSpace/\\OnlineLearningFile\\Image","/photo");
        //获取项目地址
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("common.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileServerUrl = properties.getProperty("file_server_url");
        fileUrl = fileServerUrl+"/SavyFile/"+childFiled+"/"+finalFileName;//随web服务器发布，直接根据二级地址显示
        System.out.println("返回给前端的地址为："+fileUrl);
        return  fileUrl;
    }
}
