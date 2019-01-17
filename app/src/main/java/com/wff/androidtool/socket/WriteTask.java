package com.wff.androidtool.socket;

import android.text.TextUtils;

import com.wff.androidtool.bean.MessageBean;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wufeifei on 2017/1/11.
 * д��socket�߳�
 */
public class WriteTask implements Runnable {
    private Socket socket;
    private boolean isOver = false;
    private OutputStream outputStream;
    private MessageBean mMessag;

    public WriteTask(Socket socket) {
        this.socket = socket;
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (!isOver) {
                try {
                    if (outputStream == null) {
                        isOver = true;
                    }
                    if (mMessag!=null&&!TextUtils.isEmpty(mMessag.getMessage())){
                        outputStream.write(mMessag.getMessage().getBytes());
                        outputStream.flush();
                        mMessag.setMessage("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    isOver = true;
                }
            }
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setMessag(MessageBean messag) {
        mMessag = messag;
    }
}
