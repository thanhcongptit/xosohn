<%@ page contentType="text/xml" %>
<%@page import="inet.bean.LotteryResult"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sitemapindex xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">

	<c:forEach var = "i" end = "${currentYear}" begin = "2005" varStatus="loop">
         <sitemap>
			<loc>https://xosohn.com/post-sitemap-${i}.xml</loc>
			<c:if test="${!loop.last}"><lastmod>${i}-12-31T18:45:00+07:00</lastmod></c:if>
			<c:if test="${loop.last}"><lastmod>${dayMB}</lastmod></c:if>
		</sitemap>
      </c:forEach>

	<sitemap>
		<loc>https://xosohn.com/categories-sitemap.xml</loc>
		<lastmod>${dayMB}</lastmod>
	</sitemap>
	<sitemap>
		<loc>https://xosohn.com/post-sitemap-news.xml</loc>
		<lastmod>${timeNews}+07:00</lastmod>
	</sitemap>
	<sitemap>
		<loc>https://xosohn.com/post-sitemap-dreams.xml</loc>
		<lastmod>${timeDreams}+07:00</lastmod>
	</sitemap>
</sitemapindex>
