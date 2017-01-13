package com.wff.wff_tool.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wufeifei on 2017/1/11.
 */
public class Server {
    private static ServerSocket serverSocket;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final int port = 8888;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();//监听端口 接受链接
                executorService.execute(new ReadTask(socket));
                executorService.execute(new WriteTask(socket));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
