/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import inet.bean.Lottery;
import inet.bean.LotteryResult;
import java.util.StringTokenizer;

/**
 *
 * @author HanhDung
 */
public class StringConvert {
	public static String stringConcatLottery(Lottery lotteryResult){
        String result=lotteryResult.getSpecial();
        result+=lotteryResult.getFirst().replaceAll("-", "");
        result+=lotteryResult.getSecond().replaceAll("-", "");
        result+=lotteryResult.getThird().replaceAll("-", "");
        result+=lotteryResult.getFourth().replaceAll("-", "");
        result+=lotteryResult.getFifth().replaceAll("-", "");
        result+=lotteryResult.getSixth().replaceAll("-", "");
        result+=lotteryResult.getSeventh().replaceAll("-", "");
        if(lotteryResult.getEighth()!=null){
            result+=lotteryResult.getEighth().replaceAll("-", "");
        }
        
        return result;
    }
	
    public static String stringConcat(LotteryResult lotteryResult){
        String result=lotteryResult.getSpecial();
        result+=lotteryResult.getFirst().replaceAll("-", "");
        result+=lotteryResult.getSecond().replaceAll("-", "");
        result+=lotteryResult.getThird().replaceAll("-", "");
        result+=lotteryResult.getFourth().replaceAll("-", "");
        result+=lotteryResult.getFifth().replaceAll("-", "");
        result+=lotteryResult.getSixth().replaceAll("-", "");
        result+=lotteryResult.getSeventh().replaceAll("-", "");
        if(lotteryResult.getEighth()!=null){
            result+=lotteryResult.getEighth().replaceAll("-", "");
        }
        
        return result;
    }
    
    public static String subRight(String temp,int i){
        if(temp == null||"".equals(temp)) return "";
        String result="";
        result=temp.substring(temp.length()-i, temp.length());
        return result;
    }
    
    public static String lotoResult(LotteryResult lotteryResult){
        String result=subRight(lotteryResult.getSpecial(), 2);
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFirst(),"-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            result+="-"+subRight(token, 2);
        }
                        
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSecond(),"-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            result+="-"+subRight(token, 2);
        }
        
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getThird(),"-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            result+="-"+subRight(token, 2);
        }
        
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFourth(),"-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            result+="-"+subRight(token, 2);
        }
        
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFifth(),"-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            result+="-"+subRight(token, 2);
        }
        
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSixth(),"-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            result+="-"+subRight(token, 2);
        }
        
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSeventh(),"-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            result+="-"+subRight(token, 2);
        }
        
        if(lotteryResult.getEighth()!=null&&!"".equals(lotteryResult.getEighth())){
            result+="-"+subRight(lotteryResult.getEighth(), 2);
        }
        
        
        return result;
    }
}
