package com.oztaking.www.a16_brvahdemo.MyMultiLayout;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：实体类必须实现 MultiItemEntity ，在设置数据的时候，需要给每一个数据设置 itemType
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class MultiItem implements MultiItemEntity{

    public static final int TYPE_TEXT =1;
    public static final int TYPE_IMAGE =2;
    public static final int TYPE_TEXT_IMAGE =3;


    public static final int TEXT_SPAN_SIZE = 3;
    public static final int IMG_SPAN_SIZE = 1;
    public static final int IMG_TEXT_SPAN_SIZE = 4;
    public static final int IMG_TEXT_SPAN_SIZE_MIN = 2;

    public int mItemType;
    public String content;
    public int spanSize;

    public MultiItem(int mItemType, String content, int spanSize) {
        this.mItemType = mItemType;
        this.content = content;
        this.spanSize = spanSize;
    }

    public MultiItem(int mItemType, int spanSize) {
        this.mItemType = mItemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    @Override
    public int getItemType() {
        return mItemType;
    }
}
