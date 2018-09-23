<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="inet.util.DatePro"%>
<%@page import="inet.model.MetaGuideDao"%>
<!DOCTYPE html>
<html>
<%@include file="include/header.jsp"%>
<%@include file="include/js_include.jsp"%>

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
			$("#main_content").hide();
			$("#main_content2").hide();
			$("#main_content3").hide();
			$("#main_content1").show();
			loadXSLive('MB', 1);
		} else if (currHHMMSS >= 171000 && currHHMMSS < 174500) {
			$("#main_content").hide();
			$("#main_content1").hide();
			$("#main_content3").hide();
			$("#main_content2").show();
			loadXSLive('MT', 2);
		} else if (currHHMMSS >= 161000 && currHHMMSS < 164500) {
			$("#main_content").hide();
			$("#main_content1").hide();
			$("#main_content2").hide();
			$("#main_content3").show();
			loadXSLive('MN', 3);
		} else {
			$("#main_content1").hide();
			$("#main_content2").hide();
			$("#main_content3").hide();
			$("#main_content").show();
		}
	}

	function loadAjax(url, province) {
		//console.log("url:", url);
		
		$.ajax({
			type : 'POST',
			url : url,
			dataType : 'text',
			cache : false
		}).done(function(sResponse) {
			
			if(province == 1) {
				$("#tructiep1").html(sResponse);
			} else if(province == 2) {
				$("#tructiep2").html(sResponse);
			} else if(province == 3) {
				$("#tructiep3").html(sResponse);
			}
			

		});
	}

	function loadXSLive(region, province) {
		var temp = '${pageContext.servletContext.contextPath}';
		//conglt
		if (!temp) {
			temp = '/xosohn'
		}

		if(province == 1) {
			loadAjax(temp + "/ajax/live.htm?r=" + region, province);
		} else if(province == 2) {
			loadAjax(temp + "/ajax/live_mt.htm?r=" + region, province);
		} else if(province == 3) {
			loadAjax(temp + "/ajax/live_mn.htm?r=" + region, province);
		}
		
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
					<div class="content" id="main_content">
						<%@include file="include/ul_menu.jsp"%>

						<ul class="list">
							<li><a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">XSMB</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/${linkMBThu}">XSMB ${dayOfWeek}</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/${linkMBDay}">XSMB Ngày ${ddmmyyyy}</a></li>
						</ul>
						<div class="table-item mb20">
							<%@include file="include/bangkq_mb.jsp"%>
						</div>
						<div class="table-oder mb20">

							<%@include file="include/dauduoi_mb.jsp"%>
						</div>

						<%@include file="include/menu_center.jsp"%>
						<%@include file="include/banner2_ngang.jsp"%>

						<%@include file="include/bangkq_mt.jsp"%>
						<%@include file="include/dauduoi_mt.jsp"%>

						<%@include file="include/menu_center_mt.jsp"%>
						<%@include file="include/banner3_ngang.jsp"%>

						<%@include file="include/bangkq_mn.jsp"%>

						<%@include file="include/dauduoi_mn.jsp"%>

						<%@include file="include/menu_center_mn.jsp"%>

						<%@include file="include/banner4_ngang.jsp"%>

					</div>

					<!-- truc tiep Mb -->
					<div class="content" id="main_content1" style="display: none">
						<div id="tructiep1">
							
						</div>

						<%@include file="include/menu_center.jsp"%>

						<%@include file="include/bangkq_mt.jsp"%>
						<%@include file="include/dauduoi_mt.jsp"%>

						<%@include file="include/menu_center_mt.jsp"%>

						<%@include file="include/bangkq_mn.jsp"%>

						<%@include file="include/dauduoi_mn.jsp"%>

						<%@include file="include/menu_center_mn.jsp"%>

					</div>

					<!-- truc tiep MT -->
					<div class="content" id="main_content2" style="display: none">
						<div id="tructiep2">
							
						</div>
						
						<%@include file="include/menu_center_mt.jsp"%>

						<div class="page table-item txt-heading mb20 icon-list">
							<h2>
								<span class="iconxs">
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">Xổ số miền bắc</a></span> 
									<span class="span">
									<span><a href="${pageContext.servletContext.contextPath}/${linkMBThu}">XSMB ${dayOfWeek}</a></span>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay}">XSMB Ngày ${ddmmyyyy}</a></span>
							</h2>
							<%@include file="include/bangkq_mb.jsp"%>
						</div>
						<div class="table-oder mb20">

							<%@include file="include/dauduoi_mb.jsp"%>
						</div>

						<%@include file="include/menu_center.jsp"%>

						<%@include file="include/bangkq_mn.jsp"%>

						<%@include file="include/dauduoi_mn.jsp"%>

						<%@include file="include/menu_center_mn.jsp"%>


					</div>

					<!-- truc tiep MN -->
					<div class="content" id="main_content3" style="display: none">
						<div id="tructiep3">
							
						</div>
						
						<%@include file="include/menu_center_mn.jsp"%>

						<div class="page table-item txt-heading mb20 icon-list">
							<h2>
								<span class="iconxs">
									<a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">Xổ số miền bắc</a></span> 
									<span class="span">
									<span><a href="${pageContext.servletContext.contextPath}/${linkMBThu}">XSMB ${dayOfWeek}</a></span>
									<a href="${pageContext.servletContext.contextPath}/${linkMBDay}">XSMB Ngày ${ddmmyyyy}</a></span>
							</h2>
							<%@include file="include/bangkq_mb.jsp"%>
						</div>
						<div class="table-oder mb20">
							<%@include file="include/dauduoi_mb.jsp"%>
						</div>
						
						<%@include file="include/menu_center.jsp"%>


						<%@include file="include/bangkq_mt.jsp"%>
						<%@include file="include/dauduoi_mt.jsp"%>
						<%@include file="include/menu_center_mt.jsp"%>
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