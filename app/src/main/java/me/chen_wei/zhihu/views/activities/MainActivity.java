package me.chen_wei.zhihu.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chen_wei.zhihu.R;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Bind(R.id.news_list)
    RecyclerView mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        
    }

}
