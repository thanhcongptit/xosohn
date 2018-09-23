/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author Designer Nguyễn
 */
public class RequestUtil {

    public static int getInt(HttpServletRequest request, String paramName, Integer defaultValue) {
        try {
            Integer rs = Integer.parseInt(Jsoup.clean(request.getParameter(paramName), Whitelist.basic()));
            return rs;
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public static BigDecimal getBigDecimal(HttpServletRequest request, String paramName, BigDecimal defaultValue) {
        try {
            BigDecimal rs = new BigDecimal(Jsoup.clean(request.getParameter(paramName), Whitelist.basic()));
            return rs;
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public static String getString(HttpServletRequest request, String paramName, String defaultValue) {
        try {
            if (request.getParameter(paramName) != null && !"".equals(request.getParameter(paramName))) {
                return Jsoup.clean(request.getParameter(paramName), Whitelist.basic());
            }
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public static String getRealContextPath(HttpServletRequest request) {
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = "";
        if ("localhost".equals(serverName)) {
            contextPath = request.getScheme() + "://" + serverName + ":" + serverPort + request.getContextPath();
        } else {
            contextPath = request.getScheme() + "://" + serverName;
        }
        return contextPath;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /* For MultipartRequest */
    public static int getIntReq(MultipartRequest request, String paramName, Integer defaultValue) {
        try {
            Integer rs = Integer.parseInt(Jsoup.clean(request.getStringParameter(paramName), Whitelist.basic()));
            return rs;
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public static BigDecimal getBigDecimalReq(MultipartRequest request, String paramName, BigDecimal defaultValue) {
        try {
            BigDecimal rs = new BigDecimal(Jsoup.clean(request.getStringParameter(paramName), Whitelist.basic()));
            return rs;
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public static BigDecimal getBigDecimalReq(MultipartRequest request, String paramName) {
        try {
            BigDecimal rs = new BigDecimal(Jsoup.clean(request.getStringParameter(paramName), Whitelist.basic()));
            return rs;
        } catch (Exception ex) {
        }
        return null;
    }

    public static String getStringReq(MultipartRequest request, String paramName, String defaultValue) {
        try {
            String result = request.getStringParameter(paramName);
            if (result != null && !"".equals(result)) {
                return result;
            }
        } catch (Exception ex) {
        }
        return defaultValue;
    }

//    public static MultipartRequest.File getFile(MultipartRequest request, String paraName, MultipartRequest.File defaultValue) {
//        try {
//            MultipartRequest.File file = request.getFileParameter(paraName);
//            if (file != null) {
//                return file;
//            }
//        } catch (Exception ex) {
//        }
//        return defaultValue;
//    }
//
//    public static InputStream getInputStream(MultipartRequest request, String paraName, InputStream defaultValue) {
//        try {
//            MultipartRequest.File file = request.getFileParameter(paraName);
//            if (file != null) {
//                return file.getInputStream();
//            }
//        } catch (Exception ex) {
//        }
//        return defaultValue;
//    }
//
//    public static byte[] getBytes(MultipartRequest request, String paraName, byte[] defaultValue) {
//        try {
//            MultipartRequest.File file = request.getFileParameter(paraName);
//            if (file != null) {
//                return getBytes(file.getInputStream());
//            }
//        } catch (Exception ex) {
//        }
//        return defaultValue;
//    }

    public static byte[] getBytes(InputStream is) {
        byte[] buf = null;
        try {
            int size = is.available();
            buf = new byte[size];
            is.read(buf, 0, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }

    public static String getDomainName(HttpServletRequest request) throws MalformedURLException {
        String result = null;
        String referrer = request.getHeader("referer");
        if (referrer != null && !"".equals(referrer)) {
            if (!referrer.startsWith("http") && !referrer.startsWith("https")) {
                referrer = "http://" + referrer;
            }
            URL netUrl = new URL(referrer);
            result = netUrl.getHost();
            if (result.startsWith("www")) {
                result = result.substring("www".length() + 1);
            }
        }

        return result;
    }

    public static String excutePost(String targetURL, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection         
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            java.io.DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();

            //Get Response 
            InputStream is = connection.getInputStream();
            StringBuilder response;
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            return response.toString();

        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
