/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

/**
 *
 * @author iNET
 */
public class Contant {            
    
    public static String SITE_ID="13";
    public static String MAX_NEWS="5";
    public static String SITE_PHAP_LUAT="15";
    public static String MAX_PHAP_LUAT="5";
    public static String DAY_OF_WEEK_NUM_DAY="7";
    public static String LOTO_MAX_GAN="15";
    public static String LOTO_NUM_WEEK="4";
    public static String STATISTICAL_SPECIAL_NUM_WEEK="30";
    public static String XSTTI="XSTTI";
    public static String XSDT123="XSDT123";
    public static String XSDT636="XSDT636";
    
    public static String URL_LOTTERY_COMPANY="http://api.xoso.wap.vn:8080/getdataxs?CMD=GET_LIST_PROVINCE";
    public static String URL_LOTTERY_RESULT_OF_REGION="http://api.xoso.wap.vn:8080/getdataxs?CMD=GETKQXSBYREGION&DATE=ddmmyyyy&REGION=region";
    public static String URL_LOTTERY_RESULT_REGION="http://api.xoso.wap.vn:8080/getdataxs?CMD=GETKQXSREGION&SDATE=sdate&EDATE=edate&REGION=region";
    public static String URL_LOTTERY_NEWS="http://api.xoso.wap.vn:8080/getnews?CMD=NEWS_LIST&SITE_ID=sid&START=start&END=end";
    public static String URL_LOTTERY_NEWS_DETAIL="http://api.xoso.wap.vn:8080/getnews?CMD=NEWS_DETAIL&SITE_ID=sid&NEWS_ID=nid";    
    public static String URL_LOTTERY_RESULT_DAY_OF_WEEK="http://api.xoso.wap.vn:8080/getdataxs?CMD=GET_RESULT_DAY_OF_WEEK&CODE=code&DAYOFWEEK=dayofweek&NUMDAY=numday";
    public static String URL_LOTTERY_RESULT_START_TO_END="http://api.xoso.wap.vn:8080/getdataxs?CMD=GETKQXS&CODE=code&SDATE=sdate&EDATE=edate";
    public static String URL_GET_RESULT_OF_CODE_NUM_ROW="http://api.xoso.wap.vn:8080/getdataxs?CMD=GET_RESULT_OF_CODE_NUM_ROW&CODE=code&NUMROW=numrow";
    public static String URL_LOTTERY_RESULT_SPECIAL_START_TO_END="http://api.xoso.wap.vn:8080/getdataxs?CMD=GET_RESULT_SPECIAL&CODE=code&SDATE=sdate&EDATE=edate";
    public static String URL_LOTO_GAN="http://api.xoso.wap.vn:8080/getdataxs?CMD=GET_LOTO_GAN&CODE=code&NUMWEEK=numweek&MAX=max";
    public static String URL_STATISTICAL_SPECIAL="http://api.xoso.wap.vn:8080/getdataxs?CMD=GET_STATISTICAL_SPECIAL&CODE=code&NUMWEEK=numweek";
    public static String URL_DT_TTI="http://api.xoso.wap.vn:8080/getdataxs?CMD=GET_RESULT_COMPUTING&CODE=code&SDATE=sdate&EDATE=edate";
    public static String URL_LOTTERY_SEO="http://api.xoso.wap.vn:8080/getnews?CMD=SEO&SITE_ID=siteid&LINK=link";
    public static String URL_LOTTERY_NEWEST="http://api.xoso.wap.vn:8080/getdataxs?CMD=GETKQXSNEWEST&CODE=code";
    
    
    // thong ke
    public static String URL_THONG_KE_LOTO_CAP_SO="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_LO_TO_CAP_SO&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_NGAY_NAY_NAM_TRUOC="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_NGAY_NAY_NAM_TRUOC&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_NGAY_NAY_THANG_TRUOC="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_NGAY_NAY_THANG_TRUOC&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_MO_BAT="http://api.xoso.wap.vn:8080/getdataxs?CMD=MOBAT&SDATE=sdate&CODE=code";
    public static String URL_THONG_KE_DACBIET_CAP_SO="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_CAP_SO&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_DAC_BIET_CAP_SO_HANG_CHUC="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_CAP_SO_HANG_CHUC&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_DAC_BIET_CAP_SO_HANG_DON_VI="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_CAP_SO_HANG_DON_VI&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_DAC_BIET_TONG="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_TONG&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_DAC_BIET_HIEU="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_HIEU&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_DAC_BIET_CHAM="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_CHAM&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_DAC_BIET_CHIA_HET_CHO_3="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_CHIA_HET_CHO_3&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_DAC_BIET_GIAI_SO="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_GIAI_SO&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_DAC_BIET_CAP_SO_CHAN_LE="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_DAC_BIET_CAP_SO_CHAN_LE&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_LO_TO_HANG_CHUC="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_LO_TO_HANG_CHUC&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_LO_TO_DON_VI="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_LO_TO_DON_VI&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_THONG_KE_LO_ROI="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_LO_ROI&CODE=code&NUMOFWEEK=numofweek";
    public static String URL_SO_MO="http://api.xoso.wap.vn:8080/getdataxs?CMD=SO_MO";
    public static String URL_THONG_KE_VIP="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_VIP&CODE=code&DDMMYYYY=ddmmyyyy";
    public static String URL_GET_RESULT_OF_CODE="http://api.xoso.wap.vn:8080/getdataxs?CMD=GET_RESULT_OF_CODE&CODE=code&DATE=date";
    public static String URL_THONG_KE_VIPS="http://api.xoso.wap.vn:8080/getdataxs?CMD=THONG_KE_VIPS&CODE=code&NUMROW=numrow";
    public static String URL_PHANTICHLOTO="http://api.xoso.wap.vn:8080/getdataxs?CMD=PHANTICHLOTO&STARTDATE=startdate&LON=lon&NHAY=nhay&CAU=cau&MAXCAU=maxcau";
    public static String URL_LOTTERYPHANTICHLOTO="http://api.xoso.wap.vn:8080/getdataxs?CMD=LOTTERYPHANTICHLOTO";
    
    
    // xo so vui
    public static String URL_XO_SO_VUI_LOGIN="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=LOGIN&USERNAME=username&PASSWORD=password&MOBILE=mobile";
    public static String URL_XO_SO_VUI_CHANGEUSER="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=CHANGEUSER&USERNAME=username&MOBILE=mobile";
    public static String URL_XO_SO_VUI_BETTING="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=BETTING&MOBILE=mobile&TYPE=type&USERNAME=username&USERID=userid"
                                                + "&SO1=so1&SO2=so2&SO3=so3&COIN1=coin1&COIN2=coin2&COIN3=coin3";
    
    public static String URL_XO_SO_VUI_GET_BETTING="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=GETBETTING&MOBILE=mobile&TYPE=type&SDAY=sday&EDAY=eday";
    public static String URL_XO_SO_VUI_GET_BETTING_ALL="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=GETBETTINGALL&MOBILE=mobile&TYPE=type&DAY=day&LIMIT=limit&SDAY=sday&EDAY=eday&PAGE=page&PAGESIZE=pagesize";
    public static String URL_XO_SO_VUI_CHANGEPASSWD="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=CHANGEPASSWD&MOBILE=mobile&OLDPASS=oldpass&NEWPASS=newpass";
    public static String URL_XO_SO_VUI_STATISCBETTING="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=STATISCBETTING&TYPE=type&DAY=day&LIMIT=limit";
    public static String URL_XO_SO_VUI_STATISCLUCKY="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=STATISCLUCKY&LIMIT=limit&PAGE=page&PAGESIZE=pagesize";
    public static String URL_XO_SO_VUI_STATISCTOPUSER="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=STATISCTOPUSER&LIMIT=limit&PAGE=page&PAGESIZE=pagesize";
    public static String URL_XO_SO_VUI_STATISCTOPUSERDAY="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=STATISCTOPUSERDAY&LIMIT=limit&SDAY=sday&EDAY=eday&PAGE=page&PAGESIZE=pagesize";
    public static String URL_XO_SO_VUI_STATISCLUCKYDAY="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=STATISCLUCKYDAY&LIMIT=limit&SDAY=sday&EDAY=eday&PAGE=page&PAGESIZE=pagesize";
    public static String URL_XO_SO_VUI_FOLLOW="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=FOLLOW&MOBILE1=mobile1&MOBILE2=mobile2";
    public static String URL_XO_SO_VUI_FOLLOWUSERID="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=FOLLOWUSERID&USERID=userid";
    public static String URL_XO_SO_VUI_FOLLOWEDID="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=FOLLOWEDID&FOLLOWEDID=userid";
    public static String URL_XO_SO_VUI_HISTORY="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=HISTORY&MOBILE=mobile&SDAY=sday&EDAY=eday&PAGE=1&PAGESIZE=100";
    public static String URL_XO_SO_VUI_ADDCOIN="http://api.xoso.wap.vn:8080/xsVuiApi?CMD=ADDCOIN&MOBILE=mobile&COIN=coin&CONTENT=content&TYPE=type&CONTENTID=contentid&FUSER=fuser&FEDUSER=feduser";
    
    
    
    // message
    public static final String msg_error_password="Sai mật khẩu. Vui lòng thử lại!";
    public static final String msg_error_password_old="Sai mật khẩu cũ. Vui lòng thử lại!";
    public static final String msg_change_password="Đổi mật khẩu thành công";
    public static final String msg_follow_success="Theo dõi thành công";
    public static final String msg_follow_unsuccess="Theo dõi không thành công";
    
}
