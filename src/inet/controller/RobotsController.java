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
 * @author conglt
 */
public class RobotsController extends BaseController {
    
    public RobotsController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        mod.setViewName("/sitemap/robots");
        
        String url_can = "/robots.txt";
        mod.addObject("url_ca", Constants.URL_CAN + url_can);
        
        return mod;
    }
}
