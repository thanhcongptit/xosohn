<%@page import="inet.util.DateUtil"%>
<%@page import="inet.util.DatePro"%>
<%@page import="inet.util.DateProc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.bean.Lottery"%>
<%@page import="java.util.*" %>	

<%
	List<Lottery> listLoteryDB = (List<Lottery>) request.getAttribute("bdb");
	Collections.reverse(listLoteryDB);
%>	
<div class="page page-none txt-heading mb20">
	<h2>THỐNG KÊ GIẢI ĐẶC BIỆT</h2>
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
				for(int i=0; i< 10; i++) {
			
			%>
				<tr>
					<%
					  for(int j = 0; j< 7; j++) {
						Lottery lottery = listLoteryDB.get(indexBDB);
						int dayOfWeek = DateUtil.getWeekFromDateForDB(lottery.getOpenDate());
						if(dayOfWeek == j) {
						   indexBDB ++;		
					%>
						<td>
							<%=lottery.getSpecial() %>
						</td>
						<%} else { %>
							<td></td>
						<%} %>
					<% }%>
				</tr>
			<%} 
				if(indexBDB < listLoteryDB.size() - 1) {
					
					  for(int j = 0; j< 7; j++) {
						Lottery lottery = listLoteryDB.get(indexBDB);
						int dayOfWeek = DateUtil.getWeekFromDateForDB(lottery.getOpenDate());
						if(dayOfWeek == j) {
						   
						   if(indexBDB < listLoteryDB.size() - 1) {
							   indexBDB ++;	
						   }
					%>
						<td>
							<%=lottery.getSpecial() %>
						</td>
						<%} else { %>
							<td></td>
						<%} %>
					<% }%>
				</tr>
				<%
				}
			%>
			
		</tbody>
	</table>
</div>