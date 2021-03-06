<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page table-item txt-heading mb20 icon-list">
	<h2>
		<span class="iconxs"><a href="${pageContext.servletContext.contextPath}/xsmt-xo-so-mien-trung.html">Xổ số miền trung</a></span>
		 <span class="span">
		 <span><a href="${pageContext.servletContext.contextPath}/${linkMTThu1}">XSMT ${dayOfWeekMT1}</a></span>
		 <a href="${pageContext.servletContext.contextPath}/${linkMTDay1}">XSMT ngày ${ddmmyyyyMT1}</a></span>
	</h2>
	<c:if test="${lotterysMT1!=null&&numSizeMT1>0}">
		<c:set var="width" value="33%" />
		<c:if test="${numSizeMT1>2}">
			<c:set var="width" value="25%" />
		</c:if>

		<table>

			<tbody>
				<tr class="red">
					<td width="width=${width}"></td>
					<c:set var="index" value="0" />
					<c:set var="link" value="" />
					
					<c:forTokens items="${lotterysMT1.province}" delims="+"
						var="province">
						<c:if test="${listCompany!=null}">
							<c:forEach items="${listCompany}" var="company">
								<c:if test="${province==company.company}">
									<c:set var="link" value="${company.codeLowerCase}-ket-qua-xo-so-${company.linkKq247}.html" />
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
					<c:forTokens items="${lotterysMT1.eighth}" delims="+" var="eighth">
						<td>${eighth}</td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải bảy</td>
					<c:forTokens items="${lotterysMT1.seventh}" delims="+" var="seventh">
						<td>${seventh}</td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải sáu</td>
					<c:forTokens items="${lotterysMT1.sixth}" delims="+" var="sixth">
						<td><c:forTokens items="${sixth}" delims="-" var="six">
                                                    ${six}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải năm</td>
					<c:forTokens items="${lotterysMT1.fifth}" delims="+" var="fifth">
						<td>${fifth}</td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải tư</td>
					<c:forTokens items="${lotterysMT1.fourth}" delims="+" var="fourth">
						<td><c:forTokens items="${fourth}" delims="-" var="fo">
                                                    ${fo}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải ba</td>
					<c:forTokens items="${lotterysMT1.third}" delims="+" var="third">
						<td><c:forTokens items="${third}" delims="-" var="th">
                                                    ${th}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải nhì</td>
					<c:forTokens items="${lotterysMT1.second}" delims="+" var="second">
						<td>${second}</td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải nhất</td>
					<c:forTokens items="${lotterysMT1.first}" delims="+" var="first">
						<td>${first}</td>
					</c:forTokens>
				</tr>
				<tr class="red tr">
					<td>Đặc biệt</td>
					<c:forTokens items="${lotterysMT1.special}" delims="+" var="special">
						<td>${special}</td>
					</c:forTokens>
				</tr>
			</tbody>
		</table>
		
		<%  if(position != 1) {
		MetaGuide metaGuide1 = new MetaGuideDao().findByPosition(28);	
	
			
			String titleTemp1 = metaGuide1.getTitle();
			
			String dateIndex1 = (String)request.getAttribute("ddmmyyyyMT1");
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
                  "url": "https://xosohn.com/xsmt-xo-so-mien-trung.html",
                  "version": "<%=temporalCoverage1%>",
                  "variableMeasured": "<%=titlePage%>",
                  "sameAs": "https://xosohn.com/xsmt-<%=version1%>.html",
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
</div>