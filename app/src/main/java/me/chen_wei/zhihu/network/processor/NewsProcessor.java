package me.chen_wei.zhihu.network.processor;

import android.content.Context;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.cache.ACache;
import me.chen_wei.zhihu.event.NewsDownloadedEvent;
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

    /**
     * 获取文章内容
     *
     * @param context
     * @param id
     */
    @Override
    public void getNewsContent(Context context, final int id) {
        final ACache cache = ACache.get(context);
        News news;
        if ((news = getNewsFromCache(cache, id)) != null) {
            EventBus.getDefault().post(new NewsLoadedEvent(news));
        } else {
            retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build();

            ZhihuAPI zhihuAPI = retrofit.create(ZhihuAPI.class);
            Call<News> call = zhihuAPI.getNews(id);
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    EventBus.getDefault().post(new NewsLoadedEvent(response.body()));

                    putNewsToCache(cache, id, response.body());
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    //TODO
                }
            });
        }
    }

    /**
     * 从Cache中获取文章内容
     *
     * @param cache
     * @param id
     * @return
     */
    public News getNewsFromCache(ACache cache, int id) {
        News news = (News) cache.getAsObject(Integer.toString(id));
        return news;
    }

    /**
     * 将文章内容保存到Cache中（保存两周）
     *
     * @param cache
     * @param id
     * @param news
     */
    public void putNewsToCache(ACache cache, int id, News news) {
        cache.put(Integer.toString(id), news, ACache.TIME_DAY * 14);
    }

    /**
     * 离线下载文章
     *
     * @param context
     * @param id
     */
    @Override
    public void loadNewsContent(Context context, final int id) {
        final ACache cache = ACache.get(context);
        if ((getNewsFromCache(cache, id)) == null) {
            retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build();

            ZhihuAPI zhihuAPI = retrofit.create(ZhihuAPI.class);
            Call<News> call = zhihuAPI.getNews(id);
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    //加入缓存
                    putNewsToCache(cache, id, response.body());

                    EventBus.getDefault().post(new NewsDownloadedEvent());
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    //TODO
                }
            });
        } else {
            EventBus.getDefault().post(new NewsDownloadedEvent());
        }
    }
}
