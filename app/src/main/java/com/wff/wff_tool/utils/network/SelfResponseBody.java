package com.wff.wff_tool.utils.network;


import com.wff.wff_tool.dao.listener.ProgressListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by wufeifei on 2016/11/18.
 */

public class SelfResponseBody extends ResponseBody {
    private final ResponseBody responseBody;
    private ProgressListener progressListener;
    private BufferedSource bufferedSource;

    public SelfResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }


    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        try {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                try {
                    long bytesRead = super.read(sink, byteCount);
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    if (progressListener != null) {
                        progressListener.onProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    }

                    return bytesRead;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };
    }
}
