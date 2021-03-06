package me.chen_wei.zhihu.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.network.model.News;
import me.chen_wei.zhihu.presenter.StoryPresenter;

public class StoryActivity extends AppCompatActivity implements IStoryActivity {

    @Bind(R.id.tool_bar_content)
    Toolbar toolbar;
    @Bind(R.id.story_content)
    WebView content;
    @Bind(R.id.tv_news_title)
    TextView newsTitle;
    @Bind(R.id.tv_img_source)
    TextView imgSource;
    @Bind(R.id.img_news_header)
    ImageView newsHeader;

    StoryPresenter mPresenter;

    News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        int id = (int) bundle.get(Constants.KEY_STORY_ID);

        mPresenter = new StoryPresenter(getApplicationContext(), this);

        mPresenter.loadNewsContent(id);
    }

    @Override
    public void setNewsContent(News news) {
        mNews = news;

        //Load Html into WebView with CSS
        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"zhihu.css\" />" + news.getBody();
        content.loadDataWithBaseURL("file:///android_asset/", htmlData, "text/html", "utf-8", null);
//        content.loadData(news.getBody(), "text/html", "utf-8");

        newsTitle.setText(news.getTitle());
        imgSource.setText(news.getImage_source());
        Picasso.with(this).load(news.getImage()).into(newsHeader);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.hold, android.R.anim.fade_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_story_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                if (mNews != null) {
                    shareStory();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
     * 分享文章
     */
    public void shareStory() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mNews.getShare_url());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
