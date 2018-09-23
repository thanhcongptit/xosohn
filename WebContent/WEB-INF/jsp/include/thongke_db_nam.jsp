<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String data[][] = (String[][]) request.getAttribute("lotteryYear");
%>

<div class="table-small" id = "db_year">
	<table>
		<thead>
			<tr>
				<th>Ngày</th>
				<th>Th 1</th>
				<th>Th 2</th>
				<th>Th 3</th>
				<th>Th 4</th>
				<th>Th 5</th>
				<th>Th 6</th>
				<th>Th 7</th>
				<th>Th 8</th>
				<th>Th 9</th>
				<th>Th 10</th>
				<th>Th 11</th>
				<th>Th 12</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 1; i <= 31; i++) {

					out.print("<tr><td>");

					if (i < 10) {
						out.print("0" + i);
					} else {
						out.print(i);
					}

					out.print("</td>");
					for (int j = 1; j <= 12; j++) {
						out.print("<td>");

						if (null != data[i][j]) {
							out.print(data[i][j]);
						}

						out.print("</td>");
					}

					out.print("</tr>");
				}
			%>
		</tbody>
	</table>
</div>