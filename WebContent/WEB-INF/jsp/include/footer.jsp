<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerNeo = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerNeo = new BannerDAO().find("index", Banner.POSITION_NEO);

	} else {
		bannerNeo = new BannerDAO().find("other", Banner.POSITION_NEO);

	}
%>

<footer>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-4 col-md-3">
				<div class="item">
					<h3>TRỰC TIẾP XỔ SỐ</h3>
					<ul>
						<li><a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">Xổ số Miền Bắc</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/xsmt-xo-so-mien-trung.html">Xổ số Miền Trung</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/xsmn-xo-so-mien-nam.html">Xổ số Miền Nam</a></li>
					</ul>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<div class="item">
					<h3>SOI CẦU XỔ SỐ</h3>
					<ul>
						<li><a href="${pageContext.servletContext.contextPath}/soi-cau-bach-thu.html">Soi cầu Miền Bắc</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/soi-cau-mien-trung.html">Soi cầu Miền Trung</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/soi-cau-mien-nam.html">Soi cầu Miền Nam</a></li>
					</ul>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<div class="item">
					<h3>QUAY THỬ XỔ SỐ</h3>
					<ul>
						<li><a href="${pageContext.servletContext.contextPath}/quay-thu-ket-qua-xo-so-mien-bac-xsmb.html">Quay thử Miền Bắc</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/quay-thu-ket-qua-xo-so-mien-trung-xsmt.html">Quay thử Miền Trung</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/quay-thu-ket-qua-xo-so-mien-nam-xsmn.html">Quay thử Miền Nam</a></li>
					</ul>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<div class="item text-center">
					<img src="${pageContext.servletContext.contextPath}/resources/img/img-ft.png" alt="" title="">
				</div>
			</div>
		</div>
		<hr>
		
		<%if(!CommonUtil.isEmptyString(textPage)) {%>
		<div class="row">
			<%= textPage%>
		</div>
		<hr>
		<%} %>
		
		<div class="row rowbt">
			<div class="col-xs-12 col-sm-6">© Copyright 2018 xosohn.com
			</div>
			<div class="col-xs-12 col-sm-6">
				<ul class="list-item-ft">
<!-- 					<li><a href="">liên hệ</a></li>
 -->					<li><a href="" data-toggle="modal" data-target="#myModal">Liên
							hệ</a></li>
				</ul>
				<!-- Modal content-->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Liên hệ</h4>
							</div>
							<div class="modal-body">
								<p>Liên hệ quảng cáo</p>
								<p>hoặc đóng góp ý kiến với chúng tôi.</p>
								<p>Email: admyxoso@gmail.com</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="banner-ft" id="banner_hidden13">
	<%
		if (bannerNeo != null) {
	%>
		<%=bannerNeo.getCode()%>
	<%
		}
	%>
	</div>
	
</footer>

<script>
	$(".datepicker").datepicker();
</script>

<a href="#" class="delay gotop"><img
	src="${pageContext.servletContext.contextPath}/resources/img/top.png"
	alt="go top" title="go top"></a>
<script type="text/javascript">
	(function() {
		$(window).scroll(function() {
			if ($(window).scrollTop() == 0) {
				$('.gotop').stop(false, true).fadeOut(600);
			} else {
				$('.gotop').stop(false, true).fadeIn(600);
			}
		});
		$('.gotop').click(function() {
			$('body,html').animate({
				scrollTop : 0
			}, 400);
			return false;
		})
	})(jQuery);
</script>
