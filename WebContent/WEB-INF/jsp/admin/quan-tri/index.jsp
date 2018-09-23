<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.model.AdminDAO"%>
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
            <script>
                function updateStatus(status,id){                    
                    $("#updateForm input[name=action]").val("updateStatus");
                    $("#updateForm input[name=id]").val(id);
                    $("#updateForm input[name=status]").val(status);
                    $("#updateForm").submit();
                }
                function deleteMember(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa quản trị này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
                function resetPass(id){
                    var c = confirm('Mật khẩu của quản trị viên này sẽ được đưa về 123456 ?');
                    if(c){
                        $("#updateForm input[name=action]").val("resetPass");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
            </script>
            <%
                String action = RequestUtil.getString(request, "action", "");
                if("updateStatus".equals(action)){
                    BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                    int status = RequestUtil.getInt(request, "status", 0);
                    AdminDAO adminDAO = new AdminDAO();
                    if(adminDAO.updateStatus(id, status)){
                        %>
                        <script>alert("Cập nhật thành công!");</script>
                        <%
                    }else{
                        %>
                        <script>alert("Cập nhật thất bại!");</script>
                        <%
                    }
                }else if("delete".equals(action)){
                    BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                    AdminDAO adminDAO = new AdminDAO();
                    if(adminDAO.delete(id)){
                        %>
                        <script>alert("Xóa quản trị thành công!");</script>
                        <%
                    }else{
                        %>
                        <script>alert("Xóa quản trị thất bại!");</script>
                        <%
                    }
                }else if("resetPass".equals(action)){
                    BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                    AdminDAO adminDAO = new AdminDAO();
                    if(adminDAO.updatePassword(id, "123456")){
                        %>
                        <script>alert("Reset pass thành công!");</script>
                        <%
                    }else{
                        %>
                        <script>alert("Reset pass thất bại!");</script>
                        <%
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
                    <h1>Quản lý admin</h1>
                    <div style="margin-bottom: 5px;">
                        <a class="btnCMS" href="<%= request.getContextPath() %>/admin/quan-tri/create.htm">Thêm mới</a>
                    </div>
                    <div class="form1">
                        <%
                            int rowsPerPage = 10;
                            int currentPage = 1;
                            int rowNumber = 0;
                            int totalPage = 0;
                            AdminDAO adminDAO = new AdminDAO();
                            currentPage = RequestUtil.getInt(request, "p", 1);
                            rowNumber = adminDAO.count("", null);
                            totalPage = (int) Math.ceil((double) rowNumber / rowsPerPage);
                            List<Admin> listAdmin = adminDAO.find("", null, currentPage, rowsPerPage);
                        %>
                        <%
                            Admin admin = null;
                            if(listAdmin != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Username</td>
                                <td>Tên</td>
                                <td>Trạng thái</td>
                                <td>Quyền</td>
                                <td>Hành động</td>
                            </tr>
                            <%
                                for(int j=0; j<listAdmin.size(); j++){
                                admin = listAdmin.get(j);
                            %>
                            <tr>
                                <td><%= (currentPage - 1) * rowsPerPage + j + 1%></td>
                                <td><%= admin.getUsername() %></td>
                                <td><%= admin.getName() %></td>
                                <td><%= admin.getStatus() == Admin.STATUS_ACTIVE ? "Hoạt động" : "Khóa" %></td>
                                <td><%= admin.getRight() == Admin.RIGHT_ADMIN ? "Quản trị viên" : "Biên tập viên" %></td>
                                <td>
                                    <%
                                        if(adminOnl.getId().compareTo(admin.getId()) != 0){
                                            if(admin.getStatus() == Admin.STATUS_ACTIVE){
                                                %>
                                                    <a onclick="updateStatus(0,<%= admin.getId() %>)">Khóa</a> 
                                                <%
                                            }else{
                                                %>
                                                    <a onclick="updateStatus(1,<%= admin.getId() %>)">Mở khóa</a> 
                                                <%
                                            }
                                        %>                                                                                        
                                            | <a href="<%= request.getContextPath() %>/admin/quan-tri/update.htm?id=<%= admin.getId() %>">Sửa</a>
                                            | <a onclick="resetPass(<%= admin.getId() %>)">Reset Pass</a>
                                            | <a onclick="deleteMember(<%= admin.getId() %>)">Xóa</a>
                                        <%
                                        }
                                    %>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                        <%
                            }
                        %>
                        <div class="navigation">
                            <%
                                if (totalPage > 0) {
                            %>
                            <form method="get" id="frmNavigation">
                                Chuyển trang: <select name="p" use="inputUtil" idFormSubmit="frmNavigation">
                                    <%
                                        for (int i = 0; i < totalPage; i++) {
                                    %>
                                    <option value="<%= i + 1%>" <%= currentPage == (i + 1) ? "selected" : ""%>><%= i + 1%></option>
                                    <%
                                        }
                                    %>                                        
                                </select>
                            </form>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>