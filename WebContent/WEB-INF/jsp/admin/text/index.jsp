<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.model.DescriptionDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="inet.bean.Description"%>
<%@page import="inet.model.DescriptionDao"%>
<%@page import="java.math.BigDecimal"%>
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
           
            <%
                DescriptionDao desDao = new DescriptionDao();
                List<Description> listDes = desDao.findAll();
            %>
            <form style="display: none" id="updateForm" method="get">
                <input type="hidden" name="show"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Quản lý Text</h1>
                    <div class="form1">
                        <%
                            Description des = null;
                            if(listDes != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Vị trí</td>
                                <td>Code</td>
                                <td>Ngày tạo</td>
                                <td>Cập nhật lần cuối</td>
                                <td>Hành động</td>
                            </tr>
                            <%
                                for(int j=0; j<listDes.size(); j++){
                                	des = listDes.get(j);
                            %>
                            <tr>
                                <td><%= j + 1 %></td>
                                <td><%= des.getPositionName() %></td>
                                <td style="max-width: 800px;"><c:out value="<%= des.getCode() %>"/></td>
                                
                                <td><%= des.getGenDate() %></td>
                                <td><%= des.getLastUpdated() != null ? des.getLastUpdated() : ""%></td>
                                <td>
                                    <a href="<%= request.getContextPath() %>/admin/text/update.htm?id=<%= des.getId() %>">Sửa</a>
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