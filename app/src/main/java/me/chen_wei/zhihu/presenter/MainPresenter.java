package me.chen_wei.zhihu.presenter;

import android.content.Context;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.event.AllStoriedDownloadedEvent;
import me.chen_wei.zhihu.event.ContentsLoadedEvent;
import me.chen_wei.zhihu.event.LoadContentEvent;
import me.chen_wei.zhihu.event.LoadFailureEvent;
import me.chen_wei.zhihu.event.TopStoriesLoadedEvent;
import me.chen_wei.zhihu.network.processor.ContentsProcessor;
import me.chen_wei.zhihu.network.processor.IContentsProcessor;
import me.chen_wei.zhihu.network.processor.OfflineDownloadProcessor;
import me.chen_wei.zhihu.views.activities.IMainActivity;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class MainPresenter {

    private IMainActivity mMainActivity;
    private IContentsProcessor mContentsProcessor;
    private Context mContext;

    public MainPresenter(Context context, IMainActivity main) {
        mContext = context;
        mMainActivity = main;
        mContentsProcessor = new ContentsProcessor();

        EventBus.getDefault().register(this);
    }

    /**
     * 加载文章列表
     *
     * @param dayOffToday 与今天的日期差
     */
    public void loadContents(int dayOffToday) {
        //TODO
        mMainActivity.refresh(true);
        mContentsProcessor.getContents(mContext, dayOffToday);
    }

    /**
     * 加载热门文章列表
     */
    public void loadTopStories() {
        mContentsProcessor.getTopStories(mContext);
    }

    /**
     * 离线下载
     */
    public void offlineDownload() {
        OfflineDownloadProcessor processor = new OfflineDownloadProcessor(mContext);
        processor.downloadStories();
    }

    /**
     * 加载失败
     *
     * @param event
     */
    public void onEvent(LoadFailureEvent event) {
        //TODO
        mMainActivity.refresh(false);
    }

    /**
     * 文章列表加载成功
     *
     * @param event
     */
    public void onEvent(ContentsLoadedEvent event) {
        mMainActivity.setContents(event.contents.getStories());
        //TODO SwipeRefreshLayout 设置
        mMainActivity.refresh(false);
    }

    /**
     * 单个文章加载成功
     *
     * @param event
     */
    public void onEvent(LoadContentEvent event) {
        mMainActivity.gotoStoryActivity(event.id);
    }

    /**
     * 热门文章列表加载成功
     *
     * @param event
     */
    public void onEvent(TopStoriesLoadedEvent event) {
        mMainActivity.setTopStories(event.latest);
    }

    public void onEvent(AllStoriedDownloadedEvent event) {
        if (mContext != null) {
            Toast.makeText(mContext, R.string.download_finish, Toast.LENGTH_LONG).show();
        }
    }
}
