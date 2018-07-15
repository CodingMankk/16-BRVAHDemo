package com.oztaking.www.a16_brvahdemo.MySectionDemo;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
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

public class SectionAdapter extends BaseSectionQuickAdapter<Section,BaseViewHolder>{

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }


    @Override
    protected void convertHead(BaseViewHolder helper, Section item) {
        helper.setText(R.id.header_section,item.header);
        helper.setVisible(R.id.more_section,item.isMore);
        helper.addOnClickListener(R.id.more_section);
    }

    @Override
    protected void convert(BaseViewHolder helper, Section item) {
        SectionItem sectionItem = item.t;
        helper.setText(R.id.tv_section,sectionItem.getContent1());
    }
}
