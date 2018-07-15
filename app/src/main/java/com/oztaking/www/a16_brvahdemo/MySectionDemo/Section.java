package com.oztaking.www.a16_brvahdemo.MySectionDemo;

import com.chad.library.adapter.base.entity.SectionEntity;

/***********************************************
 * 文 件 名: 
 * 创 建 人: OzTaking
 * 功    能：SectionItem的包装层，在adapter中传递的对象是Section
 * 创建日期: 
 * 修改时间：
 * 修改备注：
 ***********************************************/

public class Section extends SectionEntity<SectionItem>{

    public boolean isMore;

    public Section(boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }

    public Section(SectionItem sectionItem) {
        super(sectionItem);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
