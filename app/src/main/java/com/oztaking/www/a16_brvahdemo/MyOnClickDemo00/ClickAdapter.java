package com.oztaking.www.a16_brvahdemo.MyOnClickDemo00;

import android.animation.Animator;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oztaking.www.a16_brvahdemo.R;

import java.util.List;

/**
 * @author
 * @function:
 */

public class ClickAdapter extends BaseQuickAdapter<ClickItem, BaseViewHolder> {


    public ClickAdapter(@LayoutRes int layoutResId, @Nullable List<ClickItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClickItem item) {

        helper.setText(R.id.tv,"Item位置："+helper.getLayoutPosition()) //显示当前的item的position
                .setText(R.id.tv1, "item点击事件：new")  //更改标题
                .setText(R.id.tv2_home, item.getContent2())
                .addOnClickListener(R.id.rl1) //整个item的点击事件
                .addOnLongClickListener(R.id.rl1) // //整个item的长按事件
                .addOnClickListener(R.id.iv_num_home) //绑定布局子view的点击事件
                .addOnLongClickListener(R.id.iv_num_home) //绑定布局子view的点击事件
                .addOnClickListener(R.id.btn_num_home)//绑定布局子view的点击事件
                .addOnLongClickListener(R.id.btn_num_home); //绑定布局子view的点击事件

        helper.addOnClickListener(R.id.tv_header)
                .addOnLongClickListener(R.id.iv_header)
                .addOnClickListener(R.id.tv_footer)
                .addOnLongClickListener(R.id.iv_footer)
                .addOnClickListener(R.id.ll_header)
                .addOnLongClickListener(R.id.ll_header)
                .addOnClickListener(R.id.ll_footer)
                .addOnLongClickListener(R.id.ll_footer);




    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 3){
            anim.setStartDelay(index*150);
        }
    }
}
