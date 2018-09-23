<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>
<%@page import="java.net.URLDecoder"%>
<%@page import="inet.model.AdvertisingSiteDao"%>
<%@page import="inet.bean.AdvertisingSite"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.News"%>
<%@page import="inet.model.NewsDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%>
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
				String link = RequestUtil.getString(request, "link", "");
				String title = RequestUtil.getString(request, "title", "");
				int index = RequestUtil.getInt(request, "index", 1);

				if ("".equals(link)) {
					msg = "Vui lòng điền link!";
				}  else {
					AdvertisingSite advertisingSite = new AdvertisingSite();
					advertisingSite.setIndex(index);
					advertisingSite.setLink(link);
					advertisingSite.setTitle(title);

					if (new AdvertisingSiteDao().create(advertisingSite)) {
						msg = "<span style='color: green'>Tạo Link thành công!</span>";
					} else {
						msg = "Tạo quản trị viên thất bại!";
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
				<h1>Thêm mới Link quảng cáo</h1>
				<div class="form1">
					<div style="color: red"><%=msg%></div>
					<form method="post">
						<table class="table-auto">
							<tr>
								<td>Link</td>
								<td><input name="link" /></td>
							</tr>
							<tr>
								<td>Tiêu đề</td>
								<td><input name="title" /></td>
							</tr>
							<tr>
								<td>Vị trí quảng cáo</td>
								<td><select name="index">
										<option value="1">vị trí 1</option>
										<option value="2">vị trí 2</option>
										<option value="3">vị trí 3</option>
										<option value="0">vị trí footer</option>
										<option value="4">vị trí đại gia</option>
										<option value="5">vị trí dưới chơi nhiều</option>
								</select></td>
							</tr>
							<tr>
								<td><input type="hidden" name="action" value="create" />
									<button type="submit" class="btnCMS">Tạo mới</button></td>
								<td><a
									href="<%=request.getContextPath()%>/admin/quan-ly-link-quangcao.htm"
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