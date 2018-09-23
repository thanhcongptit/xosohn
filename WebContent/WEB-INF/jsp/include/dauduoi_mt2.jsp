<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="table-oder oder-mn mb20">
	<span class="heading">Bảng loto xổ số miền trung</span>
	<c:if test="${listLotteryDuoiMT2!=null}">
	</c:if>
	<table>
		<tr class="red">
			<th width="${width}">Đầu</th>
			<c:set var="index" value="0" />
			<c:forTokens items="${lotterysMT2.province}" delims="+" var="province">
				<c:if test="${listCompany!=null}">
					<c:forEach items="${listCompany}" var="company">
						<c:if test="${province==company.company}">
							<c:set var="link" value="${company.codeLowerCase}-ket-qua-xo-so-${company.linkKq247}.html" />
						</c:if>
					</c:forEach>
				</c:if>
				<th width="${width}"><a
					href="/${link}"
					title="Xổ số ${province}">${province}</a></th>
				<c:set var="index" value="${index+1}" />
			</c:forTokens>
		</tr>

		<c:set var="dau" value="0" />
		<c:forEach items="${listLotteryDuoiMT2}" var="duois">
			<tr>
				<td><span class="red">${dau}</span></td>
				<c:forTokens items="${duois}" delims="+" var="duoi">
					<c:if test="${duoi == 'del'}">
						<td>&nbsp</td>
					</c:if>
					<c:if test="${duoi != 'del'}">
						<td>${duoi}</td>
					</c:if>
				</c:forTokens>
			</tr>
			<c:set var="dau" value="${dau+1}" />
		</c:forEach>
	</table>
</div>