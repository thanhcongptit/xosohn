/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import inet.bean.AdvertisingSite;
import inet.model.AdvertisingSiteDao;

import inet.bean.Banner;
import inet.model.BannerDAO;

/**
 *
 * @author 24h
 */
public class AdvantiseCache implements ServletContextListener, Runnable {

	// key: code_openDate
	private static List<AdvertisingSite> advertisingSites = new ArrayList<>();
	public static HashMap<String, Banner> hmBanner = new HashMap<String, Banner>();
	private boolean running = true;
	private static AdvertisingSiteDao advertisingSiteDao = new AdvertisingSiteDao();

	public static void clearCache() {
		if (!hmBanner.isEmpty())
			hmBanner.clear();
		if (!advertisingSites.isEmpty()) {
			advertisingSites.clear();
		}
	}
	
	public static Banner findBanner(String page, int position){
        String key = page + "_" + position;
        if(hmBanner.get(key) != null){
            return hmBanner.get(key);
        }else{
            BannerDAO bannerDAO = new BannerDAO();
            Banner banner = bannerDAO.find(page, position);
            if(banner != null){
                hmBanner.put(key, banner);
                return banner;
            }
        }
        return null;
    }

	public static List<AdvertisingSite> getAdv() {
		advertisingSites = advertisingSiteDao.getAvertisingSite();
		return advertisingSites;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Thread BannerCache Starting...");
		new Thread(this).start();
		System.out.println("Thread BannerCache Started");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Stop running Thread BannerCache");
		running = false;
	}

	@Override
	public void run() {
		while (running) {
			clearCache();
			try {
				Thread.sleep(1000 * 60 * 10);
			} catch (InterruptedException ex) {
				Logger.getLogger(AdvantiseCache.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
