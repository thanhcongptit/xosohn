<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.InputStream"%>

<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<%@page import="inet.util.EntityDecoder"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.Dream"%>
<%@page import="inet.model.News99DAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<%@page import="inet.bean.LotteryCompany"%>
<%@page import="inet.model.ProvinceDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý chuyên mục soi cầu 99</title>
<%@include file="../include/css-lib.jsp"%>
<%@include file="../include/js-lib.jsp"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/libraries/ckeditor/ckeditor.js"></script>

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
			
			$(document).ready(function() {
				setTimeout(function() {
					$(".mce-i-code").html("HTML");
					$(".mce-i-code").css("font-size", "10px");
					$(".mce-i-code").css("font-weight", "bold");
					$(".mce-i-code").parent().css("padding-right", "16px");
				}, 500);
			});
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
			String action = "";
			String fileName = "";
			String titleSeo = "";
			String metaSeo = "";
			String keyword = "";
			
			String title = RequestUtil.getString(request, "title", "");
			//String imageURL = RequestUtil.getString(request, "imageURL", "");
			String desc = RequestUtil.getString(request, "desc", "");
			//String content = RequestUtil.getString(request, "content", "");
			String content = request.getParameter("content");

			try {
				ServletFileUpload uploader = null;
				DiskFileItemFactory fileFactory = new DiskFileItemFactory();
				File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE5");
				fileFactory.setRepository(filesDir);
				uploader = new ServletFileUpload(fileFactory);

				List<FileItem> fileItemsList = uploader.parseRequest(request);
				Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
				while (fileItemsIterator.hasNext()) {
					FileItem fileItem = fileItemsIterator.next();

					if (fileItem.isFormField()) {
						String name = fileItem.getFieldName();
						String value = fileItem.getString("UTF-8");

						if (name.equalsIgnoreCase("action")) {
							action = value;
						} else if (name.equalsIgnoreCase("title")) {
							title = value;
						} else if (name.equalsIgnoreCase("desc")) {
							desc = value;
						} else if (name.equalsIgnoreCase("content")) {
							content = value;
						} else if (name.equalsIgnoreCase("titleSeo")) {
							titleSeo = value;
						} else if (name.equalsIgnoreCase("metaSeo")) {
							metaSeo = value;
						} else if (name.equalsIgnoreCase("keyword")) {
							keyword = value;
						}

					} else {
						Date date = new Date();
						String currentTime = DateUtil.date2String(date, "dd/MM/YYYY hh:mm:ss");
						String temps[] = currentTime.split(" ");
						
						String folder = temps[0];
						String folders[] = folder.split("/");
						String folderString = folders[2]+"/"+ folders[1] + "/" + folders[0]+ "/";
						
						currentTime = temps[1].replace(":", "_");

						fileName = folderString + currentTime + "_" + fileItem.getName();
						
						if ("create".equals(action)) {
							
							File file1 = new File(
									request.getServletContext().getAttribute("FILES_DIR5") + File.separator + folderString);
							
							if (!file1.exists()) {
								file1.mkdirs();
							}
							
							File file = new File(
									request.getServletContext().getAttribute("FILES_DIR5") + File.separator  + fileName);
							fileItem.write(file);
						}
						
					}

				}
			} catch (FileUploadException e) {
				//e.printStackTrace();
			} catch (Exception e) {
				//e.printStackTrace();
			}


			if ("create".equals(action)) {

				if (CommonUtil.isEmptyString(content)) {
					content = "";
				}

				if ("".equals(title)) {
					msg = "Vui lòng điền tiêu đề!";
				} else {
					
					Dream news = new Dream();
					news.setTitle(EntityDecoder.convertHtmlEntityToChar(title));
					news.setImageUrl(fileName);
					news.setDescription(EntityDecoder.convertHtmlEntityToChar(desc));
					news.setContent(EntityDecoder.convertHtmlEntityToChar(content));
					news.setStatus(1);
					news.setMetaSeo(metaSeo);
					news.setTitleSeo(titleSeo);
					news.setKeyword(keyword);
					
					News99DAO newsDAO = new News99DAO();
					if (newsDAO.create(news)) {
						msg = "<span style='color:green'>Tạo tin bài thành công!</span>";
					} else {
						msg = "Tạo tin bài thất bại!";
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
				<h1>Thêm mới chuyên mục soi cầu 99</h1>
				<div class="form1">
					<div style="color: red"><%=msg%></div>
					<form method="post" enctype="multipart/form-data" accept-charset="UTF-8">
						<table class="table-auto">
							<tr>
								<input type="hidden" name="action" value="create" />
								<td>Tiêu đề</td>
								<td><input name="title" style="width: 100%"/> (tối đa 150 ký tự)</td>
							</tr>
							
							<tr>
								<input type="hidden" name="action" value="create" />
								<td>Tiêu đề SEO</td>
								<td><input name="titleSeo" style="width: 100%"/> (tối đa 150 ký tự)</td>
							</tr>
							
							<tr>
								<td>Mô tả</td>
								<td><textarea
										style="border: solid 1px #0b9444;min-width: 100%;  padding: 5px;" 
										name="desc" rows="10" cols="35"></textarea> (tối đa 200 ký tự)
								</td>
							</tr>
							
							<tr>
								<td>Meta SEO</td>
								<td><textarea
										style="border: solid 1px #0b9444; min-width: 100%; padding: 5px;" 
										name="metaSeo" rows="10" cols="35"></textarea> (tối đa 200 ký tự)
								</td>
							</tr>
							
							<tr>
								<td>Keyword</td>
								<td><textarea
										style="border: solid 1px #0b9444; min-width: 100%; padding: 5px;" 
										name="keyword" rows="10" cols="35"></textarea> (tối đa 200 ký tự)
								</td>
							</tr>
							
							<tr>
								<td>Nội dung</td>
								<td><textarea id = "editor" name="content" width="100%"></textarea></td>
							</tr>
							
							<tr>
								<td>Ảnh đại diện</td>
								<td><input required="required" type="file" name="fileName" accept=".jpg, .jpeg, .png, .gif"> <br /> <!-- <br />(vd: http://domain.com/upload/thumb-nail.jpg)</td> -->
							</tr>
							<tr>
								<td>
									<button type="submit" class="btnCMS">Tạo mới</button></td>
								<td><a
									href="<%=request.getContextPath()%>/admin/news_99/index.htm"
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
	var editor =CKEDITOR.replace( 'editor',{
		filebrowserBrowseUrl: '/libraries/filemanager/index.html'
	});
	
</script>

</html>