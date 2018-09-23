<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
	<%@include file="include/header.jsp"%> 
	<%@include file="include/js_include.jsp" %>
<script type="text/javascript">

function handelCodeChangeSoiCau() {
	var value = $('#province_code_soicau').val();
	var fields = value.split('_');
	var url = "${pageContext.servletContext.contextPath}/"
			+ "thong-ke-00-99-" + fields[0].toLowerCase() + "-" + fields[1].toLowerCase()
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
								<h2>THỐNG KÊ 00-99 XỔ SỐ ${fn:toUpperCase(lotteryCompany.company)}</h2>
								
								<%@include file="include/option_thongke.jsp"%>
								
							</div>
							
							<div class="page txt-heading mb20 parent-list">
								<%
									
									Integer numberAward = (Integer) request.getAttribute("numberAward");
									String[] values = (String[]) request.getAttribute("lotos");
									Integer maxCount = (Integer) request.getAttribute("maxCount");
								%>
									<table>
										<tr>
											<th>Lô tô</th>
											<th colspan="3">Lần xuất hiện từ 00-99</th>
										</tr>
										
										<%for(int i=0; i< 100; i++)  {
											String loto = ""+ i;
											if(i < 10) {
												loto = "0" + loto;
											} 
											
											
											int valueCounter = Integer.parseInt(values[i]);
											double width = Double.parseDouble(values[i]) *100/ maxCount;
											
											if(valueCounter > 0) {
										%>
											<tr>
												<%
														double percent = Double.parseDouble(values[i]) * 100 / numberAward;
														BigDecimal bigDecimal = new BigDecimal(percent);
														BigDecimal roundOff = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
													%>
												<td><%=loto %></td>
												<td>
													<div class="pBar" data-from="0" data-to="<%=width%>" data-color="red">
														
													</div>
												</td>
												<td>
													
													<%=roundOff%>%
												</td>
												<td>
													<%=values[i] %> lượt
												</td>
											</tr>
										<%	}
										}	
										%>
										
									</table>
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
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery.pBar.js"></script>
	<!-- end HTML -->
</body>

</html>