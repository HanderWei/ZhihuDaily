package me.chen_wei.zhihu.network.processor;

/**
 * Created by Hander on 16/3/2.
 * <p/>
 * Email : hander_wei@163.com
 */
public interface IOfflineDownloadProcessor {

    /**
     * 下载文章，加入磁盘缓存中
     */
    void downloadStories();
}
