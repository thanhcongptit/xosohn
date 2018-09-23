<%@page import="inet.bean.Lottery"%>
<%@page import="inet.bean.CauDetailBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="inet.bean.SoicauDetailBean"%>
<%@page import="inet.bean.SoicauBean"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<%@include file="include/header.jsp"%>
<%@include file="include/js_include.jsp"%>
<script type="text/javascript">

function callServer(indexList, indexCau) {
	var openDate = $('[name=openDate]').val();
	var value = $('#province_code_soicau').val();
	var fields = value.split('_');
	
	var nhay = "${nhay}";
	var lon = "${lon}";
	var ca_cap = "${ca_cap}";
	var params = "indexList="+indexList;
	params +=  "&indexCau="+indexCau;
	params +=  "&openDate="+openDate;
	params +=  "&nhay="+nhay;
	params +=  "&lon="+lon;
	params +=  "&ca_cap="+ca_cap;
	params +=  "&code="+fields[0].toLowerCase();
	var url =    '${pageContext.request.contextPath}'+'/soicauchitiet_mt.htm?' + params;
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
			+ "soi-cau-" + fields[1].toLowerCase() + "-" + fields[0].toLowerCase()
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
				<p>
					<span class="btn off-seo pull-right" id="btn_seo" onclick="disableAdv();"></span>
				</p>
				<%@include file="include/banner1_ngang.jsp"%>
			</div>
			<div class="row row-s">
				<div class="col-xs-12 col-sm-6 col-s">
					<div class="content">
						<div class="again mb20">
							<form>

								<div class="text-center">
									<ul>
										<form id="trial_form" method="post" role="form">
											<li><select id="province_code_soicau"
												class="form-control">

													<c:forEach items="${lotteryCompanies}" var="company">
														<option value="${company.code}_${company.companyLink}"
															<c:if test="${code == company.codeLowerCase}"> selected </c:if>>${company.company}</option>

													</c:forEach>
											</select></li>
											<li>
												<button type="button" class="btn"
													onclick='handelCodeChangeSoiCau();'>Soi cầu</button>
											</li>
										</form>
									</ul>
								</div>
							</form>
						</div>

						<%@include file="include/soicau_tinh_content.jsp"%>
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