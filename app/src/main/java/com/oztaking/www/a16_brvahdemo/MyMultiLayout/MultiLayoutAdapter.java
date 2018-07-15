package com.oztaking.www.a16_brvahdemo.MyMultiLayout;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
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

public class MultiLayoutAdapter extends BaseMultiItemQuickAdapter<MultiItem,BaseViewHolder>{


    String url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2743039436,399523121&fm=27&gp=0.jpg";
    String url1 = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=585834479,2252628897&fm=27&gp=0.jpg";
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiLayoutAdapter(List<MultiItem> data) {
        super(data);
        addItemType(MultiItem.TYPE_TEXT, R.layout.item_text_view_multi);
        addItemType(MultiItem.TYPE_IMAGE,R.layout.item_image_view_multi);
        addItemType(MultiItem.TYPE_TEXT_IMAGE,R.layout.item_textview_image_multi);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItem item) {

        switch (helper.getItemViewType()){
            case MultiItem.TYPE_TEXT:
                helper.setText(R.id.id_tv_multi,item.getContent());
                break;

            case MultiItem.TYPE_IMAGE:
                Glide.with(mContext)
                        .load(url1)
                        .into((ImageView) helper.getView(R.id.iv_num_multil));
                break;

            case MultiItem.TYPE_TEXT_IMAGE:
                helper.setText(R.id.tv1_multis,item.getContent());
                helper.setText(R.id.tv_multis,item.getContent());
                helper.setText(R.id.tv2_multis,item.getContent());
                Glide.with(mContext)
                        .load(url)
                        .into((ImageView) helper.getView(R.id.iv_num_multis));
                break;
        }
    }
}
