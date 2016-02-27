package me.chen_wei.zhihu.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.network.model.Latest;
import me.chen_wei.zhihu.presenter.MainPresenter;
import me.chen_wei.zhihu.views.adapter.StoriesAdapter;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Bind(R.id.srl)
    SwipeRefreshLayout srl;

    @Bind(R.id.news_list)
    RecyclerView mNewsList;

    private static final String TAG = "MainActivity";

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        init();
    }

    private void init(){
        srl.setEnabled(true);
        srl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(true);
            }
        });

        mPresenter = new MainPresenter(this);

        //加载最新的新闻
        mPresenter.loadLatestContent();

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
    public void setLatestStories(List<Latest.StoriesEntity> entities) {
        StoriesAdapter adapter = new StoriesAdapter(entities, getApplicationContext());
        mNewsList.setAdapter(adapter);
        mNewsList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void gotoStoryActivity(int id) {
        Intent intent = new Intent(MainActivity.this, StoryActivity.class);
        intent.putExtra(Constants.KEY_STORY_ID, id);
        startActivity(intent);
    }
}
