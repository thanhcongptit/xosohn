<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<table>
	<thead>
		<tr class="red">
			<th colspan="5">10 số LÔ GAN ${lotteryCompany.company} có khả năng về cao trong
				ngày</th>
		</tr>
		<tr >
			<th>Số</th>
			<th>Chưa về</th>
			<th>Cực đại</th>
			<th>Xuất hiện</th>
			<th>Về gần nhất</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listLoto}" var="loto">
			<tr>
				<td><span class="red">${loto.loto}</span></td>
				<td>${loto.ngaychuave}</td>
				<td>${loto.ganmax }</td>
				<td>${loto.solanxuathien}</td>
				<td><a href="${pageContext.servletContext.contextPath}/${linkXS}-${fn:replace(loto.ngayxuathiengannhat, '/', '-')}.html">${loto.ngayxuathiengannhat }</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>