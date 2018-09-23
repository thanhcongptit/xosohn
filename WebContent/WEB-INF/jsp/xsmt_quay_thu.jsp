<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="include/header.jsp"%>
<%@include file="include/js_include.jsp"%>
<style>
.imgloadig {
	background: url('https://xsmn.me/images/loading-green.gif') no-repeat;
	width: 16px;
	height: 18px;
	margin: auto;
	display: block;
	zoom: 1;
	padding: 2px 0;
}
</style>
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

						<%@include file="include/quaythu_option.jsp"%>

						<div class="table-item txt-heading mb20">
							<h1>Quay thử ${company}</h1>
							<c:set var="width" value="33%" />
							<c:if test="${numSize==3}">
								<c:set var="width" value="25%" />
							</c:if>

							<c:if test="${numSize>3}">
								<c:set var="width" value="20%" />
							</c:if>

							<table>
								<td width="${width}">
										
									</td>
								<c:forEach items="${companies}" var="company">
									<td width="${width}">
										${company.company}
									</td>
								</c:forEach>
								<tbody>

									<tr class="red tr">
										<td>Giải Tám</td>
										<c:forEach items="${companies}" var="company">
											<td><span class="imgloadig"></span></td>
										</c:forEach>

									</tr>
									<tr>
										<td>Giải bảy</td>
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<td rowspan="3">Giải sáu</td>
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr>
										<td>Giải năm</td>
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<td rowspan="7">Giải bốn</td>
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr>
										<td rowspan="2">Giải ba</td>
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr">
										<td>Giải nhì</td>
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr>
										<td>Giải nhất</td>
										<c:forEach items="${companies}" var="company">
											<td><strong class="imgloadig"></strong></td>
										</c:forEach>
									</tr>
									<tr class="tr red">
										<td>GĐB</td>
										<c:forEach items="${companies}" var="company">
											<td><span class="imgloadig"></span></td>
										</c:forEach>
									</tr>
								</tbody>
							</table>
						</div>

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

<script type="text/javascript">
	var t;
	var result = {};

	function getRandomInt(min, max) {
		return Math.floor(Math.random() * (max - min + 1)) + min;
	}

	function getValueLoto(range) {
		var loto = "";
		for (var index = 1; index <= range; index++) {
			loto = loto + getRandomInt(0, 9);
		}

		return loto;
	}

	function randomMN(id, size) {
		if (id <= (18 * size)) {
			var loto = "";

			if (id <= size) {
				loto = getValueLoto(2);
			} else if (id <= 2 * size) {
				loto = getValueLoto(3);
			} else if (id > (2 * size) && id <= (6 * size)) {
				loto = getValueLoto(4);
			} else if (id > (6 * size) && id <= (17 * size)) {
				loto = getValueLoto(5);
			} else {
				loto = getValueLoto(6);
			}

			$(".imgloadig:eq(0)").removeClass('imgloadig').html(loto);
			id++;
			t = setTimeout("randomMN(" + id + "," + size + ")", 1500);
		} else {
			clearTimeout(t);
		}

	}

	function runResult() {
		clearTimeout(t);

		var size = "${numSize}";
		t = setTimeout("randomMN(1, " + size + ")", 2000);
	};

	$(document).ready(runResult

	);
</script>
</html>