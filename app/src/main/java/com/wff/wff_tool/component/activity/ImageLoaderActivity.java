package com.wff.wff_tool.component.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wff.wff_tool.R;
import com.wff.wff_tool.testapi.LruCacheT;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageLoaderActivity extends Activity {

    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Bitmap bitmap=new LruCacheT(ImageLoaderActivity.this).get();
        img.setImageBitmap(bitmap);
        //ImageLoader.getInstance().displayImage("http://e.hiphotos.baidu.com/image/pic/item/241f95cad1c8a786f5d00e7a6a09c93d71cf50cf.jpg",img);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoader.getInstance().clearDiskCache();ImageLoader.getInstance().clearMemoryCache();

    }
}
