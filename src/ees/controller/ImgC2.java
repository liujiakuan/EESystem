package ees.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImgC2 {
	
	@RequestMapping(value="/upLoadH.action")
	public String upLoadH(@RequestParam(value = "file", required = false) MultipartFile file, 
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception{
		//先判断是否是文件上传类型enctype="multipart/form-data"
		boolean isFileUpload = ServletFileUpload.isMultipartContent(request);
		//如果是文件上传类型
		if(isFileUpload){
		//得到文件上传工厂
		    FileItemFactory factory = new DiskFileItemFactory();
		    //处理文件上传核心类
		    ServletFileUpload fileUpload = new ServletFileUpload(factory);
		    //设置文件上传类的编码格式
		    fileUpload.setHeaderEncoding("UTF-8");
		    // 集合数据 :  FileItem对象 注意: 每一个表单域 对应一个 FileItem对象(封装)
		    @SuppressWarnings("unchecked")
			List<FileItem> fileItemList = fileUpload.parseRequest(request);
		    //遍历fileItemList
		    for(FileItem item: fileItemList){
		        //如果这个文本域是文件类型的
		        if(!item.isFormField()){
		        //得到文本域的value值，即 路径+文件名
		        String value = item.getName();
		        //得到文件名
		        String fileName = value.substring(value.lastIndexOf("\\")+1);
		        //得到上传的文件类型
		        //String fileType = fileName.substring(fileName.lastIndexOf("."));
		        //给文件重新命名 日期+文件名
		        fileName = new Date().getTime() + fileName;
		        //得到服务器的根路径
		        //String rootPath = request.getRealPath("/");
		        //指定文件存放路径
		        String realPath = "E:\\EESystemImg\\headImg";
		        //定义文件存放的目录，注意 目录也是文件
		 
		        File fil = new File(realPath);
		        //如果目录不存在
		        if(!fil.isDirectory()){
		            //创建文件上传目录
		            fil.mkdirs();
		        }
		        File newFile = new File(realPath+fileName);
		        //向newFile文件中写入数据
		        item.write(newFile);
		        }else {//如果不是文件上传的文本域，把输入的内容显示在页面上
		        	PrintWriter out = response.getWriter();
		            out.print("name=" + new String(
		item.getFieldName().getBytes("ISO-8859-1"),"utf-8")
		+",value="+ new String(
		item.getString().getBytes("ISO-8859-1"),"utf-8"));
		         }
		      }
		    }
		return null;
	}
}
