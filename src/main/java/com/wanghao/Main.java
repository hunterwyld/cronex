package com.wanghao;

import com.wanghao.util.ParseUtil;

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
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis()/1000;
        //args[0]
        String input = "0 15 20 3 FEB/2 ?";
        //args[1]
        int count = 3;
        Cron cron = ParseUtil.parse(input);
        System.out.println(cron);
        List<String> triggerTimes = getNextTriggerTimes(cron, count);
        System.out.println(triggerTimes);
        long end = System.currentTimeMillis()/1000;
        System.out.println(end-start);
    }

    /** 获取接下来count次的触发时间 */
    private static List<String> getNextTriggerTimes(Cron cron, int count) {
        List<String> retList = new ArrayList<>(count);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date baseTime = new Date();
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
        //2099-12-31 23:59:59
        final Date MAX_DATE = new Date(4102415999000L);
        Date time = new Date(baseTime.getTime() + 1000);
        while (time.before(MAX_DATE)) {
            if (cron.matches(time)) {
                return time;
            }
            time = new Date(time.getTime() + 1000);
        }

        return null;
    }
}
