<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil" %>
<%@page import="inet.bean.LotteryCompany" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page table-item txt-heading mb20 icon-list">
							
							<h2>
								<span class="iconxs"><a href="${pageContext.servletContext.contextPath}/${linkXS}">XS ${lotteryCompany.company}</a></span> 
								<span class="span"><span>
								<a href="${pageContext.servletContext.contextPath}/${linkThu3}">${code} &nbsp;${week3}</a></span>
								<a href="${pageContext.servletContext.contextPath}/${linkDay3}">${code} ngày ${date3}</a></span>
							
							</h2>
							<table>
								<tr>
									<th class="red"><span>Giải tám</span></th>
									<td colspan="12"><span>${lottery3.eighth}</span></td>
								</tr>
								<tr class="tr">
									<th>Giải bảy</th>
									<td colspan="12">${lottery3.seventh}</td>
								</tr>
								<tr>
									<th>Giải sáu</th>
									<c:forTokens items="${lottery3.sixth}" delims="-" var="sixth">
		                                               	<td colspan="4" >${sixth}</td>
		                                       </c:forTokens>
								</tr>
								<tr class="tr">
									<th>Giải năm</th>
									<c:forTokens items="${lottery3.fifth}" delims="-" var="fifth" >
		                                               	<td colspan="12" >${fifth}</td>
		                                               </c:forTokens>
								</tr>
								<tr class="tr">
									<th rowspan="2">Giải bốn</th>
									<c:forTokens items="${lottery3.fourth}" delims="-" var="fourth" begin="0" end="3">
		                                               	<td colspan="3" >${fourth}</td>
		                                               </c:forTokens>
								</tr>
								<tr class="tr">
									<c:forTokens items="${lottery3.fourth}" delims="-" var="fourth" begin="4" end="6" >
		                                               	<td colspan="4">${fourth}</td>
		                                               </c:forTokens>
								</tr>
								<tr>
									<th>Giải ba</th>
									<c:forTokens items="${lottery3.third}" delims="-" var="third" >
		                                               	<td colspan="6">${third}</td>
		                                               </c:forTokens>
								</tr>
								<tr class="tr">
									<th>Giải nhỉ</th>
									<c:forTokens items="${lottery3.second}" delims="-" var="second" >
		                                               	<td colspan="12">${second}</td>
		                                               </c:forTokens> 
								</tr>
								<tr>
									<th>Giải nhất</th>
									 <c:forTokens items="${lottery3.first}" delims="-" var="first"  >
		                                                                            <td colspan="12">${first}</td>    
		                                                                     </c:forTokens>
								</tr>
								<tr class="red">
									<th>GĐB</th>
									<c:forTokens items="${lottery3.special}" delims="-" var="special"  >
		                                                                            <td colspan="12"><span >${special}</span></td>    
		                                                                     </c:forTokens>
								</tr>
							</table>
										
							  <%  if(position != 1) {
		MetaGuide metaGuide = new MetaGuideDao().findByPosition(30);	
	
			
			String titleTemp = metaGuide.getTitle();
			
			String dateIndex = (String)request.getAttribute("date3");
			if(!CommonUtil.isEmptyString(dateIndex)) {
			Date tempDate = DateUtil.toDate(dateIndex, "dd/MM/yyyy");
			String version = DateUtil.date2String(tempDate, "dd-MM-yyyy");
			String temporalCoverage = DateUtil.date2String(tempDate, "yyyy-MM-dd");
			titleTemp = titleTemp.replace("{ngay}", dateIndex);
			
			LotteryCompany company = (LotteryCompany)request.getAttribute("lotteryCompany");
			
			titleTemp = titleTemp.replace("{code}", company.getCode());
			titleTemp = titleTemp.replace("{company}", company.getCompany());
			
		%>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Dataset",
                  "name": "<%=titleTemp%>",
                  "description": "<%=metaPage%>",
                  "url": "https://xosohn.com/${lotteryCompany.codeLowerCase}-ket-qua-xo-so-${lotteryCompany.linkKq247}.html",
                  "version": "<%=temporalCoverage%>",
                  "variableMeasured": "<%=titlePage%>",
                  "sameAs": "https://xosohn.com/${lotteryCompany.codeLowerCase}-<%=version%>.html",
                  "keywords": [<%=keyword%>],
                  "temporalCoverage" : "<%=temporalCoverage%>",
                    "creator":{
                        "@type":"Organization",
                        "url": "https://xosohn.com",
                        "name":"xosohn"
                    }
                }</script>
                
		<%} }%>
						</div>