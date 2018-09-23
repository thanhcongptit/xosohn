<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.MetaGuide"%>

<%@page import="inet.model.MetaGuideDao"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="inet.util.DatePro"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý title, meta, guide, text</title>
        <%@include file="../include/css-lib.jsp" %>
        <%@include file="../include/js-lib.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../include/header.jsp" %>
            <script>
                function deleteTKV(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa dự đoán này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
            </script>
            <%
                MetaGuideDao metaGuideDao = new MetaGuideDao();
                List<MetaGuide> listMetaGuides = metaGuideDao.findAll();
            %>
            <form style="display: none" id="updateForm" method="post">
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Quản lý title, meta, guide, text</h1>
                    <div class="form1">
                        <%
                            MetaGuide metaGuide = null;
                            if(listMetaGuides != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Title</td>
                                <td>Meta</td>
                                <td>Keyword</td>
                                <td>Hướng dẫn</td>
                                <td>Text</td>
                                <td>Page</td>
                                <td>Cập nhật lần cuối</td>
                                <td>Hành động</td>
                            </tr>
                            <%
                                for(int j=0; j<listMetaGuides.size(); j++){
                                metaGuide = listMetaGuides.get(j);
                            %>
                            <tr>
                                <td><%= j + 1 %></td>
                                <td ><%= metaGuide.getTitle()%></td>
                                <td><%= metaGuide.getMeta()%></td>
                                <td><%= metaGuide.getKeyword()%></td>
                                <td><%= metaGuide.getGuide() %></td>
                                <td><%= metaGuide.getFooter() %></td>
                                <td><%= metaGuide.getPage()%></td>
                                <td><%= metaGuide.getLastUpdated() != null ? metaGuide.getLastUpdated() : ""%></td>
                                <td>
                                    <a href="<%= request.getContextPath() %>/admin/meta/update.htm?id=<%= metaGuide.getPosition() %>">Sửa</a>
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