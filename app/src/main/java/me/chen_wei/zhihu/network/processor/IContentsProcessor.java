package me.chen_wei.zhihu.network.processor;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public interface IContentsProcessor {

    void getContents(int dayOfToday);

    void getTopStories();
}
