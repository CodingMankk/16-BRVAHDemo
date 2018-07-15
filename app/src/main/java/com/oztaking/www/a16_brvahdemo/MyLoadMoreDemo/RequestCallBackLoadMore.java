package com.oztaking.www.a16_brvahdemo.MyLoadMoreDemo;

import java.util.List;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：下拉刷新请求数据回调接口
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public interface RequestCallBackLoadMore {
    /**
     *
     * @param data 请求成功传入更新的数据
     */
    public void onSuccess(List<LoadMoreItem> data);

    /**
     *
     * @param e 失败，抛出异常
     */
    public void onFailed(Exception e);
}
