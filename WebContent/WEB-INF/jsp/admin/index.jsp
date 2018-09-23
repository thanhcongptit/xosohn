<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>xosohn.COM CMS</title>
        <%@include file="include/css-lib.jsp" %>
        <%@include file="include/js-lib.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="include/header.jsp" %>
            <div class="content">
                <div class="formWrapper">
                    <h1>WELCOME TO xosohn.COM CMS</h1>
                    
                    
                    <div class="form1"></div>
                </div><!--end form-->                
                <div class="ndung">
                    <strong>
                    			<p>
			                    <ul >1. Các chuyên mục: 
			                    		<li style="padding: 10px;">
					                    <a href="<%= request.getContextPath() %>/admin/news/index.htm">1.1 Quản lý Tin XS</a>
					                </li>
					                
					                <li style="padding: 10px;">
					                    <a href="<%= request.getContextPath() %>/admin/dream/index.htm">1.2 Quản lý sổ mơ</a>
					                </li>
			                    		<li style="padding: 10px;">
			                   			 <a href="<%= request.getContextPath() %>/admin/news_db/index.htm">1.3 Quản lý chuyên mục: "Thống kê 2 số cuối giải đặc biệt"</a>
			                    		</li>	
			                    		
			                    		<li style="padding: 10px;">
			                   			 <a href="<%= request.getContextPath() %>/admin/news_888/index.htm">1.4 Quản lý chuyên mục: "Soi cau 888"</a>
			                    		</li>
			                    		
			                    		<li style="padding: 10px;">
			                   			 <a href="<%= request.getContextPath() %>/admin/news_99/index.htm">1.5 Quản lý chuyên mục: "Soi cau 99"</a>
			                    		</li>
			                    		
			                    		<li style="padding: 10px;">
			                   			 <a href="<%= request.getContextPath() %>/admin/news_scmb/index.htm">1.6 Quản lý chuyên mục: "soi cầu mb"</a>
			                    		</li>
			                    		
			                    		<li style="padding: 10px;">
			                   			 <a href="<%= request.getContextPath() %>/admin/news_scmb_chinhxac/index.htm">1.7 Quản lý chuyên mục: "soi cầu xsmb chính xác nhất"</a>
			                    		</li>
			                    </ul>
			                </p>
                    </strong>
                   
                </div>
                
                <%-- <h2><a href="${pageContext.servletContext.contextPath}/admin/quan-ly-text.htm">QL text chân trang</a></h2> --%>
                <%-- <h2><a href="${pageContext.servletContext.contextPath}/admin/quan-ly-du-doan-xoso.htm">Quản lý dự đoán xổ số</a></h2> --%>
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="include/footer.jsp" %>
    </body>
</html>