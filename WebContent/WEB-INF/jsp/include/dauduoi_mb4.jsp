<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<span class="heading">Bảng loto xổ số miền bắc</span>
<div class="row">
	<div class="col-xs-6">
		<table>
			<tr>
				<td>Đầu</td>
				<td>Loto</td>
			</tr>
			<c:forEach items="${listLotteryDuoiMB4}"  var="loto"
				varStatus="dau">
				<tr>
					<td>${dau.index}</td>
					<td>${loto}</td>
				</tr>

			</c:forEach>

		</table>
	</div>
	<div class="col-xs-6">
		<table>
			<tr>
				<td>Đuôi</td>
				<td>Loto</td>
			</tr>
			<c:forEach items="${listLotteryDauMB4}" var="loto"
				varStatus="duoi">
				<tr >
					<td >${duoi.index}</td>
					<td >${loto}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>