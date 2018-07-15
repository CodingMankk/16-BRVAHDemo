package com.oztaking.www.a16_brvahdemo.MyLoadMoreDemo;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oztaking.www.a16_brvahdemo.R;

import java.util.List;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class LoadMoreAdapter extends BaseQuickAdapter<LoadMoreItem,BaseViewHolder>{



    public LoadMoreAdapter(@LayoutRes int layoutResId, @Nullable List<LoadMoreItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoadMoreItem item) {
        helper.setText(R.id.tv1_loaderMore,item.getContent1())
                .setText(R.id.tv2_loaderMore,item.getContent2());
    }
}
