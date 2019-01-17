package com.wff.androidtool.utils.network;

import android.text.TextUtils;


import com.wff.androidtool.dao.listener.ProgressListener;
import com.wff.androidtool.utils.FileUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by wufeifei on 2016/11/21.
 */

public class DownLoadBean {
    private String url;
    private String filePath;
    private ProgressListener progressListener;

    public DownLoadBean(String url) {
        this.url = url;
    }

    public String getFileName() {
        String fileName = "";
        String tempName = "filename=";
        String tempId = "fileid=";
        if (TextUtils.isEmpty(url)) {
            return fileName;
        }
        if (url.contains(tempName)) {
            fileName = url.substring(url.lastIndexOf(tempName) + tempName.length());
        } else if (url.contains(tempId)) {
            fileName = url.substring(url.lastIndexOf(tempId) + tempId.length());
        }

        try {
            return URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return fileName;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        return FileUtil.PARENTPARENT + getFileName();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ProgressListener getProgressListener() {
        return progressListener;
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }
}
