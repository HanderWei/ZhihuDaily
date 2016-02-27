package me.chen_wei.zhihu.event;

import me.chen_wei.zhihu.network.model.Contents;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class ContentsLoadedEvent {

    public Contents contents;

    public ContentsLoadedEvent(Contents contents){
        this.contents = contents;
    }
}
