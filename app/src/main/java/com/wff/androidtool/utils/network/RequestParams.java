package com.wff.androidtool.utils.network;


/**
 * Created by wufeifei on 2016/11/21.
 */

public class RequestParams {
    private String baseUrl = "";
    private DownLoadBean downLoadBean;

    public RequestParams(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RequestParams() {
    }

    public RequestParams(DownLoadBean downLoadBean) {
        this.downLoadBean = downLoadBean;
    }

    public String getUrl() {
        return baseUrl;
    }

    public void setUrl(String url) {
        this.baseUrl = url;
    }

    public DownLoadBean getDownLoadBean() {
        return downLoadBean;
    }

    public void setDownLoadBean(DownLoadBean downLoadBean) {
        this.downLoadBean = downLoadBean;
    }
}
