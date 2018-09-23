<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page table-item txt-heading mb20 icon-list">
							<h2>
								<span class="iconxs"><a href="${pageContext.servletContext.contextPath}/${linkXS}">XS ${lotteryCompany.company}</a></span>
								 <span class="span"><span>
								 <a href="${pageContext.servletContext.contextPath}/${linkThu1}">${code} &nbsp;${week1}</a></span>
								 <a href="${pageContext.servletContext.contextPath}/${linkDay1}">${code} ngày ${date1}</a></span>
							
							</h2>
							<ul class="list red">
								<li>${code}</li>
								<li>${code} ${week1}</li>
								<li>${code} Ngày ${date1}</li>
							</ul>
							<table>
								<tr>
									<th class="red"><span>Giải tám</span></th>
									<td colspan="12"><span>${lottery1.eighth}</span></td>
								</tr>
								<tr class="tr">
									<th>Giải bảy</th>
									<td colspan="12">${lottery1.seventh}</td>
								</tr>
								<tr>
									<th>Giải sáu</th>
									<c:forTokens items="${lottery1.sixth}" delims="-" var="sixth">
		                                               	<td colspan="4" >${sixth}</td>
		                                       </c:forTokens>
								</tr>
								<tr class="tr">
									<th>Giải năm</th>
									<c:forTokens items="${lottery1.fifth}" delims="-" var="fifth" >
		                                               	<td colspan="12" >${fifth}</td>
		                                               </c:forTokens>
								</tr>
								<tr class="tr">
									<th rowspan="2">Giải bốn</th>
									<c:forTokens items="${lottery1.fourth}" delims="-" var="fourth" begin="0" end="3">
		                                               	<td colspan="3" >${fourth}</td>
		                                               </c:forTokens>
								</tr>
								<tr class="tr">
									<c:forTokens items="${lottery1.fourth}" delims="-" var="fourth" begin="4" end="6" >
		                                               	<td colspan="4">${fourth}</td>
		                                               </c:forTokens>
								</tr>
								<tr>
									<th>Giải ba</th>
									<c:forTokens items="${lottery1.third}" delims="-" var="third" >
		                                               	<td colspan="6">${third}</td>
		                                               </c:forTokens>
								</tr>
								<tr class="tr">
									<th>Giải nhì</th>
									<c:forTokens items="${lottery1.second}" delims="-" var="second" >
		                                               	<td colspan="12">${second}</td>
		                                               </c:forTokens> 
								</tr>
								<tr>
									<th>Giải nhất</th>
									 <c:forTokens items="${lottery1.first}" delims="-" var="first"  >
		                                                                            <td colspan="12">${first}</td>    
		                                                                     </c:forTokens>
								</tr>
								<tr class="red">
									<th>GĐB</th>
									<c:forTokens items="${lottery1.special}" delims="-" var="special"  >
		                                                                            <td colspan="12"><span >${special}</span></td>    
		                                                                     </c:forTokens>
								</tr>
							</table>
						</div>