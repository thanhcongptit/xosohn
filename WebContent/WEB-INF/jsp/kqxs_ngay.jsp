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
							<%-- <%@include file="include/ul_menu.jsp"%> --%>
							<%-- <ul class="list">
								<li>
									XSMB
								</li>
								<li>
									XSMB ${dayOfWeek}
								</li>
								<li>
									XSMB Ngày ${ddmmyyyy}
								</li>
							</ul> --%>
							<div class="page table-item txt-heading mb20 icon-list">
								<h2>
								<span class="iconxs">Xổ số miền bắc</span> <span class="span">
								<span><a href="${pageContext.servletContext.contextPath}/${linkMBThu}">XSMB ${dayOfWeek} </a></span>
								<a href="${pageContext.servletContext.contextPath}/${linkMBDay}">XSMB ngày ${ddmmyyyy}</a></span>
							</h2>
								<%@include file="include/bangkq_mb.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb.jsp" %>
							</div>
							<%-- <div class="page txt-heading mb20">
								<%@include file="include/lotogan_mb.jsp" %>
							</div>
							<div class="page txt-heading mb20">
								<%@include file="include/10cap_loto_gan.jsp" %>
							</div>
							<div class="page txt-heading mb20">
								<%@include file="include/30cap.jsp" %>
							</div> --%>
							<%@include file="include/menu_center.jsp" %>
							<%@include file="include/banner2_ngang.jsp" %>
							
							<%@include file="include/bangkq_mt.jsp" %>
							<%@include file="include/dauduoi_mt.jsp" %>
							<%@include file="include/menu_center_mt.jsp" %>
							
							<%@include file="include/banner3_ngang.jsp" %>
							
							<%@include file="include/bangkq_mn.jsp" %>
							
							<%@include file="include/dauduoi_mn.jsp" %>
							
							<%@include file="include/menu_center_mn.jsp" %>
							
							<%@include file="include/banner4_ngang.jsp" %>
							
							
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