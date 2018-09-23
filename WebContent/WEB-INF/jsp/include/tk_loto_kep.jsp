<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="page txt-heading mb20 parent-list">
	<h2>LÔ KÉP XỔ SỐ ${fn:toUpperCase(lotteryCompany.company)}</h2>
	<table>
		<thead>
			<tr class="red">
				<th colspan="3">10 Cặp LÔ KÉP ${lotteryCompany.company} về nhiều nhất trong ${biendo}
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
				<td width="60px">Lô kép</td>
				<td width="130px">Số lần xuất hiện</td>
				<td>Các ngày xuất hiện</td>
			</tr>
			<c:forEach items="${lokeps}" var="loto">
				<c:if test="${loto.count>1 }">
					<tr>
						<td><span class="txt_red">${loto.capLoto}</span></td>
						<td>${loto.count} ngày</td>
						<td>
							<c:forEach var="dateItem" items="${loto.openDates}" varStatus="loop">
								<a href= "${pageContext.servletContext.contextPath}/${linkXS}-${dateItem.dateOpen}.html">${dateItem.dateView}</a><c:if test="${!loop.last}">, </c:if>
							</c:forEach>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			
			
		</tbody>
	</table>
</div>