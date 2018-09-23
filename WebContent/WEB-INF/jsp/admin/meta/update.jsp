<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.bean.MetaGuide"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý title, meta, guide, text</title>
<%@include file="../include/css-lib.jsp"%>
<%@include file="../include/js-lib.jsp"%>

<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/libraries/ckeditor/ckeditor.js"></script>

<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/libraries/ckfinder/ckfinder.js"></script>
</head>
<body>
	<script>
		$(function() {
			$("input[name=genDate]").datepicker({
				inline : true,
				dateFormat : 'dd/mm/yy'
			});
		});
	</script>
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
		<%
			String position = RequestUtil.getString(request, "id", "");
			MetaGuideDao metaGuideDao = new MetaGuideDao();
			MetaGuide metaGuide = metaGuideDao.findByPosition(Integer.parseInt(position));
			String msg = "";
			String action = RequestUtil.getString(request, "action", "");
			if ("update".equals(action)) {
				position = RequestUtil.getString(request, "id", "");
				String title = RequestUtil.getString(request, "title", "");
				String meta = RequestUtil.getString(request, "meta", "");
				String keyword = RequestUtil.getString(request, "keyword", "");
				//String guide = request.getParameter("guide");
				String footer = request.getParameter("footer");

				metaGuide.setFooter(footer);
				metaGuide.setTitle(title);
				metaGuide.setMeta(meta);
				metaGuide.setKeyword(keyword);

				if (metaGuideDao.update(metaGuide, Integer.parseInt(position))) {
					msg = "<span class='ok'>Cập nhật thành công!</span>";
				} else {
					msg = "<span class='error'>Cập nhật thất bại!</span>";
				}
			}
		%>
		<div class="content">
			<div class="formWrapper">
				<h1>Quan ly meta, title</h1>
				<div class="form1">
					<div><%=msg%></div>
					<form method="post">
						<table class="table-auto">
							<tr>
								<td>Page</td>
								<td><%=metaGuide.getPage()%></td>
							</tr>

							<tr>
								<td>Link</td>
								<td><%=metaGuide.getGuide()%></td>
							</tr>
							
							

							<tr>
								<td>Title</td>
								<td><textarea
										style="border: solid 1px #0b9444; min-width: 143px; padding: 5px;"
										name="title" cols="80" rows="10"><%=metaGuide.getTitle()%></textarea>
								</td>
							</tr>

							<tr>
								<td>Meta</td>
								<td><textarea
										style="border: solid 1px #0b9444; min-width: 143px; padding: 5px;"
										name="meta" cols="80" rows="10"><%=metaGuide.getMeta()%></textarea>
								</td>
							</tr>
							
							<tr>
								<td>Keyword</td>
								<td><textarea
										style="border: solid 1px #0b9444; min-width: 143px; padding: 5px;"
										name="keyword" cols="80" rows="10"><%=metaGuide.getKeyword()%></textarea>
								</td>
							</tr>

							<%-- <tr>
                                    <td>Hướng dẫn</td>
                                    <td>
                                        <textarea id="guide" name="guide"><%=metaGuide.getGuide() %></textarea>
                                    </td>
                                </tr> --%>
							<br />
							<tr>
								<td>Text</td>
								<td><textarea id="footer" name="footer"><%=metaGuide.getFooter()%></textarea>
								</td>
							</tr>

							<tr>
								<td><input type="hidden" name="id" value="<%=position%>" />
									<input type="hidden" name="action" value="update" />
									<button type="submit" class="btnCMS">Update</button></td>
								<td><a
									href="<%=request.getContextPath()%>/admin/meta/index.htm"
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

<script type="text/javascript">
	var editor = CKEDITOR.replace( 'footer');
</script>
</html>