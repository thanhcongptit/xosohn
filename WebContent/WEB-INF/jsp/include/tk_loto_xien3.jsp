<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page txt-heading mb20 parent-list">
	<h2>Lô xiên 3 ${code}</h2>
	<table>
		<thead>
			<tr class="red">
				<th colspan="3">10 LÔ XIÊN 3 ${lotteryCompany.company} về nhiều nhất trong ${biendo}
					lần quay
					<!-- <form class="form-table txt-red">
						<select class="form-control">
							<option>Lần quay</option>
						</select>
					</form> -->
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Lô xiên 3</th>
				<td>Số lần xuất hiện</td>
				<td>Các ngày xuất hiện</td>
			</tr>
			<c:forEach items="${loxien3}" var="loto">
				<tr>
					<td><span class="txt_red">${loto.capLoto}</span></td>
					<td>${loto.count} ngày</td>
					<td>
							<c:forEach var="dateItem" items="${loto.openDates}" varStatus="loop">
								<a href= "${pageContext.servletContext.contextPath}/${linkXS}-${dateItem.dateOpen}.html">${dateItem.dateView}</a><c:if test="${!loop.last}">, </c:if>
							</c:forEach>
						</td>
				</tr>
			</c:forEach>
			
			
		</tbody>
	</table>
</div>