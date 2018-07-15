package com.oztaking.www.a16_brvahdemo.MyOnClickDemo00;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：自定义加载布局
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

class CustomMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected int getLoadingViewId() {
        return 0;
    }

    @Override
    protected int getLoadFailViewId() {
        return 0;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
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
