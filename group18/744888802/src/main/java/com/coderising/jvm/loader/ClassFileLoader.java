package com.coderising.jvm.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		String filePath = getClassPath()+className.replace(".","\\")+".class";
		byte[] itemBuf = null;
		File file = new File(filePath);
		InputStream in = null;
		try {
			 in = new FileInputStream(file);

			itemBuf = new byte[(int)file.length()];
			in.read(itemBuf, 0, (int)file.length());


			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return itemBuf;
		
		
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		String result = null;
		StringBuffer sb = new StringBuffer();
		for(String classPath : this.clzPaths){
			sb.append(classPath+";");
		}
		if(sb.length() != 0){
			 result = sb.toString();
			 result = result.substring(0,result.length()-1);

		}

		return result;
	}

	

	

}
