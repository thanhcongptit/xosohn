<%-- 
    Document   : header
    Created on : Nov 17, 2014, 2:20:03 PM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.bean.Admin"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page import="inet.constant.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<%
    Admin adminOnl = null;
    try{
        adminOnl = (Admin) session.getAttribute(Constants.ADMIN_LOGIN_SESSION);
    }catch(Exception ex){}
    if(adminOnl == null){
        response.sendRedirect(request.getContextPath()+"/admin/");
        return;
    }
    request.setCharacterEncoding("UTF-8");
    String uri = request.getRequestURI();
    if(uri.endsWith("admin/index.jsp") || uri.endsWith("admin/change-pass.jsp")){
        //cho qua
    }else if(!uri.contains("admin/du") && !uri.contains("admin/news") && adminOnl.getRight() == Admin.RIGHT_POSTER){
        response.sendRedirect(request.getContextPath()+"/admin/index.htm");
        return;
    }
%>
<link rel="shortcut icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAABklBMVEUAAAD////l4dzm493k4Nvm493m493m493m493V2NiLxNrm493a1dLj39rm493b3dq81t7Q19eazN/W0c7Y1NDL297LxMPi4t2Vy9/M297c19Pm493E2d7k493m493J296z1N6Du9K21d7I2t6t0t7m493Tzcvm493NxsVpudlmttdlveBbs9h6u9bg4d1Tstrg4d2XxNam0N4lqeElquErq+AsrOEsrOIvq94vrOAwrOAxrOAxreAyrd8yreAzreA0rd41rt41ruA2ruA4r+A5r+A6rdw9seFAsuFBsuFCsN5CsuBGst9IteNPt+NQuORcvudiut9mwuhowuhzwN96wN2Fzu2LyuSS1O+Wyt+W1e6m2/Gnzt+v3/Ow3/KyytW+2uW/5fXA5fXB2uXG6PbL6vfO6/fPzc3VzszY0c/Z0tDa8Prc1dPf2NXf8vrg2dfh2tji29ji3Nnj9PvtHyTtLTLuLjPvPUHzam30eXz1iIr1+/73lpn3/P74paf5tLb6w8X7/f79/v/+8PD+//////8O3ccoAAAAM3RSTlMAAAEBAgIGDBESFxkhLjAxMjk/R0pMXWZrgICHlpianKKjvMTF0dbd6e/x8vT29/r6+/4dYH0dAAAA10lEQVQY00XIy0rDQBiA0W/+SSY3Go21BPG2EMGi4Cv44j6IRSkFUaO1xqRNJrWTuHDhWR4FkO5HUd825QZQYPLJZTxydfP0UoCHPp1mVwBOJg8LNMd5fg2wac4/6RoJb9UFVHXrZWpsDpGsOIoh7QngxM+NRGigVxrwvC4SYQs0gQNWvYjsqOasjdgOXgexmkwPiR/qyGPx5r6etT0QVbk9wc4K9/NeatyZNcN3vfxorVs+DprWejdpEsbJyMzmWxQQjNM7pYb7crUDBYAfBkO3BkD9zb9ft/1SXawC1lwAAAAASUVORK5CYII=" type="image/x-icon"/>
<div class="header">
    <div class="nav_top">
        <a href="<%= request.getContextPath() %>/admin/" class="logo">xosohn.COM CMS</a>
        <ul>
            <% 
                if(adminOnl.getRight() == Admin.RIGHT_ADMIN){
            %>
                
                 <li>
                    <a href="<%= request.getContextPath() %>/admin/meta/index.htm">Quản lý title,meta</a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/admin/quan-tri/index.htm">Quản lý Admin</a>                
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/admin/banner/index.htm">Quản lý Banner</a>
                </li>
                
                <li>
                    <a href="<%= request.getContextPath() %>/admin/quan-ly-link-quangcao.htm">Quản lý link quảng cáo</a>
                </li>
                
                 <li>
                    <a href="<%= request.getContextPath() %>/admin/kqxs/index.htm">Thêm KQ XSMB</a>
                </li>
                
              <%--   <li>
                    <a href="<%= request.getContextPath() %>/admin/sms.htm">Quản lý SMS</a>
                </li>
                
                <li>
                    <a href="<%= request.getContextPath() %>/admin/cuphap.htm">Quản lý Cú pháp</a>
                </li> --%>
            <%
                }else{
            %>
                <li>
                    <a href="<%= request.getContextPath() %>/admin/news/index.htm">Quản lý Tin XS</a>
                </li>
            <%
                }
            %>
        </ul>
        <div class="user">
            <p>
                <a href="<%= request.getContextPath() %>/admin/change-pass.htm">Xin chào <%= adminOnl.getUsername() %>!</a>
                <a href="<%= request.getContextPath() %>/admin/login/logout.htm">Thoát</a>
            </p>
        </div><!--end user-->
    </div><!--end nav_top--->
    <div style="clear:both;"></div>
</div><!--end header-->
