package com.wff.androidtool.bean;

import java.io.ObjectInputStream;

public class ImgBean{
    private int type;
    private byte[] img;
    private Object o;

    public int getType() {
        ObjectInputStream inputStream;
        return type;
    }

    public void setType(int type) {
        new String("aaa".getBytes());
        this.type = type;
    }


    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
