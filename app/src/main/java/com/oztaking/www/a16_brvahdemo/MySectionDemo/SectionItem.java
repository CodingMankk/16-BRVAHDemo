package com.oztaking.www.a16_brvahdemo.MySectionDemo;

import android.graphics.Bitmap;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class SectionItem {

    public SectionItem(String mContent1, String mContent2, Bitmap mBitmap) {
        this.mContent1 = mContent1;
        this.mContent2 = mContent2;
        this.mBitmap = mBitmap;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    private int mCount = 0;
    private String mContent1;
    private String mContent2;
    private Bitmap mBitmap;

    public String getContent1() {
        return mContent1;
    }

    public void setContent1(String mContent1) {
        this.mContent1 = mContent1;
    }

    public String getContent2() {
        return mContent2;
    }

    public void setContent2(String mContent2) {
        this.mContent2 = mContent2;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
}
