package com.oztaking.www.a16_brvahdemo.MyOnClickDemo00;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：定义多布局
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class MultiEntry implements MultiItemEntity {

    private  static final  int ITEM_ONCLICK = 0;


    int type;

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
