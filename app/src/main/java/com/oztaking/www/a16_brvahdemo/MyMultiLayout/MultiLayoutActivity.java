package com.oztaking.www.a16_brvahdemo.MyMultiLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oztaking.www.a16_brvahdemo.R;

import java.util.ArrayList;
import java.util.List;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：多种布局设置，其中设置了三种布局：一种是textView；tv+iv; 组合布局
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class MultiLayoutActivity extends Activity{
    private List<MultiItem> mData;
    private RecyclerView mRv;
    private MultiLayoutAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        initData();
        initView();
        initAdapter();

    }

//    public static final int TEXT_SPAN_SIZE = 3;
//    public static final int IMG_SPAN_SIZE = 1;
//    public static final int IMG_TEXT_SPAN_SIZE = 4;
//    public static final int IMG_TEXT_SPAN_SIZE_MIN = 2;


    private void initData() {
        mData = new ArrayList<>();

        for (int i = 0; i <= 4; i++) {
        mData.add(new MultiItem(MultiItem.TYPE_TEXT,"电影",MultiItem.TEXT_SPAN_SIZE));
        mData.add(new MultiItem(MultiItem.TYPE_IMAGE, MultiItem.IMG_SPAN_SIZE));
        mData.add(new MultiItem(MultiItem.TYPE_TEXT_IMAGE, "电视",MultiItem.IMG_TEXT_SPAN_SIZE));
        mData.add(new MultiItem(MultiItem.TYPE_IMAGE, MultiItem.IMG_TEXT_SPAN_SIZE_MIN));
        mData.add(new MultiItem(MultiItem.TYPE_IMAGE, MultiItem.IMG_TEXT_SPAN_SIZE_MIN));
        mData.add(new MultiItem(MultiItem.TYPE_IMAGE, MultiItem.IMG_TEXT_SPAN_SIZE));
            mData.add(new MultiItem(MultiItem.TYPE_TEXT,"电影",MultiItem.IMG_TEXT_SPAN_SIZE));
        }
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv_multi);
    }

    private void initAdapter() {
        mAdapter = new MultiLayoutAdapter(mData);
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRv.setLayoutManager(manager);

        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mData.get(position).getSpanSize();
            }
        });

        mRv.setAdapter(mAdapter);
    }


}
