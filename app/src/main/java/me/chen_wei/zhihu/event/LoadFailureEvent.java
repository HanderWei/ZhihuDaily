package me.chen_wei.zhihu.event;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class LoadFailureEvent {
    public String msg;

    public LoadFailureEvent(String msg){
        this.msg = msg;
    }
}
