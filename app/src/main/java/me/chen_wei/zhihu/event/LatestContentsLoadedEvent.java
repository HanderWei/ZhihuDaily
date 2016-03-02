package me.chen_wei.zhihu.event;

import me.chen_wei.zhihu.network.model.Contents;

/**
 * Created by Hander on 16/3/2.
 * <p/>
 * Email : hander_wei@163.com
 */
public class LatestContentsLoadedEvent {

    public Contents contents;

    public LatestContentsLoadedEvent(Contents contents) {
        this.contents = contents;
    }
}
