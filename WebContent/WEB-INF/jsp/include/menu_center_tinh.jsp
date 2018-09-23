<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<div class="page-list mb20">
	<ul>
		<li><a href="${pageContext.servletContext.contextPath}/thong-ke-lo-gan-${fn:toLowerCase(lotteryCompany.companyLink)}-${fn:toLowerCase(lotteryCompany.code)}.html">Lô gan</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/thong-ke-lo-xien-${fn:toLowerCase(lotteryCompany.companyLink)}-${fn:toLowerCase(lotteryCompany.code)}.html">Lô xiên</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/thong-ke-giai-dac-biet.html">Thông kê giải đặc biệt</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/soi-cau-${fn:toLowerCase(lotteryCompany.companyLink)}-${fn:toLowerCase(lotteryCompany.code)}.html">Soi cầu ${fn:toUpperCase(lotteryCompany.code)}</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/du-doan-${fn:toLowerCase(lotteryCompany.companyLink)}.html">Dự đoán ${fn:toUpperCase(lotteryCompany.code)}</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/quay-thu-ket-qua-xo-so-${fn:toLowerCase(lotteryCompany.companyLink)}-${fn:toLowerCase(lotteryCompany.code)}.html">Quay thử ${fn:toUpperCase(lotteryCompany.code)}</a></li>
	</ul>
</div>