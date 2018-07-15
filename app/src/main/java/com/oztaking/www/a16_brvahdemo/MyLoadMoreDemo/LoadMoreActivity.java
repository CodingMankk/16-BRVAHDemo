package com.oztaking.www.a16_brvahdemo.MyLoadMoreDemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oztaking.www.a16_brvahdemo.R;

import java.util.ArrayList;
import java.util.List;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：[1]下拉刷新（mAdapter.setNewData(data);）[2]上拉加载（mAdapter.addData(data);）
 *          可以无限刷新和加载
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class LoadMoreActivity extends Activity{

    private RecyclerView mRv;
    private SwipeRefreshLayout mSRLayout;

    private static final int PAGE_SIZE = 5;

    private List<LoadMoreItem> mData;
    private LoadMoreAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore_layout);

        initData();
        initView();
        initAdapter();
        initSwipeRefreshLayout();

        //替换加载布局
        mAdapter.setLoadMoreView(new CustomMoreView());
    }

    private void initLoadMore() {
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
    }

    private static final int LOAD_MORE_SIZE = 3;
    private void loadMore() {
        new RequestLoadMore(LOAD_MORE_SIZE, new RequestCallBackLoadMore() {
            @Override
            public void onSuccess(List<LoadMoreItem> data) {
                int size = data.size();
                if (data.size() >0){
                    mAdapter.addData(data);
                }

                if (size < PAGE_SIZE) {
                    //第一页如果不够一页就不显示没有更多数据布局
                    mAdapter.loadMoreEnd(false);
                    Toast.makeText(getApplicationContext(), "没有数据啦！", Toast.LENGTH_SHORT).show();
                } else {
                    mAdapter.loadMoreComplete();
                }
            }
            @Override
            public void onFailed(Exception e) {
                mAdapter.loadMoreFail();
                Toast.makeText(getApplicationContext(), "请检查网络...", Toast.LENGTH_LONG).show();
            }
        }).start(); //【注意】不要忘了开启线程，否则无法加载数据
    }

    private void initSwipeRefreshLayout() {
        mSRLayout.setColorSchemeColors(Color.rgb(125, 1255, 0));
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }


    //[1]下拉刷新
    private static final int FRESH_SIZE = 6;
    private void refresh() {
        mAdapter.setEnableLoadMore(false); //防止产生冲突，下拉刷新和上拉加载更多不能同时进行
        new RequestLoadMore(FRESH_SIZE, new RequestCallBackLoadMore() {
            @Override
            public void onSuccess(List<LoadMoreItem> data) {
                int size = data.size();
                if (size > 0){
                    mAdapter.setNewData(data);
                }else{
                    mAdapter.loadMoreComplete();
                }
                if (size < PAGE_SIZE){
                    //第一页如果不够一页就不显示没有更多数据布局
                    mAdapter.loadMoreEnd(false);
                    Toast.makeText(getApplicationContext(), "没有数据啦！", Toast.LENGTH_SHORT).show();
                }
                mAdapter.setEnableLoadMore(true);
                mSRLayout.setRefreshing(false);
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(getApplicationContext(), "请检查网络...", Toast.LENGTH_LONG).show();
                mAdapter.setEnableLoadMore(true);
                mSRLayout.setRefreshing(false);
            }
        }).start();

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i=0; i<PAGE_SIZE; i++){
            LoadMoreItem item = new LoadMoreItem("Content" + i, "Content" + i, null);
            mData.add(item);
        }
    }

    private void initAdapter() {
        mAdapter = new LoadMoreAdapter(R.layout.item_loadermore_view, mData);
        //[2]增加上拉加载更多功能
        initLoadMore();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mRv.setAdapter(mAdapter);

    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.id_recyclerView_loadMore);
        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipeLayout_loadMore);
    }
}
