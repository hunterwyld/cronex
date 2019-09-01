package com.wanghao;

import com.wanghao.exception.InvalidCronException;
import com.wanghao.util.ParseUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wanghao
 * @description: 程序入口
 * @date 5/30/19 7:47 PM
 */
public class Main {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //1970-01-01 08:00:00
    private static final Date MIN_DATE = new Date(0L);
    //2099-12-31 23:59:59
    private static final Date MAX_DATE = new Date(4102415999000L);

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("illegal number of arguments: " + args.length + ", should be 3");
        }
        String input = args[0];
        int count = Integer.parseInt(args[1]);
        String startDateStr = args[2];

        if (count > 10) {
            throw new IllegalArgumentException("invalid count: " + count + ", should be within [1, 10]");
        }
        Date startDate;
        try {
            startDate = sdf.parse(startDateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid start date: " + startDateStr + ", should be with pattern: " + sdf.toPattern());
        }
        if (startDate.before(MIN_DATE) || startDate.after(MAX_DATE)) {
            throw new IllegalArgumentException("invalid start date: " + startDateStr + ", should be within [1970-01-01 08:00:00, 2099-12-31 23:59:59] (BST)");
        }

        //String input = "*/1 15 20 * * ?";
        //int count = 3;
        Cron cron;
        try {
            cron = ParseUtil.parse(input);
        } catch (InvalidCronException e) {
            throw new IllegalArgumentException("invalid cron expression: " + input);
        }
        //System.out.println(cron);
        List<String> triggerTimes = getNextTriggerTimes(cron, count, startDate);
        System.out.println(triggerTimes);
    }

    /** 获取接下来count次的触发时间，从startDate开始计算 */
    private static List<String> getNextTriggerTimes(Cron cron, int count, Date startDate) {
        List<String> retList = new ArrayList<>(count);
        Date baseTime = startDate;
        for (int i = 0; i < count; i++) {
            Date triggerTime = getNextTriggerTime(cron, baseTime);
            if (triggerTime == null) {
                break;
            }
            retList.add(sdf.format(triggerTime));
            baseTime = triggerTime;
        }

        return retList;
    }

    /** 根据baseTime获取下次触发时间 */
    private static Date getNextTriggerTime(Cron cron, Date baseTime) {
        Date time = new Date(baseTime.getTime() + 1000);
        while (time.before(MAX_DATE)) {
            if (cron.matches(time)) {
                return time;
            }
            //每次增加1秒
            time = new Date(time.getTime() + 1000);
        }

        return null;
    }
}
