/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author iNET
 */
public class HttpURLRequest {
    private static final String USER_AGENT = "Mozilla/5.0";
    // HTTP GET request
    public static String sendGet(String url) throws Exception {

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();

            //print result
            //System.out.println(response.toString());
            
            return response.toString();

    }

    // HTTP POST request
    public static String sendPost(String url) throws Exception {

            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add request header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());            
            return response.toString();
    }
    
    public static void main(String[] arg){
        try{
            //String str=HttpURLRequest.sendGet("http://localhost:8084/api.xoso/getnews?SITE_ID=1&CMD=NEWS_LIST&START=1&END=8");
            String str1=HttpURLRequest.sendGet("http://ole.vn/widgets/preview?all=0&width=1&limit=8&color=4&font=true");
            System.out.println(str1);
            //str="["+str+"]";
            //String[]arrStr=str.replace("}","").replace("{", "").replace("\"", "").replace("mobile", "").split(",");
            //System.out.println("arr "+arrStr[3]);
            //Jsonparser.parser(str);
            //System.out.println("str---"+str);
        }catch(Exception e){System.out.println("errrrrrro "+e.toString());}
        
    }
}
