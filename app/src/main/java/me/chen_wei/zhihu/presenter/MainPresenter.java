package me.chen_wei.zhihu.presenter;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.event.LatestContentLoadedEvent;
import me.chen_wei.zhihu.event.LoadFailureEvent;
import me.chen_wei.zhihu.network.processor.ILatestProcessor;
import me.chen_wei.zhihu.network.processor.LatestProcessor;
import me.chen_wei.zhihu.views.activities.IMainActivity;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class MainPresenter {

    private IMainActivity mMainActivity;
    private ILatestProcessor mLatestProcessor;

    public MainPresenter(IMainActivity main){
        mMainActivity = main;
        mLatestProcessor = new LatestProcessor();

        EventBus.getDefault().register(this);
    }

    public void loadLatestContent(){
        mLatestProcessor.getLatestContent();
    }

    public void onEvent(LoadFailureEvent event){
        mMainActivity.refresh(false);
    }

    public void onEvent(LatestContentLoadedEvent event){
        mMainActivity.setLatestStories(event.latestContent.getStories());
        mMainActivity.refresh(false);
    }
}
