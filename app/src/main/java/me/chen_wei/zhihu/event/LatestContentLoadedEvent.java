package me.chen_wei.zhihu.event;

import me.chen_wei.zhihu.network.model.Latest;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class LatestContentLoadedEvent {

    public Latest latestContent;

    public LatestContentLoadedEvent(Latest latest){
        this.latestContent = latest;
    }
}
