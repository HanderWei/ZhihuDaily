package me.chen_wei.zhihu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hander on 16/2/27.
 * <p/>
 * Email : hander_wei@163.com
 */
public class DateUtil {

    /**
     * 获取日期字符串
     *
     * @param i 与当天的天数差
     * @return
     */
    public static String getDateString(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, i + 1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public static int getHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
