package me.chen_wei.zhihu.network.api;

import me.chen_wei.zhihu.network.model.Latest;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public interface ZhihuAPI {

    @GET("api/4/news/latest")
    Call<Latest> getLatestContent();

}
