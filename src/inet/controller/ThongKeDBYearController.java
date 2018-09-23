/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.thongke.ThongKeDacBiet;

/**
 *
 * @author hanhlm
 */
public class ThongKeDBYearController extends BaseController {
	
	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.

	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);
		String year = request.getParameter("year");
		
		ThongKeDacBiet thongKeDacBiet = new ThongKeDacBiet();
        String[][] kqYear = thongKeDacBiet.findBangDacBietThang("01/01/"+year, "31/12/"+year);
		
        mod.addObject("activeMenu", '7');
		mod.addObject("lotteryYear", kqYear);
		mod.setViewName("/include/thongke_db_nam");

		return mod;
	}

}
