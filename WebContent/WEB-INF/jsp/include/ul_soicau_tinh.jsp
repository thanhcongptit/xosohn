<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<p>
										<strong> *Bấm vào số trong danh sách lô tô để xem
											thống kê cụ thể </strong>
									</p>
									<p>*Cặp số màu đỏ chị cặp lô tô đã về, cặp số màu xanh chỉ
										vị trí thống kê.</p>
									<p>*Lô tô sẽ hiển thị trong danh sách kết quả xổ số dưới
										đây, bạn cũng có thể click chuột vào danh sách này để tự thành
										lập cho lựa chọn của mình</p>
<ul class="list-parent-item">
	<c:set var="link" value="${company.codeLowerCase}-ket-qua-xo-so-${company.linkKq247}.html" />
	<li><a href="/${link}">${fn:toUpperCase(company.code)}</a></li>
	<li><a href="${pageContext.servletContext.contextPath}/soi-cau-${fn:toLowerCase(company.companyLink)}-${fn:toLowerCase(company.code)}.html">Soi cầu bạch thủ</a></li>
	<li><a href="${pageContext.servletContext.contextPath}/thong-ke-lo-gan-${fn:toLowerCase(company.companyLink)}-${fn:toLowerCase(company.code)}.html">Lô gan ${company.company}</a></li>
</ul>