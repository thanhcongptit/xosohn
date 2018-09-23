<%@page import="inet.bean.Lottery"%>
<%@page import="inet.bean.CauDetailBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="inet.bean.SoicauDetailBean"%>
<%@page import="inet.bean.SoicauBean"%>
<%@page import="java.util.List"%>
<div
	class="page table-item txt-heading mb20 icon-list parent-list page-none">
	<h2>
		<span class="iconxs">Xổ số ${company.company } </span>
		<span class="iconxs"><a href="${pageContext.servletContext.contextPath}/soi-cau-${company.companyLink}-${company.codeLowerCase}.html">Soi cầu
		${company.company }</a></span>
	</h2>


	<%
		SoicauBean soicauBean = (SoicauBean) request.getAttribute("soicauBean");
		List<SoicauDetailBean> soicauDetailBeans = soicauBean.getSoicauDetailBeans();

		List<Lottery> lotteries = (List<Lottery>) request.getAttribute("lotteryResults");
		List<List<String>> listLotterysDuoiMB = (List<List<String>>) request.getAttribute("duoiResults");
		List<List<String>> listLotterysDauMB = (List<List<String>>) request.getAttribute("dauResults");
		String openDate = (String) request.getAttribute("startDate");
	%>
	<input type="hidden" name="openDate" value="<%=openDate%>" />

	<div class="box-item">
		<div class="ttl-erro">
			<strong class="red"><i>*Bấm vào trong danh sách lô tô và
					kéo xuống để xem danh sách cụ thể</i></strong>
		</div>
		<div class="list-box">
			<%
				int i = 1;
				if (soicauDetailBeans.size() > 3) {
					i = 2;
				}

				for (int index = soicauDetailBeans.size() - 1; index >= i; index--) {
					SoicauDetailBean soicauDetailBean = soicauDetailBeans.get(index);
					List<CauDetailBean> cauDetailBeans = soicauDetailBean.getDetailCauList();
			%>
			
				<%if(cauDetailBeans.size() > 0)  {%>
				<p>
					<strong>*Biên độ <%=soicauDetailBean.getSongay()%> ngày
					</strong>
				</p>
				<ul class="list-item clearfix">
					<%
						for (int z = 0; z < cauDetailBeans.size(); z++) {
								CauDetailBean cauDetailBean = cauDetailBeans.get(z);
					%>
					<li><a onclick="callServer(<%=index%> , <%=z%>)"
						class="pointer"><%=cauDetailBean.getValue()%></a></li>
					<%
						}
					%>
	
				</ul>
			     <%
				   }
				}
			%>

			<%@include file="ul_soicau_tinh.jsp"%>
		</div>
	</div>
</div>
<div id="soicau">

	<%
		for (int j = 0; j < lotteries.size(); j++) {

			Lottery lottery = lotteries.get(j);
			List<String> dauMB = listLotterysDauMB.get(j);
			List<String> duoiMB = listLotterysDuoiMB.get(j);
	%>
	<div class="table-item txt-heading mb20">
		<%
			if (j == 0) {
		%>
		<h2>Chi tiết cầu đã chọn</h2>
		<%
			}
		%>
		<table>
			<tbody>
				<tr>
					<td colspan="13" class="text-left"><%=(j + 1)%>- Mở thưởng
						ngày <%=lottery.getOpenDate()%></td>
				</tr>

				<tr class="tr">
					<th>Giải tám</th>
					<td colspan="12"><span><%=lottery.getEighth()%></span></td>
				</tr>
				<tr>
					<th>Giải bảy</th>
					<td colspan="12"><%=lottery.getSeventh()%></td>
				</tr>

				<tr class="tr">
					<th>Giải sáu</th>
					<%
						String sixths[] = lottery.getSixth().split("-");
							for (String sixth : sixths) {
					%>
					<td colspan="4"><%=sixth%></td>
					<%
						}
					%>
				</tr>

				<tr>
					<th>Giải năm</th>
					<td colspan="12"><%=lottery.getFifth()%></td>
				</tr>

				<tr>
					<th rowspan="2">Giải bốn</th>
					<%
						String fourths[] = lottery.getFourth().split("-");
							for (int z = 0; z <= 3; z++) {
					%>
					<td colspan="3"><%=fourths[z]%></td>
					<%
						}
					%>
				</tr>
				<tr>
					<%
						for (int z = 4; z <= 6; z++) {
					%>
					<td colspan="4"><%=fourths[z]%></td>
					<%
						}
					%>
				</tr>

				<tr class="tr">
					<th>Giải ba</th>
					<%
						String thirds[] = lottery.getThird().split("-");
							for (String third : thirds) {
					%>
					<td colspan="6"><%=third%></td>
					<%
						}
					%>

				</tr>
				<tr>
					<th>Giải nhì</th>
					<td colspan="12"><%=lottery.getSecond()%></td>
				</tr>
				<tr>
					<th>Giải nhất</th>
					<td colspan="12"><%=lottery.getFirst()%></td>
				</tr>
				<tr class="tr">
					<th>GĐB</th>
					<td colspan="12"><span><%=lottery.getSpecial()%></span></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="table-oder mb20">
		<span class="heading">Bảng loto xổ số ${company.company}</span>
		<div class="row">
			<div class="col-xs-6">
				<table>
					<tbody>

						<tr>
							<td>Đầu</td>
							<td>Loto</td>
						</tr>
						<%
							for (int z = 0; z < 10; z++) {
						%>
						<tr>
							<td><%=(z)%></td>
							<td><%=duoiMB.get(z)%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-xs-6">
				<table>
					<tbody>
						<tr>
							<td>Đuôi</td>
							<td>Loto</td>
						</tr>
						<%
							for (int z = 0; z < 10; z++) {
						%>
						<tr>
							<td><%=(z)%></td>
							<td><%=dauMB.get(z)%></td>
						</tr>
						<%
							}
						%>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%
		}
	%>


</div>