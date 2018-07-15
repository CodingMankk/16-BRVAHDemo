package com.oztaking.www.a16_brvahdemo.BRVADLoadMoreDemo;

import com.oztaking.www.a16_brvahdemo.BRVADDemo.Status;

import java.util.List;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public interface RequestCallBack {
    void success(List<Status> data);
    void fail(Exception e);
}
