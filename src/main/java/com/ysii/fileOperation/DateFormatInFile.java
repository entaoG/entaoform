package com.ysii.fileOperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 源文件中日期的格式为month/day/year
 * 将其改为year/month/day格式
 * @author yskkysll
 * @version 1.1.0
 *
 */
public class DateFormatInFile {

    public static void main(String args[]){
        System.out.println("Start");
		/*源文件路径*/
        String srcPath = "...";
        /*目标文件路径*/
        String tarPath= "...result_";
        File file = new File(srcPath);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File fileFrom : files){
                if(fileFrom.getName().endsWith("txt")){
                    ChangeLocToFile(fileFrom.getAbsolutePath() , tarPath +fileFrom.getName());
                }
            }
        }
        System.out.println("End");
    }


    /**
     * 将其改为year/month/day格式
     * @param srcPath 源目录
     * @param tarPath 写入文件目录
     * @return
     */
    private static void ChangeLocToFile(String srcPath ,String tarPath){
        File file = new File(srcPath);
        FileInputStream in = null;
        InputStreamReader iReader = null;
        BufferedReader bufReader = null;
        FileWriter fw = null;
        try {
            in = new FileInputStream(file);
            iReader = new InputStreamReader(in,"UTF-8");
            bufReader = new BufferedReader(iReader);
            String line = null;
            fw = new FileWriter(tarPath, true);
            while((line = bufReader.readLine() )!= null){
                if(!"".equals(line)){
					/*分隔符/截取*/
                    String dateContent[] = line.split("/");
					/*年*/
                    String year = dateContent[dateContent.length-1];
					/*月*/
                    String month = dateContent[0];
					/*日*/
                    String day = dateContent[1];
                    String newDate = year + "/" +month + "/" + day;
                    System.out.println(newDate);
                    //追加写入追加方式写入
                    fw.write(newDate+"\r\n");
                }else{
                    System.out.println(line);
                    //追加写入追加方式写入
                    fw.write(line+"\r\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fw.close();
                bufReader.close();
                iReader.close();
                in.close();
            } catch (IOException e) {
                System.out.println("流关闭异常！");
            }
        }
    }
}
