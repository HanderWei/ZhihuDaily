package me.chen_wei.zhihu.presenter;

import android.content.Context;

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
    private Context mContext;

    public StoryPresenter(Context context, IStoryActivity storyActivity) {
        mContext = context;
        mStoryActivity = storyActivity;
        mProcessor = new NewsProcessor();

        EventBus.getDefault().register(this);
    }

    /**
     * 加载文章内容
     *
     * @param id
     */
    public void loadNewsContent(int id) {
        mProcessor.getNewsContent(mContext, id);
    }

    public void onEvent(NewsLoadedEvent event) {
        mStoryActivity.setNewsContent(event.news);
    }
}
