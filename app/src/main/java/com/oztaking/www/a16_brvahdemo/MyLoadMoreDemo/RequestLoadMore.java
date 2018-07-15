package com.oztaking.www.a16_brvahdemo.MyLoadMoreDemo;

import android.os.Handler;
import android.os.Looper;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    //主要为了模拟mCallBack.fail
    private int tempPageSize =0;
    private boolean isFirstError = false;

    public RequestLoadMore(int requestPageSize,RequestCallBackLoadMore callBackLoadMore){
        this.mRequestPageSize = requestPageSize;
        this.mCallBackLoadMore = callBackLoadMore;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {

        //延时500ms
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (mRequestPageSize == 0){
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {

//                mCallBackLoadMore.onFailed(new RuntimeException("数据加载失败"));

                List<LoadMoreItem> mNewData = new ArrayList<>();
                for (int i=0; i<mRequestPageSize;i++){
                    Random random = new Random();
                    int randomInt = random.nextInt(100);
                    Logger.d(randomInt);
                    LoadMoreItem newItem = new LoadMoreItem("newTitle" + randomInt, "newContent" +
                            randomInt, null);
                    mNewData.add(newItem);
                }
                mCallBackLoadMore.onSuccess(mNewData);
            }
        });

    }
}
