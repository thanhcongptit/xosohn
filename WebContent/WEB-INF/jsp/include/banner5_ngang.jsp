<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerContent4 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerContent4 = new BannerDAO().find("index", Banner.POSITION_CONTENT3);

	} else {
		bannerContent4 = new BannerDAO().find("other", Banner.POSITION_CONTENT3);

	}
%>


<div class="banner mb20" id="banner_hidden6">
	<%
		if (bannerContent4 != null) {
	%>
	<%=bannerContent4.getCode()%>
	<%
		}
	%>
</div>
