<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerContent3 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerContent3 = new BannerDAO().find("index", Banner.POSITION_CONTENT2);

	} else {
		bannerContent3 = new BannerDAO().find("other", Banner.POSITION_CONTENT2);

	}
%>


<div class="banner mb20" id="banner_hidden5">
	<%
		if (bannerContent3 != null) {
	%>
	<%=bannerContent3.getCode()%>
	<%
		}
	%>
</div>
