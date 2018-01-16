package com.ysii.picture;

import java.io.*;

/**
 * 图片处理
 * update time 20180112
 * @author ysii
 */

public class PictureChange
{
	public static void  main(String args[]){
             System.out.println("PictureChange is test!");
	}

	/**
	 * 去掉图片文件中存在的空格(更名)
	 * update time 20180116
	 * @author ysii
	 */
	public static void changePhotoTrime(String path){
	     File file = new File(path);
		 String dirPath = file.getAbsolutePath();//目录路径
	     if (file.isDirectory()) {
	        File[] files = file.listFiles();//获取此目录下的文件列表
	        long starttime = System.currentTimeMillis();
           for (File fileFrom : files) {
	           String fromFile = fileFrom.getName();//得到单个文件名
	           int leng=fromFile.indexOf(".");
	           //去除存在的空格
	           String result=fromFile.substring(0,leng).trim();
	           String toFileName;
	           //获取文件后缀
	    	   String extension = fromFile.substring(fromFile.lastIndexOf(".")+1);
	    	   fromFile=result+"."+extension.toUpperCase();
	           toFileName = dirPath + "\\" + fromFile;
	           File toFile = new File(toFileName);
	           if (( fileFrom.exists() && !toFile.exists() )
	        		  || ( fileFrom.exists() && toFile.exists() && ! extension.equals(extension.toUpperCase()) )) {
	        	   //开始更名
	               fileFrom.renameTo(toFile);
	         }
	      }
	      long endtime = System.currentTimeMillis();
	      System.out.println("Time:" + new Long(endtime - starttime));//耗时
	     }
	}

}
