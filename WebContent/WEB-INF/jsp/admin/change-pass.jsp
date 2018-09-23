<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.model.AdminDAO"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SOICAUPRO.COM CMS</title>
        <%@include file="include/css-lib.jsp" %>
        <%@include file="include/js-lib.jsp" %>
        <style>
            .error{color: red}
            .ok{color:green}
        </style>
    </head>
    <body>
        <%
            Admin adminChangePass = (Admin) session.getAttribute(Constants.ADMIN_LOGIN_SESSION);
            String action = RequestUtil.getString(request, "action", "");
            String msg = "";
            if("update".equals(action)){
                String oldPass = RequestUtil.getString(request, "oldPass", "");
                String newPass = RequestUtil.getString(request, "newPass", "");
                String reNewPass = RequestUtil.getString(request, "reNewPass", "");
                if("".equals(oldPass)) msg = "<span class='error'>Vui lòng nhập mật khẩu cũ!</span>";
                else if("".equals(newPass)) msg = "<span class='error'>Vui lòng nhập mật khẩu mới!</span>";
                else if(!newPass.equals(reNewPass)) msg = "<span class='error'>Xác nhận mật khẩu mới không chính xác!</span>";
                else{
                    AdminDAO adminDAO = new AdminDAO();
                    if(adminDAO.login(adminChangePass.getUsername(), oldPass) == null){
                        msg = "<span class='error'>Mật khẩu cũ không đúng!</span>";
                    }else if(adminDAO.updatePassword(adminChangePass.getId(), newPass)){
                        msg = "<span class='ok'>Thay đổi mật khẩu thành công!</span>";
                    }else{
                        msg = "<span class='error'>Thay đổi mật khẩu thất bại!</span>";
                    }
                }
            }
        %>
        <div class="container">
            <%@include file="include/header.jsp" %>
            <div class="content">
                <div class="formWrapper">
                    <h1>Đổi mật khẩu</h1>
                    <div><%= msg %></div>
                    <div class="form1">
                        <form method="POST">
                            <table class="table-auto">
                                    <tr>
                                        <td>Mật khẩu cũ </td>
                                        <td>
                                            <input type="password" name="oldPass"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Mật khẩu mới </td>
                                        <td>
                                            <input type="password" name="newPass"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Xác nhận mật khẩu mới </td>
                                        <td>
                                            <input type="password" name="reNewPass"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <button class="btnCMS" type="submit">Cập nhật</button>
                                            <input name="action" value="update" type="hidden"/>
                                        </td>
                                        <td>
                                            <a class="btnCMS" href="<%= request.getContextPath() %>/admin/index.htm">Trở về</a>
                                        </td>
                                    </tr>
                            </table>
                        </form>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="include/footer.jsp" %>
    </body>
</html>