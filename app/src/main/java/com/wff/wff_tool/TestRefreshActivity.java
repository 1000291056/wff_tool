package com.wff.wff_tool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.wff.wff_tool.ui.view.MyRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class TestRefreshActivity extends AppCompatActivity {
    private List<String> mList = new ArrayList<>();
    private MyRefreshListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView=new MyRefreshListView(this);
        setContentView(mListView);
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mList.add("aaa");
        mListView.setAdapter(new CommonRecyclerAdapter<String>(this, R.layout.item_image_layout, mList) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {

            }
        });
    }
}
