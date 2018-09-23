/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.trimfilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hanhlm
 */
public class ReplaceHTMLFilter implements Filter {
    
   
    
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        HtmlResponseWrapper capturingResponseWrapper = new HtmlResponseWrapper(
                (HttpServletResponse) response);

        filterChain.doFilter(request, capturingResponseWrapper);

        if (response.getContentType() != null
                && response.getContentType().contains("text/html")) {
            HttpServletRequest req = (HttpServletRequest) request;
            if(req.getRequestURI().contains(".html")){
                response.setCharacterEncoding("UTF-8");
            }
            String content = capturingResponseWrapper.getCaptureAsString();

            // replace stuff here
            String replacedContent = content;
//            System.out.println(replacedContent);
            
            BufferedReader st = new BufferedReader( new StringReader( content ) );
            StringBuffer buf = new StringBuffer();
            try{
                String str = st.readLine();                
                while( str != null ){
                    str=str.trim();
                   // System.out.println("str======"+str.length());
                    if(!"".equals(str)&&str.length()>0){buf.append( str+"\n");}
                    
                    str = st.readLine();
                }
                
                replacedContent=buf.toString();
            }catch( IOException e )
            {
              e.printStackTrace();
            }
            response.getWriter().write(replacedContent);
            
            
            
        }

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

}
