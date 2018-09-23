<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.model.AdminDAO"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.News"%>
<%@page import="inet.model.NewsDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%> 
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý admin</title>
        <%@include file="../include/css-lib.jsp" %>
        <%@include file="../include/js-lib.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../include/header.jsp" %>
            <style>
                .error{color:red}
                .ok{color:green}
            </style>
            <script>
                function updateStatus(status,id){                    
                    $("#updateForm input[name=action]").val("updateStatus");
                    $("#updateForm input[name=id]").val(id);
                    $("#updateForm input[name=status]").val(status);
                    $("#updateForm").submit();
                }
                function deleteTKV(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa dự đoán này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
                $(function() {
                    $( "input[name=genDate]" ).datepicker();
                 });
            </script>
            <%
                String msg = "";
                String action = RequestUtil.getString(request, "action", "");
                if("create".equals(action)){
                    String username = RequestUtil.getString(request, "username", "");
                    String password = RequestUtil.getString(request, "password", "");
                    String rePassword = RequestUtil.getString(request, "rePassword", "");
                    String name = RequestUtil.getString(request, "name", "");
                    int right = RequestUtil.getInt(request, "right", 0);
                    if("".equals(username)){
                        msg = "Vui lòng điền username!";
                    }else if(username.contains(" ")){
                        msg = "Username không được chứa khoảng trắng!";
                    }else if("".equals(password)){
                        msg = "Vui lòng điền password!";
                    }else if(!password.equals(rePassword)){
                        msg = "Xác nhận password không chính xác!";
                    }else{
                        Admin admin = new Admin();
                        admin.setUsername(username);
                        admin.setPassword(password);
                        admin.setName(name);
                        admin.setStatus(Admin.STATUS_ACTIVE);
                        admin.setRight(right);
                        
                        AdminDAO adminDAO = new AdminDAO();
                        if(adminDAO.checkExistUsername(username)){
                            msg = "Username này đã tồn tại!";
                        }else if(adminDAO.create(admin)){
                            msg = "<span style='color: green'>Tạo quản trị viên thành công!</span>";
                        }else{
                            msg = "Tạo quản trị viên thất bại!";
                        }
                    }
                }
            %>
            <form style="display: none" id="updateForm" method="post">
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Thêm mới admin</h1>
                    <div class="form1">
                        <div style="color: red"><%= msg %></div>
                        <form method="post">
                            <table class="table-auto">
                                <tr>
                                    <td>Username</td>
                                    <td>
                                        <input name="username"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Password</td>
                                    <td>
                                        <input type="password" name="password"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Re-Password</td>
                                    <td>
                                        <input type="password" name="rePassword"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tên</td>
                                    <td>
                                        <input name="name"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Quyền</td>
                                    <td>
                                        <select name="right">
                                            <option value="0">Biên tập viên</option>
                                            <option value="1">Quản trị viên</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>                                    
                                    <td>
                                        <input type="hidden" name="action" value="create"/>
                                        <button type="submit" class="btnCMS">Tạo mới</button>
                                    </td>
                                    <td><a href="<%= request.getContextPath() %>/admin/quan-tri/index.htm" class="btnCMS">Trở về</a></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>