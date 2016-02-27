package me.chen_wei.zhihu.network.processor;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.Constants;
import me.chen_wei.zhihu.event.ContentsLoadedEvent;
import me.chen_wei.zhihu.event.LoadFailureEvent;
import me.chen_wei.zhihu.network.api.ZhihuAPI;
import me.chen_wei.zhihu.network.model.Contents;
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

    @Override
    public void getContents(int dayOfToday) {
        retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build();

        ZhihuAPI zhihuAPI = retrofit.create(ZhihuAPI.class);

        String before = DateUtil.getDateString(dayOfToday);
        Call<Contents> call = zhihuAPI.getContents(before);
        call.enqueue(new Callback<Contents>() {
            @Override
            public void onResponse(Call<Contents> call, Response<Contents> response) {
                //利用EventBus通知Presenter内容已经下载完成
                EventBus.getDefault().post(new ContentsLoadedEvent(response.body()));
            }

            @Override
            public void onFailure(Call<Contents> call, Throwable t) {
                //利用EventBus通知Presenter加载失败
                EventBus.getDefault().post(new LoadFailureEvent("最新消息列表加载失败"));
            }
        });

    }
}
