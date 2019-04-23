package ees.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImgC {
	//上传组织架构图的控制器
	@RequestMapping(value="/T.action")
	public String T(@RequestParam(value = "file", required = false) MultipartFile file, 
			HttpServletRequest request, ModelMap model){
		try {
		//图片存放路径
		String path="E:\\EESystemImg\\epOrganize";
		//图片原始名称
		String fileName = file.getOriginalFilename();
		//获取文件后缀名
		int dot=fileName.lastIndexOf(".");
		String ext = "";//文件后缀名
        if ((dot > -1) && (dot < (fileName.length() - 1))) {
            ext = fileName.substring(dot + 1);
        }
		//新图片
		File targetFile=null;
		if(fileName!=null){
			targetFile= new File(path+fileName);
		}
      //保存  ，并且判断是否是指定格式的图片
		if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)){
			file.transferTo(targetFile);
		}
		else{
			//上传不能保存
		}
		String epOrganizeImgPath="http://localhost:8080/headImg/epOrganize/"+fileName;
      model.addAttribute("epOrganizeImg",epOrganizeImgPath);//以后数据库就存储这个路径
      return "result";
      } catch (Exception e) {
          e.printStackTrace();
      }
     return null;
	}
}
