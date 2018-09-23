<%@ page contentType="text/xml" %>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.News"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
<c:if test="${iPage == 0}">
	<%
		Integer totalPage =(Integer) request.getAttribute("totalPage");
		if(totalPage == 1) {
			
			List<News> news = (List<News>) request.getAttribute("listNews");
			for(int i=0;i<news.size();i++) {
				News item = news.get(i);
	%>
	
	<url>
			<loc>https://xosohn.com/du-doan/<%=item.getUrl()%>-<%=item.getId()%>.html</loc>
			<lastmod><%=DateUtil.timestamp2String(item.getPublishDate(), "yyyy-MM-dd'T'hh:mm:ss"+"+07:00")%></lastmod>
			<changefreq>always</changefreq>
      		<priority>0.7</priority>
	</url>
		<%}
	} else {
 		for(int i=1;i<=totalPage;i++) {
	%>
		<url>
			<loc>https://xosohn.com/post-url-news<%=i%>.xml</loc>
		</url>
	<%}
	}%>
</c:if>	
<c:if test="${iPage != 0}">
	<%
		Integer totalPage =(Integer) request.getAttribute("totalPage");
		List<News> news = (List<News>) request.getAttribute("listNews");
			for(int i=0;i<news.size();i++) {
				News item = news.get(i);
	%>
	
	<url>
			<loc>https://xosohn.com/du-doan/<%=item.getUrl()%>-<%=item.getId()%>.html</loc>
			<lastmod><%=DateUtil.timestamp2String(item.getPublishDate(), "yyyy-MM-dd'T'hh:mm:ss"+"+07:00")%></lastmod>
			<changefreq>always</changefreq>
      		<priority>0.7</priority>
	</url>
	<%}%>
</c:if>
</urlset>
