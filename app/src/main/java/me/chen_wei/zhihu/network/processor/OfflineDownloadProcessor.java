package me.chen_wei.zhihu.network.processor;

import android.content.Context;

import java.util.List;

import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.cache.ACache;
import me.chen_wei.zhihu.event.AllStoriedDownloadedEvent;
import me.chen_wei.zhihu.event.NewsDownloadedEvent;
import me.chen_wei.zhihu.network.model.Contents;
import me.chen_wei.zhihu.util.DateUtil;

/**
 * Created by Hander on 16/3/2.
 * <p/>
 * Email : hander_wei@163.com
 */
public class OfflineDownloadProcessor implements IOfflineDownloadProcessor {

    private static int storiesSize = 0;

    public Context mContext;

    public OfflineDownloadProcessor(Context context) {
        mContext = context;

        EventBus.getDefault().register(this);
    }

    public void onEvent(NewsDownloadedEvent event) {
        storiesSize--;
        if (storiesSize <= 0) {
            EventBus.getDefault().post(new AllStoriedDownloadedEvent());
        }
    }

    @Override
    public void downloadStories() {
        String today = DateUtil.getDateString(0);
        ACache cache = ACache.get(mContext);
        Contents contents = (Contents) cache.getAsObject(today);
        List<Contents.StoriesEntity> stories = contents.getStories();
        storiesSize = stories.size();
        NewsProcessor processor = new NewsProcessor();
        for (Contents.StoriesEntity story : stories) {
            //离线下载文章
            processor.loadNewsContent(mContext, story.getId());
        }
    }
}
