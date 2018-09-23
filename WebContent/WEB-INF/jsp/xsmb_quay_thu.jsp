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
							<h1>Quay thử kết quả xổ số miền bắc</h1>
							<table>
								<tbody>
									<tr class="tr">
										<th>GĐB</th>
										<td colspan="12"><span class="imgloadig"></span></td>
									</tr>
									<tr>
										<th>Giải Nhất</th>
										<td colspan="12"><strong class="imgloadig"></strong></td>
									</tr>
									<tr class="tr">
										<th>Giải Nhì</th>
										<td colspan="6"><strong class="imgloadig"></strong></td>
										<td colspan="6"><strong class="imgloadig"></strong></td>
									</tr>
									<tr>
										<th rowspan="2">Giải Ba</th>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
									</tr>
									<tr>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
									</tr>
									<tr class="tr">
										<th>Giải Tư</th>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
									</tr>
									<tr>
										<th rowspan="2">Giải Năm</th>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
									</tr>
									<tr>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
									</tr>
									<tr class="tr">
										<th>Giải Sáu</th>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
									</tr>
									<tr>
										<th>Giải Bảy</th>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="table-oder mb20" id="dd"></div>

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

	function write(daus, duois, code) {

		var text = '<span class="heading">Bảng loto xổ số ' + code + '</span>'
				+ '<div class="row">' + '<div class="col-xs-6">'
				+ '<table><tbody>' + '   <tr>' + '		<td>Đầu</td>'
				+ '		<td>Loto</td>' + '	</tr>';

		var body = "";
		for (var index = 0; index < 10; index++) {

			body = '<tr> <td> ' + index.toString() + '</td>';
			body = body + '<td>' + daus[index] + '</td> </tr>'

			text = text + body;
		}

		text = text + '</tbody></table>' + '</div>' + '<div class="col-xs-6">'
				+ '	<table>' + '	<tbody><tr>' + '		<td>Đuôi</td>'
				+ '	<td>Loto</td>' + '	</tr>';

		for (var index = 0; index < 10; index++) {

			body = '<tr> <td> ' + index.toString() + '</td>';
			body = body + '<td>' + duois[index] + '</td> </tr>'

			text = text + body;
		}

		text = text + '</tbody></table></div></div>';
		$('#dd').html(text);
	}

	function writeDauDuoi(length, code) {
		var daus = {};
		var duois = {};

		for (var index = 0; index < 10; index++) {

			var dau = "";
			var duoi = "";

			for (var index1 = 1; index1 <= length; index1++) {
				var award = result[index1].substr(result[index1].length - 2);

				if (award.startsWith(index.toString())) {

					if (index1 != length) {
						dau = dau + award.substr(1, 2) + ",";
					} else {
						dau = dau + "<span class = 'red'>" + award.substr(1, 2)
								+ "</span>,";
					}
				}

				if (award.endsWith(index.toString())) {
					if (index1 != length) {
						duoi = duoi + award.substr(0, 1) + ",";
					} else {
						duoi = duoi + "<span class = 'red'>"
								+ award.substr(0, 1) + "</span>,";
					}
				}
			}

			dau = dau.substr(0, dau.length - 1);
			duoi = duoi.substr(0, duoi.length - 1)

			daus[index] = dau;
			duois[index] = duoi;

		}

		write(daus, duois, code);
	}

	function randomMB(id) {
		if (id < 27) {
			var loto = "";
			if (id > 0 && id < 10) {
				loto = getValueLoto(5);
			} else if (id >= 10 && id <= 19) {
				loto = getValueLoto(4);
			} else if (id >= 20 && id < 23) {
				loto = getValueLoto(3);
			} else if (id >= 23) {
				loto = getValueLoto(2);
			}
			result[id] = loto;

			$($(".imgloadig:eq(1)")).removeClass('imgloadig').html(loto);
			id++;
			t = setTimeout("randomMB(" + id + ")", 1500);
		} else if (id == 27) {
			var loto = getValueLoto(5);
			result[id] = loto;

			$($(".imgloadig:eq(0)")).removeClass('imgloadig').html(loto);

			id++;
			writeDauDuoi(27, "Miền Bắc");
			$("#dd").show();
		} else {
			clearTimeout(t);

		}
	}

	function runResult() {
		clearTimeout(t);

		$("#dd").hide();
		t = setTimeout("randomMB(1)", 2000);
	};

	$(document).ready(runResult);
</script>
</html>