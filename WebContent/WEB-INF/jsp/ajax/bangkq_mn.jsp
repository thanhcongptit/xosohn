<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page table-item txt-heading mb20 icon-list">
	
	<h2>
		<span class="iconxs"><a href="/xsmn-xo-so-mien-nam.html">Xổ số miền nam</a></span> <span class="span">
		<span><a href="/${linkMNThu}">XSMN ${dayOfWeekMN}</a></span>
		<a href="/${linkMNDay}">XSMN ngày ${ddmmyyyyMN}</a></span>
		
	</h2>
	
	
	<c:if test="${lotterysMN!=null&&numSizeMN>0}">
		<c:set var="width" value="25%" />
		<c:if test="${numSizeMN>3}">
			<c:set var="width" value="20%" />
		</c:if>

		<table>

			<tbody>
				<tr class="red">
					<td width="width=${width}"></td>
					<c:set var="index" value="0" />
					<c:set var="link" value="" />
					<c:forTokens items="${lotterysMN.province}" delims="+"
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
					<c:forTokens items="${lotterysMN.eighth}" delims="+" var="eighth">
						<td>${eighth}</td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải bảy</td>
					<c:forTokens items="${lotterysMN.seventh}" delims="+" var="seventh">
						<td>${seventh}</td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải sáu</td>
					<c:forTokens items="${lotterysMN.sixth}" delims="+" var="sixth">
						<td><c:forTokens items="${sixth}" delims="-" var="six">
                                                    ${six}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải năm</td>
					<c:forTokens items="${lotterysMN.fifth}" delims="+" var="fifth">
						<td>${fifth}</td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải tư</td>
					<c:forTokens items="${lotterysMN.fourth}" delims="+" var="fourth">
						<td><c:forTokens items="${fourth}" delims="-" var="fo">
                                                    ${fo}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr class="tr">
					<td>Giải ba</td>
					<c:forTokens items="${lotterysMN.third}" delims="+" var="third">
						<td><c:forTokens items="${third}" delims="-" var="th">
                                                    ${th}<br />
							</c:forTokens></td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải nhì</td>
					<c:forTokens items="${lotterysMN.second}" delims="+" var="second">
						<td>${second}</td>
					</c:forTokens>
				</tr>
				<tr>
					<td>Giải nhất</td>
					<c:forTokens items="${lotterysMN.first}" delims="+" var="first">
						<td>${first}</td>
					</c:forTokens>
				</tr>
				<tr class="red tr">
					<td>Đặc biệt</td>
					<c:forTokens items="${lotterysMN.special}" delims="+" var="special">
						<td>${special}</td>
					</c:forTokens>
				</tr>
			</tbody>
		</table>
		
	</c:if>
</div>