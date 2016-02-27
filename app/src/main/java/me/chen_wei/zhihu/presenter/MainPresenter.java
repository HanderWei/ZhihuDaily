package me.chen_wei.zhihu.presenter;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.event.ContentsLoadedEvent;
import me.chen_wei.zhihu.event.LoadContentEvent;
import me.chen_wei.zhihu.event.LoadFailureEvent;
import me.chen_wei.zhihu.network.processor.IContentsProcessor;
import me.chen_wei.zhihu.network.processor.ContentsProcessor;
import me.chen_wei.zhihu.views.activities.IMainActivity;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class MainPresenter {

    private IMainActivity mMainActivity;
    private IContentsProcessor mContentsProcessor;

    public MainPresenter(IMainActivity main){
        mMainActivity = main;
        mContentsProcessor = new ContentsProcessor();

        EventBus.getDefault().register(this);
    }

    public void loadContents(int dayOffToday){
        mMainActivity.refresh(true);
        mContentsProcessor.getContents(dayOffToday);
    }

    public void onEvent(LoadFailureEvent event){
        mMainActivity.refresh(false);
    }

    public void onEvent(ContentsLoadedEvent event){
        mMainActivity.setLatestStories(event.contents.getStories());
        mMainActivity.refresh(false);
    }

    public void onEvent(LoadContentEvent event){
        mMainActivity.gotoStoryActivity(event.id);
    }
}
