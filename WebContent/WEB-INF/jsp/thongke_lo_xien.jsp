<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
	<%@include file="include/header.jsp"%> 
	<%@include file="include/js_include.jsp" %>
<script type="text/javascript">
function callServer(indexList, indexCau) {
	var openDate = $('[name=openDate]').val();
	
	var nhay = "${nhay}";
	var lon = "${lon}";
	var ca_cap = "${ca_cap}";
	var params = "indexList="+indexList;
	params +=  "&indexCau="+indexCau;
	params +=  "&openDate="+openDate;
	params +=  "&nhay="+nhay;
	params +=  "&lon="+lon;
	params +=  "&ca_cap="+ca_cap;
	
	var code = "${code}";
	var url = "";
	if(code == 'XSMB') {
		url =    '${pageContext.request.contextPath}'+'/soicauchitiet.htm?'
	} else {
		url =    '${pageContext.request.contextPath}'+'/soicauchitiet_mt.htm?'
		params +=  "&code="+code.toLowerCase();
	}	
	
	url =  url + params;
	loadAjaxSoiCau(url);
	event.preventDefault();
	
}

function loadAjaxSoiCau(url) {
	
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'text',
        cache: false                    
    }).done(function(sResponse) {
   	 	$("#soicau").html(sResponse);
   	 $('html, body').animate({
         scrollTop: $('#soicau').position().top }, 300);
    });
}

function handelCodeChangeSoiCau() {
	var value = $('#province_code_soicau').val();
	var fields = value.split('_');
	var url = "${pageContext.servletContext.contextPath}/"
			+ "thong-ke-lo-xien-" + fields[0].toLowerCase() + "-" + fields[1].toLowerCase()
			+ ".html";
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
					<p><span class="btn off-seo pull-right" id="btn_seo" onclick="disableAdv();"></span></p>
					<%@include file="include/banner1_ngang.jsp"%>
				</div>
				<div class="row row-s">
					<div class="col-xs-12 col-sm-6 col-s">
						
						<div class="content">
							<div class="page txt-heading mb20 parent-list">
								<h2>THỐNG KÊ LÔ XIÊN XỔ SỐ ${fn:toUpperCase(lotteryCompany.company)}</h2>
								
								<%@include file="include/option_thongke.jsp"%>
								
							</div>
							
							<%@include file="include/tk_loto_xien2.jsp"%>
							<%@include file="include/tk_loto_xien3.jsp"%>
							
							<div class="page txt-heading mb20 parent-list">
								<h2>THỐNG KÊ LÔ GAN XỔ SỐ ${fn:toUpperCase(lotteryCompany.company)}</h2>
								
								<%@include file="include/tk_lotogan.jsp"%>
								
							</div>
							<%@include file="include/tk_10_cap_nhieu_it.jsp"%>
							
							<c:if test="${code eq 'XSMB'}">
								<%@include file="include/soicau_mb_content.jsp"%>
							</c:if>
							
							<c:if test="${code ne 'XSMB'}">
								<%@include file="include/soicau_tinh_content.jsp"%>
							</c:if>
							
							
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