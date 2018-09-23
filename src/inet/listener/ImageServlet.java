package inet.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inet.util.ImageBuffer;

public class ImageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE_GIF = "image/gif";

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String sId = (String) req.getParameter("id");
		if (sId == null || "".equals(sId)) {
			res.setContentType("text/plain");
			java.io.PrintWriter out = res.getWriter();
			out.println("?");
			return;
		}
		sId = sId.trim();
		if (sId.contains("gif"))
			sId = sId.replaceAll(".gif", "");
		if (sId.contains(".jpg"))
			sId = sId.replaceAll(".jpg", "");
		javax.servlet.ServletOutputStream out = res.getOutputStream();

		try {
			res.setContentType(CONTENT_TYPE_GIF);
			byte[] contentGif = ImageBuffer.lookup(sId);
			res.setContentLength(contentGif.length);
			out.write(contentGif, 0, contentGif.length);
			out.flush();
			out.close();
		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
		}
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	// Clean up resources
	public void destroy() {
	}
}
