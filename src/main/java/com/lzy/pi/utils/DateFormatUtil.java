package com.lzy.pi.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zbw<br>
 * @date 2019年1月23日<br>
 */
public final class DateFormatUtil {

    private static final String START = "00:00:00";
    public static final String END = "23:59:59";
    public static final String YYYY_MM_DD = "yyyy/MM/dd";
    private static final int THOUSAND = 1000;
    public static final String YYYY_MM_DD_HH_MM = "yyyy年M月dd日 HH:mm";
    public static final String YYYY_M_DD_HH_MM = "yyyy.M.dd HH:mm";
    public static final String YYYYMMDD = "YYYYMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS = " yyyy/MM/dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS1 = " yyyy-MM-dd HH:mm:ss";

    private DateFormatUtil() {

    }

    public static String getFormatDate(Date time) {
        String desc = "";
        if (null != time) {
            desc = DateUtil.getDateString(time, YYYY_MM_DD);
        }
        return desc;
    }

    public static String getFormatDate(Date time, String argument) {
        String desc = "";
        if (null != time) {
            desc = DateUtil.getDateString(time, argument);
        }
        return desc;
    }


    /**
     * 计算两个日期的时间差(分钟)
     *
     * @param formatTime1
     * @param formatTime2
     * @return
     */
    public static long getSecondDif(Timestamp formatTime1, Timestamp formatTime2) {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        long t1 = 0L;
        long t2 = 0L;
        try {
            t1 = timeformat.parse(DateUtil.getTimeStampNumberFormat(formatTime1)).getTime();
        } catch (ParseException e) {
        }
        try {
            t2 = timeformat.parse(DateUtil.getTimeStampNumberFormat(formatTime2)).getTime();
        } catch (ParseException e) {
        }
        // 毫秒ms
        long diff = t1 - t2;
        return diff / THOUSAND;
    }


    //获得指定时间段内的所有日期（天）
    public static List<String> getDaysByRange(Date dBegin, Date dEnd) {
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }


    /**
     * 获取当前是几点
     *
     * @return
     */
    public static int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 以起始时间为开始,限制结束日期为多少天内
     * 例 开始为 startTime 2020-12-20 endTime 2020-12-30  days 3 返回结果startTime 2020-12-20 endTime 2020-12-23
     * 开始为 startTime 2020-12-20 endTime 2020-12-30  days 20 返回结果startTime 2020-12-20 endTime 2020-12-30
     * 开始为 startTime null endTime 2020-12-30  days 20 返回结果startTime 2020-12-10 endTime 2020-12-30
     * 开始为 startTime 2020-12-20  endTime null  days 20 返回结果startTime 2020-12-10 endTime 2020-12-30
     * 开始为 startTime null endTime null   days 20 返回20天前的开始时间,结束时间为当前时间
     *
     * @param startTime
     * @param endTime
     * @param days
     * @return
     */
    public static Map<String, Date> transDateLengthInDays(Date startTime, Date endTime, int days) {
        Map<String, Date> resultTime = new HashMap<String, Date>();
        if (startTime != null) {
            Date endDate = DateUtil.getOffsetDaysDate(startTime, days);
            resultTime.put("startTime", startTime);
            resultTime.put("endTime", endTime == null ? endDate : endTime.getTime() > endDate.getTime() ? endDate : endTime);
        } else {
            if (endTime != null) {
                Date startDate = DateUtil.getOffsetDaysDate(endTime, -days);
                resultTime.put("startTime", startDate);
                resultTime.put("endTime", endTime);
            } else {
                Date endDate = DateUtil.getSysDate();
                Date startDate = DateUtil.getOffsetDaysDate(endDate, -days);
                resultTime.put("startTime", startDate);
                resultTime.put("endTime", endDate);
            }
        }
        return resultTime;
    }
}
