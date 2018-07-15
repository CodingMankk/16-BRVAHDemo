package com.oztaking.www.a16_brvahdemo.MyLoadMoreDemo;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：数据请求
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class RequestLoadMore extends Thread{


    private int mRequestPageSize;// 要刷新的页数

    private Handler mHandler;//数据发送handler

    private RequestCallBackLoadMore mCallBackLoadMore;//接口

    public RequestLoadMore(int requestPageSize,RequestCallBackLoadMore callBackLoadMore){
        this.mRequestPageSize = requestPageSize;
        this.mCallBackLoadMore = callBackLoadMore;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        if (mRequestPageSize == 0){
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                List<LoadMoreItem> mNewData = new ArrayList<>();
                for (int i=0; i<mRequestPageSize;i++){
                    LoadMoreItem newItem = new LoadMoreItem("newTitle" + i, "newContent" +
                            i, null);
                    mNewData.add(newItem);
                }
                mCallBackLoadMore.onSuccess(mNewData);
            }
        });

    }
}
