<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${listLotteryMB5 != null}">
	<c:forEach items="${listLotteryMB}" var="lottery">
		<table>
			
			<tr class="tr">
				<th>GĐB</th>
				<td colspan="12"><span>${lottery.special}</span></td>
			</tr>
			<tr>
				<th>Giải Nhất</th>
				<td colspan="12">${lottery.first}</td>
			</tr>
			<tr class="tr">
				<th>Giải Nhì</th>
				<c:forTokens items="${lottery.second}" delims="-" var="second">
					<td colspan="6">${second}</td>
				</c:forTokens>
			</tr>
			<tr>
				<th rowspan="2">Giải Ba</th>
				<c:forTokens items="${lottery.third}" delims="-" var="third"
					begin="0" end="2">
					<td colspan="4">${third}</td>
				</c:forTokens>
			</tr>
			<tr>
				<c:forTokens items="${lottery.third}" delims="-" var="third"
					begin="3" end="5">
					<td colspan="4">${third}</td>
				</c:forTokens>
			</tr>
			<tr class="tr">
				<th>Giải Tư</th>
				<c:forTokens items="${lottery.fourth}" delims="-" var="fourth">
					<td colspan="3">${fourth}</td>
				</c:forTokens>
			</tr>
			<tr>
				<th rowspan="2">Giải Năm</th>
				<c:forTokens items="${lottery.fifth}" delims="-" var="fifth"
					begin="0" end="2">
					<td colspan="4">${fifth}</td>
				</c:forTokens>
			</tr>
			<tr>
				<c:forTokens items="${lottery.fifth}" delims="-" var="fifth"
					begin="3" end="5">
					<td colspan="4">${fifth}</td>
				</c:forTokens>
			</tr>
			<tr class="tr">
				<th>Giải Sáu</th>
				<c:forTokens items="${lottery.sixth}" delims="-" var="sixth">
					<td colspan="4">${sixth}</td>
				</c:forTokens>
			</tr>
			<tr>
				<th>Giải Bảy</th>
				<c:forTokens items="${lottery.seventh}" delims="-" var="seventh">
					<td colspan="3">${seventh}</td>
				</c:forTokens>
			</tr>

		</table>
	</c:forEach>
		<%  if(position != 1) {
		MetaGuide metaGuide5 = new MetaGuideDao().findByPosition(27);	
	
			
			String titleTemp5 = metaGuide5.getTitle();
			
			String dateIndex5 = (String)request.getAttribute("ddmmyyyy5");
			Date tempDate5 = DateUtil.toDate(dateIndex5, "dd/MM/yyyy");
			String version5 = DateUtil.date2String(tempDate5, "dd-MM-yyyy");
			String temporalCoverage5 = DateUtil.date2String(tempDate5, "yyyy-MM-dd");
			titleTemp5 = titleTemp5.replace("{ngay}", dateIndex5);
			
		%>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Dataset",
                  "name": "<%=titleTemp5%>",
                  "description": "<%=metaPage%>",
                  "url": "https://xosohn.com/xsmb-xo-so-mien-bac.html",
                  "version": "<%=temporalCoverage5%>",
                  "variableMeasured": "<%=titlePage%>",
                  "sameAs": "https://xosohn.com/xsmb-<%=version5%>.html",
                  "keywords": [<%=keyword%>],
                  "temporalCoverage" : "<%=temporalCoverage5%>",
                    "creator":{
                        "@type":"Organization",
                        "url": "https://xosohn.com",
                        "name":"xosohn"
                    }
                }</script>
                
		<%} %>
</c:if>