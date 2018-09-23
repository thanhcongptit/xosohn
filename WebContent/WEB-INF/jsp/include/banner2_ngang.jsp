<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerContent1 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerContent1 = new BannerDAO().find("index", Banner.POSITION_CONTENT);

	} else {
		bannerContent1 = new BannerDAO().find("other", Banner.POSITION_CONTENT);

	}
%>


<div class="banner mb20" id="banner_hidden3">
<%
	if (bannerContent1 != null) {
%>
	<%=bannerContent1.getCode()%>
<%
	}
%>
</div>
