package me.chen_wei.zhihu.views.activities;

import java.util.List;

import me.chen_wei.zhihu.network.model.Latest;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public interface IMainActivity {

    //刷新SwipeRefreshLayout
    void refresh(boolean flag);

    //设置最新新闻列表
    void setLatestStories(List<Latest.StoriesEntity> entities);

    //加载故事页面
    void gotoStoryActivity(int id);
}
