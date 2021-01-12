package com.sleep.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
public class TimeUtils {
    public static final String YYYYMMDD = "yyyyMMdd";


    /**
     * 获取当前毫秒数
     *
     * @return 当前毫秒数
     */
    public static long nowMillis() {
        return System.currentTimeMillis();
    }

    /**
     * yyyyMMddHHmmss 转换为 时间戳
     * @param yyyyMMddHHmmss
     * @return
     */
    public static Long yyyyMMddHHmmss2Timestamp(String yyyyMMddHHmmss){
        if(StringUtils.isEmpty(yyyyMMddHHmmss)){
            return -1L;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        try {
            date = format.parse(yyyyMMddHHmmss);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    /**
     * string类型转换为date类型
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * HH时mm分ss秒，
     * strTime的时间格式必须要与formatType的时间格式相同
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
        throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * string类型转换为long类型
     * strTime要转换的String类型的时间
     * formatType时间格式
     * strTime的时间格式和formatType的时间格式必须相同
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType) throws ParseException {
        // String类型转成date类型
        Date date = stringToDate(strTime, formatType);
        if (date == null) {
            return 0;
        } else {
            // date类型转成long类型
            return dateToLong(date);
        }
    }

    /**
     * date类型转换为long类型
     * date要转换的date类型的时间
     *
     * @param date
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     *
     * @return 当天的年月日对应的时间戳
     */
    public static long getToday4yyyyMMdd(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     *
     * @param baseTime 时间戳
     * @param days 向前移动的天数
     * @return 时间戳
     */
    public static long forwardDays(long baseTime, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(baseTime);
        calendar.add(Calendar.DAY_OF_MONTH,days);
        return calendar.getTimeInMillis();
    }

    /**
     * 时间戳转换为 HH:mm
     * @param timestamp
     * @return
     */
    public static String timestamp2HHmm(Long timestamp){
        if (timestamp == null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(new Date(timestamp));
        return time;
    }

    /**
     * 时间戳转换为 d日
     * @param timestamp
     * @return
     */
    public static String timestamp2d(Long timestamp){
        if (timestamp == null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("d日");
        String time = format.format(new Date(timestamp));
        return time;
    }

    /**
     *
     * @return 当年的年月日对应的时间戳
     */
    public static long getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private static final long dayMillisecond = 24*3600*1000;
}
