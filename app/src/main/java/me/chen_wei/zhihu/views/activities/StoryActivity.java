package me.chen_wei.zhihu.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.network.model.News;
import me.chen_wei.zhihu.presenter.StoryPresenter;

public class StoryActivity extends AppCompatActivity implements IStoryActivity{

    @Bind(R.id.tool_bar_content)
    Toolbar toolbar;
    @Bind(R.id.story_content)
    WebView content;

    StoryPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        content.getSettings().setJavaScriptEnabled(true);

        Bundle bundle = getIntent().getExtras();
        int id = (int) bundle.get(Constants.KEY_STORY_ID);

        mPresenter = new StoryPresenter(this);

        mPresenter.loadNewsContent(id);
    }

    @Override
    public void setNewsContent(News news) {
        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"zhihu.css\" />" +news.getBody();
        content.loadDataWithBaseURL("file:///android_asset/", htmlData, "text/html", "utf-8", null);
//        content.loadData(news.getBody(), "text/html", "utf-8");
    }
}
