package me.chen_wei.zhihu.network.processor;

import android.content.Context;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public interface IContentsProcessor {

    /**
     * 获取某一天的文章列表
     *
     * @param context
     * @param dayOfToday
     */
    void getContents(Context context, int dayOfToday);

    /**
     * 获取某一天的文章列表
     *
     * @param context
     * @param dayOfToday
     * @param refresh    是否要刷新
     */
    void getContents(Context context, int dayOfToday, boolean refresh);

    /**
     * 获取热门文章列表
     *
     * @param context
     */
    void getTopStories(Context context);

    /**
     * 获取热门文章列表
     *
     * @param context
     * @param refresh 是否要刷新
     */
    void getTopStories(Context context, boolean refresh);
}
