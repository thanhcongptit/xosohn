<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			var code = '${code}';
			var isOpenToday = '${isOpenToday}';
			var region = '${region}';
			
			if(isOpenToday == "1") {
				
				if(region == "MT") {
					if (currHHMMSS >= 171000 && currHHMMSS < 174500) {
						$("#main_content_tinh1").hide();
						$("#main_content_tinh2").show();
						loadXSLive(code);
					} else {
						$("#main_content_tinh2").hide();
						$("#main_content_tinh1").show();
					}
					
				} else {
					
					if (currHHMMSS >= 161000 && currHHMMSS < 164500) {
						$("#main_content_tinh1").hide();
						$("#main_content_tinh2").show();
						loadXSLive(code);
					} else {
						$("#main_content_tinh2").hide();
						$("#main_content_tinh1").show();
					}
				}
			}
			
		}
	
		function loadAjax(url) {
			$.ajax({
				type : 'POST',
				url : url,
				dataType : 'text',
				cache : false
			}).done(function(sResponse) {
				$("#tructiep_tinh").html(sResponse);
			});
		}
	
		function loadXSLive(region) {
			var temp = '${pageContext.servletContext.contextPath}';
			//conglt
			if (!temp) {
				temp = '/xosohn'
			}
			loadAjax(temp + "/ajax/live_tinh.htm?r=" + region);
			
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
						<div class="content" id="main_content_tinh1">
							<%@include file="include/bangkq_tinh.jsp"%>
							<%@include file="include/dauduoi_tinh.jsp"%>
							
							<div class="page txt-heading mb20">
								<%@include file="include/lotogan_mb.jsp" %>
							</div>
							<div class="page txt-heading mb20">
								<%@include file="include/30cap.jsp" %>
							</div>
							
							<%@include file="include/menu_center_tinh.jsp" %>
							<%@include file="include/banner2_ngang.jsp"%>
							
							<%@include file="include/bangkq_tinh1.jsp"%>
							<%@include file="include/dauduoi_tinh1.jsp"%>
							<%@include file="include/menu_center_tinh.jsp" %>
							
							<%@include file="include/bangkq_tinh2.jsp"%>
							<%@include file="include/dauduoi_tinh2.jsp"%>
							<%@include file="include/menu_center_tinh.jsp" %>
							
							
						</div>
						
						<div class="content" id="main_content_tinh2" style="display: none">
							<div id="tructiep_tinh">
								
							</div>
							
							<div class="page txt-heading mb20">
								<%@include file="include/lotogan_mb.jsp" %>
							</div>
							<div class="page txt-heading mb20">
								<%@include file="include/30cap.jsp" %>
							</div>
							
							<%@include file="include/menu_center_tinh.jsp" %>
							
							<%@include file="include/bangkq_tinh_tructiep.jsp"%>
							<%@include file="include/dauduoi_tinh.jsp"%>
							<%@include file="include/menu_center_tinh.jsp" %>
							
							<%@include file="include/bangkq_tinh1.jsp"%>
							<%@include file="include/dauduoi_tinh1.jsp"%>
							
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