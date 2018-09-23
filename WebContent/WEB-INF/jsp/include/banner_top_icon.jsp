<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.Banner"%>
<%
	Banner banner = null;
	if (request.getRequestURI().endsWith("/index.jsp")) {
		banner = new BannerDAO().find("index", Banner.POSITION_HEADER);

	} else {
		banner = new BannerDAO().find("other", Banner.POSITION_HEADER);

	}
%>
<div class="top clearfix">
	<div class="logo pull-left">
		<a href="${pageContext.servletContext.contextPath}/"><img
			src="${pageContext.servletContext.contextPath}/resources/img/logo.png"
			alt="logo" title="logo"></a>
	</div>

			<div class="banner hidden-sm hidden-xs pull-right" id ="banner_hidden0">
				<%
						if (banner != null) {
					%>
				<%=banner.getCode() %> 	
			<%
		}
	%>
			</div>
	
</div>