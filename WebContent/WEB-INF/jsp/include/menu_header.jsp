<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="container">
		<%@include file="banner_top_icon.jsp"%>

		<div class="menu">
			<nav id="mainMenu">
				<ul class="mainMenu">
					<li <c:if test="${empty activeMenu}"> class="active" </c:if>><a href="${pageContext.servletContext.contextPath}/">TRANG CHỦ</a></li>
					<li <c:if test="${activeMenu == 2}"> class="active" </c:if>><a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">MIỀN BẮC</a>
						<ul>
							<li><a href="${pageContext.servletContext.contextPath}/xsmb-thu-2.html">Thứ 2</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmb-thu-3.html">Thứ 3</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmb-thu-4.html">Thứ 4</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmb-thu-5.html">Thứ 5</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmb-thu-6.html">Thứ 6</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmb-thu-7.html">Thứ 7</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmb-chu-nhat.html">Chủ nhật</a></li>
						</ul></li>
					<li <c:if test="${activeMenu == 3}"> class="active" </c:if>><a href="${pageContext.servletContext.contextPath}/xsmt-xo-so-mien-trung.html">MIỀN TRUNG</a>
						<ul>
							<li><a href="${pageContext.servletContext.contextPath}/xsmt-thu-2.html">Thứ 2</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmt-thu-3.html">Thứ 3</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmt-thu-4.html">Thứ 4</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmt-thu-5.html">Thứ 5</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmt-thu-6.html">Thứ 6</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmt-thu-7.html">Thứ 7</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmt-chu-nhat.html">Chủ nhật</a></li>
						</ul></li>
					<li <c:if test="${activeMenu == 4}"> class="active" </c:if>><a href="${pageContext.servletContext.contextPath}/xsmn-xo-so-mien-nam.html">MIỀN NAM</a>
						<ul>
							<li><a href="${pageContext.servletContext.contextPath}/xsmn-thu-2.html">Thứ 2</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmn-thu-3.html">Thứ 3</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmn-thu-4.html">Thứ 4</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmn-thu-5.html">Thứ 5</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmn-thu-6.html">Thứ 6</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmn-thu-7.html">Thứ 7</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/xsmn-chu-nhat.html">Chủ nhật</a></li>
						</ul></li>
					<li <c:if test="${activeMenu == 5}"> class="active" </c:if>><a href="${pageContext.servletContext.contextPath}/soi-cau-bach-thu.html">SOI CẦU</a>
						<ul>
							<li><a href="${pageContext.servletContext.contextPath}/soi-cau-bach-thu.html">Bạch thủ (MB)</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/cau-lat-lien-tuc.html">Lật liên tục (MB)</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/cau-ve-nhieu-nhay.html">Về nhiều nháy (MB)</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/cau-ve-ca-cap.html">Về cả cặp (MB)</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/soi-cau-mien-trung.html">Cầu miền trung</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/soi-cau-mien-nam.html">Cầu miền nam</a></li>
						</ul>
					</li>
					<li <c:if test="${activeMenu == 6}"> class="active" </c:if>><a href="${pageContext.servletContext.contextPath}/du-doan.html">DỰ ĐOÁN</a>
					
						<ul>
							<li><a href="${pageContext.servletContext.contextPath}/du-doan-xsmb.html">Dự đoán XSMB</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/du-doan-xsmt.html">Dự đoán XSMT</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/du-doan-xsmn.html">Dự đoán XSMN</a></li>
							
						</ul>
					</li>
					<li <c:if test="${activeMenu == 7}"> class="active" </c:if>><a href="${pageContext.servletContext.contextPath}/thong-ke-lo-gan.html">THỐNG KÊ</a>
						<ul>
							<li><a href="${pageContext.servletContext.contextPath}/thong-ke-lo-gan.html">Lô gan</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/thong-ke-lo-kep.html">Lô kép</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/thong-ke-giai-dac-biet.html">Giải ĐB</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/thong-ke-tan-suat-lo-to.html">Tần suất</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/thong-ke-00-99.html">00-99</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/thong-ke-lo-xien.html">Lô xiên</a></li>
						</ul>
					</li>
					<li <c:if test="${activeMenu == '8'}"> class="active" </c:if>><a href="${pageContext.servletContext.contextPath}/quay-thu-ket-qua-xo-so-mien-bac-xsmb.html">QUAY THỬ</a></li>
				</ul>
			</nav>
			<button id="button-toggle-menu">
				<img src="${pageContext.servletContext.contextPath}/resources/img/menu-sp.png" alt="menu" title="menu">
			</button>
		</div>
	</div>
</header>