package xs.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import inet.controller.BaseController;

public class QuanlyTextController extends BaseController {
	public QuanlyTextController() {
    }

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);

		// seo
		String slogan = "Lịch mở thưởng xổ số";
		String title = "Lịch mở thưởng kết quả xổ số";
		String keywords = "lich mo thuong xo so, lich mo thuong xo so mien bac, lich mo thuong xo so mien trung, lich mo thuong xo so mien nam";
		String description = "Lịch mở thưởng kết quả xổ số miền Bắc, miền Trung, Miền Nam. Thống kê kết quả xổ số ba miền nhanh và chính xác.";

		mod.addObject("slogan", slogan);
		mod.addObject("title", title);
		mod.addObject("keywords", keywords);
		mod.addObject("description", description);

		mod.setViewName("/admin/text/index");

		return mod;
	}
}
