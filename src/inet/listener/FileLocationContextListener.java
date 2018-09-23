package inet.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FileLocationContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String rootPath = System.getProperty("catalina.home") + "/webapps/xosohn/images";
		ServletContext ctx = servletContextEvent.getServletContext();

		String relativePath = ctx.getInitParameter("news.dir");
		String relativePath1 = ctx.getInitParameter("banners.dir");
		String relativePath2 = ctx.getInitParameter("dreams.dir");
		String relativePath3 = ctx.getInitParameter("news_db.dir");
		String relativePath4 = ctx.getInitParameter("news_888.dir");
		String relativePath5 = ctx.getInitParameter("news_99.dir");
		String relativePath6 = ctx.getInitParameter("news_scmb.dir");
		String relativePath7 = ctx.getInitParameter("news_scmb_chinhxac.dir");

		File file = new File(rootPath + File.separator + relativePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		File file1 = new File(rootPath + File.separator + relativePath1);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		
		File file2 = new File(rootPath + File.separator + relativePath2);
		if (!file2.exists()) {
			file2.mkdirs();
		}
		
		File file3 = new File(rootPath + File.separator + relativePath3);
		if (!file3.exists()) {
			file3.mkdirs();
		}
		
		File file4 = new File(rootPath + File.separator + relativePath4);
		if (!file4.exists()) {
			file4.mkdirs();
		}
		
		
		File file5 = new File(rootPath + File.separator + relativePath5);
		if (!file5.exists()) {
			file5.mkdirs();
		}
		
		File file6 = new File(rootPath + File.separator + relativePath6);
		if (!file6.exists()) {
			file6.mkdirs();
		}
		
		File file7 = new File(rootPath + File.separator + relativePath7);
		if (!file7.exists()) {
			file7.mkdirs();
		}
		System.out.println("File Directory created to be used for storing files");
		ctx.setAttribute("FILES_DIR_FILE", file);
		ctx.setAttribute("FILES_DIR", rootPath + File.separator + relativePath);
		
		ctx.setAttribute("FILES_DIR_FILE1", file1);
		ctx.setAttribute("FILES_DIR1", rootPath + File.separator + relativePath1);
		
		ctx.setAttribute("FILES_DIR_FILE2", file2);
		ctx.setAttribute("FILES_DIR2", rootPath + File.separator + relativePath2);
		
		ctx.setAttribute("FILES_DIR_FILE3", file3);
		ctx.setAttribute("FILES_DIR3", rootPath + File.separator + relativePath3);
		
		ctx.setAttribute("FILES_DIR_FILE4", file4);
		ctx.setAttribute("FILES_DIR4", rootPath + File.separator + relativePath4);
		
		ctx.setAttribute("FILES_DIR_FILE5", file5);
		ctx.setAttribute("FILES_DIR5", rootPath + File.separator + relativePath5);
		
		ctx.setAttribute("FILES_DIR_FILE6", file6);
		ctx.setAttribute("FILES_DIR6", rootPath + File.separator + relativePath6);
		
		ctx.setAttribute("FILES_DIR_FILE7", file7);
		ctx.setAttribute("FILES_DIR7", rootPath + File.separator + relativePath7);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// do cleanup if needed
	}

}
