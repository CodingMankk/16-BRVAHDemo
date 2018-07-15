package com.oztaking.www.a16_brvahdemo.MyUpFetchDemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.oztaking.www.a16_brvahdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：[1]下拉加载：符合聊天软件下拉历史数据需求
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class UpFetchActivity extends Activity{

    private RecyclerView mRv;

    private static final int PAGE_SIZE = 5;

    private List<UpFetchItem> mData;
    private UpFetchAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore_layout);

        initData();
        initView();
        initAdapter();
        initUpFetch();

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i=0; i<PAGE_SIZE; i++){
            UpFetchItem item = new UpFetchItem("Content" + i, "Content" + i, null);
            mData.add(item);
        }
    }

    //[1]下拉加载
    private void initUpFetch() {
        //设置下拉加载开关
        mAdapter.setUpFetchEnable(true);
        //开始加载的位置
        mAdapter.setStartUpFetchPosition(0);
        mAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                startUpFetch();
            }
        });
    }

    private int count =0;
    private static final int UP_FETCH_SIZE = 3; //每次取出数据的个数
    private void startUpFetch() {
        count++;
        mAdapter.setUpFetching(true);
        mRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addData(0,newUpFetchData(UP_FETCH_SIZE));
                mAdapter.setUpFetching(false);
                //为了模拟数据，此处只容许up-fetch数据3次
                if (count > 3){
                    mAdapter.setUpFetchEnable(false);
                }
            }
        },300);
    }



    private void initAdapter() {
        mAdapter = new UpFetchAdapter(R.layout.item_loadermore_view, mData);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mRv.setAdapter(mAdapter);

    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.id_recyclerView_loadMore);
    }


    private List<UpFetchItem> newUpFetchData(int mRequestPageSize){
        List<UpFetchItem> newUpFetchData = new ArrayList<>();
        for (int i=0; i<mRequestPageSize;i++){
            Random random = new Random();
            int randomInt = random.nextInt(100);
            Logger.d(randomInt);
            UpFetchItem newItem = new UpFetchItem("UpFetchTitle" + randomInt, "UpFetchContent" +
                    randomInt, null);
            newUpFetchData.add(newItem);
        }
        return newUpFetchData;
    }
}
