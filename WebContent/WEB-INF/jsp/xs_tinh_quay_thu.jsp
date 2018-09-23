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
						<div class="again mb20">
							<form>
								<div class="text-center">
									<ul>
										<li><label> <input type="radio" name="optradio"
												<c:if test="${choice eq '1'}"> checked="checked"</c:if>
												value="1" onclick="handleOptions(1);">Quay thử theo
												miền
										</label></li>
										<li><label> <input type="radio" name="optradio"
												<c:if test="${choice eq '2'}"> checked="checked"</c:if>
												value="2" onclick="handleOptions(2);">Quay thử theo
												tỉnh
										</label></li>
									</ul>
								</div>
								<div class="text-center">
									<ul>
										<form id="trial_form" method="post" role="form">
											<li><select id="province_code1" style="display: none;"  class="form-control"
												name="code" onchange="handelCodeChange(1);">
													<c:if test="${code == 'xsmb'}">
														<option value="xsmb_mien-bac" selected="selected">Miền
															Bắc</option>
														<option value="xsmt_mien-trung">Miền Trung</option>
														<option value="xsmn_mien-nam">Miền Nam</option>
													</c:if>
													<c:if test="${code=='xsmt'}">
														<option value="xsmb_mien-bac">Miền Bắc</option>
														<option value="xsmt_mien-trung" selected="selected">Miền
															Trung</option>
														<option value="xsmn_mien-nam">Miền Nam</option>
													</c:if>
													<c:if test="${code=='xsmn'}">
														<option value="xsmb_mien-bac">Miền Bắc</option>
														<option value="xsmt_mien-trung">Miền Trung</option>
														<option value="xsmn_mien-nam" selected="selected">Miền
															Nam</option>
													</c:if>

											</select> <select id="province_code2" name="code" class="form-control"
												onchange="handelCodeChange(2);">

													<c:forEach items="${lotteryCompanies}" var="company">
															<option value="${company.code}_${company.companyLink}"
																<c:if test="${code == company.codeLowerCase}"> selected </c:if> >${company.company}</option>

													</c:forEach>
											</select></li>
											<li>
												<button type="button" class="btn" onclick='reloadUrl();'>Quay thử</button>
											</li>
										</form>
									</ul>
								</div>
							</form>
						</div>

						<div class="table-item txt-heading mb20">
							<h1>Quay thử xổ số ${company}</h1>
							<table>
								<tbody>
									<tr>
										<th class="red"><span>Giải tám</span></th>
										<td colspan="12"><span class="imgloadig"></span></td>
									</tr>
									<tr class="tr">
										<th>Giải bảy</th>
										<td colspan="12"><strong class="imgloadig"></strong></td>
									</tr>
									<tr>
										<th>Giải sáu</th>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
									</tr>
									<tr class="tr">
										<th>Giải năm</th>
										<td colspan="12"><strong class="imgloadig"></strong></td>
									</tr>
									<tr class="tr">
										<th rowspan="2">Giải bốn</th>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
										<td colspan="3"><strong class="imgloadig"></strong></td>
									</tr>
									<tr class="tr">
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
										<td colspan="4"><strong class="imgloadig"></strong></td>
									</tr>
									<tr>
										<th>Giải ba</th>
										<td colspan="6"><strong class="imgloadig"></strong></td>
										<td colspan="6"><strong class="imgloadig"></strong></td>
									</tr>
									<tr class="tr">
										<th>Giải nhỉ</th>
										<td colspan="12"><strong class="imgloadig"></strong></td>
									</tr>
									<tr>
										<th>Giải nhất</th>
										<td colspan="12"><strong class="imgloadig"></strong></td>
									</tr>
									<tr class="red">
										<th>GĐB</th>
										<td colspan="12"><span class="imgloadig"></span></td>
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

	function randomTMN(id) {

		if (id <= 18) {
			var loto = "";

			if (id == 1) {
				loto = getValueLoto(2);
			} else if (id == 2) {
				loto = getValueLoto(3);
			} else if (id >= 3 && id <= 6) {
				loto = getValueLoto(4);
			} else if (id > 6 && id < 18) {
				loto = getValueLoto(5);
			} else {
				loto = getValueLoto(6);
			}

			result[id] = loto;
			$(".imgloadig:eq(0)").removeClass('imgloadig').html(result[id]);
			id++;
			t = setTimeout("randomTMN(" + id + ")", 1500);

		} else {
			var company = "${company}"
			writeDauDuoi(18, company);
			$("#dd").show();
			clearTimeout(t);
		}
	}

	function runResult() {
		clearTimeout(t);

		$("#dd").hide();
		t = setTimeout("randomTMN(1)", 2000);
	};

	$(document).ready(runResult);
</script>
</html>