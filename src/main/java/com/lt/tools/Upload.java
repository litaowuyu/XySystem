package com.lt.tools;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Component
public class Upload {
    public String upload(MultipartFile photo,String imgType) throws IOException {
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        //photo目录的路径
//        ServletContext servletContext = session.getServletContext();
//        String photoPath = servletContext.getRealPath("photo");
        String photoPath = null;
        if(imgType=="goodsImg"){
             photoPath = "D:\\GraduationPaper\\Project\\view\\xy\\src\\assets\\images";
        }else {
             photoPath = "D:\\GraduationPaper\\Project\\view\\xy\\src\\assets\\avatar";
        }

        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        //实现上传功能
        photo.transferTo(new File(finalPath));
        return fileName;
    }
}
