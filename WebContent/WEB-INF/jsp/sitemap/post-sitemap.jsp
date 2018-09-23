<%@page import="inet.util.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<%@ page contentType="text/xml" %>

<%@page import="inet.bean.LotteryResult"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
<c:if test="${year == 0}">
	<c:forEach var = "i" end = "${currentYear}" begin = "2005">
         <url>
			<loc>https://xosohn.com/post-url-${i}.xml</loc>
		</url>
      </c:forEach>
</c:if>	
<c:if test="${year != 0}">
	<%
		
		List<LotteryResult> lotteries = (List<LotteryResult>) request.getAttribute("lotteries");
		String opendate = "";
			for(int i=0;i<lotteries.size();i++) {
				LotteryResult item = lotteries.get(i);
				
				Date tempDate = DateUtil.toDate(item.getOpenDate(), "yyyy-MM-dd");
				
				if(tempDate != null) {
					item.setOpenDate(DateUtil.date2String(tempDate, "dd-MM-yyyy"));
				}
						
				if(!opendate.equalsIgnoreCase(item.getOpenDate())) {
					opendate = item.getOpenDate();
					
					
	%>
		<url>
			<loc>https://xosohn.com/xsmb-<%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>.html</loc>
			<lastmod><%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>T18:15:00+07:00</lastmod>
		    <changefreq>always</changefreq>
		    <priority>0.8</priority>
		</url>
		
		<%
			Integer year = (Integer)request.getAttribute("year");
			String tempMonth[] = opendate.split("-");
			int month = Integer.parseInt(tempMonth[1]);
			
			if(year > 2008 && month >6) {
		%>
		<url>
			<loc>https://xosohn.com/xsmt-<%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>.html</loc>
			<lastmod><%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>T17:15:00+07:00</lastmod>
		    <changefreq>always</changefreq>
		    <priority>0.8</priority>
		</url>
		<url>
			<loc>https://xosohn.com/xsmn-<%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>.html</loc>
			<lastmod><%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>T16:15:00+07:00</lastmod>
		    <changefreq>always</changefreq>
		    <priority>0.8</priority>
		</url>
		<%}%>
	<%}%>
	
	<%if( !item.getCode().equalsIgnoreCase("XSTD")) {%>
	<url>
			<loc>https://xosohn.com/<%=item.getCode().toLowerCase()%>-<%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>.html</loc>
			<%
				if(CommonUtil.checkRegionMNByCode(item.getCode())) {
			%>
			<lastmod><%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>T16:15:00+07:00</lastmod>
			<%} else {%>
			<lastmod><%=CommonUtil.convertDatetoSitemap(item.getOpenDate())%>T17:15:00+07:00</lastmod>
			<%}%>
		    <changefreq>always</changefreq>
		    <priority>0.8</priority>
	</url>
	<%}}%>
</c:if>
</urlset>
