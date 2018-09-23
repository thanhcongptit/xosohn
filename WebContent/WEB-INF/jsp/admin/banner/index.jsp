<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="inet.bean.Banner"%>
<%@page import="inet.model.BannerDAO"%>
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
        <title>Quản lý Banner</title>
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
                function deleteTKV(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa dự đoán này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
                function show(trang){                    
                    $("#updateForm input[name=show]").val(trang);
                    $("#updateForm").submit();
                }
            </script>
            <%
                String action = RequestUtil.getString(request, "action", "");
                if("updateStatus".equals(action)){
                    BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                    int status = RequestUtil.getInt(request, "status", 0);
                    BannerDAO bannerDao = new BannerDAO();
                    if(bannerDao.updateStatus(status, id)){
                        %>
                        <script>alert("Cập nhật thành công!");</script>
                        <%
                    }else{
                        %>
                        <script>alert("Cập nhật thất bại!");</script>
                        <%
                    }
                }             
            %>
            <%
                BannerDAO bannerDAO = new BannerDAO();
                String trang = RequestUtil.getString(request, "show", "index");
                List<Banner> listBanner = bannerDAO.findAll(trang);
            %>
            <form style="display: none" id="updateForm" method="post">
            		<input type="hidden" name="show"/>
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Quản lý Banner</h1>
                    <div style="margin-bottom: 5px;">
                        <select name="trang" onchange="show($(this).val());">
                            <option value="index" <%= "index".equals(trang) ? "selected" : "" %> >Trang chủ</option>
                            <option value="other" <%= "other".equals(trang) ? "selected" : "" %>>Các trang còn lại</option>
                        </select>
                        <div class="clearfix" style="height: 20px;"></div>
                    </div>
                    <div class="form1">
                        <%
                            Banner banner = null;
                            if(listBanner != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Code</td>
                                <td>Vị trí</td>
                                <td>Mô tả</td>
                                <td>Ngày tạo</td>
                                <td>Cập nhật lần cuối</td>
                                <td>Hành động</td>
                            </tr>
                            <%
                                for(int j=0; j<listBanner.size(); j++){
                                banner = listBanner.get(j);
                            %>
                            <tr>
                                <td><%= j + 1 %></td>
                                <td style="max-width: 600px;"><c:out value="<%= banner.getCode() %>"/></td>
                                <td><%= banner.getPositionName() %></td>
                                <td><%= banner.getDesc() != null ? banner.getDesc() : "" %></td>
                                <td><%= banner.getGenDate() %></td>
                                <td><%= banner.getLastUpdated() != null ? banner.getLastUpdated() : ""%></td>
                                <td>
                                		<%
                                        if(banner.getStatus() == 1){
                                        %>
                                        <a onclick="updateStatus(0,<%= banner.getId() %>)">Ẩn</a> 
                                        <%
                                        }else{
                                        %>
                                            <a onclick="updateStatus(1,<%= banner.getId() %>)">Hiện</a> 
                                        <%
                                        }
                                    %>    
                                     | <a href="<%= request.getContextPath() %>/admin/banner/update.htm?id=<%= banner.getId() %>">Sửa</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                        <%
                            }
                        %>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>