package com.wff.wff_tool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wufeifei on 2017/1/12.
 */

public class MessageBean extends BaseBean implements Parcelable {
    private int id;
    private String message = "default";
    private List<String> msg = new ArrayList<>();

    public MessageBean() {
        msg.add("msg1");
//        msg.add("msg2");
//        msg.add("msg3");
//        msg.add("msg4");
    }

    public List<String> getMsg() {
        return msg;
    }

    public MessageBean(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public MessageBean(String message) {
        this.message = message;
    }

    protected MessageBean(Parcel in) {
        id = in.readInt();
        message = in.readString();
        msg = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(message);
        dest.writeStringList(msg);
    }

    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public String toString() {
        return "MessageBean{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", msg=" + msg +
                '}';
    }

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

}
