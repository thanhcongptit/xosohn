<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.bean.AdvertisingSite"%>
<%@page import="inet.model.AdvertisingSiteDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="inet.bean.Banner"%>
<%@page import="inet.model.BannerDAO"%>
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
<title>Quản lý Link</title>
<%@include file="../include/css-lib.jsp"%>
<%@include file="../include/js-lib.jsp"%>
</head>
<body>
	<div class="container">
		<%@include file="../include/header.jsp"%>
		<script>
                function updateStatus(status,id){                    
                    $("#updateForm input[name=action]").val("updateStatus");
                    $("#updateForm input[name=id]").val(id);
                    $("#updateForm input[name=status]").val(status);
                    $("#updateForm").submit();
                }
                function deleteNews(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa Link này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
                function show(trang){                    
                    $("#updateForm input[name=show]").val(trang);
                    $("#updateForm").submit();
                }
            </script>
		<%
			String action = RequestUtil.getString(request, "action", "");
			if ("updateStatus".equals(action)) {
				BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
				int status = RequestUtil.getInt(request, "status", 0);
				NewsDAO newsDAO = new NewsDAO();
				if (newsDAO.updateStatus(status, id)) {
		%>
		<script>alert("Cập nhật thành công!");</script>
		<%
			} else {
		%>
		<script>alert("Cập nhật thất bại!");</script>
		<%
			}
			} else if ("delete".equals(action)) {
				int id = RequestUtil.getInt(request, "id", null);
				AdvertisingSiteDao aSiteDao = new AdvertisingSiteDao();
				if (aSiteDao.deleteLink(id)) {
		%>
		<script>alert("Xóa thành công!");</script>
		<%
			} else {
		%>
		<script>alert("Xóa thất bại!");</script>
		<%
			}
			}
		%>
		<%
			AdvertisingSiteDao aSiteDao = new AdvertisingSiteDao();
			List<AdvertisingSite> listAdvertisingSites = aSiteDao.getAvertisingSite();
		%>
		<form style="display: none" id="updateForm" method="get">
			<input type="hidden" name="action" /> <input type="hidden" name="id" />
			<input type="hidden" name="status" />
		</form>
		</form>
		<div class="content">
			<div class="formWrapper">
				<h1>Quản lý link</h1>
				<div style="margin-bottom: 5px;">
					<a class="btnCMS"
						href="<%=request.getContextPath()%>/admin/link/create.htm">Thêm
						mới</a>
				</div>
				<%-- <div style="margin-bottom: 5px;">
                        <select name="trang" onchange="show($(this).val());">
                            <option value="index" <%= "index".equals(trang) ? "selected" : "" %> >Trang chủ</option>
                            <option value="other" <%= "other".equals(trang) ? "selected" : "" %>>Các trang còn lại</option>
                        </select>
                        <div class="clearfix" style="height: 20px;"></div>
                    </div> --%>
				<div class="form1">
					<%
						if (listAdvertisingSites != null) {
					%>
					<table class="info" style="width: 100%">
						<tr>
							<td>STT</td>
							<td>Tiêu đề</td>
							<td>Link</td>
							<td>Vị trí</td>
							<td>Hành động</td>
						</tr>
						<%
							for (int j = 0; j < listAdvertisingSites.size(); j++) {
									AdvertisingSite advertisingSite = listAdvertisingSites.get(j);
						%>
						<tr>
							<td><%=j + 1%></td>
							<td style="max-width: 600px;"><c:out
									value="<%=advertisingSite.getTitle()%>" /></td>
							<td><%=advertisingSite.getLink()%></td>
							<td>
								<%
									if (advertisingSite.getIndex() == 0) {
												out.print("vị trí footer");
											} else if(advertisingSite.getIndex() == 4) {
												out.print("vị trí đại gia");
											} else if(advertisingSite.getIndex() == 5) {
												out.print("vị trí dưới chơi nhiều");
											} else {
												out.print(advertisingSite.getIndex());
											}
								%>
							</td>
							<td><a onclick="deleteNews(<%=advertisingSite.getId()%>)">Xóa</a> </td>
							
						</tr>
						<%
							}
						%>
					</table>
					<%
						}
					%>
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