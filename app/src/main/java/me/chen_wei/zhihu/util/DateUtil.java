package me.chen_wei.zhihu.util;

import java.text.ParseException;
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
     * 获取最新日期
     *
     * @return
     */
    public static String getLatestDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 7) {//知乎日报7点之前不更新，所以7点以前最新列表应该加载之前一天的内容
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取前一天日期
     *
     * @param dateStr
     * @return
     */
    public static String getDayBeforeThisDayString(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            calendar.setTime(sdf.parse(dateStr));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            date = calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

}
