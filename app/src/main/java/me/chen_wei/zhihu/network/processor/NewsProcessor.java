package me.chen_wei.zhihu.network.processor;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.event.NewsLoadedEvent;
import me.chen_wei.zhihu.network.api.ZhihuAPI;
import me.chen_wei.zhihu.network.model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hander on 16/2/27.
 * <p/>
 * Email : hander_wei@163.com
 */
public class NewsProcessor implements INewsProcessor {

    Retrofit retrofit;

    @Override
    public void getNewsContent(int id) {
        retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build();

        ZhihuAPI zhihuAPI = retrofit.create(ZhihuAPI.class);
        Call<News> call = zhihuAPI.getNews(id);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                EventBus.getDefault().post(new NewsLoadedEvent(response.body()));
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
