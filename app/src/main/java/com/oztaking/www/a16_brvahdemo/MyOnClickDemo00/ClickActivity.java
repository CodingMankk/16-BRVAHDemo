package com.oztaking.www.a16_brvahdemo.MyOnClickDemo00;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
    private View footerView;


    private boolean isErr;

    private static final int TOTAL_COUNTER = 20;
    private static final int PAGER_SIZE = 5;
    private static final int PRE_LOAD_POSITION = 5;

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
//        addAnimator();

        //增加头部
        addHeaderView();
        //添加尾部
        addFootView();





        final int delayMills = 100;
        BaseQuickAdapter.RequestLoadMoreListener loadMoreListener = new BaseQuickAdapter
                .RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int mCurrentCounter = mAdapter.getItemCount();
                        if (mCurrentCounter > TOTAL_COUNTER){
                            //数据加载完毕
                            mAdapter.loadMoreEnd();
                        }else{
                            if(isErr){
//                                mAdapter.addData(DataServer.getSampleData(PAGER_SIZE));
                                mCurrentCounter = mAdapter.getData().size();
                                mAdapter.loadMoreComplete();
                            }else{
                                //获取更多数据失败
                                isErr = true;
                                Toast.makeText(ClickActivity.this, "", Toast.LENGTH_SHORT).show();
                                mAdapter.loadMoreFail();
                            }
                        }
                    }
                },delayMills);
            }
        };

        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        mAdapter.setOnLoadMoreListener(loadMoreListener,mRv);

        //默认第一次加载会进入回调，如果不需要可以配置：
        mAdapter.disableLoadMoreIfNotFullPage();

        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
        mAdapter.setPreLoadNumber(PRE_LOAD_POSITION);

//        mAdapter.loadMoreComplete();
//        mAdapter.loadMoreFail();

        //设置自定义布局
        mAdapter.setLoadMoreView(new CustomMoreView());



    }

    private void addFootView() {
        //添加尾部
        View footerView = getLayoutInflater().inflate(R.layout.item_foot, (ViewGroup) mRv.getParent
                (), false);
        mAdapter.addFooterView(footerView);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeAllFooterView();
            }
        });

    }

    /**
     * 单击最上面的headview会增加headview；
     * 单击最下面的headview会删除当前的view
     */
    private void addHeaderView() {
        //默认头部尾部都是占满一行，如果需要不占满可以配置
        mAdapter.setHeaderViewAsFlow(false);
        //默认出现了头部就不会显示Empty，和尾部，配置以下方法也支持同时显示：
        mAdapter.setHeaderAndEmpty(true);

        //添加头部
        final View headerView = getLayoutInflater().inflate(R.layout.item_header, (ViewGroup) mRv
                .getParent(), false);

        mAdapter.addHeaderView(headerView);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View headerView1 = getLayoutInflater().inflate(R.layout.item_header,
                        (ViewGroup) mRv.getParent(), false);
                mAdapter.addHeaderView(headerView1);
                TextView headerTV = headerView.findViewById(R.id.tv_header);
                headerView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAdapter.removeHeaderView(v);
                    }
                });

            }
        });
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
               switch(view.getId()){
                    case R.id.ll_header:
                        Logger.i("onItemClick:头部click");
                         break;
                   case R.id.ll_footer:
                       Logger.i("onItemClick:尾部click");
                       break;

                    default:
                         break;
               }

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

                switch(view.getId()){
                    case R.id.ll_header:
                        Logger.i("onItemClick:头部LongClick");
                        break;
                    case R.id.ll_footer:
                        Logger.i("onItemClick:尾部LongClick");
                        break;
                    default:
                        break;
                }

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
                    case R.id.iv_header:
                        Logger.i("onItemClick:头部ImageView-Click");
                        break;

                    case R.id.tv_header:
                        Logger.i("onItemClick:头部TextView-Click");
                        break;

                    case R.id.iv_footer:
                        Logger.i("onItemClick:尾部ImageView-Click");
                        break;

                    case R.id.tv_footer:
                        Logger.i("onItemClick:尾部TextView-Click");
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

                switch(view.getId()){
                    case R.id.iv_header:
                        Logger.i("onItemClick:头部ImageView-LongClick");
                        break;
                    case R.id.tv_header:
                        Logger.i("onItemClick:头部TextView-LongClick");
                        break;
                    case R.id.iv_footer:
                        Logger.i("onItemClick:尾部ImageView-LongClick");
                        break;
                    case R.id.tv_footer:
                        Logger.i("onItemClick:尾部TextView-LongClick");
                        break;

                    default:
                          break;
                }
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



        for (int i = 1; i < 3; i++) {
            mDatas.add(new ClickItem("Title:" + i, "Content:" + i, null));
        }
    }



}
