package me.chen_wei.zhihu.views.activities;

import java.util.List;

import me.chen_wei.zhihu.network.model.Contents;
import me.chen_wei.zhihu.network.model.Latest;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public interface IMainActivity {

    //设置文章列表
    void setContents(List<Contents.StoriesEntity> entities);

    //设置最新文章列表
    void setLatestContents(List<Contents.StoriesEntity> entities);

    //加载故事页面
    void gotoStoryActivity(int id);

    //设置最热文章
    void setTopStories(Latest latest);
}
