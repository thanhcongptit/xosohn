/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xs.controller.admin;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Lottery;
import inet.bean.LotteryResult;
import inet.controller.BaseController;
import inet.model.LotteryResultDAO;

/**
 *
 * @author conglt
 */
public class AdminSaveKQXSController extends BaseController {
    private CommonUtil commonUtil = new CommonUtil();   
    
    public AdminSaveKQXSController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String openDate = request.getParameter("date");
        String special = request.getParameter("special");
        String first = request.getParameter("first");
        String second[] = request.getParameterValues("second");
        String third[] = request.getParameterValues("third");
        String fourth[] = request.getParameterValues("fourth");
        String fifth[] = request.getParameterValues("fifth");
        String sixth[] = request.getParameterValues("sixth");
        String seventh[] = request.getParameterValues("seventh");
        
        String ses = commonUtil.getStringFromArray(second).trim();
        String thi = commonUtil.getStringFromArray(third).trim();
        String forths = commonUtil.getStringFromArray(fourth).trim();
        String fif = commonUtil.getStringFromArray(fifth).trim();
        String sixes = commonUtil.getStringFromArray(sixth).trim();
        String seven = commonUtil.getStringFromArray(seventh).trim();
        
        LotteryResult lottery = new LotteryResult();
        lottery.setOpenDate(openDate);
        lottery.setSpecial(special);
        lottery.setFirst(first);
        lottery.setSecond(ses);
        lottery.setThird(thi);
        lottery.setFourth(forths);
        lottery.setFifth(fif);
        lottery.setSixth(sixes);
        lottery.setSeventh(seven);
        lottery.setCode("XSTD");
        lottery.setSymbol("TD");
        lottery.setPrice(new BigDecimal(5000));
        lottery.setStatus(1);
        lottery.setUserName("kqvn");
        
        updateLottery(lottery);
        // seo
        String slogan="Lịch mở thưởng xổ số";
        String title="Lịch mở thưởng kết quả xổ số";
        String keywords="lich mo thuong xo so, lich mo thuong xo so mien bac, lich mo thuong xo so mien trung, lich mo thuong xo so mien nam";
        String description="Lịch mở thưởng kết quả xổ số miền Bắc, miền Trung, Miền Nam. Thống kê kết quả xổ số ba miền nhanh và chính xác."; 
        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
        
        mod.setViewName("/admin/index");
        
        System.out.println("Ok");
        return mod;
    }

    private void updateLottery(LotteryResult lottery) {
        LotteryResultDAO dao = new LotteryResultDAO();
               
        String temp = lottery.getOpenDate();
        String s[] = temp.split("/");
        String openDate = s[2]+"-"+ s[1] + "-" + s[0];
        List<Lottery> list = dao.getDulicateRecords(openDate);
        
        if(list == null || list.size() == 0) {
            dao.createWithOpenDate77(lottery);
        } else {
            Lottery item = list.get(0);
            item.setSpecial(lottery.getSpecial());
            item.setFirst(lottery.getFirst());
            item.setSecond(lottery.getSecond());
            item.setThird(lottery.getThird());
            item.setFourth(lottery.getFourth());
            item.setFifth(lottery.getFifth());
            item.setSixth(lottery.getSixth());
            item.setSeventh(lottery.getSeventh());
            dao.updateLottery(item);
            
            if(list.size() > 1) {
                for(int i=1; i< list.size(); i++) {
                    dao.removeRecords(list.get(i).getId());
                }
            } 
        }
    }
    
}
