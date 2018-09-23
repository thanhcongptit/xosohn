<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page txt-heading mb20 parent-list">

	<h2>Thống kê các số về nhiều và về ít trong ${biendo} lần quay ${lotteryCompany.code}</h2>
	<table>
		<thead>
			<tr class="red">
				<th colspan="2" >Về nhiều nhất trong ${biendo} lần quay</th>
				<th colspan="2" >Về ít nhất trong ${biendo} lần quay</th>
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
</div>