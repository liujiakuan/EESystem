package util;

import java.io.UnsupportedEncodingException;

public class Utf8 {
	public static void main(String[] args){
		//System.out.println(utf8("4-5"));
	}
	public Utf8(){
		
	}
	public static String utf8(String string){
		try {
			return new String(string.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};
		return null;
	}
}
