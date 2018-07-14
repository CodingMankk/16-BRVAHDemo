package com.oztaking.www.a16_brvahdemo.MyOnClickDemo00;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.oztaking.www.a16_brvahdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 【1】item点击事件
 * 【2】item 长按事件
 * 【3】子view（Image/Button）的点击事件,点击Image tv2_home的内容+1；点击Button tv2_home内容-1；
 * 【4】子view（Image/Button）的长按事件
 */
public class ClickActivity extends AppCompatActivity {

    private RecyclerView mRv;

    private List<ClickItem> mDatas;
    private ClickAdapter mAdapter;

    //    private int mCount = 0;
    private ClickItem mClickItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Logger.init("ClickActivity").methodCount(0).hideThreadInfo();

        setTitle("ClickActivity");
        initData();
        initView();

        mRv.setLayoutManager(new LinearLayoutManager(ClickActivity.this, LinearLayoutManager
                .VERTICAL, false));
        mAdapter = new ClickAdapter(R.layout.item_home_1_view, mDatas);
        mRv.setAdapter(mAdapter);
        //点击事件
        AdapterClick();
        //添加动画
        addAnimator();

        mAdapter.addHeaderView(getHeaderView());

    }

    private View getHeaderView() {

        return null;
    }

    private void addAnimator() {
        //        mAdapter.openLoadAnimation();
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        //        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        //动画默认只执行一次,如果想重复执行可设置
        mAdapter.isFirstOnly(false);

        //设置不显示动画数量--
        mAdapter.setNotDoAnimationCount(2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.ALPHAIN_menu:
                //开启动画---默认为渐显效果
                /**
                 * 渐显
                 */
                mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                break;

            case R.id.SCALEIN_menu:
                /**
                 * 缩放
                 */
                mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

                break;

            case R.id.SLIDEIN_LEFT_menu:
                /**
                 * 从左到右
                 */
                mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                break;

            case R.id.SLIDEIN_RIGHT_menu:
                /**
                 * 从右到左
                 */
                mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                break;

            case R.id.SLIDEIN_BOTTOM_menu:

                /**
                 * 从下到上
                 */
                mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                break;

            default:
                break;
        }

//                return super.onOptionsItemSelected(item);
        return true;
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
    }

    private void AdapterClick() {
        /**
         * 设置item点击事件
         */
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ClickActivity.this, "onItemClick" + position, Toast
                        .LENGTH_SHORT).show();
                Logger.i("onItemClick");
                ImageView imageView = (ImageView) mAdapter.getViewByPosition(mRv, position, R.id
                        .iv_num_home);

            }
        });

        /**
         * 设置item长按事件
         */
        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ClickActivity.this, "onItemLongClick" + position, Toast
                        .LENGTH_SHORT).show();
                Logger.i("onItemLongClick");
                return false;
            }
        });


        /**
         * 设置子view的点击事件
         */
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ClickActivity.this, "onItemChildClick" + position, Toast
                        .LENGTH_SHORT).show();
                Logger.i("onItemChildClick : view" + view);

                //                TextView tv2 = (TextView) mAdapter.getViewByPosition(mRv,
                // position, R.id.tv2);

                ClickItem clickItem = mDatas.get(position);
                int count = clickItem.getCount();

                int id = view.getId();
                switch (id) {
                    case R.id.btn_num_home:
                        if (count > 0) {
                            count--;
                            clickItem.setCount(count);
                        }

                        break;
                    case R.id.iv_num_home:
                        if (count >= 0) {
                            count++;
                            clickItem.setCount(count);
                        }
                        break;
                    default:
                        break;
                }
                clickItem.setContent2(String.valueOf(count));
                mAdapter.notifyDataSetChanged();
                Logger.d(count);

            }
        });

        /**
         * 设置子view的长按事件
         */
        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter
                .OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ClickActivity.this, "onItemChildLongClick:" + position, Toast
                        .LENGTH_SHORT).show();
                Logger.i("onItemChildLongClick");
                return false;
            }
        });
    }

    private void initData() {
        //        mDatas = new ArrayList<>();
        //        for (char i='A'; i<'z'; i++){
        //            mDatas.add(String.valueOf(i));
        //        }
        mDatas = new ArrayList<>();
        mClickItem = new ClickItem("Content1", "子view-Image/button-click-longClick", null);
        mDatas.add(mClickItem);

        for (int i = 1; i < 15; i++) {
            mDatas.add(new ClickItem("Title:" + i, "Content:" + i, null));
        }
    }


}
