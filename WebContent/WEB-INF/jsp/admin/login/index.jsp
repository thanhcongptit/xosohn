<%-- 
    Document   : index
    Created on : Nov 20, 2014, 4:15:27 PM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.constant.Constants"%>
<%@page import="inet.bean.Admin"%>
<%@page import="inet.model.AdminDAO"%>
<%@page import="inet.util.RequestUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>xosohn.COM CMS</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/style-login.css" media="screen" type="text/css" />
        <!--[if lt IE 9]>
                <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <%@include file="../include/js-lib.jsp" %>
    </head>
    <body>
        <div id="login-form">
            <h3>Login</h3>
            <fieldset>
                <%

                    String action = request.getParameter("action");
                    String msg = "";
                    if ("login".equals(action)) {
                        String username = RequestUtil.getString(request, "username", "");
                        String password = RequestUtil.getString(request, "password", "");
                        if ("".equals(username)) {
                            msg = "Vui lòng điền username!";
                        } else if ("".equals(password)) {
                            msg = "Vui lòng điền password!";
                        }

                        if ("".equals(msg)) {
                            AdminDAO adminDao = new AdminDAO();
                            Admin admin = adminDao.login(username, password);
                            if (admin != null) {
                                if(admin.getStatus() == Admin.STATUS_BLOCK){
                                    msg = "Tài khoản của bạn đã bị khóa!";
                                }else{
                                    session.setAttribute(Constants.ADMIN_LOGIN_SESSION, admin);
                                    response.sendRedirect(request.getContextPath() + "/admin/");
                                    return;
                                }
                            } else {
                                msg = "Thông tin tài khoản không chính xác!";
                            }
                        }
                    }
                %>
                <div style="color: red; text-align: center"><%= msg%></div>
                <form method="post" use="inputUtil">
                    <input type="text" name="username" required="true" msgRequire="Vui lòng điền Username!" value="" placeholder="Username"/> 
                    <input type="password" name="password" required="true" msgRequire="Vui lòng điền Password!" value="" placeholder="Password"/>
                    <input type="hidden" value="login" name="action">
                    <input type="submit" value="Login">
                    <footer class="clearfix">                    
                    </footer>
                </form>
            </fieldset>
        </div>
    </body>
</html>
