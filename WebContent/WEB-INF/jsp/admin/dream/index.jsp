<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.model.DreamsDAO"%>
<%@page import="inet.bean.Dream"%>
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
        <title>Quản lý dreams</title>
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
                function deleteNews(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa tin này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
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
                    DreamsDAO dreamDao = new DreamsDAO();
                    
                    if(dreamDao.updateStatus(status, id)){
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
                    DreamsDAO dreamsDAO = new DreamsDAO();
                    if(dreamsDAO.delete(id)){
                        %>
                        <script>alert("Xóa thành công!");</script>
                        <%
                    }else{
                        %>
                        <script>alert("Xóa thất bại!");</script>
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
                    <h1>Quản lý sổ mơ  </h1>
                    <div style="margin-bottom: 5px;">
                        <a class="btnCMS" href="<%= request.getContextPath() %>/admin_dream_create.htm">Thêm mới</a>
                    </div>
                    <div class="form1">
                        <%
                            int rowsPerPage = 10;
                            int currentPage = 1;
                            int rowNumber = 0;
                            int totalPage = 0;
                            DreamsDAO dreamDao = new DreamsDAO();
                            currentPage = RequestUtil.getInt(request, "p", 1);
                            String title = RequestUtil.getString(request, "title", "");
                            rowNumber = dreamDao.count(currentPage, rowsPerPage, title);
                            totalPage = (int) Math.ceil((double) rowNumber / rowsPerPage);
                            List<Dream> listDreams = dreamDao.find(currentPage, rowsPerPage, title);
                        %>
                        <%
                            Dream dream = null;
                            if(listDreams != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Tiêu đề</td>
                                <td>Link</td>
                                <td>Mô tả</td>
                                <td>Trạng thái</td>
                                                               
                                <td>Ngày tạo</td>
                                <td>Hành động</td>
                            </tr>
                            <%
                                for(int j=0; j< listDreams.size(); j++){
                                dream = listDreams.get(j);
                            %>
                            <tr>
                                <td><%= (currentPage - 1) * rowsPerPage + j + 1%></td>
                                <td><%= dream.getTitle() %></td>
                                <td><a href="<%= request.getContextPath() %>/so-mo-lo-de/<%=dream.getUrl() %>-<%=dream.getId() %>.html"><%= request.getContextPath() %>/so-mo-lo-de/<%=dream.getUrl() %>-<%=dream.getId() %>.html</a></td>
                                <td><%= dream.getDescription()%></td>
                                <td><%= dream.getStatus() == 1 ? "Hiển thị" : "Ẩn"%></td>
                                <td><%= dream.getPublishDate()%></td>
                                <td>
                                    <%
                                        if(dream.getStatus() == 1){
                                        %>
                                        <a onclick="updateStatus(0,<%= dream.getId() %>)">Ẩn</a> 
                                        <%
                                        }else{
                                        %>
                                            <a onclick="updateStatus(1,<%= dream.getId() %>)">Hiện</a> 
                                        <%
                                        }
                                    %>                                    
                                    | <a href="<%= request.getContextPath() %>/admin_dream_update.htm?id=<%= dream.getId() %>">Sửa</a>
                                    | <a onclick="deleteNews(<%= dream.getId() %>)">Xóa</a>
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