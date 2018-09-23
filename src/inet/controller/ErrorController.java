/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import inet.constant.Constants;

/**
 *
 * @author hanhlm
 */
public class ErrorController extends BaseController {

	public ErrorController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools | Templates.
	}

	@Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);
        
        mod.addObject("categoryNews", "");
        mod.setViewName("/404");
        String url_ca = "/404.html";
		mod.addObject("url_ca", Constants.URL_CAN + url_ca);

        return mod;
    }

}
