
package inet.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.CategorySitemap;
import inet.constant.Constants;
import inet.model.CategorySiteMapDAO;
import inet.util.DatePro;
import inet.util.DateUtil;

public class CategoriesSiteMapController extends BaseController {

	public CategoriesSiteMapController() {
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);

		CategorySiteMapDAO categorySiteMapDAO = new CategorySiteMapDAO();
		
		CategorySitemap dudoanTime = categorySiteMapDAO.getLastestNews();

		String dayMB = categorySiteMapDAO.lastestRegion("MB");
		mod.addObject("dayMB", dayMB);
		String temp[] = dayMB.split("T")[0].split("-");
		String tempMB = temp[2] + "/" + temp[1] + "/" + temp[0];
		String t2MB = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMB, "thu2")) +"T18:45:00+07:00";
		mod.addObject("t2MB", t2MB);
		String t3MB = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMB, "thu3"))+"T18:45:00+07:00";
		mod.addObject("t3MB", t3MB);
		String t4MB = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMB, "thu4"))+"T18:45:00+07:00";
		mod.addObject("t4MB", t4MB);
		String t5MB = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMB, "thu5"))+"T18:45:00+07:00";
		mod.addObject("t5MB", t5MB);
		String t6MB = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMB, "thu6"))+"T18:45:00+07:00";
		mod.addObject("t6MB", t6MB);
		String t7MB = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMB, "thu7"))+"T18:45:00+07:00";
		mod.addObject("t7MB", t7MB);
		String cnMB = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMB, "chu nhat"))+"T18:45:00+07:00";
		mod.addObject("cnMB", cnMB);

		String dayMT = categorySiteMapDAO.lastestRegion("MT");
		mod.addObject("dayMT", dayMT);
		temp = dayMT.split("T")[0].split("-");
		String tempMT = temp[2] + "/" + temp[1] + "/" + temp[0];
		String t2MT = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMT, "thu2"))+"T17:45:00+07:00";
		mod.addObject("t2MT", t2MT);
		String t3MT = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMT, "thu3"))+"T17:45:00+07:00";
		mod.addObject("t3MT", t3MT);
		String t4MT = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMT, "thu4"))+"T17:45:00+07:00";
		mod.addObject("t4MT", t4MT);
		String t5MT = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMT, "thu5"))+"T17:45:00+07:00";
		mod.addObject("t5MT", t5MT);
		String t6MT = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMT, "thu6"))+"T17:45:00+07:00";
		mod.addObject("t6MT", t6MT);
		String t7MT = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMT, "thu7"))+"T17:45:00+07:00";
		mod.addObject("t7MT", t7MT);
		String cnMT = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMT, "chu nhat"))+"T17:45:00+07:00";
		mod.addObject("cnMT", cnMT);

		String dayMN = categorySiteMapDAO.lastestRegion("MN");
		mod.addObject("dayMN", dayMN);
		temp = dayMN.split("T")[0].split("-");
		String tempMN = temp[2] + "/" + temp[1] + "/" + temp[0];
		String t2MN = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMN, "thu2"))+"T16:45:00+07:00";
		mod.addObject("t2MN", t2MN);
		String t3MN = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMN, "thu3"))+"T16:45:00+07:00";
		mod.addObject("t3MN", t3MN);
		String t4MN = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMN, "thu4"))+"T16:45:00+07:00";
		mod.addObject("t4MN", t4MN);
		String t5MN = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMN, "thu5"))+"T16:45:00+07:00";
		mod.addObject("t5MN", t5MN);
		String t6MN = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMN, "thu6"))+"T16:45:00+07:00";
		mod.addObject("t6MN", t6MN);
		String t7MN = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMN, "thu7"))+"T16:45:00+07:00";
		mod.addObject("t7MN", t7MN);
		String cnMN = CommonUtil.convertDatetoSitemap2(DatePro.getDateDDMMYYYY2(tempMN, "chu nhat"))+"T16:45:00+07:00";
		mod.addObject("cnMN", cnMN);

		CategorySitemap categorySitemap = categorySiteMapDAO.getLastestResultOfEachProvince();
		String currentTime = DateUtil.date2String(new Date(), "yyyy-MM-dd'T'hh:mm:ss"+"+07:00");
		mod.addObject("currentTime", currentTime);
		mod.addObject("categorySitemap", categorySitemap);
		mod.addObject("dudoanTime", dudoanTime);
		
		//thong ke, du doan TODO 

		mod.setViewName("/sitemap/categories-sitemap");

		String url_ca = "/categories-sitemap.xml";
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);

		return mod;
	}
}
