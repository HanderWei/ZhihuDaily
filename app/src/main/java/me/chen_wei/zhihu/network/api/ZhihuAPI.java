package me.chen_wei.zhihu.network.api;

import me.chen_wei.zhihu.network.model.Latest;
import me.chen_wei.zhihu.network.model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public interface ZhihuAPI {

    //获取最新故事列表
    @GET("api/4/news/latest")
    Call<Latest> getLatestContent();

    //获取单条新闻内容
    @GET("api/4/news/{id}")
    Call<News> getNews(@Path("id") int id);

}
