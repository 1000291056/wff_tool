package com.wff.wff_tool.utils.network;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import com.wff.wff_tool.BaseActivity;
import com.wff.wff_tool.dao.listener.CallBack;
import com.wff.wff_tool.ui.dialog.MyProgressDialog;
import com.wff.wff_tool.utils.FileUtil;

import java.io.File;

/**
 * Created by wufeifei on 2016/11/18.
 */

public class NetWork {
    private Handler mHandler = new Handler(Looper.myLooper());
    private MyProgressDialog mProgressDialog;

    /**
     * 默认弹框
     *
     * @param
     * @param context
     */
    public void downLoad(DownLoadBean downLoadBean, Context context) {
        mProgressDialog = new MyProgressDialog(context);
        downLoad(downLoadBean, context, null);
    }

    /**
     * 下载文件
     *
     * @param
     * @param context
     */
    public void downLoad(final DownLoadBean downLoadBean, final Context context, final CallBack callBack) {
//        try {
//
//            File file = new File(downLoadBean.getFilePath());
//            if (file.exists() && file.length() > 0) {
//                openFile(context, file);
//                return;
//            }
//            downLoadBean.setProgressListener(mProgressDialog.getProgressListener());
//            if (context instanceof BaseActivity) {
//                BaseActivity baseActivity = (BaseActivity) context;
//                if (mProgressDialog != null && baseActivity.isNormol()) {
//                    mProgressDialog.show();
//                }
//            }
//            RequestParams params = new RequestParams(downLoadBean);
//            RetrofitUtil.getRetrofit(context, params).create(DownLoadService.class).downloadFile(downLoadBean.getUrl())
//                    .subscribeOn(Schedulers.io())
//                    .map(new Func1<ResponseBody, File>() {
//                        @Override
//                        public File call(ResponseBody responseBody) {
//                            String parentParent = FileUtil.PARENTPARENT;
//                            String fileName = downLoadBean.getFileName();
//
//                            final File parent = new File(parentParent);
//                            Log.i(NetWork.class.getSimpleName(), "可读" + parent.canRead());
//                            Log.i(NetWork.class.getSimpleName(), "可写" + parent.canWrite());
//                            Log.i(NetWork.class.getSimpleName(), "可执行" + parent.canExecute());
//                            if (!parent.exists()) {
//                                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                                    parent.mkdirs();
//                                }
//                            } else {
//                            }
//                            final File file = new File(parentParent + fileName);
//                            try {
//                                if (!file.exists()) {
//                                    file.createNewFile();
//                                }
//
//                                byte[] buffer = responseBody.bytes();
//                                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
//                                randomAccessFile.write(buffer);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                mHandler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(context, "文件创建失败", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                            return file;
//                        }
//                    }).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<File>() {
//                        @Override
//                        public void onStart() {
//                            super.onStart();
//
//                        }
//
//                        @Override
//                        public void onCompleted() {
//                            if (mProgressDialog != null && mProgressDialog.isShowing()) {
//                                mProgressDialog.dismiss();
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            e.printStackTrace();
//                            if (mProgressDialog != null && mProgressDialog.isShowing()) {
//                                mProgressDialog.dismiss();
//                            }
//                        }
//
//                        @Override
//                        public void onNext(File file) {
//                            openFile(context, file);
//                            if (callBack != null) {
//                                callBack.callBack(file);
//                            }
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 打开文件
     *
     * @param context
     * @param file
     */
    private void openFile(Context context, File file) {
        try {

            Intent intent;
            intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, FileUtil.getMIMEType(file));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void open(final Context context, final File file) {
        try {
            if (context instanceof BaseActivity) {
            } else {
                openFile(context, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
