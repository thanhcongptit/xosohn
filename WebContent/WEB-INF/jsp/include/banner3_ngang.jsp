<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerContent2 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerContent2 = new BannerDAO().find("index", Banner.POSITION_CONTENT1);

	} else {
		bannerContent2 = new BannerDAO().find("other", Banner.POSITION_CONTENT1);

	}
%>


<div class="banner mb20" id="banner_hidden4">
	<%
		if (bannerContent2 != null) {
	%>
	<%=bannerContent2.getCode()%>
	<%
		}
	%>
</div>
