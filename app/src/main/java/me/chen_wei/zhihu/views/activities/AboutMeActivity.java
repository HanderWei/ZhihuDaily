package me.chen_wei.zhihu.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chen_wei.zhihu.R;

public class AboutMeActivity extends AppCompatActivity {

    @Bind(R.id.tool_bar_about_me)
    Toolbar toolbar;
    @Bind(R.id.tv_about_me)
    TextView tvAboutMe;
    @Bind(R.id.tv_license)
    TextView tvLicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String aboutMeHtml = "<html><head><title><h3>项目地址:<a href=\"https://github.com/HanderWei/ZhihuDaily\">https://github.com/HanderWei/ZhihuDaily</a>欢迎 <b><font color=\"#0288D1\">Star</b>, <b><font color=\"#0288D1\">Fork</b> , <b><font color=\"#0288D1\">Issue</b></h3></title></head><body><p><h4>本项目仅供学习，请勿用于其他用途。若被告侵权，本人会及时删除整个项目。</h4></p><p><h4>请您暸解相关情况，并遵守知乎协议。</h4></p><p><h4>Email:hander_wei@163.com</h4></p><p><h4>Github:<a href=\"https://github.com/HanderWei\">https://github.com/HanderWei</h4></p><p><h4>个人主页:<a href=\"http://chen-wei.me/\">http://chen-wei.me/</h4></p></body></html>";

        tvAboutMe.setText(Html.fromHtml(aboutMeHtml));
        tvAboutMe.setMovementMethod(LinkMovementMethod.getInstance());

        String licenseHtml = "<html><head><title><h3><b>The MIT License (MIT)</b></h3></title></head><body><p><h4>Copyright (c)  2016 Chen Wei</h4></p><h5><p>Permission is hereby granted, free of charge, to any person obtaining a copy" +
                " of this software and associated documentation files (the \"Software\"), to deal" +
                " in the Software without restriction, including without limitation the rights" +
                " to use, copy, modify, merge, publish, distribute, sublicense, and/or sell" +
                " copies of the Software, and to permit persons to whom the Software is" +
                " furnished to do so, subject to the following conditions:</p><p>The above copyright notice and this permission notice shall be included in" +
                " all copies or substantial portions of the Software.</p><p>THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR" +
                " IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY," +
                " FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE" +
                " AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER" +
                " LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM," +
                " OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN" +
                " THE SOFTWARE.</p><h5></body></html>";
        tvLicense.setText(Html.fromHtml(licenseHtml));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.hold, android.R.anim.fade_out);
    }
}
