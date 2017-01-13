package com.wff.wff_tool.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wufeifei on 2017/1/11.
 */
public class Client {
    private static Socket clentSocket;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final int PORT = 8888;
    private static final String HOST = "10.108.6.89";

    public static void main(String[] args) {
        try {
            clentSocket = new Socket(HOST,PORT);
            executorService.execute(new ReadTask(clentSocket));
            executorService.execute(new WriteTask(clentSocket));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
