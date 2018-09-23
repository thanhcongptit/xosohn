<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="com.dao.DuDoanXoSoDao"%>
<%@page import="inet.bean.DuDoanXoso"%>
<%@page import="com.dao.AdvertisingSiteDao"%>
<%@page import="com.bean.AdvertisingSite"%>
<%@page import="inet.model.AdminDAO"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.ThongKeVip"%>
<%@page import="inet.model.ThongKeVipDAO"%>
<%@page import="inet.bean.News"%>
<%@page import="inet.model.NewsDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.model.MemberDAO"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý admin</title>
<%@include file="../include/css-lib.jsp"%>
<%@include file="../include/js-lib.jsp"%>
</head>
<body>
	<div class="container">
		<%@include file="../include/header.jsp"%>
		<style>
.error {
	color: red
}

.ok {
	color: green
}
</style>
		<script>
			function updateStatus(status, id) {
				$("#updateForm input[name=action]").val("updateStatus");
				$("#updateForm input[name=id]").val(id);
				$("#updateForm input[name=status]").val(status);
				$("#updateForm").submit();
			}
			function deleteTKV(id) {
				var c = confirm('Bạn có chắc chắn muốn xóa dự đoán này?');
				if (c) {
					$("#updateForm input[name=action]").val("delete");
					$("#updateForm input[name=id]").val(id);
					$("#updateForm").submit();
				}
			}
			$(function() {
				$("input[name=genDate]").datepicker();
			});
		</script>
		<%
			String msg = "";
			String action = RequestUtil.getString(request, "action", "");
			if ("create".equals(action)) {
				String dayso = RequestUtil.getString(request, "dayso", "");
				int xu = RequestUtil.getInt(request, "xu", 1);
				int index = RequestUtil.getInt(request, "vitri", 1);

				if ("".equals(dayso)) {
					msg = "Vui lòng cặp số hôm nay!";
				}  else {
					DuDoanXoso duDoanXoso = new DuDoanXoso();
					duDoanXoso.setDayso(dayso);
					duDoanXoso.setXu(xu);
					duDoanXoso.setVitri(index);

					if (new DuDoanXoSoDao().create(duDoanXoso)) {
						msg = "<span style='color: green'>Tạo dự đoán xổ số thành công!</span>";
					} else {
						msg = "Tạo dự đoán xổ số thất bại!";
					}
				}
			}
		%>
		<form style="display: none" id="updateForm" method="post">
			<input type="hidden" name="action" /> <input type="hidden" name="id" />
			<input type="hidden" name="status" />
		</form>
		<div class="content">
			<div class="formWrapper">
				<h1>Thêm mới dự đoán xổ số hôm nay</h1>
				<div class="form1">
					<div style="color: red"><%=msg%></div>
					<form method="post">
						<table class="table-auto">
							<tr>
								<td>Dãy số</td>
								<td><input name="dayso" /></td>
							</tr>
							
							<tr>
								<td>Xu</td>
								<td><input name="xu" /></td>
							</tr>
							<tr>
								<td>Vị trí quảng cáo</td>
								<td><select name="vitri">
										<option value="1">vị trí 1</option>
										<option value="2">vị trí 2</option>
								</select></td>
							</tr>
							<tr>
								<td><input type="hidden" name="action" value="create" />
									<button type="submit" class="btnCMS">Tạo mới</button></td>
								<td><a
									href="<%=request.getContextPath()%>/admin/quan-ly-du-doan-xoso.htm"
									class="btnCMS">Trở về</a></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!--end form-->
		</div>
		<!--end ndung-->
	</div>
	<!--end content-->
	<%@include file="../include/footer.jsp"%>
</body>
</html>