/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.constant;

import inet.util.DateProc;
import inet.util.Today;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author TUYEN_CUTE
 */
public class Constants {
    
    public static final String DATABASE = "lottery";
    public static boolean THREAD_RUNNING = true;
    public static final String UNDER_SCORE = "_";
    
	
    //time
    public static final long TEN_MINUS = 10 * 60 * 1000;
    
    //news status
    public static int NEWS_STATUS_NONE = 0;// trạng thái mới được tạo
    public static int NEWS_STATUS_DISPLAY = 1;//Cho phép tin tức được hiển thị
    public static int NEWS_STATUS_DISABLE = 2;//Không cho hiển thị tin tức
    public static int NEWS_STATUS_HOT = 3;//Đánh dấu là tin tức đang hot
    public static int NEWS_STATUS_UN_DISPLAY = 4;//Tin tức không đạt yêu cầu
    
    //for Login
    public static final String LOGIN_SESSION = "LOGIN_SESSION";
    public static final String ADMIN_LOGIN_SESSION = "ADMIN_LOGIN_SESSION";
    
    public static final String NEWS = "NEWS";
    public static final String NEWS_DISPLAY = "NEWS_DISPLAY";
    public static final String NEWS_DETAIL = "NEWS_DETAIL";
    
    public static final String SITE = "LOTTERY_SITE";
    public static final String SEO = "LOTTERY_SEO";
     //for upload images - folder
    public static final String NEWS_FOLDER = "news";  
    public static final String NEWS_URL = "http://static.xoso.wap.vn/";  
    public static final String URL_CAN = "https://xosohn.com";
    
    //message
    public static final String MSG_INSERT_SUCCESS = "Thêm mới thành công";
    public static final String MSG_INSERT_FAIL = "Thêm mới thất bại";
    public static final String MSG_UPDATE_SUCCESS = "Sửa thành công";
    public static final String MSG_UPDATE_FAIL = "Không sửa được dữ liệu";
    public static final String MSG_DELETE_FAIL = "Không xóa được bản ghi vừa chọn";
    public static final String MSG_DELETE_SUCCESS = "Xóa thành công bản ghi";
    public static final String MSG_CHANGE_STATUS_SUCCESS = "Thay đổi trạng thái thành công";
    public static final String MSG_CHANGE_STATUS_FAIL = "Thay đổi trạng thái thất bại";
    
    public static String[] LOTO={"00","01","02","03","04","05","06","07","08","09",
                                "10","11","12","13","14","15","16","17","18","19",
                                "20","21","22","23","24","25","26","27","28","29",
                                "30","31","32","33","34","35","36","37","38","39",
                                "40","41","42","43","44","45","46","47","48","49",
                                "50","51","52","53","54","55","56","57","58","59",
                                "60","61","62","63","64","65","66","67","68","69",
                                "70","71","72","73","74","75","76","77","78","79",
                                "80","81","82","83","84","85","86","87","88","89",
                                "90","91","92","93","94","95","96","97","98","99"};
    //XSVui User display type
    public static final int DISPLAY_MOBILE = 0;
    public static final int DISPLAY_USERNAME = 1;
    
    //XSVui User status
    public static final int USER_PENDING = 0;
    public static final int USER_ACTIVE = 1;
    
    //Prefix 2 hash code left coin (Hashcode = MD5(Prefix + left_coin))
    public static final String PREFIX_2_HASHCODE = "XSVUI";
    
    //Loại giao dịch trong XSVui
    public static final int TYPE_OTHER = 0;
    public static final int TYPE_RENEW = 1;
    public static final int TYPE_CHONSO = 2;
    public static final int TYPE_KQSO = 3;
    public static final int TYPE_FOLLOW = 4;
    public static final int TYPE_VIEWCONTENT = 5;
    
    //Trạng thái giao dịch
    public static final int STATUS_OK = 1;
    public static final int STATUS_FAIL = 0;
    
    public static final HashMap<String, String> errorMsgMap = new HashMap<String, String>();
    public static final String NOT_ENOUGH_COIN = "not_enough_coin";
    public static final String MISSING_USER_MOBILE = "missing_user_mobile";
    public static final String MISSING_USER_MOBILE_ID = "missing_user_mobile_id";
    public static final String NOT_EXIST_USER_MOBILE = "not_exist_user_mobile";
    public static final String NOT_EXIST_USER = "not_exist_user";
    public static final String LOGIN_SUCCESS = "login_success";
    public static final String WRONG_PASSWORD = "wrong_password";
    public static final String SYSTEM_ERROR = "system_error";
    public static final String TRANSACTION_SUCCESS = "transaction_success";
    public static final String ALREADY_EXIST_USER_MOBILE = "already_exist_user_mobile";
    public static final String REGISTER_SUCCESS = "register_success";
    public static final String UPDATE_SUCCESS = "update_success";
    public static final String BETTING_NUMBER_NOT_CORRECT = "betting_number_not_correct";
    public static final String BETTING_COIN_NOT_CORRECT = "betting_coin_not_correct";
    public static final String BETTING_TIMEOUT = "betting_timeout";
    public static final String BETTING_TYPE_NOT_CORRECT = "betting_type_not_correct";
    public static final String GET_BETTING_DAY_NOT_CORRECT = "get_betting_day_not_correct";
    public static final String OLD_PASSWD_NOT_CORRECT = "old_passwd_not_correct";
    public static final String CHANGE_PASSWD_SUCCESS = "change_passwd_success";
    public static final String CONTENT_NOT_EXIST = "content_not_exist";
    public static final String CONTENT_ALREADY_CHARGE = "content_already_charge";
    public static final String CONTENT_NOT_CHARGE = "content_not_charge";
    public static final String MAX_RECORD_NOT_CORRECT = "max_record_not_correct";
    public static final String NO_CONTENT = "no_content";
    
    static {
        errorMsgMap.put(NOT_ENOUGH_COIN, "Tài khoản không đủ xu để thanh toán");
        errorMsgMap.put(MISSING_USER_MOBILE, "Thiếu tham số (mobile hoặc password)!");
        errorMsgMap.put(MISSING_USER_MOBILE_ID, "Thiếu tham số (mobile, username hoặc user id)!");
        errorMsgMap.put(NOT_EXIST_USER_MOBILE, "Mobile hoặc username không tồn tại!");
        errorMsgMap.put(NOT_EXIST_USER, "User không tồn tại!");
        errorMsgMap.put(LOGIN_SUCCESS, "Đăng nhập thành công!");
        errorMsgMap.put(WRONG_PASSWORD, "Sai mật khẩu. Vui lòng thử lại!");
        errorMsgMap.put(SYSTEM_ERROR, "Lỗi hệ thống!");
        errorMsgMap.put(TRANSACTION_SUCCESS, "Giao dịch thành công");
        errorMsgMap.put(ALREADY_EXIST_USER_MOBILE, "Mobile hoặc username đã tồn tại trên hệ thống!");
        errorMsgMap.put(REGISTER_SUCCESS, "Đăng ký thành công!");
        errorMsgMap.put(UPDATE_SUCCESS, "Cập nhật thành công!");
        errorMsgMap.put(BETTING_NUMBER_NOT_CORRECT, "Số được chốt không đúng!");
        errorMsgMap.put(BETTING_COIN_NOT_CORRECT, "Số xu đặt cược không đúng!");
        errorMsgMap.put(BETTING_TIMEOUT, "Đang thời gian quay giải. Không thể đặt cược thời điểm này!");
        errorMsgMap.put(BETTING_TYPE_NOT_CORRECT, "Kiểu đặt cược không đúng (type bằng 0 hoặc type bằng 1)!");
        errorMsgMap.put(GET_BETTING_DAY_NOT_CORRECT, "Ngày cần lấy lịch sử đặt số không chính xác!");
        errorMsgMap.put(OLD_PASSWD_NOT_CORRECT, "Mật khẩu cũ không đúng!");
        errorMsgMap.put(CHANGE_PASSWD_SUCCESS, "Đổi mật khẩu thành công!");
        errorMsgMap.put(CONTENT_NOT_EXIST, "Nội dung cần xem không tồn tại!");
        errorMsgMap.put(CONTENT_ALREADY_CHARGE, "Nội dung cần xem đã được thanh toán!");
        errorMsgMap.put(CONTENT_NOT_CHARGE, "Nội dung cần xem chưa được thanh toán!");
        errorMsgMap.put(MAX_RECORD_NOT_CORRECT, "Số lượng bản ghi cần lấy không đúng!");
        errorMsgMap.put(NO_CONTENT, "Không có dữ liệu!");
    }
    
    //Content id khi xem phai tra phi
    public static final BigDecimal CONTENTID_DEFAULT = BigDecimal.ZERO;
    public static final BigDecimal CONTENTID_THONGKE = BigDecimal.ONE;
    public static final BigDecimal CONTENTID_SOICAU = new BigDecimal(2);
    public static final BigDecimal [] arrContent = new BigDecimal[]{CONTENTID_DEFAULT, CONTENTID_THONGKE, CONTENTID_SOICAU};
    
    //Kiểu chốt số
    public static final int CHOTSO_LO = 0;
    public static final int CHOTSO_DE = 1;
    
    public static final String CHAM_DB = "CHAM_DB";
    public static final String CHAM_LOTO = "CHAM_LOTO";
    
    //Mảng các giá trị có thể của lô hoặc đề
    public static final String [] arrLoDe = new String[100];
    static{
        for(int i = 0; i < 100; i++){
            if(i < 10){
                arrLoDe[i] = "0" + i;
            }else{
                arrLoDe[i] = "" + i;
            }
        }
    }
    
    //public static final String GOOGLE_REDIRECT_URL = "http://localhost:8080/xoso/login/google/index.htm";
    public static final String GOOGLE_REDIRECT_URL = "http://ketqua247.com/ketqua247/login/google/index.htm";
    
    public static void main(String[] args) {
        String a = "01";
        String b = "100";
        String c = null;
        System.out.println("lo contains a is " + Arrays.asList(arrLoDe).contains(a));
        System.out.println("lo contains b is " + Arrays.asList(arrLoDe).contains(b));
        System.out.println("lo contains c is " + Arrays.asList(arrLoDe).contains(c));
        
        Timestamp now = DateProc.createTimestamp();
        Today today = new Today();
        Timestamp startCheck = DateProc.buildTimestamp(today.getYear(), today.getMonth(), today.getDay(), 18, 0);
        Timestamp endCheck = DateProc.buildTimestamp(today.getYear(), today.getMonth(), today.getDay(), 18, 30);
        System.out.println("Now = " + now);
        System.out.println("startCheck = " + startCheck);
        if(now.after(startCheck)){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
        System.out.println("endCheck = " + endCheck);
        if(now.before(endCheck)){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
    }
}
