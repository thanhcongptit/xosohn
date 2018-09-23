<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerContent7 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerContent7 = new BannerDAO().find("index", Banner.POSITION_CONTENT6);

	} else {
		bannerContent7 = new BannerDAO().find("other", Banner.POSITION_CONTENT6);

	}
%>


<div class="banner mb20" id="banner_hidden9">
	<%
		if (bannerContent7 != null) {
	%>
	<%=bannerContent7.getCode()%>
	<%
		}
	%>
</div>
