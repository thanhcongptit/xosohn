<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div
	class="list-link icon">
	<h2>Xổ số hôm nay</h2>
	<ul>
		<li><a href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">Miền Bắc</a></li>
		<c:forEach items="${listLotteryCompanyMT}" var="company">
				<c:set var="link" value="${company.codeLowerCase}-ket-qua-xo-so-${company.linkKq247}.html" />
				<li><a href="/${link}">${company.company}</a></li>
		</c:forEach>
		
		<c:forEach items="${listLotteryCompanyMN}" var="companyMN">
				
				 <c:set var="link" value="${companyMN.codeLowerCase}-ket-qua-xo-so-${companyMN.linkKq247}.html" />
				<li><a href="/${link}">${companyMN.company}</a></li>
				
		</c:forEach>
	</ul>
</div>