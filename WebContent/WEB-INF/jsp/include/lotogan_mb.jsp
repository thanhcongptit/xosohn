<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:if test="${code ne 'XSTD'}">
	<h2><a style="color: white;" href="${pageContext.servletContext.contextPath}/thong-ke-lo-gan-${lotteryCompany.companyLink}-${lotteryCompany.codeLowerCase}.html">THỐNG KÊ LO GAN XỔ SỐ ${lotteryCompany.company}</a></h2>
</c:if>
<c:if test="${code eq 'XSTD'}">
	<h2><a style="color: white;" href="${pageContext.servletContext.contextPath}/thong-ke-lo-gan.html">THỐNG KÊ LO GAN XỔ SỐ MIỀN BẮC</a></h2>
</c:if>

<table>
	<thead>
		<tr class="red">
			<th colspan="5">10 Số LÔ GAN <c:if test="${code eq 'XSTD'}"> Miền Bắc</c:if><c:if test="${code ne 'XSTD'}"> ${lotteryCompany.company}</c:if> có khả năng về cao trong
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
				<td>
					<c:if test="${not empty loto.ngayxuathiengannhat}">
					    <a href="${pageContext.servletContext.contextPath}/${linkXSGan}-${fn:replace(loto.ngayxuathiengannhat, '/', '-')}.html">${loto.ngayxuathiengannhat }</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>