package com.oztaking.www.a16_brvahdemo.BRVADLoadMoreDemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.oztaking.www.a16_brvahdemo.BRVADDemo.Status;
import com.oztaking.www.a16_brvahdemo.BRVADDemo.Utils;
import com.oztaking.www.a16_brvahdemo.R;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class PullToRefreshUseActivity extends Activity {

    private static final int PAGE_SIZE = 6;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PullToRefreshAdapter mAdapter;

    private int mNextRequestPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_brvah);


       Utils.init(getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setTitle("Pull TO Refresh Use");
//        setBackBtn();
        initAdapter();
//        addHeadView();
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void initAdapter() {
        mAdapter = new PullToRefreshAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(PullToRefreshUseActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

//    private void addHeadView() {
//        View headView = getLayoutInflater().inflate(R.layout.head_view_brvah, (ViewGroup) mRecyclerView.getParent(), false);
//        headView.findViewById(R.id.iv).setVisibility(View.GONE);
//        ((TextView) headView.findViewById(R.id.tv)).setText("change load view");
//        headView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAdapter.setNewData(null);
//                mAdapter.setLoadMoreView(new CustomLoadMoreView());
//                mRecyclerView.setAdapter(mAdapter);
//                Toast.makeText(PullToRefreshUseActivity.this, "change complete", Toast.LENGTH_LONG).show();
//
//                mSwipeRefreshLayout.setRefreshing(true);
//                refresh();
//            }
//        });
//        mAdapter.addHeaderView(headView);
//    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                setData(true, data);
                mAdapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void fail(Exception e) {
                Toast.makeText(PullToRefreshUseActivity.this, "请检查网络...", Toast.LENGTH_LONG).show();
                mAdapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }).start();
    }

    private void loadMore() {
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                setData(false, data);
            }

            @Override
            public void fail(Exception e) {
                mAdapter.loadMoreFail();
                Toast.makeText(PullToRefreshUseActivity.this, "请检查网络...", Toast.LENGTH_LONG).show();
            }
        }).start();
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(this, "没有数据啦！", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}