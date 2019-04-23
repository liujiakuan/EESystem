package util;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class WriteJson {
	public static void main(String[] args){
		//***********************************怎么规定限制这个字符串的格式
		saveJsonData("ljk","[a:\"aa\",b:{b1:[{a:2},{a:1}]},c:3,a:1,b:bbb1]");
	}
	public static void saveJsonData(String fileName,String jsonData){
		try {
			//建立一个新的文件
			File file=new File("D:/AAAAAAASoft/EclipseJavaEE/workspace/UserAction/WebContent/js/data/"+fileName+".json");
			char[] stack = new char[1024];// 存放括号，如 "{","}","[","]"  
	        int top = -1;
	        //自己写一个json转换插件
	        //String string = "[a:\"aa\",b:{b1:[{a:2},{a:1}]},c:3,a:1,b:"bbb"1]";
	        String string =jsonData;
	        StringBuffer sb = new StringBuffer();
	        char[] charArray = string.toCharArray();
	        for (int i = 0; i < charArray.length; i++) {
	            char c = charArray[i];
	            if ('{' == c || '[' == c) {
	                stack[++top] = c;// 将括号添加到数组中，这个可以简单理解为栈的入栈  
	                sb.append(charArray[i] + "\n");
	                for (int j = 0; j <= top; j++) {
	                    sb.append("\t");
	                }
	                continue;
	            }
	            if ((i + 1) <= (charArray.length - 1)) {
	                char d = charArray[i+1];
	                if ('}' == d || ']' == d) {
	                    top--;//将数组的最后一个有效内容位置下标减 1，可以简单的理解为将栈顶数据弹出  
	                    sb.append(charArray[i] + "\n");
	                    for (int j = 0; j <= top; j++) {
	                        sb.append("\t");
	                    }
	                    continue;
	                }
	            }
	            if (',' == c) {
	                sb.append(charArray[i] + "\n");
	                for (int j = 0; j <= top; j++) {
	                    sb.append("\t");
	                }
	                continue;
	            }
	            sb.append(c);
	        }
	        Writer write = new FileWriter(file);
	        write.write(sb.toString());
	        write.flush();
	        write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		}
	}
}
