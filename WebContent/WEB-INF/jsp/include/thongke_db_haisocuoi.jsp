<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="db_week">
	<table>
		<thead>
			<tr class="red">
				<th colspan="2">10 cặp số về nhiều nhất (00 - 99)</th>
				<th colspan="2">10 cặp số về ít nhất (00 - 99)</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${max30LotoList != null}">
				<c:set var="index" value="0" />
				<c:forEach items="${max30LotoList}" var="loto">
					<tr>
						<td><span class="red">${loto.loto}</span></td>
						<td>${loto.solanxuathien} lần</td>
						<td><span class="red">${min30LotoList[index].loto}</span></td>
						<td>${min30LotoList[index].solanxuathien } lần</td>
					</tr>
					<c:set var="index" value="${index+1}" />
				</c:forEach>
			</c:if>

		</tbody>
	</table>

</div>