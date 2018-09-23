<%@page import="inet.model.BannerDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page table-item txt-heading mb20 icon-list">
	<h2>
		<span class="iconxs"><a href="/xsmb-xo-so-mien-bac.html">Xổ số miền bắc</a></span> <span class="span"><span>
		<a href="/${linkMBThu}">XSMB ${dayOfWeek}</a></span>
		<a href="/${linkMBDay}">XSMB ngày ${ddmmyyyy}</a></span>
	</h2>
	<%@include file="bangkq_mb.jsp"%>
</div>

<div class="table-oder mb20">
	<%@include file="../include/dauduoi_mb.jsp"%>
</div>
