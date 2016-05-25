package me.chen_wei.zhihu.views.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.network.model.Contents;
import me.chen_wei.zhihu.network.model.Latest;
import me.chen_wei.zhihu.presenter.MainPresenter;
import me.chen_wei.zhihu.util.DateUtil;
import me.chen_wei.zhihu.views.EndlessRecyclerViewScrollListener;
import me.chen_wei.zhihu.views.adapter.StoryListAdapter;
import me.chen_wei.zhihu.views.adapter.TopStoriesAdapter;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Bind(R.id.srl)
    SwipeRefreshLayout srl;

    @Bind(R.id.news_list)
    RecyclerView mNewsList;

    @Bind(R.id.header)
    RecyclerViewHeader mNewsListHeader;

    @Bind(R.id.view_pager)
    InfiniteViewPager viewPager;

    @Bind(R.id.indicator)
    LinePageIndicator indicator;

    private MainPresenter mPresenter;

    private List<Contents.StoriesEntity> mStories;

    private List<String> dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        mPresenter = new MainPresenter(getApplicationContext(), this);

        //加载热门文章列表
        mPresenter.loadTopStories();

        //加载最新文章列表
        String latestDate = DateUtil.getLatestDateString();
        dateList = new ArrayList<>();
        dateList.add(latestDate);
        mPresenter.loadContents(latestDate);

        srl.setColorSchemeResources(R.color.colorAccent);
        //下拉刷新
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);

                //刷新热门文章列表
                mPresenter.loadTopStories(true);

                //刷新当前文章列表
                String curLatestDate = DateUtil.getLatestDateString();
                if (dateList != null) {
                    dateList.removeAll(dateList);
                    dateList.add(curLatestDate);
                }
                mPresenter.loadLatestContents(DateUtil.getLatestDateString());

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                    }
                }, 1200);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mNewsList.setLayoutManager(linearLayoutManager);

        mNewsListHeader.attachTo(mNewsList, true);

        mNewsList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                String farthestDateStr;
                farthestDateStr = dateList.get(dateList.size() - 1);
                String beforeFarthestDateStr = DateUtil.getDayBeforeThisDayString(farthestDateStr);
                dateList.add(beforeFarthestDateStr);
                mPresenter.loadContents(beforeFarthestDateStr);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @Override
    public void setContents(List<Contents.StoriesEntity> entities) {
        StoryListAdapter adapter = null;
        if (null == mStories || mStories.size() == 0) {
            mStories = entities;
            adapter = new StoryListAdapter(mStories, this);
            mNewsList.setAdapter(adapter);
        } else {
            mStories.addAll(entities);
            if (adapter == null) {
                adapter = new StoryListAdapter(mStories, this);
            }
            int curSize = adapter.getItemCount();
            adapter.notifyItemRangeChanged(curSize, mStories.size() - 1);
        }
    }

    @Override
    public void setLatestContents(List<Contents.StoriesEntity> entities) {
        mStories = entities;
        StoryListAdapter adapter = new StoryListAdapter(mStories, this);
        mNewsList.setAdapter(adapter);

        adapter.notifyItemRangeChanged(0, mStories.size() - 1);
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
        //设置ViewPager
        TopStoriesAdapter adapter = new TopStoriesAdapter(this);
        adapter.setDataList(latest.getTop_stories());
        viewPager.setAdapter(adapter);
        viewPager.setAutoScrollTime(3000);
        viewPager.startAutoScroll();
        indicator.setViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (viewPager != null) {
            viewPager.startAutoScroll();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (viewPager != null) {
            viewPager.stopAutoScroll();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_download:
                //离线下载
                Toast.makeText(getApplicationContext(), R.string.start_download, Toast.LENGTH_LONG).show();
                mPresenter.offlineDownload();
                return true;
            case R.id.action_day_night:
                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                switch (currentNightMode){
                    case Configuration.UI_MODE_NIGHT_NO:
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        recreate();
                        break;
                    case Configuration.UI_MODE_NIGHT_YES:
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        recreate();
                        break;
                    case Configuration.UI_MODE_NIGHT_UNDEFINED:
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                        break;
                }
                return true;
            case R.id.action_about_me:
                Intent intent = new Intent(this, AboutMeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, android.R.anim.fade_in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
