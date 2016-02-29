package me.chen_wei.zhihu.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.LinePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.network.model.Contents;
import me.chen_wei.zhihu.network.model.Latest;
import me.chen_wei.zhihu.presenter.MainPresenter;
import me.chen_wei.zhihu.views.EndlessRecyclerViewScrollListener;
import me.chen_wei.zhihu.views.adapter.StoryListAdapter;
import me.chen_wei.zhihu.views.adapter.TopStoriesAdapter;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private static final String TAG = "MainActivity";
    private static int dayOfToday = 0;
    private int position = Integer.MAX_VALUE / 2;
    private boolean isTouched = false;

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Bind(R.id.srl)
    SwipeRefreshLayout srl;

    @Bind(R.id.news_list)
    RecyclerView mNewsList;

    @Bind(R.id.header)
    RecyclerViewHeader header;

    @Bind(R.id.view_pager)
    InfiniteViewPager viewPager;

    @Bind(R.id.indicator)
    LinePageIndicator indicator;

    Latest latest;

    private MainPresenter mPresenter;

    private List<Contents.StoriesEntity> mContents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        init();
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(position);
        }
    };

    private void init() {
        srl.setEnabled(true);
        srl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mNewsList.setLayoutManager(linearLayoutManager);

        header.attachTo(mNewsList, true);

        mNewsList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                dayOfToday--;
                mPresenter.loadContents(dayOfToday);
            }
        });

        mPresenter = new MainPresenter(this);

        mPresenter.loadTopStories();

        //加载当天故事列表
        mPresenter.loadContents(dayOfToday);
    }

    @Override
    protected void onPause() {
        super.onPause();
        srl.setRefreshing(false);
        dayOfToday = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @Override
    public void refresh(final boolean flag) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(flag);
            }
        });
    }

    @Override
    public void setContents(List<Contents.StoriesEntity> entities) {
        StoryListAdapter adapter = null;
        if (null == mContents || mContents.size() == 0) {
            mContents = entities;
            adapter = new StoryListAdapter(mContents, this);
            mNewsList.setAdapter(adapter);
        } else {
            mContents.addAll(entities);
            if (adapter == null) {
                adapter = new StoryListAdapter(mContents, this);
            }
            int curSize = adapter.getItemCount();
            adapter.notifyItemRangeChanged(curSize, mContents.size() - 1);
        }
    }

    @Override
    public void gotoStoryActivity(int id) {
        Intent intent = new Intent(MainActivity.this, StoryActivity.class);
        intent.putExtra(Constants.KEY_STORY_ID, id);
        startActivity(intent);
        overridePendingTransition(R.anim.hold, android.R.anim.fade_in);
    }

    @Override
    public void setTopStories(Latest latest) {
        this.latest = latest;

        //设置ViewPager
        TopStoriesAdapter adapter = new TopStoriesAdapter(this);
        adapter.setDataList(latest.getTop_stories());
        viewPager.setAdapter(adapter);
        viewPager.setAutoScrollTime(3000);
        viewPager.startAutoScroll();
        indicator.setViewPager(viewPager);
    }
}
