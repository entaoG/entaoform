package main.java.com.ysii.socket.commonsocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 普通Socket通信客户端
 * @author: yskkysll
 * @Date: Created in 18:38 2018/8/3
 * @Modify by:
 * @version 1.1.0
 */
public class Client {

    public static void main(String args[]){

        String msg = "Client Date";
        try{
            //创建一个Socket,端口为本机的8080端口
            Socket socket = new Socket("127.0.0.1",8080);
            //使用Socket创建PrintWriter和BufferedReader进行读写数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            InputStreamReader is = new InputStreamReader(socket.getInputStream());
            BufferedReader bReader = new BufferedReader(is);
            //发送数据
            pw.println(msg);
            pw.flush();
            //读取数据
            String line = bReader.readLine();
            System.out.println("received  from server  : " + line);
            //关闭资源
            pw.close();
            bReader.close();
            socket.close();

        } catch(Exception e){
             e.printStackTrace();
        }

    }

}
