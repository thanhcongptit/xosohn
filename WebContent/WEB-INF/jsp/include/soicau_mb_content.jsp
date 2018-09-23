<%@page import="inet.bean.Lottery"%>
<%@page import="inet.bean.CauDetailBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="inet.bean.SoicauDetailBean"%>
<%@page import="inet.bean.SoicauBean"%>
<%@page import="java.util.List"%>
<div
	class="page table-item txt-heading mb20 icon-list parent-list page-none">
	<h1>
		<span class="iconxs">Xổ số miền bắc</span> ${text_soicau}
	</h1>
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
				}}
			%>

			<%@include file="ul_soicau.jsp"%>
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
					<th>GĐB</th>
					<td colspan="12"><span><%=lottery.getSpecial()%></span></td>
				</tr>
				<tr>
					<th>Giải Nhất</th>
					<td colspan="12"><%=lottery.getFirst()%></td>
				</tr>
				<tr class="tr">
					<th>Giải Nhì</th>
					<%
						String seconds[] = lottery.getSecond().split("-");
							for (String second : seconds) {
					%>
					<td colspan="6"><%=second%></td>
					<%
						}
					%>

				</tr>
				<tr>
					<th rowspan="2">Giải Ba</th>
					<%
						String thirds[] = lottery.getThird().split("-");
							for (int z = 0; z <= 2; z++) {
					%>
					<td colspan="4"><%=thirds[z]%></td>
					<%
						}
					%>
				</tr>
				<tr>
					<%
						for (int z = 3; z <= 5; z++) {
					%>
					<td colspan="4"><%=thirds[z]%></td>
					<%
						}
					%>
				</tr>
				<tr class="tr">
					<th>Giải Tư</th>
					<%
						String fourths[] = lottery.getFourth().split("-");
							for (String fourth : fourths) {
					%>
					<td colspan="3"><%=fourth%></td>
					<%
						}
					%>

				</tr>
				<tr>
					<th rowspan="2">Giải Năm</th>
					<%
						String fifths[] = lottery.getFifth().split("-");
							for (int z = 0; z <= 2; z++) {
					%>
					<td colspan="4"><%=fifths[z]%></td>
					<%
						}
					%>
				</tr>
				<tr>
					<%
						for (int z = 3; z <= 5; z++) {
					%>
					<td colspan="4"><%=fifths[z]%></td>
					<%
						}
					%>
				</tr>
				<tr class="tr">
					<th>Giải Sáu</th>
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
					<th>Giải Bảy</th>
					<%
						String sevenths[] = lottery.getSeventh().split("-");
							for (String seventh : sevenths) {
					%>
					<td colspan="3"><%=seventh%></td>
					<%
						}
					%>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="table-oder mb20">
		<span class="heading">Bảng loto xổ số miền bắc</span>
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