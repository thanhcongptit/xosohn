<%-- 
    Document   : logout
    Created on : 25-Jan-2016, 00:49:15
    Author     : 24h
--%>

<%@page import="inet.constant.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.removeAttribute(Constants.ADMIN_LOGIN_SESSION);
    response.sendRedirect(request.getContextPath() + "/admin/");
%>