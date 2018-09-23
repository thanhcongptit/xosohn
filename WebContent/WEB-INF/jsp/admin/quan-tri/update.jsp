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
                BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                if("update".equals(action) && id != null){
                    String name = RequestUtil.getString(request, "name", "");
                    int right = RequestUtil.getInt(request, "right", 0);
                    Admin admin = new Admin();
                    admin.setName(name);
                    admin.setRight(right);
                    admin.setId(id);

                    AdminDAO adminDAO = new AdminDAO();
                    if(adminDAO.updateQT(admin)){
                        msg = "<span style='color: green'>Cập nhật trị viên thành công!</span>";
                    }else{
                        msg = "Cập nhật quản trị viên thất bại!";
                    }
                }
                
                Admin adminUpdate = null;
                if(id != null){
                    AdminDAO adminDAO = new AdminDAO();
                    adminUpdate = adminDAO.findById(id);
                }
            %>
            <form style="display: none" id="updateForm" method="post">
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Cập nhật admin</h1>
                    <div class="form1">
                        <div style="color: red"><%= msg %></div>
                        <%
                            if(adminUpdate != null){
                        %>
                        <form method="post">
                            <table class="table-auto">
                                <tr>
                                    <td>Username</td>
                                    <td>
                                        <%= adminUpdate.getUsername() %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tên</td>
                                    <td>
                                        <input name="name" value="<%= adminUpdate.getName() %>"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Quyền</td>
                                    <td>
                                        <select name="right">
                                            <option value="0" <%= adminUpdate.getRight() == 0 ? "selected" : "" %>>Biên tập viên</option>
                                            <option value="1" <%= adminUpdate.getRight() == 1 ? "selected" : "" %>>Quản trị viên</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>                                    
                                    <td>
                                        <input type="hidden" name="action" value="update"/>
                                        <button type="submit" class="btnCMS">Cập nhật</button>
                                    </td>
                                    <td><a href="<%= request.getContextPath() %>/admin/quan-tri/index.htm" class="btnCMS">Trở về</a></td>
                                </tr>
                            </table>
                        </form>
                        <%
                            }else out.print("Quản trị viên này không tồn tại!");
                        %>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>