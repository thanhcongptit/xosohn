<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page table-item txt-heading mb20 icon-list">
	<h2>
		<span class="iconxs"><a href="${pageContext.servletContext.contextPath}/xsmn-xo-so-mien-nam.html">Xổ số miền nam</a></span> <span class="span"><span>
		<a href="${pageContext.servletContext.contextPath}/${linkMNThu2}">XSMN ${dayOfWeekMN2}</a></span>
		<a href="${pageContext.servletContext.contextPath}/${linkMNDay2}">XSMN ngày ${ddmmyyyyMN2}</a></span>
		
	</h2>
	<c:if test="${lotterysMN2!=null&&numSizeMN>0}">
		<c:set var="width" value="25%" />
		<c:if test="${numSizeMN2>3}">
			<c:set var="width" value="20%" />
		</c:if>

		<table>

			<tbody>
				<tr class="red">
					<td width="width=${width}"></td>
					<c:set var="index" value="0" />
					<c:set var="link" value="" />
					<c:forTokens items="${lotterysMN2.province}" delims="+"
						var="province">
						<c:if test="${listCompany!=null}">
							<c:forEach items="${listCompany}" var="company">
								<c:if test="${province==company.company}">
									<c:set var="link"
										value="${company.codeLowerCase}-ket-qua-xo-so-${company.linkKq247}.html" />
								</c:if>
							</c:forEach>
						</c:if>
						<td width="${width}">
							<p>
								<a href="/${link}" title="Xổ số ${province}">${province}</a>
							</p>
						</td>
						<c:set var="index" value="${index+1}" />
					</c:forTokens>
				</tr>
				<tr class="red tr">
					<td>Giải tám</td>
					<c:forTokens items="${lotterysMN2.eighth}" delims="+" var="eighth">
						<td>${eighth}</td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải bảy</td>
					<c:forTokens items="${lotterysMN2.seventh}" delims="+" var="seventh">
						<td>${seventh}</td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải sáu</td>
					<c:forTokens items="${lotterysMN2.sixth}" delims="+" var="sixth">
						<td><c:forTokens items="${sixth}" delims="-" var="six">
                                                    ${six}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải năm</td>
					<c:forTokens items="${lotterysMN2.fifth}" delims="+" var="fifth">
						<td>${fifth}</td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải tư</td>
					<c:forTokens items="${lotterysMN2.fourth}" delims="+" var="fourth">
						<td><c:forTokens items="${fourth}" delims="-" var="fo">
                                                    ${fo}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải ba</td>
					<c:forTokens items="${lotterysMN2.third}" delims="+" var="third">
						<td><c:forTokens items="${third}" delims="-" var="th">
                                                    ${th}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải nhì</td>
					<c:forTokens items="${lotterysMN2.second}" delims="+" var="second">
						<td>${second}</td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải nhất</td>
					<c:forTokens items="${lotterysMN2.first}" delims="+" var="first">
						<td>${first}</td>
					</c:forTokens>
				</tr>
				<tr class="red tr">
					<td>Đặc biệt</td>
					<c:forTokens items="${lotterysMN2.special}" delims="+" var="special">
						<td>${special}</td>
					</c:forTokens>
				</tr>
			</tbody>
		</table>
		
		<%
		if(position != 1) {
			MetaGuide metaGuide2 = new MetaGuideDao().findByPosition(29);	
	
			
			String titleTemp2 = metaGuide2.getTitle();
			
			String dateIndex2 = (String)request.getAttribute("ddmmyyyyMN2");
			Date tempDate2 = DateUtil.toDate(dateIndex2, "dd/MM/yyyy");
			String version2 = DateUtil.date2String(tempDate2, "dd-MM-yyyy");
			String temporalCoverage2 = DateUtil.date2String(tempDate2, "yyyy-MM-dd");
			titleTemp2 = titleTemp2.replace("{ngay}", dateIndex2);
			
		%>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Dataset",
                  "name": "<%=titleTemp2%>",
                  "description": "<%=metaPage%>",
                  "url": "https://xosohn.com/xsmn-xo-so-mien-nam.html",
                  "version": "<%=temporalCoverage2%>",
                  "variableMeasured": "<%=titlePage%>",
                  "sameAs": "https://xosohn.com/xsmn-<%=version2%>.html",
                  "keywords": [<%=keyword%>],
                  "temporalCoverage" : "<%=temporalCoverage2%>",
                    "creator":{
                        "@type":"Organization",
                        "url": "https://xosohn.com",
                        "name":"xosohn"
                    }
                }</script>
                <%} %>
	</c:if>
</div>