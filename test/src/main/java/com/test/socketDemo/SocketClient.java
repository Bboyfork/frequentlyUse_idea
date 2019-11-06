package com.test.socketDemo;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main (String[] args){
        SocketClient socketClient = new SocketClient();
        socketClient.scoketSend();
    }

    public void scoketSend(){
        Socket socket = null;
        String temp;
        try {
            socket = new Socket("192.168.56.1",1234);
            //不那么麻烦 直接读文件 往出打！static/socketTest.txt
            File file = new File("H:\\workspaceForIdea\\test\\src\\main\\resources\\static\\socketTest.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            do {
                temp = reader.readLine();
                System.out.println(temp);
                writer.println(temp);
            }while (!temp.equals("end"));
            writer.flush();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            do {
                temp = reader2.readLine();
                System.out.println(temp);
            }while(!temp.equals("end"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
