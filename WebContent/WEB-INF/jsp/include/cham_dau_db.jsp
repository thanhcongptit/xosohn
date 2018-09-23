<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<h2>Thống kê chạm đầu đặc biệt Miền Bắc hôm nay </h2>
		<table>
			<thead>

				<tr >
					<th>Số</th>
					<th>Chưa về</th>
					<th>Cực đại</th>
					<th>Xuất hiện</th>
					<th>Về gần nhất</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dauDacBiet}" var="dacbiet">
					<tr>
						<td><span class="red">${dacbiet.dau}</span></td>
						<td>${dacbiet.ngayChuave}</td>
						<td>${dacbiet.ganMax}</td>
						<td>${dacbiet.tong}</td>
						<td><a href="${pageContext.servletContext.contextPath}/xsmb-${fn:replace(dacbiet.ngayve, '/', '-')}.html">${dacbiet.ngayve}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>