<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerRight3 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerRight3 = new BannerDAO().find("index", Banner.POSITION_RIGHT2);

	} else {
		bannerRight3 = new BannerDAO().find("other", Banner.POSITION_RIGHT2);

	}
%>


<div class="banner mb20" id="banner_hidden12">
	<%
		if (bannerRight3 != null) {
	%>
	<%=bannerRight3.getCode()%>
	<%
		}
	%>
</div>

