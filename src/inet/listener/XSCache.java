/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import inet.bean.LotteryCompany;
import inet.bean.LotteryResult;
import inet.model.LotteryCompanyDAO;
import inet.model.LotteryResultDAO;

/**
 *
 * @author 24h
 */
public class XSCache implements ServletContextListener, Runnable{
     
    //key: code_openDate
    public static HashMap<String, LotteryResult> hmLotteryByCodeAndOpenDate = new HashMap<String, LotteryResult>();
    public static HashMap<String, Integer> hmCheckInsert = new HashMap<String, Integer>();
    //key: dayOfWeek_region
    public static HashMap<String, List<LotteryCompany>> hmCompany = new HashMap<String, List<LotteryCompany>>();
    private boolean running = true;
    
    public static LotteryResult getLotteryByCodeAndOpenDate(String code, String openDate){
        String key = code + "_" + openDate;
        if(hmLotteryByCodeAndOpenDate.get(key) != null){
            return hmLotteryByCodeAndOpenDate.get(key);
        }else{
            LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
            LotteryResult lottery = lotteryResultDAO.findLRByCodeInDay(code, openDate);
            if(lottery != null){
                hmLotteryByCodeAndOpenDate.put(key, lottery);
                return lottery;
            }
        }
        return null;
    }
    
    public static List<LotteryCompany> getListCompanyToday(String region){
        String dayOfWeek = getDayOfWeek4Today();
        String key = dayOfWeek + "_" + region;
        if(hmCompany.get(key) != null){
            return hmCompany.get(key);
        }else{
            LotteryCompanyDAO companyDAO = new LotteryCompanyDAO();
            List<LotteryCompany> list = companyDAO.findDayOfWeek(dayOfWeek, region);
            if(list != null){
                hmCompany.put(key, list);
                return list;
            }
        }
        return null;
    }
    
    public static void addLottery(LotteryResult lottery){
        String key = lottery.getCode() + "_" + lottery.getOpenDate();
        hmLotteryByCodeAndOpenDate.put(key, lottery);
    }
    
    public static void saveInsert(String code, String openDate){
        String key = code + "_" + openDate;
        hmCheckInsert.put(key, 1);
    }
    
    public static boolean checkInsert(String code, String openDate){
        String key = code + "_" + openDate;
        return hmCheckInsert.get(key) != null;
    }
    
    public static void clearCache(){
        if(!hmLotteryByCodeAndOpenDate.isEmpty()) hmLotteryByCodeAndOpenDate.clear();
        if(!hmCompany.isEmpty()) hmCompany.clear();
    }
    
    public static String getDayOfWeek4Today(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == 1 || dayOfWeek == 8) return "CN";
        return dayOfWeek + "";
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Thread XSCache Starting...");
        new Thread(this).start();
        System.out.println("Thread XSCache Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Stop running Thread XSCache");
        running = false;
    }
    
    @Override
    public void run() {
        while (running) {
            clearCache();
            try {
                Thread.sleep(1000 * 60 * 45);
            } catch (InterruptedException ex) {
                Logger.getLogger(XSCache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
