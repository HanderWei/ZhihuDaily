package me.chen_wei.zhihu;

import android.app.Application;

/**
 * Created by Hander on 16/2/28.
 * <p/>
 * Email : hander_wei@163.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
    }
}
