<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerContent5 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerContent5 = new BannerDAO().find("index", Banner.POSITION_CONTENT4);

	} else {
		bannerContent5 = new BannerDAO().find("other", Banner.POSITION_CONTENT4);

	}
%>

<div class="banner mb20" id="banner_hidden7">

	<%
		if (bannerContent5 != null) {
	%>
	<%=bannerContent5.getCode()%>
	<%
		}
	%>
</div>
