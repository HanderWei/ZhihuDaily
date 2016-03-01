package me.chen_wei.zhihu.network.processor;

import android.content.Context;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.cache.ACache;
import me.chen_wei.zhihu.event.ContentsLoadedEvent;
import me.chen_wei.zhihu.event.LoadFailureEvent;
import me.chen_wei.zhihu.event.TopStoriesLoadedEvent;
import me.chen_wei.zhihu.network.api.ZhihuAPI;
import me.chen_wei.zhihu.network.model.Contents;
import me.chen_wei.zhihu.network.model.Latest;
import me.chen_wei.zhihu.util.DateUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class ContentsProcessor implements IContentsProcessor {

    Retrofit retrofit;

    /**
     * 获取某一天文章列表
     *
     * @param dayOfToday
     */
    @Override
    public void getContents(final Context context, final int dayOfToday) {
        final ACache cache = ACache.get(context);

        final String before = DateUtil.getDateString(dayOfToday);

        //从Cache中获取了文章列表
        Contents contents;
        if ((contents = readContentsFromCache(cache, before)) != null) {
            EventBus.getDefault().post(new ContentsLoadedEvent(contents));
        } else {
            retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build();

            ZhihuAPI zhihuAPI = retrofit.create(ZhihuAPI.class);
            Call<Contents> call = zhihuAPI.getContents(before);
            call.enqueue(new Callback<Contents>() {
                @Override
                public void onResponse(Call<Contents> call, Response<Contents> response) {
                    //利用EventBus通知Presenter内容已经下载完成
                    EventBus.getDefault().post(new ContentsLoadedEvent(response.body()));

                    putContentsToCache(cache, before, response.body());
                }

                @Override
                public void onFailure(Call<Contents> call, Throwable t) {
                    //利用EventBus通知Presenter加载失败
                    EventBus.getDefault().post(new LoadFailureEvent("文章列表加载失败"));
                }
            });
        }

    }

    /**
     * 从Cache中读取某一天的文章列表
     *
     * @param cache
     * @param dateString
     * @return
     */
    public Contents readContentsFromCache(ACache cache, String dateString) {
        Contents contents = (Contents) cache.getAsObject(dateString);
        return contents;
    }

    /**
     * 将文章列表保存在Cache中（保存两周）
     *
     * @param cache
     * @param dateString
     * @param contents
     */
    public void putContentsToCache(ACache cache, String dateString, Contents contents) {
        cache.put(dateString, contents, ACache.TIME_DAY * 14);
    }

    /**
     * 获取热门文章列表
     */
    @Override
    public void getTopStories(Context context) {
        final ACache cache = ACache.get(context);

        Latest latest;
        if ((latest = getLatestFromCache(cache)) != null) {
            EventBus.getDefault().post(new TopStoriesLoadedEvent(latest));
        } else {
            retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build();

            ZhihuAPI zhihuAPI = retrofit.create(ZhihuAPI.class);

            Call<Latest> call = zhihuAPI.getLatestContent();
            call.enqueue(new Callback<Latest>() {
                @Override
                public void onResponse(Call<Latest> call, Response<Latest> response) {
                    //利用EventBus通知Presenter内容已经下载完成
                    EventBus.getDefault().post(new TopStoriesLoadedEvent(response.body()));

                    putLatestToCache(cache, response.body());
                }

                @Override
                public void onFailure(Call<Latest> call, Throwable t) {
                    //利用EventBus通知Presenter加载失败
                    EventBus.getDefault().post(new LoadFailureEvent("热门文章列表加载失败"));
                }
            });
        }
    }

    /**
     * 将Latest保存到Cache中
     *
     * @param cache
     * @return
     */
    public Latest getLatestFromCache(ACache cache) {
        Latest latest = (Latest) cache.getAsObject(Integer.toString(1));
        return latest;
    }

    /**
     * 保存Latest信息（保存三天）
     *
     * @param cache
     * @param latest
     */
    public void putLatestToCache(ACache cache, Latest latest) {
        cache.put(String.valueOf(1), latest, ACache.TIME_DAY * 3);
    }
}
