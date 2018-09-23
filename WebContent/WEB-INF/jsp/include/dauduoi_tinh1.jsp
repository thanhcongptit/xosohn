<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="table-oder mb20">
	<span class="heading">Bảng loto xổ số miền nam</span>
	<div class="row">
		<div class="col-xs-6">
			<table>
				<tbody>
					<tr>
						<td>Đầu</td>
						<td>Loto</td>
					</tr>
					<c:forEach items="${listDuoi2}" var="loto" varStatus="dau">
						<tr>
							<td>${dau.index}</td>
							<td>${loto}</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-xs-6">
			<table>
				<tbody>
					<tr>
						<td>Đuôi</td>
						<td>Loto</td>
					</tr>
					<c:forEach items="${listDau2}" var="loto" varStatus="duoi">
						<tr>
							<td>${duoi.index}</td>
							<td>${loto}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>