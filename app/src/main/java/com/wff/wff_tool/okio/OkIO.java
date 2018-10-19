package com.wff.wff_tool.okio;

import android.os.Environment;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public class OkIO {
    public void testOkio() {
        File file = new File(Environment.getExternalStorageDirectory() + "/K3DX/GAME/Z+Game/Config/com.ww.ff/3dconfig.xml");
        File wFile = new File(Environment.getExternalStorageDirectory() + "/K3DX/GAME/Z+Game/Config/com.ww.ff/read.xml");
        Logger.d("ev:" + Environment.getExternalStorageDirectory());
        if (!file.exists()) {
            Logger.d("文件不存在");
            return;
        }
        Source source = null;
        BufferedSource bufferedSource = null;
        Sink sink = null;
        BufferedSink bufferedSink = null;
        try {
            source = Okio.source(file);
            bufferedSource = Okio.buffer(source);
            String str = bufferedSource.readString(Charset.forName("utf-8"));
            Logger.d(str);
            if (!wFile.exists()) {
                wFile.createNewFile();
            }
            sink = Okio.sink(wFile);
            bufferedSink=Okio.buffer(sink);
            bufferedSink.writeAll(bufferedSource);
            bufferedSink.flush();
        } catch (FileNotFoundException e) {
            Logger.d(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Logger.d(e.getMessage());
            e.printStackTrace();
        }

    }
}
