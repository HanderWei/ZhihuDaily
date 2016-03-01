package me.chen_wei.zhihu.network.processor;

import android.content.Context;

/**
 * Created by Hander on 16/2/27.
 * <p/>
 * Email : hander_wei@163.com
 */
public interface INewsProcessor {

    /**
     * 获取某篇文章的内容
     *
     * @param context
     * @param id      文章的唯一ID
     */
    void getNewsContent(Context context, int id);
}
