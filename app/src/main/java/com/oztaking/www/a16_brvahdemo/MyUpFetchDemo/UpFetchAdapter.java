package com.oztaking.www.a16_brvahdemo.MyUpFetchDemo;

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

public class UpFetchAdapter extends BaseQuickAdapter<UpFetchItem,BaseViewHolder>{

    public UpFetchAdapter(@LayoutRes int layoutResId, @Nullable List<UpFetchItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UpFetchItem item) {
        helper.setText(R.id.tv1_loaderMore,item.getContent1())
                .setText(R.id.tv2_loaderMore,item.getContent2())
                .setText(R.id.tv_loaderMore,String.valueOf(helper.getLayoutPosition()));

    }
}
