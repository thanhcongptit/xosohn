<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<%@page import="inet.util.EntityDecoder"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.Dream"%>
<%@page import="inet.model.NewsSCMBDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.InputStream"%>

<%@page import="inet.bean.LotteryCompany"%>
<%@page import="inet.model.ProvinceDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý chuyên mục soi cầu mb</title>
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
			
			BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
			
			ProvinceDao provinceDao = new ProvinceDao();
			List<LotteryCompany> provinces = provinceDao.findLotteryCompany();

			try {
				ServletFileUpload uploader = null;
				DiskFileItemFactory fileFactory = new DiskFileItemFactory();
				File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE6");
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
						}  else if (name.equalsIgnoreCase("id")) {
							id = new BigDecimal(value);
						} else if (name.equalsIgnoreCase("titleSeo")) {
							titleSeo = value;
						} else if (name.equalsIgnoreCase("metaSeo")) {
							metaSeo = value;
						}
						else if (name.equalsIgnoreCase("keyword")) {
							keyword = value;
						}

					} else {
						Dream newsUpdate = null;
						if (id != null) {
							NewsSCMBDAO newsDAO = new NewsSCMBDAO();
							newsUpdate = newsDAO.getRow(id);
						}
						
						String oldImage = newsUpdate.getImageUrl();
						
						if(!oldImage.endsWith(fileItem.getName()) && !CommonUtil.isEmptyString(fileItem.getName())) {
							Date date = new Date();
							String currentTime = DateUtil.date2String(date, "dd/MM/YYYY hh:mm:ss");
							String temps[] = currentTime.split(" ");
							
							String folder = temps[0];
							String folders[] = folder.split("/");
							String folderString = folders[2]+"/"+ folders[1] + "/" + folders[0]+ "/";
							
							currentTime = temps[1].replace(":", "_");

							fileName = folderString + currentTime + "_" + fileItem.getName();

							if ("update".equals(action)) {
								File fileRemove = new File(
										request.getServletContext().getAttribute("FILES_DIR6") + File.separator + oldImage);
								if(fileRemove.exists()) {
									fileRemove.delete();
								}
								
								File file1 = new File(
										request.getServletContext().getAttribute("FILES_DIR6") + File.separator + folderString);
								
								if (!file1.exists()) {
									file1.mkdirs();
								}
								
								File file = new File(
										request.getServletContext().getAttribute("FILES_DIR6") + File.separator  + fileName);
								fileItem.write(file);
							}
						} else {
							fileName = oldImage;
						}
						
					} 

				}
			} catch (FileUploadException e) {
			} catch (Exception e) {
			}
			
			if ("update".equals(action) && id != null) {
				
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
					news.setTitleSeo(titleSeo);
					news.setMetaSeo(metaSeo);
					news.setKeyword(keyword);
					news.setId(id);
					NewsSCMBDAO newsDAO = new NewsSCMBDAO();
					if (newsDAO.update(news)) {
						msg = "<span style='color:green'>Cập nhật tin bài thành công!</span>";
					} else {
						msg = "Cập nhật tin bài thất bại!";
					}
				}
			}

			Dream newsUpdate = null;
			if (id != null) {
				NewsSCMBDAO newsDAO = new NewsSCMBDAO();
				newsUpdate = newsDAO.getRow(id);
			}
		%>
		
		<form style="display: none" id="updateForm" method="post">
			<input type="hidden" name="action" /> <input type="hidden" name="id" />
			<input type="hidden" name="status" />
		</form>
		<div class="content">
			<div class="formWrapper">
				<h1>Cập nhật chuyên mục soi cầu mb</h1>
				<div class="form1">
					<div style="color: red"><%=msg%></div>
					<%
						if (newsUpdate != null) {
					%>
					<form method="post" enctype="multipart/form-data" accept-charset="UTF-8">
						<table class="table-auto">
							<input type="hidden" name="id" value="<%=id %>" />
							<input type="hidden" name="action" value="update" />
							<tr>
								<td>Tiêu đề</td>
								<td><input name="title" style="width: 100%"
									value="<%=newsUpdate.getTitle()%>" /> (tối đa 150 ký tự)</td>
							</tr>
							
							<tr>
								<td>Tiêu đề SEO</td>
								<td><input name="titleSeo" value="<%=newsUpdate.getTitleSeo()%>" style="width: 100%"/> (tối đa 150 ký tự)</td>
							</tr>
							
							<tr>
								<td>Mô tả</td>
								<td><textarea
										style="border: solid 1px #0b9444;min-width: 100%; padding: 5px;" 
										name="desc" rows="10" cols="35"><%=newsUpdate.getDescription()%></textarea>
									(tối đa 200 ký tự)</td>
							</tr>
							
							<tr>
								<td>Meta SEO</td>
								<td><textarea
										style="border: solid 1px #0b9444;min-width: 100%;  padding: 5px;" 
										name="metaSeo" rows="10" cols="35"> <%=newsUpdate.getMetaSeo()%></textarea> (tối đa 200 ký tự)
								</td>
							</tr>
							
							<tr>
								<td>Keyword</td>
								<td><textarea
										style="border: solid 1px #0b9444;min-width: 100%; padding: 5px;" 
										name="keyword" rows="10" cols="35"> <%=newsUpdate.getKeyword() %></textarea> (tối đa 200 ký tự)
								</td>
							</tr>
							
							<tr>
								<td>Nội dung</td>
								<td><textarea width="100%" id="editor" name="content"><%=newsUpdate.getContent()%></textarea>
								</td>
							</tr>
							
							<tr>
								<td>Ảnh đại diện</td>
								<%
									String oldImageFile = newsUpdate.getImageUrl();
									String filePath = request.getServletContext().getAttribute("FILES_DIR6") + File.separator + oldImageFile;
									File f = new File(filePath);
									
								%>
								<td><input type="file" name="fileName" value="<%=f%>" accept=".jpg, .jpeg, .png, .gif"> <br /> <!-- <br />(vd: http://domain.com/upload/thumb-nail.jpg)</td> -->
							</tr>
							<tr>
								<td>
									<button type="submit" class="btnCMS">Cập nhật</button></td>
								<td><a
									href="<%=request.getContextPath()%>/admin/news_scmb/index.htm"
									class="btnCMS">Trở về</a></td>
							</tr>
						</table>
					</form>
					<%
						} else
							out.print("Tin bài này không tồn tại!");
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

<script type="text/javascript">
	var editor =CKEDITOR.replace( 'editor',{
		filebrowserBrowseUrl: '/libraries/filemanager/index.html'
	});
	
</script>
</html>