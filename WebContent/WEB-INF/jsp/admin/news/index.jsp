<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.bean.News"%>
<%@page import="inet.model.NewsDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>

<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.InputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý tin tức</title>
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
                    NewsDAO newsDAO = new NewsDAO();
                    if(newsDAO.updateStatus(status, id)){
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
                    NewsDAO newsDAO = new NewsDAO();
                    if(newsDAO.delete(id)){
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
                    <h1>Quản lý tin xổ số</h1>
                    <div style="margin-bottom: 5px;">
                        <a class="btnCMS" href="<%= request.getContextPath() %>/admin_news_create.htm">Thêm mới</a>
                    </div>
                    <div class="form1">
                        <%
                            int rowsPerPage = 10;
                            int currentPage = 1;
                            int rowNumber = 0;
                            int totalPage = 0;
                            NewsDAO newsDAO = new NewsDAO();
                            
                            currentPage = RequestUtil.getInt(request, "p", 1);
                            String title = RequestUtil.getString(request, "title", "");
                            rowNumber = newsDAO.count(currentPage, rowsPerPage, title);
                            totalPage = (int) Math.ceil((double) rowNumber / rowsPerPage);
                            List<News> listNews = newsDAO.find(currentPage, rowsPerPage, title);
                        %>
                        <%
                            News news = null;
                            if(listNews != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Tiêu đề</td>
                                <td>Ảnh</td>
                                <td>Mô tả</td>
                                <td>Trạng thái</td>
                                <td>Lượt xem</td>                                
                                <td>Ngày tạo</td>
                                <td>Địa phương</td>
                                <td>Hành động</td>
                            </tr>
                            <%
                                for(int j=0; j<listNews.size(); j++){
                                news = listNews.get(j);
                                String fileName = news.getImageUrl();
                                //fileName = request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName;
                            %>
                            <tr>
                                <td><%= (currentPage - 1) * rowsPerPage + j + 1%></td>
                                <td><%= news.getTitle() %></td>
                                <td><a><img src="${pageContext.servletContext.contextPath}/images/news/<%=fileName%>" width="100"/></a></td>
                                <td><%= news.getDescription()%></td>
                                <td><%= news.getStatus() == 1 ? "Hiển thị" : "Ẩn"%></td>
                                <td><%= news.getViewCounter()%></td>
                                <td><%= news.getPublishDate()%></td>
                                <td><%= news.getProvince()%></td>
                                <td>
                                    <%
                                        if(news.getStatus() == 1){
                                        %>
                                        <a onclick="updateStatus(0,<%= news.getId() %>)">Ẩn</a> 
                                        <%
                                        }else{
                                        %>
                                            <a onclick="updateStatus(1,<%= news.getId() %>)">Hiện</a> 
                                        <%
                                        }
                                    %>                                    
                                    | <a href="<%= request.getContextPath() %>/admin_news_update.htm?id=<%= news.getId() %>">Sửa</a>
                                    | <a onclick="deleteNews(<%= news.getId() %>)">Xóa</a>
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