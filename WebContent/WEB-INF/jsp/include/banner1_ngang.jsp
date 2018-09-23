
<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner bannerTop1 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerTop1 = new BannerDAO().find("index", Banner.POSITION_TOP_1);

	} else {
		bannerTop1 = new BannerDAO().find("other", Banner.POSITION_TOP_1);

	}

	Banner bannerTop2 = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		bannerTop2 = new BannerDAO().find("index", Banner.POSITION_TOP_2);

	} else {
		bannerTop2 = new BannerDAO().find("other", Banner.POSITION_TOP_2);

	}
%>

<div class="row">
	
	<div class="col-xs-12 col-sm-6" id="banner_hidden1">
	<%
		if (bannerTop1 != null) {
	%>
		<%=bannerTop1.getCode()%>
	<%
		}
	%>
	</div>
	

	
	<div class="col-xs-12 col-sm-6" id="banner_hidden2">
	<%
		if (bannerTop2 != null) {
	%>
		<%=bannerTop2.getCode()%>
		<%
		}
	%>
	</div>
	

</div>