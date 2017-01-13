package com.wff.wff_tool.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wufeifei on 2017/1/12.
 */

public class MessageBean extends BaseBean implements Parcelable {
    private int id;
    private String message;

    public MessageBean() {
    }

    public MessageBean(String message) {
        this.message = message;
    }

    protected MessageBean(Parcel in) {
        id = in.readInt();
        message = in.readString();
    }

    public static final Creator<MessageBean> CREATOR = new Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel in) {
            return new MessageBean(in);
        }

        @Override
        public MessageBean[] newArray(int size) {
            return new MessageBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(message);
    }
}
