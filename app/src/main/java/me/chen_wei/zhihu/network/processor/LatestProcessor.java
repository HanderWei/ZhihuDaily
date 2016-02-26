package me.chen_wei.zhihu.network.processor;

import android.util.Log;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.event.LatestContentLoadedEvent;
import me.chen_wei.zhihu.event.LoadFailureEvent;
import me.chen_wei.zhihu.network.Constants;
import me.chen_wei.zhihu.network.api.ZhihuAPI;
import me.chen_wei.zhihu.network.model.Latest;
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
public class LatestProcessor implements ILatestProcessor{

    Retrofit retrofit;

    @Override
    public void getLatestContent() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build();

        ZhihuAPI zhihuAPI = retrofit.create(ZhihuAPI.class);

        Call<Latest> call = zhihuAPI.getLatestContent();
        call.enqueue(new Callback<Latest>() {
            @Override
            public void onResponse(Call<Latest> call, Response<Latest> response) {
                //利用EventBus通知Presenter内容已经下载完成
                EventBus.getDefault().post(new LatestContentLoadedEvent(response.body()));
            }

            @Override
            public void onFailure(Call<Latest> call, Throwable t) {
                Log.d("Test", "failure");
                //利用EventBus通知Presenter加载失败
                EventBus.getDefault().post(new LoadFailureEvent("加载失败"));
            }
        });

    }
}
