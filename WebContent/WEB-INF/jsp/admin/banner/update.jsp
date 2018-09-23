<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="java.net.URLDecoder"%>
<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.News"%>
<%@page import="inet.model.NewsDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = "";
    String action = RequestUtil.getString(request, "action", "");
    BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);

    if("update".equals(action) && id != null){                    
        BannerDAO bannerDAO = new BannerDAO();                    
        String queryString = request.getQueryString();
        String code = "";
        try{
            code = queryString.split("&")[1].replace("code=", "");
        }catch(Exception ex){}
        if(code != null && !"".equals(code)) code = URLDecoder.decode(code, "UTF-8");
        if(bannerDAO.update(code, id)){
            msg = "<span style='color:green'>Cập nhật banner thành công!</span>";
            out.print("1");
            return;
        }else{
            msg = "Cập nhật banner thất bại!";
            out.print("0");
            return;
        }
    }
%>
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
                function updateAjax(code, id){
                    $.ajax({
                        type: 'GET',
                        url: '${pageContext.servletContext.contextPath}/admin/banner/update.htm',
                        data: {
                            action: 'update',
                            code: code,
                            id: id
                        },
                        success: function(rs) {
                            rs = rs.trim();
                            if(rs === '1') alert("Cập nhật thành công!");
                            else alert("Cập nhật thất bại!");
                        }
                    });
                }
            </script>
            <%
                Banner bannerCurrent = null;
                if(id != null){
                    BannerDAO bannerDAO = new BannerDAO();
                    bannerCurrent = bannerDAO.findById(id);
                }
            %>
            <form style="display: none" id="updateForm" method="post">
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Sửa Banner</h1>
                    <div class="form1">
                        <div style="color:red"><%= msg %></div>
                        <%
                            if(bannerCurrent != null){
                        %>
                        <form method="post">
                            <table class="table-auto">
                                <tr>
                                    <td>Page</td>
                                    <td>
                                        <%= bannerCurrent.getPage().equals("index") ? "Trang chủ" : "Các trang khác" %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Vị trí</td>
                                    <td>
                                        <%= bannerCurrent.getPositionName() %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Mô tả</td>
                                    <td>
                                        <%= bannerCurrent.getDesc() %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Code</td>
                                    <td>
                                        <textarea style="border: solid 1px #0b9444;min-width: 143px;padding: 5px;" name="code" cols="80" rows="10"><%= bannerCurrent.getCode() %></textarea>
                                    </td>
                                </tr>
                                <tr>                                    
                                    <td>
                                        <input type="hidden" name="action" value="update"/>
                                        <button type="button" onclick="updateAjax($('textarea[name=code]').val(),<%= bannerCurrent.getId() %>)" class="btnCMS">Cập nhật</button>
                                    </td>
                                    <td><a href="<%= request.getContextPath() %>/admin/banner/index.htm" class="btnCMS">Trở về</a></td>
                                </tr>
                            </table>
                        </form>
                        <%
                            }else out.print("Banner không tồn tại!");
                        %>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>