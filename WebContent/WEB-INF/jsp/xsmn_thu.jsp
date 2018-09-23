<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DatePro"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="inet.model.MetaGuideDao"%>
<%
	MetaGuideDao metaGuideEventDaoULMenuMN = new MetaGuideDao();
	Date openDateEventMN = DateUtil.addDay(new Date(), 7);
	String openDateEventStringMN = DateUtil.date2String(openDateEventMN, "dd/MM/yyyy");

	MetaGuide metaGuideEvent2 = metaGuideEventDaoULMenuMN.findByPosition(19);
	String dateOfT2 = DatePro.getDateYYYYMMDDEvent(openDateEventStringMN, "thu2");
	
	MetaGuide metaGuideEvent3 = metaGuideEventDaoULMenuMN.findByPosition(20);
	String dateOfT3 = DatePro.getDateYYYYMMDDEvent(openDateEventStringMN, "thu3");
	
	MetaGuide metaGuideEvent4 = metaGuideEventDaoULMenuMN.findByPosition(21);
	String dateOfT4 = DatePro.getDateYYYYMMDDEvent(openDateEventStringMN, "thu4");
	
	MetaGuide metaGuideEvent5 = metaGuideEventDaoULMenuMN.findByPosition(22);
	String dateOfT5 = DatePro.getDateYYYYMMDDEvent(openDateEventStringMN, "thu5");
	
	MetaGuide metaGuideEvent6 = metaGuideEventDaoULMenuMN.findByPosition(23);
	String dateOfT6 = DatePro.getDateYYYYMMDDEvent(openDateEventStringMN, "thu6");
	
	MetaGuide metaGuideEvent7 = metaGuideEventDaoULMenuMN.findByPosition(24);
	String dateOfT7 = DatePro.getDateYYYYMMDDEvent(openDateEventStringMN, "thu7");
	
	MetaGuide metaGuideEvent8 = metaGuideEventDaoULMenuMN.findByPosition(25);
	String dateOfT8 = DatePro.getDateYYYYMMDDEvent(openDateEventStringMN, "cn");
	
%>
<!DOCTYPE html>
<html>
<%@include file="include/header.jsp"%>
<%@include file="include/js_include.jsp"%>
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
							<%@include file="include/ul_mn_menu.jsp"%>
							<%@include file="include/bangkq_mn.jsp"%>
							<%@include file="include/dauduoi_mn.jsp" %>
							<%@include file="include/menu_center_mn.jsp" %>
							<%@include file="include/banner2_ngang.jsp" %>
							
							<%@include file="include/bangkq_mn1.jsp"%>
							<%@include file="include/dauduoi_mn1.jsp" %>
							<%@include file="include/menu_center_mn.jsp" %>
							<%@include file="include/banner3_ngang.jsp" %>
							
							<%@include file="include/bangkq_mn2.jsp"%>
							<%@include file="include/dauduoi_mn2.jsp" %>
							<%@include file="include/menu_center_mn.jsp" %>
							<%@include file="include/banner4_ngang.jsp" %>
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
	<!-- end HTML -->

	
</body>
</html>