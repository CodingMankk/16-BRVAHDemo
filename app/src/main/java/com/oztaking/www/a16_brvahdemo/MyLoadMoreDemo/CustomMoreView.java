package com.oztaking.www.a16_brvahdemo.MyLoadMoreDemo;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.oztaking.www.a16_brvahdemo.R;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：自定义加载布局
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class CustomMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

    /**
     * 如果返回true，数据全部加载完毕后会隐藏加载更多
     * 如果返回false，数据全部加载完毕后会显示getLoadEndViewId()布局
     */
    @Override
    public boolean isLoadEndGone() {
        return super.isLoadEndGone();
    }
}
