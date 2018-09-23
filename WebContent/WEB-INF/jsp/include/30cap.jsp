<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${code ne 'XSTD'}">
	<h2>THỐNG KÊ NHANH ${lotteryCompany.company} HÔM NAY</h2>
</c:if>
<c:if test="${code eq 'XSTD'}">
	<h2>THỐNG KÊ NHANH MIỀN BẮC HÔM NAY</h2>
</c:if>
<table>
	<thead>
		<tr class="red">
			<th colspan="2"><span>Về nhiều trong 30 lần quay</span></th>
			<th colspan="2">Về ít trong 30 lần quay</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${max30LotoList != null}">
			<c:set var="index" value="0" />
			<c:forEach items="${max30LotoList}" var="loto">
				<tr>
					<td>${loto.loto}</td>
					<td>${loto.solanxuathien} lần</td>
					<td>${min30LotoList[index].loto}</td>
					<td>${min30LotoList[index].solanxuathien } lần</td>
				</tr>
				<c:set var="index" value="${index+1}" />
			</c:forEach>
		</c:if>	
		
		
	</tbody>
</table>