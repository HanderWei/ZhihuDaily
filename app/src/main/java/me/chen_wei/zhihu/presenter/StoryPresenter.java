package me.chen_wei.zhihu.presenter;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.event.NewsLoadedEvent;
import me.chen_wei.zhihu.network.processor.INewsProcessor;
import me.chen_wei.zhihu.network.processor.NewsProcessor;
import me.chen_wei.zhihu.views.activities.IStoryActivity;

/**
 * Created by Hander on 16/2/27.
 * <p/>
 * Email : hander_wei@163.com
 */
public class StoryPresenter {

    private IStoryActivity mStoryActivity;
    private INewsProcessor mProcessor;

    public StoryPresenter(IStoryActivity storyActivity){
        mStoryActivity = storyActivity;
        mProcessor = new NewsProcessor();

        EventBus.getDefault().register(this);
    }

    public void loadNewsContent(int id){
        mProcessor.getNewsContent(id);
    }

    public void onEvent(NewsLoadedEvent event){
        mStoryActivity.setNewsContent(event.news);
    }

}
