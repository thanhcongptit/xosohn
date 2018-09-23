<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerContent6 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerContent6 = new BannerDAO().find("index", Banner.POSITION_CONTENT5);

	} else {
		bannerContent6 = new BannerDAO().find("other", Banner.POSITION_CONTENT5);

	}
%>


<div class="banner mb20" id="banner_hidden8">
	<%
		if (bannerContent6 != null) {
	%>
	<%=bannerContent6.getCode()%>
	<%
		}
	%>
</div>
