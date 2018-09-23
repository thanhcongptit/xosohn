<%@page import="inet.bean.TanSuatXuatHien"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<%@include file="include/header.jsp"%>
<%@include file="include/js_include.jsp"%>
<script type="text/javascript">
	function handelCodeChangeSoiCau() {
		var value = $('#province_code_soicau').val();
		var fields = value.split('_');
		var url = "${pageContext.servletContext.contextPath}/"
				+ "thong-ke-tan-suat-lo-to-" + fields[0].toLowerCase() + "-"
				+ fields[1].toLowerCase() + ".html";
		window.location.href = url;
	}
</script>

<body>
	<!-- HTML -->
	<div class="wrapper">
		<!-- header -->
		<%@include file="include/menu_header.jsp"%>
		<!-- end header -->
		<!-- main -->
		<main>
		<div class="container">
			<%@include file="include/text_link.jsp"%>
			<div class="list-banner">
				<p>
					<span class="btn off-seo pull-right" id="btn_seo" onclick="disableAdv();"></span>
				</p>
				<%@include file="include/banner1_ngang.jsp"%>
			</div>
			<div class="row row-s">
				<div class="col-xs-12 col-sm-6 col-s">

					<div class="content">
						<div class="page txt-heading mb20 parent-list">
							<h1>THỐNG KÊ TẦN XUẤT LOTO XỔ SỐ
								${fn:toUpperCase(lotteryCompany.company)}</h1>

							<%@include file="include/option_thongke.jsp"%>

						</div>
						<%
							List<TanSuatXuatHien> dbNhieuNhat = (List<TanSuatXuatHien>) request.getAttribute("dbNhieuNhat");
							List<TanSuatXuatHien> lotoNhieuNhat = (List<TanSuatXuatHien>) request.getAttribute("lotoNhieuNhat");
							
							List<TanSuatXuatHien> dbItNhat = (List<TanSuatXuatHien>) request.getAttribute("dbItNhat");
							List<TanSuatXuatHien> lotoItNhat = (List<TanSuatXuatHien>) request.getAttribute("lotoItNhat");
						%>

						<div class="page txt-heading mb20 parent-list">
							<h2>TK lần xuất hiện xổ số ${fn:toUpperCase(lotteryCompany.company)}</h2>
							<br>
							<p>
								<strong>Các con lô về nhiều nhất</strong>
							<p>
							<div class="mb20">
								<table>
									<tr>
										<th>Giải ĐB</th>
										<th>Xuất hiện</th>
										<th>Lô tô</th>
										<th>Xuất hiện</th>
									</tr>
									<%for(int i=0;i< dbNhieuNhat.size();i++) { %>
									<tr>
										
											<td><span class="txt_red"><%=dbNhieuNhat.get(i).getLoto() %></span></td>
											<td><%=dbNhieuNhat.get(i).getCount() %> lượt</td>
											<td><span class="txt_red"><%=lotoNhieuNhat.get(i).getLoto() %></span></td>
											<td><%=lotoNhieuNhat.get(i).getCount() %> lượt</td>
										
									</tr>
									<%} %>
								</table>
							</div>
							<p>
								<strong>Các con lô về ít nhất</strong>
							<p>
							<div class="mb20">
								<table>
									<tr>
										<th>Giải ĐB</th>
										<th>Xuất hiện</th>
										<th>Lô tô</th>
										<th>Xuất hiện</th>
									</tr>
									<%for(int i=0;i< dbItNhat.size();i++) { %>
									<tr>
										
											<td><span class="txt_red"><%=dbItNhat.get(i).getLoto() %></span></td>
											<td><%=dbItNhat.get(i).getCount() %> lượt</td>
											<td><span class="txt_red"><%=lotoItNhat.get(i).getLoto() %></span></td>
											<td><%=lotoItNhat.get(i).getCount() %> lượt</td>
										

									</tr>
									<%} %>
								</table>
							</div>
							
							<c:if test="${biendo > 10}">
								<p>
									<strong>Các con lô chưa xuất hiện</strong>
								<p>
								<div class="mb20">
									<table>
										<tr>
											<th style="width: 53%;">Giải ĐB</th>
											<th style="width: 47%;">Lô tô</th>
										</tr>
										<tr>
											<td >${dbChuaVe}</td>
											<td >${lotoChuaVe}</td>
										</tr>
	
									</table>
								</div>
							
							</c:if>
						</div>
					</div>

				</div>
				<div class="col-xs-12 col-sm-6 col-s">
					<div class="row row-s">
						<div class="col-xs-12 col-sm-5 col-s">
							<%@include file="include/menu_xoso_left.jsp"%>
							<%@include file="include/menu_soicau_left.jsp"%>
							<%@include file="include/banner1_doc.jsp"%>

							<%@include file="include/menu_tkcau_left.jsp"%>
						</div>
						<div class="col-xs-12 col-sm-7 col-s">
							<%@include file="include/banner2_doc.jsp"%>

							<%@include file="include/calendar.jsp"%>

							<%@include file="include/tintuc.jsp"%>

							<%@include file="include/banner3_doc.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
		</main>
		<!-- end main -->

		<!-- footer -->
		<%@include file="include/footer.jsp"%>
		<!-- end footer -->
	</div>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/jquery.pBar.js"></script>
	<!-- end HTML -->
</body>

</html>