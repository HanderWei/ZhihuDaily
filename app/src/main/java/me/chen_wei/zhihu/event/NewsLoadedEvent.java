package me.chen_wei.zhihu.event;

import me.chen_wei.zhihu.network.model.News;

/**
 * Created by Hander on 16/2/27.
 * <p/>
 * Email : hander_wei@163.com
 */
public class NewsLoadedEvent {

    public News news;

    public NewsLoadedEvent(News news){
        this.news = news;
    }
}
