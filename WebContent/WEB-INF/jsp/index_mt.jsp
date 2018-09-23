<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DatePro"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="inet.model.MetaGuideDao"%>
<%
	MetaGuideDao metaGuideEventMTDaoULMenu = new MetaGuideDao();
	Date openDateEvent = DateUtil.addDay(new Date(), 7);
	String openDateEventString = DateUtil.date2String(openDateEvent, "dd/MM/yyyy");

	MetaGuide metaGuideEventMT2 = metaGuideEventMTDaoULMenu.findByPosition(11);
	String dateOfT2 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu2");
	
	MetaGuide metaGuideEventMT3 = metaGuideEventMTDaoULMenu.findByPosition(12);
	String dateOfT3 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu3");
	
	MetaGuide metaGuideEventMT4 = metaGuideEventMTDaoULMenu.findByPosition(13);
	String dateOfT4 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu4");
	
	MetaGuide metaGuideEventMT5 = metaGuideEventMTDaoULMenu.findByPosition(14);
	String dateOfT5 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu5");
	
	MetaGuide metaGuideEventMT6 = metaGuideEventMTDaoULMenu.findByPosition(15);
	String dateOfT6 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu6");
	
	MetaGuide metaGuideEventMT7 = metaGuideEventMTDaoULMenu.findByPosition(16);
	String dateOfT7 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu7");
	
	MetaGuide metaGuideEventMT8 = metaGuideEventMTDaoULMenu.findByPosition(17);
	String dateOfT8 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "cn");
	
%>
<!DOCTYPE html>
<html>
	<%@include file="include/header.jsp"%> 
	<%@include file="include/js_include.jsp" %>
	<script>
		var currHHMMSS;
	
		$(function() {
	
			checkTimeLive();
			var t = setInterval(function() {
				checkTimeLive();
			}, 5000);
	
		});
	
		function getCurrHHMMSS() {
		    var currentdate = new Date();
		    var currentHH = (currentdate.getHours() < 10 ? '0' + currentdate.getHours() : currentdate.getHours());
		    var currentMM = (currentdate.getMinutes() < 10 ? '0' + currentdate.getMinutes() : currentdate.getMinutes());
		    var currentSS = (currentdate.getSeconds() < 10 ? '0' + currentdate.getSeconds() : currentdate.getSeconds());
		    return parseInt(currentHH + '' + currentMM + '' + currentSS);
		}
		
		function checkTimeLive() {
			currHHMMSS = getCurrHHMMSS();
			if (currHHMMSS >= 171000 && currHHMMSS < 174500) {
				$("#main_content_mt1").hide();
				$("#main_content_mt2").show();
				loadXSLive('MT');
			} else {
				$("#main_content_mt1").show();
				$("#main_content_mt2").hide();
			}
		}
	
		function loadAjax(url) {
			$.ajax({
				type : 'POST',
				url : url,
				dataType : 'text',
				cache : false
			}).done(function(sResponse) {
				$("#tructiep_mt").html(sResponse);
			});
		}
	
		function loadXSLive(region) {
			var temp = '${pageContext.servletContext.contextPath}';
			//conglt
			if (!temp) {
				temp = '/xosohn'
			}
			loadAjax(temp + "/ajax/live_mt.htm?r=" + region);
			
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
					<p><span class="btn off-seo pull-right" id="btn_seo" onclick="disableAdv();"></span></p>
					<%@include file="include/banner1_ngang.jsp"%>
				</div>
				<div class="row row-s">
					<div class="col-xs-12 col-sm-6 col-s">
						<div class="content" id = "main_content_mt1">
							<%@include file="include/ul_mt_menu.jsp"%>
							<%@include file="include/bangkq_mt.jsp"%>
							<%@include file="include/dauduoi_mt.jsp" %>
							<%@include file="include/menu_center_mt.jsp" %>
							<%@include file="include/banner2_ngang.jsp" %>
							
							<%@include file="include/bangkq_mt1.jsp"%>
							<%@include file="include/dauduoi_mt1.jsp" %>
							<%@include file="include/menu_center_mt.jsp" %>
							<%@include file="include/banner3_ngang.jsp" %>
							
							<%@include file="include/bangkq_mt2.jsp"%>
							<%@include file="include/dauduoi_mt2.jsp" %>
							<%@include file="include/menu_center_mt.jsp" %>
							<%@include file="include/banner4_ngang.jsp" %>
							
							
						</div>
						
						<div class="content" id = "main_content_mt2" style="display: none">
							<%@include file="include/ul_mt_menu.jsp"%>
							<div id="tructiep_mt">
							
							</div>
							<%@include file="include/menu_center_mt.jsp" %>
							
							<%@include file="include/bangkq_mt.jsp"%>
							<%@include file="include/dauduoi_mt.jsp" %>
							
							
							<%@include file="include/bangkq_mt1.jsp"%>
							<%@include file="include/dauduoi_mt1.jsp" %>
							<%@include file="include/menu_center_mt.jsp" %>
							
							
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