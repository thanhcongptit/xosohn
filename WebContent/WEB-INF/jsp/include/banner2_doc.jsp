<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerRight2 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerRight2 = new BannerDAO().find("index", Banner.POSITION_RIGHT1);

	} else {
		bannerRight2 = new BannerDAO().find("other", Banner.POSITION_RIGHT1);

	}
%>


<div class="banner baner-fix-canlenda" id="banner_hidden11">
<%
	if (bannerRight2 != null) {
%>
	<%=bannerRight2.getCode()%>
	
<%
	}
%>
</div>
