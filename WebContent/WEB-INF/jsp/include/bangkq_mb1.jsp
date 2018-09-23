<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${listLotteryMB1!=null}">
	<c:forEach items="${listLotteryMB1}" var="lottery">
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
		MetaGuide metaGuide1 = new MetaGuideDao().findByPosition(27);	
	
			
			String titleTemp1 = metaGuide1.getTitle();
			
			String dateIndex1 = (String)request.getAttribute("ddmmyyyy1");
			Date tempDate1 = DateUtil.toDate(dateIndex1, "dd/MM/yyyy");
			String version1 = DateUtil.date2String(tempDate1, "dd-MM-yyyy");
			String temporalCoverage1 = DateUtil.date2String(tempDate1, "yyyy-MM-dd");
			titleTemp1 = titleTemp1.replace("{ngay}", dateIndex1);
			
		%>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Dataset",
                  "name": "<%=titleTemp1%>",
                  "description": "<%=metaPage%>",
                  "url": "https://xosohn.com/xsmb-xo-so-mien-bac.html",
                  "version": "<%=temporalCoverage1%>",
                  "variableMeasured": "<%=titlePage%>",
                  "sameAs": "https://xosohn.com/xsmb-<%=version1%>.html",
                  "keywords": [<%=keyword%>],
                  "temporalCoverage" : "<%=temporalCoverage1%>",
                    "creator":{
                        "@type":"Organization",
                        "url": "https://xosohn.com",
                        "name":"xosohn"
                    }
                }</script>
                
		<%} %>
</c:if>