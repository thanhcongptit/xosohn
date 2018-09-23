<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerRight1 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerRight1 = new BannerDAO().find("index", Banner.POSITION_RIGHT);

	} else {
		bannerRight1 = new BannerDAO().find("other", Banner.POSITION_RIGHT);

	}
%>


<div class="banner mb10" id="banner_hidden10">
	<%
		if (bannerRight1 != null) {
	%>
	<%=bannerRight1.getCode()%>
	<%
		}
	%>
</div>

