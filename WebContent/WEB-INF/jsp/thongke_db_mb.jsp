<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<%@include file="include/header.jsp"%> 
	<%@include file="include/js_include.jsp" %>
	
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
					<p><span class="btn off-seo pull-right" id="btn_seo" onclick="disableAdv();"></span></p>
					<%@include file="include/banner1_ngang.jsp"%>
				</div>
				<div class="row row-s">
					<div class="col-xs-12 col-sm-6 col-s">
						
						<div class="content">
							<div class="page table-item txt-heading mb20 icon-list parent-list page-none">
								<h1><span class="iconxs">XỔ SỐ MIỀN BẮC</span> THỐNG KÊ ĐẶC BIỆT THEO THÁNG</h1>
								<form>
									<select class="form-control" onchange="changeThongKeDBMonth();" id = "select_db_month">
										<c:forEach begin="1" end="12" varStatus="loop">
										    <option <c:if test="${month == loop.index}"> selected </c:if> value ="${loop.index}">Tháng ${loop.index}</option>
										</c:forEach>
									</select>
								</form>
								<%@include file="include/thongke_db_thang.jsp"%>
							</div>
							
							<%@include file="include/banner2_ngang.jsp"%>
							<div class="page txt-heading mb20 parent-list">
								<h2>THỐNG KÊ HAI SỐ CUỐI GIẢI ĐẶC BIỆT THEO TUẦN</h2>
								<form>
									<select class="form-control" onchange="changeThongKeDBWeek();" id = "select_db_week">
									    <c:forEach begin="5" end="100" varStatus="loop" step="5">
										    <option <c:if test="${week == loop.index}"> selected </c:if> value ="${loop.index}">${loop.index} tuần</option>
										</c:forEach>
									</select>
								</form>
								<%@include file="include/thongke_db_haisocuoi.jsp"%>
							</div>
							
							<%@include file="include/banner3_ngang.jsp"%>
							<div class="page txt-heading mb20">
								<%@include file="include/lotogan_mb.jsp"%>
							</div>
							
							<%@include file="include/banner4_ngang.jsp"%>
							<div class="page txt-heading mb20 parent-list page-none">
								<h2>THỐNG KÊ GIẢI ĐẶC BIỆT THEO NĂM</h2>
								<form>
									<select class="form-control" onchange="changeThongKeDBYear();" id ="select_db_year">
									    <%
									    		String yearString = (String) request.getAttribute("year");
									    		int year = Integer.parseInt(yearString);
									    		for(int i=year; i>= 2005; i--) {
									    %>
										    <option <%if(i==year){out.print("selected"); } %> value="<%=i%>"><%=i %></option>
										<%} %>
									</select>
								</form>
								<%@include file="include/thongke_db_nam.jsp"%>
							</div>
						</div>
						
					</div>
					<div class="col-xs-12 col-sm-6 col-s">
						<div class="row row-s">
							<div class="col-xs-12 col-sm-5 col-s">
								<%@include file="include/menu_xoso_left.jsp" %>
								<%@include file="include/menu_soicau_left.jsp" %>
								<%@include file="include/banner1_doc.jsp" %>
								
								<%@include file="include/menu_tkcau_left.jsp" %>
							</div>
							<div class="col-xs-12 col-sm-7 col-s">
								<%@include file="include/banner2_doc.jsp" %>
								
								<%@include file="include/calendar.jsp" %>
								
								<%@include file="include/tintuc.jsp" %>
								
								<%@include file="include/banner3_doc.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<!-- end main -->

		<!-- footer -->
		<%@include file="include/footer.jsp" %>
		<!-- end footer -->
	</div>
	<!-- end HTML -->
</body>
</html>