/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import inet.bean.Dream;
import inet.bean.News;
import inet.constant.Constants;
import inet.model.CategorySiteMapDAO;
import inet.model.DreamsDAO;
import inet.model.NewsDAO;
import inet.util.DateUtil;
import inet.util.RequestUtil;

/**
 *
 * @author conglt
 */
public class SiteMapController extends BaseController {
    
    public SiteMapController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        String dayMB = new CategorySiteMapDAO().lastestRegion("MB");
        mod.addObject("dayMB", dayMB);
        mod.setViewName("/sitemap/sitemap");
        
        int year = RequestUtil.getInt(request, "year", 0);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        mod.addObject("year", year);
		mod.addObject("currentYear", currentYear);
		
        String url_ca = "/sitemap.xml";
        mod.addObject("url_ca", Constants.URL_CAN + url_ca);
        
        List<News> news = new NewsDAO().findByPage(1, 1);
        
        if(null != news && news.size() > 0) {
        		Timestamp publish = news.get(0).getPublishDate();
        		String publishDate = DateUtil.timestamp2String(publish, "yyyy-MM-dd'T'hh:mm:ss");
        		mod.addObject("timeNews", publishDate);
        }
        
        List<Dream> dreams = new DreamsDAO().findByPage(1, 1);
        
        if(null != dreams && dreams.size() > 0) {
        		Timestamp publish = dreams.get(0).getPublishDate();
        		String publishDate = DateUtil.timestamp2String(publish, "yyyy-MM-dd'T'hh:mm:ss");
        		mod.addObject("timeDream", publishDate);
        }
        
        return mod;
    }
}
