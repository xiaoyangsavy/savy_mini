package com.savy.controller;

import com.alibaba.druid.util.StringUtils;
import com.savy.util.CommonUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.savy.service.FileUploadUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/file")
@Controller
public class UploadController{

    //上传
    @RequestMapping(value = "/upload",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String,Object> up(MultipartFile fileData){
        System.out.println("调用上传文件接口！");
        System.out.println(fileData!=null?"not null":"null");
        Map<String,Object> resultMap =new HashMap<String,Object>();//根据前端富文本编辑器的要求，返回固定字段
        boolean isSuccessFlag = true;   //文件是否保存成功
        if(fileData.isEmpty()){ //文件为空时返回失败
            isSuccessFlag = false;
        }
        // List<MultipartFile> files=(Mul)(request)
        String classPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
          System.out.println("class所在目录为："+classPath);
        String projectPath=StringUtils.subString(classPath,"","savy");//获取项目所在目录
        System.out.println("项目路径为："+projectPath);
        String filePathString=projectPath+File.separator+"SavyFile"+File.separator+"Image";//定义文件的真正保存目录
        File myFileDirectory = new File(filePathString);
        System.out.println(myFileDirectory.isDirectory()?filePathString+"是目录":filePathString+"是文件");
        if(!myFileDirectory.exists()){//文件目录不存在时进行创建（当给定路径没有后缀时，系统无法判断是否为文件夹）
            System.out.println("创建文件目录:"+myFileDirectory);
            myFileDirectory.mkdirs();
        }
            String fileOriginalName = fileData.getOriginalFilename();   //初始文件名
//             String fileOriginalName = "1.png";   //初始文件名
            String fileSuffix = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));//文件后缀名称
            String  finalFileName = CommonUtil.generateRandomFilename()+fileSuffix;//生成随机文件名
            //String filePath = "E:/test_load/";
            String  finalFilePath = filePathString.toString()+File.separator+ finalFileName;//最终要保存的文件名
            System.out.print("最终保存的文件名为："+finalFilePath);
            File  finalFile = new File( finalFilePath);
        try {
            fileData.transferTo(finalFile);// 将传入的文件保存到指定位置
        } catch (IOException e) {
            isSuccessFlag = false;
            e.printStackTrace();
        }

        finalFilePath =  finalFilePath.substring(1);//windows环境中去除地址前面的
        System.out.print("返回给前端的地址为："+finalFilePath);
        
        if(isSuccessFlag) {
            resultMap.put("success", true);  //保存成功
            resultMap.put("file_path",finalFilePath);
        }else{
            resultMap.put("success", false);  //保存失败
        }
        return resultMap;
    }
    //下载
    @ResponseBody
    @RequestMapping(value = "/download",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void testDownload (@RequestParam(name = "fileName",required = false) String fileName, HttpServletResponse  response) {
        //获取文件的路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String p=StringUtils.subString(path,"","MessageBoard");
        path=p+"filed/"+fileName;
        // System.out.println("------------------------"+path);
        //File file=new File("E:/sex.jpg");//     //1.获取要下载的文件的绝对路径
        File file=new File(path);
        String newDname=fileName;     //2.获取要下载的文件名
        if(file.exists()) {  //判断文件父目录是否存在
            System.out.println("aaa");
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + newDname);  //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
            byte[] buff = new byte[1024];    //5.创建数据缓冲区
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = response.getOutputStream(); //6.通过response对象获取OutputStream流
                bis = new BufferedInputStream(new FileInputStream(file));     //4.根据文件路径获取要下载的文件输入流
                int i = bis.read(buff);         //7.将FileInputStream流写入到buffer缓冲区
                while (i != -1) {
                    os.write(buff, 0, buff.length); //8.使用将OutputStream缓冲区的数据输出到客户端浏览器
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @RequestMapping(value = "/batch",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "E:/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage();
                }
            } else {
                return "第 " + i
                        + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }

}
