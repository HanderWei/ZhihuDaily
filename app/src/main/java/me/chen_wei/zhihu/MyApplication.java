package me.chen_wei.zhihu;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by Hander on 16/2/28.
 * <p/>
 * Email : hander_wei@163.com
 */
public class MyApplication extends Application {

    static{
        //设置DayNightTheme模式
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
    }
}
