package com.test.socketDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {

    public static void main (String[] args){
        SocketService socketService = new SocketService();
        socketService.oneServer();
    }

    public void oneServer(){
        try {
            ServerSocket server = null;
            try {
                //绑定端口
                server = new ServerSocket(33333);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("端口绑定异常");
            }
            Socket socket = null;
            try {
                //调用accept方法开始监听，等待客户端连接
                //使用accept()阻塞等待用户请求，有客户请求到来，
                //则产生一个Socket对象，并继续执行。
                socket = server.accept();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("accept方法调用异常");
            }

            //获取输入流，并读取客户端信息。
            String line;
            //得到输入流，并装到BufferedReader对象
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //得到输出流，并装到PringWriter对象
            //PrintWriter writer = new PrintWriter(socket.getOutputStream());

            //由系统标准输入设备构造的BufferedReader对象
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            //line = br.readLine();

            //获取输出流，响应客户端请求
            do{    //一个简陋的结束手段
                line = in.readLine();
                System.out.println("Client:"+line);
            }while(!line.equals("end"));


            //writer.close();
            in.close();
            socket.close();
            server.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }
}
