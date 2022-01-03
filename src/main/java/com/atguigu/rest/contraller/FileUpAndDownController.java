package com.atguigu.rest.contraller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileUpAndDownController {
    @RequestMapping(value = "/testDown")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        System.out.println("获取要下载文件的真实路径" + realPath);
        //创建输入流
        FileInputStream fileInputStream = new FileInputStream(realPath);
        //创建字节数组，数组大小以文件大小为准也就是里面的available方法
        byte[] bytes = new byte[fileInputStream.available()];
        //将流读到字节数组中
        fileInputStream.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> httpHeaders = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        httpHeaders.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, statusCode);
        //关闭输入流
        fileInputStream.close();
        return responseEntity;
    }

    @RequestMapping(value = "/testUp")
    public String testUp(MultipartFile multipartFile, HttpSession httpSession) throws IOException {
        //获取上传的文件的文件名
        String filename = multipartFile.getOriginalFilename();
      if(null != filename && !"".equals(filename)){
          //获取上传的文件的后缀名
          String suffixName = filename.substring(filename.lastIndexOf("."));
          //将UUID作为文件名追加，避免重复，微信下载的图片也这么做的
          String uuid = UUID.randomUUID().toString().replaceAll("-", "");
          //将uuid和后缀名拼接后的结果作为最终的文件名
          filename = uuid +  suffixName ;
          //通过ServletContext获取服务器中photo目录的路径
          ServletContext servletContext = httpSession.getServletContext();
          String photoPath = servletContext.getRealPath("photo");
          File file = new File(photoPath);
          //判断photoPath所对应路径是否存在
          if(!file.exists()){
              //若不存在，则创建目录
              file.mkdir();
          }
          String finalPath = photoPath + File.separator + filename;
          //上传文件
          multipartFile.transferTo(new File(finalPath));
          return "success";
      }else
           
        return "success";
      }


}
