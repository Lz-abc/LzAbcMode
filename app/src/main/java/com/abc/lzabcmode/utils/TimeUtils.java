package com.abc.lzabcmode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.Time;

/**
 * 时间换算帮助类
 * Created by 14036 on 2017/12/12.
 */

public class TimeUtils {

    private String toStr(int i) {
        if (i > 9) {
            return i + "";
        } else {
            return "0" + i;
        }
    }

    /**
     * 时间转换，统一返回00:00格式
     *
     * @param secTotal 单位秒
     * @return
     */
    public String toTimeStrm(int secTotal) {
        String result = null;
//        secTotal = secTotal / 1000;
        int hour = secTotal / 3600;
        int min = (secTotal % 3600) / 60;
        int sec = (secTotal % 3600) % 60;

        if (hour == 0) {
            result = toStr(min) + "分" + toStr(sec) + "秒";
        } else {
            result = toStr(hour) + "小时" + toStr(min) + "分" + toStr(sec) + "秒";
        }
        return result;
    }

    /**
     * 时间转换，统一返回00:00格式
     *
     * @param secTotal
     * @param tag
     * @return
     */
    public String toTimeStr(int secTotal, String tag) {
        String result = null;
        secTotal = secTotal / 1000;
        int hour = secTotal / 3600;
        int min = (secTotal % 3600) / 60;
        int sec = (secTotal % 3600) % 60;

        if (hour == 0) {
            result = toStr(min) + tag + toStr(sec);
        } else {
            result = toStr(hour) + tag + toStr(min) + tag + toStr(sec);
        }
        return result;
    }

    /**
     * 时间转换，如果时间小于一分钟，简洁一点，返回例如43''
     *
     * @param secTotal
     * @param tag
     * @return
     */
    public String toTimeStrConcise(int secTotal, String tag) {
        String result = null;
        secTotal = secTotal / 1000;
        int hour = secTotal / 3600;
        int min = (secTotal % 3600) / 60;
        int sec = (secTotal % 3600) % 60;

        if (secTotal <= 60) {
            return secTotal + "''";
        }

        if (hour == 0) {
            result = toStr(min) + tag + toStr(sec);
        } else {
            result = toStr(hour) + tag + toStr(min) + tag + toStr(sec);
        }
        return result;
    }

    /**
     * 时间转换，如果时间小于一分钟，简洁一点，返回例如43''
     *
     * @param secTotal
     * @param tag
     * @return
     */
    public String toTimeStrConciseWithSec(int secTotal, String tag) {
        String result = null;
        int hour = secTotal / 3600;
        int min = (secTotal % 3600) / 60;
        int sec = (secTotal % 3600) % 60;

        if (secTotal <= 60) {
            return secTotal + "''";
        }

        if (hour == 0) {
            result = toStr(min) + tag + toStr(sec);
        } else {
            result = toStr(hour) + tag + toStr(min) + tag + toStr(sec);
        }
        return result;
    }


    /**
     * 时间转换，分钟转换0小时0分钟
     *
     * @param secTotal 总分钟
     * @return
     */
    public String toTimeStrHour(int secTotal) {
        int hour = secTotal / 60;
        int min = secTotal % 60;
        if (hour == 0) {
            return min + "分钟";
        }
        return toStr(hour) + "小时" + toStr(min) + "分钟";
    }

    private SimpleDateFormat sf = null;

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getNowTime() {
        String timeString = null;
        Time time = new Time();
        time.setToNow();
        String year = thanTen(time.year);
        String month = thanTen(time.month + 1);
        String monthDay = thanTen(time.monthDay);
        String hour = thanTen(time.hour);
        String minute = thanTen(time.minute);

        timeString = year + "-" + month + "-" + monthDay + " " + hour + ":"
                + minute;
        // System.out.println("-------timeString----------" + timeString);
        return timeString;
    }


    public boolean judge(int year) {
        boolean yearleap = (year % 400 == 0) || (year % 4 == 0)
                && (year % 100 != 0);// 采用布尔数据计算判断是否能整除
        return yearleap;
    }

    /**
     * 十一下加零
     *
     * @param str
     * @return
     */
    public String thanTen(int str) {

        String string = null;

        if (str < 10) {
            string = "0" + str;
        } else {

            string = "" + str;

        }
        return string;
    }

    /**
     * 计算时间相差多少天
     */
    public int getDataDiffer(String time) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        long diff = 0;
        try {
            Date parse = dateFormat.parse(time);
            Date currentDate = dateFormat.parse(getNowTime());

            diff = parse.getTime() - currentDate.getTime();


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Math.abs(new Long(diff).intValue());
    }

    /**
     * 计算时间差
     *
     * @param starTime 开始时间
     * @param endTime  结束时间
     * @param
     * @return 返回时间差
     */
    public boolean getTimeDifference(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);
            long diff = parse1.getTime() - parse.getTime();
            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
            // System.out.println(day + "天" + hour + "小时" + min + "分" + s +"秒");
            long hour1 = diff / (60 * 60 * 1000);
            String hourString = hour1 + "";
            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
            if ((hour1 / 24) > 7) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 计算相差的小时
     *
     * @param starTime
     * @param endTime
     * @return
     */
    public String getTimeDifferenceHour(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();
            String string = Long.toString(diff);

            float parseFloat = Float.parseFloat(string);

            float hour1 = parseFloat / (60 * 60 * 1000);

            timeString = Float.toString(hour1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeString;

    }

    /**
     * 获取时间中的某一个时间点
     *
     * @param str
     * @param type
     * @return
     */
    public String getJsonParseShiJian(String str, int type) {
        String shijanString = null;
        String nian = str.substring(0, str.indexOf("-"));
        String yue = str.substring(str.indexOf("-") + 1, str.lastIndexOf("-"));
        String tian = str.substring(str.lastIndexOf("-") + 1, str.indexOf(" "));
        String shi = str.substring(str.indexOf(" ") + 1, str.lastIndexOf(":"));
        String fen = str.substring(str.lastIndexOf(":") + 1, str.length());

        switch (type) {
            case 1:
                shijanString = nian;
                break;
            case 2:
                shijanString = yue;
                break;
            case 3:
                shijanString = tian;
                break;
            case 4:
                shijanString = shi;
                break;
            case 5:
                shijanString = fen;
                break;

        }
        return shijanString;
    }

    /**
     * Sring变int
     *
     * @param str
     * @return
     */
    public int strToInt(String str) {
        int value = 0;
        value = Integer.parseInt(str);
        return value;
    }

    /**
     * 与当前时间比较早晚
     *
     * @param time 需要比较的时间
     * @return 输入的时间比现在时间晚则返回true
     */
    public boolean compareNowTime(String time) {
        boolean isDayu = false;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(time);
            Date parse1 = dateFormat.parse(getNowTime());

            long diff = parse1.getTime() - parse.getTime();
            if (diff <= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isDayu;
    }

    /**
     * 把时间戳变yyyy-MM-dd HH:mm格式时间
     *
     * @param time
     * @return
     */
    public static String getDateToString(long time, String format) {
        if (TextUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date parse;
        parse = new Date(time);
        return dateFormat.format(parse);
    }


    /**
     * 比较两个时间
     *
     * @param starTime  开始时间
     * @param endString 结束时间
     * @return 结束时间大于开始时间返回true，否则反之֮
     */
    public boolean compareTwoTime(String starTime, String endString) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endString);

            long diff = parse1.getTime() - parse.getTime();
            if (diff >= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isDayu;

    }

    /**
     * 获取年
     */
    public String getTimeYear(String time) {

        String substring = time.substring(0, time.lastIndexOf(" "));
        return substring;

    }

    /**
     * 换算小时，0.5小时-->0小时30分
     *
     * @param hour
     * @return
     */
    private String convertTime(String hour) {

        String substring = hour.substring(0, hour.lastIndexOf("."));
        String substring2 = hour.substring(hour.lastIndexOf(".") + 1,
                hour.length());
        substring2 = "0." + substring2;
        float f2 = Float.parseFloat(substring2);
        f2 = f2 * 60;
        String string = Float.toString(f2);
        String min = string.substring(0, string.lastIndexOf("."));
        return substring + "小时" + min + "分";

    }


    /**
     * 获取年月
     *
     * @param time 2018-1-25 15:28:03
     * @return 2018-1
     */
    public String getYearMonth(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        Date targetDate = null;
        try {
            targetDate = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(targetDate);
    }


    /**
     * 时间字符 转换时间戳
     *
     * @param serverTime 时间字符
     * @param format     日期格式
     * @return
     * @name lz
     * @time 2019/5/15 18:21
     */
    public Date parseServerTime(String serverTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
//            Timber.e(e, "");
        }
        return date;
    }

    /**
     * date 转换日期
     *
     * @param date
     * @param format
     * @return
     * @name lz
     * @time 2019/5/15 18:27
     */
    public String getDateStr(Date date, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
