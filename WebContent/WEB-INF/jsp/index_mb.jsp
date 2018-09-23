<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="inet.util.DatePro"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<%@include file="include/header.jsp"%> 
	<%@include file="include/js_include.jsp" %>
	<%
	MetaGuideDao metaGuideMBDaoULMenu = new MetaGuideDao();
	Date openDateEvent = DateUtil.addDay(new Date(), 7);
	String openDateEventString = DateUtil.date2String(openDateEvent, "dd/MM/yyyy");

	MetaGuide metaGuideMB2 = metaGuideMBDaoULMenu.findByPosition(3);
	String dateOfT2 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu2");
	
	MetaGuide metaGuideMB3 = metaGuideMBDaoULMenu.findByPosition(4);
	String dateOfT3 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu3");
	
	MetaGuide metaGuideMB4 = metaGuideMBDaoULMenu.findByPosition(5);
	String dateOfT4 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu4");
	
	MetaGuide metaGuideMB5 = metaGuideMBDaoULMenu.findByPosition(6);
	String dateOfT5 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu5");
	
	MetaGuide metaGuideMB6 = metaGuideMBDaoULMenu.findByPosition(7);
	String dateOfT6 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu6");
	
	MetaGuide metaGuideMB7 = metaGuideMBDaoULMenu.findByPosition(8);
	String dateOfT7 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "thu7");
	
	MetaGuide metaGuideMB8 = metaGuideMBDaoULMenu.findByPosition(9);
	String dateOfT8 = DatePro.getDateYYYYMMDDEvent(openDateEventString, "cn");
	
%>
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
			if (currHHMMSS >= 181000 && currHHMMSS < 184500) {
				$("#main_content_mb1").hide();
				$("#main_content_mb2").show();
				loadXSLive('MB');
			} else {
				$("#main_content_mb2").hide();
				$("#main_content_mb1").show();
			}
		}
	
		function loadAjax(url) {
			$.ajax({
				type : 'POST',
				url : url,
				dataType : 'text',
				cache : false
			}).done(function(sResponse) {
				$("#tructiep_mb").html(sResponse);
			});
		}
	
		function loadXSLive(region) {
			var temp = '${pageContext.servletContext.contextPath}';
			//conglt
			if (!temp) {
				temp = '/xosohn'
			}
			loadAjax(temp + "/ajax/live.htm?r=" + region);
			
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
						<div class="content" id  = "main_content_mb1">
							<%@include file="include/ul_menu.jsp"%>
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu}">XSMB ${dayOfWeek}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay}">XSMB Ngày ${ddmmyyyy}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb.jsp" %>
							</div>
							<div class="page txt-heading mb20">
								<%@include file="include/lotogan_mb.jsp" %>
							</div>
							
							<%@include file="include/thongkedb.jsp" %>
							<div class="page txt-heading mb20">
								<%@include file="include/cham_dau_db.jsp" %>
							</div> 
							<div class="page txt-heading mb20">
								<%@include file="include/30cap.jsp" %>
							</div> 
							
							<%@include file="include/menu_center.jsp" %>
							<%@include file="include/banner2_ngang.jsp" %>
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu1}">XSMB ${dayOfWeek1}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay1}">XSMB Ngày ${ddmmyyyy1}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb1.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb1.jsp" %>
							</div>
							
							<%@include file="include/banner3_ngang.jsp" %>
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu2}">XSMB ${dayOfWeek2}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay2}">XSMB Ngày ${ddmmyyyy2}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb2.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb2.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							<%@include file="include/banner4_ngang.jsp" %>
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu3}">XSMB ${dayOfWeek3}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay3}">XSMB Ngày ${ddmmyyyy3}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb3.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb3.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							<%@include file="include/banner5_ngang.jsp" %>
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu4}">XSMB ${dayOfWeek4}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay4}">XSMB Ngày ${ddmmyyyy4}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb4.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb4.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							<%@include file="include/banner6_ngang.jsp" %>
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu5}">XSMB ${dayOfWeek5}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay5}">XSMB Ngày ${ddmmyyyy5}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb5.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb5.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							<%@include file="include/banner7_ngang.jsp" %>
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu6}">XSMB ${dayOfWeek6}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay6}">XSMB Ngày ${ddmmyyyy6}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb6.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb6.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							<%@include file="include/banner8_ngang.jsp" %>
							
						</div>
						
					
					
					<!-- truc tiep2 -->
					<div class="content" id="main_content_mb2" style="display: none">
							<%@include file="include/ul_menu.jsp"%>
							<div id="tructiep_mb">
							</div>
							
							<div class="page txt-heading mb20">
								<%@include file="include/lotogan_mb.jsp" %>
							</div>
							
							<div class="page txt-heading mb20">
								<%@include file="include/cham_dau_db.jsp" %>
							</div> 
							<div class="page txt-heading mb20">
								<%@include file="include/30cap.jsp" %>
							</div> 
							
							<%@include file="include/menu_center.jsp" %>
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu}">XSMB ${dayOfWeek}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay}">XSMB Ngày ${ddmmyyyy}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb.jsp" %>
							</div>
							
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu1}">XSMB ${dayOfWeek1}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay1}">XSMB Ngày ${ddmmyyyy1}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb1.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb1.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu2}">XSMB ${dayOfWeek2}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay2}">XSMB Ngày ${ddmmyyyy2}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb2.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb2.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu3}">XSMB ${dayOfWeek3}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay3}">XSMB Ngày ${ddmmyyyy3}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb3.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb3.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu4}">XSMB ${dayOfWeek4}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay4}">XSMB Ngày ${ddmmyyyy4}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb4.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb4.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							
							<ul class="list">
								<li>
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBThu5}">XSMB ${dayOfWeek5}</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay5}">XSMB Ngày ${ddmmyyyy5}</a>
								</li>
							</ul>
							<div class="table-item mb20">
								<%@include file="include/bangkq_mb5.jsp"%>
							</div>
							<div class="table-oder mb20">
								
								<%@include file="include/dauduoi_mb5.jsp" %>
							</div>
							
							<%@include file="include/menu_center.jsp" %>
							
							
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