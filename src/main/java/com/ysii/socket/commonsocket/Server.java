package main.java.com.ysii.socket.commonsocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 普通Socket通信服务器端
 * @author: yskkysll
 * @Date: Created in 18:35 2018/8/3
 * @Modify by:
 * @version 1.1.0
 */
public class Server {

    public static void main(String args[]){

        try{
            //创建一个ServerSocket监听8080端口
            ServerSocket serverSocket = new ServerSocket(8080);
            //等待请求
            Socket socket = serverSocket.accept();
            //接收到请求后使用socket进行通信，创建BufferedReader进行通信
            InputStreamReader is = new InputStreamReader(socket.getInputStream());
            BufferedReader bReader = new BufferedReader(is);
            //接收信息
            String line = bReader.readLine();
            //输出打印接收的信息
            System.out.println("received from client: " + line);
            //创建PrintWriter，用于发送数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("received data: " + line);
            pw.flush();
			/*关闭资源*/
            pw.close();
            bReader.close();
            socket.close();
            socket.close();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

}
