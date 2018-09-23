<%@page import="inet.bean.Lottery"%>
<%@page import="java.util.List"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	List<Lottery> lotteriesMonth = (List<Lottery>) request.getAttribute("lotteryMonth");
	Collections.reverse(lotteriesMonth);
%>
<div id="db_thang">
	<table>
		<thead>
			<tr>
				<th>Thứ 2</th>
				<th>Thứ 3</th>
				<th>Thứ 4</th>
				<th>Thứ 5</th>
				<th>Thứ 6</th>
				<th>Thứ 7</th>
				<th>C.nhật</th>
			</tr>
		</thead>
		<tbody>
			<%
				int indexBDB = 0;
				for (int i = 0; i < 4; i++) {
			%>
			<tr>
				<%
					for (int j = 0; j < 7; j++) {
							if(indexBDB < lotteriesMonth.size()) {
								
								Lottery lottery = lotteriesMonth.get(indexBDB);
								int dayOfWeek = DateUtil.getWeekFromDateForDB(lottery.getOpenDate());
								if (dayOfWeek == j ) {
									indexBDB++;
									String special = lottery.getSpecial();
									String first = special.substring(0, 3);
									String second = special.substring(3);
					%>
									<td><%=first%><span class="txt_red"><%=second%></span></td>
					<%
								} else {
					%>
									<td></td>
					<%
								}
							} else {
				%>           <!-- cho truong hop khong co du lieu duoc hien thi -->
								<td style="height: 27px;"></td>
				<%
							}
					}		
				%>
			</tr>
			<%
				}
				if (indexBDB < lotteriesMonth.size() - 1) {

					for (int j = 0; j < 7; j++) {
						Lottery lottery = lotteriesMonth.get(indexBDB);
						int dayOfWeek = DateUtil.getWeekFromDateForDB(lottery.getOpenDate());
						if (dayOfWeek == j) {

							if (indexBDB < lotteriesMonth.size() - 1) {
								indexBDB++;
							}
							String special = lottery.getSpecial();
							String first = special.substring(0, 3);
							String second = special.substring(3);
			%>
			<td><%=first%><span class="txt_red"><%=second%></span></td>
			<%
				} else {
			%>
			<td></td>
			<%
				}
			%>
			<%
				}
			%>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>

</div>