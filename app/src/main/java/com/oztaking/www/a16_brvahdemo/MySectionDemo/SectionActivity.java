package com.oztaking.www.a16_brvahdemo.MySectionDemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oztaking.www.a16_brvahdemo.R;

import java.util.ArrayList;
import java.util.List;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：[1]分组布局
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class SectionActivity extends Activity {

    private List<Section> mData;
    private RecyclerView mRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        initData();
        initView();
        initAdapter();

    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Section(true, "一年级", true));
        mData.add(new Section(new SectionItem("1-1",null,null)));
        mData.add(new Section(new SectionItem("1-2",null,null)));
        mData.add(new Section(new SectionItem("1-3",null,null)));
        mData.add(new Section(new SectionItem("1-4",null,null)));
        mData.add(new Section(new SectionItem("1-5",null,null)));
        mData.add(new Section(true, "二年级", true));
        mData.add(new Section(new SectionItem("2-1",null,null)));
        mData.add(new Section(new SectionItem("2-2",null,null)));
        mData.add(new Section(true, "三年级", true));
        mData.add(new Section(new SectionItem("3-1",null,null)));
        mData.add(new Section(true, "四年级", true));
        mData.add(new Section(new SectionItem("4-1",null,null)));
        mData.add(new Section(new SectionItem("4-2",null,null)));
        mData.add(new Section(new SectionItem("4-3",null,null)));
        mData.add(new Section(new SectionItem("4-4",null,null)));
        mData.add(new Section(new SectionItem("4-5",null,null)));
        mData.add(new Section(new SectionItem("4-6",null,null)));
        mData.add(new Section(new SectionItem("4-7",null,null)));
        mData.add(new Section(new SectionItem("4-8",null,null)));
        mData.add(new Section(new SectionItem("4-9",null,null)));
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv_section);
    }

    private void initAdapter() {
        SectionAdapter mAdapter = new SectionAdapter(R.layout.item_section_content, R
                .layout.header_section, mData);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(mAdapter);

    }


}
