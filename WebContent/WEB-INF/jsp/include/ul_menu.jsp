<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<ul class="ul list-date">

	<li <c:if test="${empty activeMB}"> class="active" </c:if>><a
		href="${pageContext.servletContext.contextPath}/xsmb-xo-so-mien-bac.html">MIỀN
			BẮC</a></li>

	<li <c:if test="${activeMB == '2'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmb-thu-2.html">Thứ
			2</a>
			
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMB thứ 2",
                  "url": "https://xosohn.com/xsmb-thu-2.html",
                  "startDate": "<%=dateOfT2%>",
                  "location": {
                    "@type": "Place",
                    "name": "53E - Hàng Bài - Hoàn Kiếm - Hà Nội",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": "53E - Hàng Bài",
                      "addressLocality": "Hoàn Kiếm - Hà Nội",
                      "addressRegion": "MB",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideMB2.getMeta()%>"        
                  }
                }</script>	
	</li>
	<li <c:if test="${activeMB == '3'}">class="active"</c:if> ><a 
		href="${pageContext.servletContext.contextPath}/xsmb-thu-3.html">Thứ
			3</a>
			<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMB thứ 3",
                  "url": "https://xosohn.com/xsmb-thu-3.html",
                  "startDate": "<%=dateOfT3%>",
                  "location": {
                    "@type": "Place",
                    "name": "53E - Hàng Bài - Hoàn Kiếm - Hà Nội",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": "53E - Hàng Bài",
                      "addressLocality": "Hoàn Kiếm - Hà Nội",
                      "addressRegion": "MB",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideMB3.getMeta()%>"        
                  }
                }</script>
	</li>
	<li <c:if test="${activeMB == '4'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmb-thu-4.html">Thứ
			4</a>
	
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMB thứ 4",
                  "url": "https://xosohn.com/xsmb-thu-4.html",
                  "startDate": "<%=dateOfT4%>",
                  "location": {
                    "@type": "Place",
                    "name": "53E - Hàng Bài - Hoàn Kiếm - Hà Nội",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": "53E - Hàng Bài",
                      "addressLocality": "Hoàn Kiếm - Hà Nội",
                      "addressRegion": "MB",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideMB4.getMeta()%>"        
                  }
                }</script>
                
                		
	</li>
	<li <c:if test="${activeMB == '5'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmb-thu-5.html">Thứ
			5</a>
			
			<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMB thứ 5",
                  "url": "https://xosohn.com/xsmb-thu-5.html",
                  "startDate": "<%=dateOfT5%>",
                  "location": {
                    "@type": "Place",
                    "name": "53E - Hàng Bài - Hoàn Kiếm - Hà Nội",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": "53E - Hàng Bài",
                      "addressLocality": "Hoàn Kiếm - Hà Nội",
                      "addressRegion": "MB",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideMB5.getMeta()%>"        
                  }
                }</script>
      </li>
	<li <c:if test="${activeMB == '6'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmb-thu-6.html">Thứ
			6</a>
	
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMB thứ 6",
                  "url": "https://xosohn.com/xsmb-thu-6.html",
                  "startDate": "<%=dateOfT6%>",
                  "location": {
                    "@type": "Place",
                    "name": "53E - Hàng Bài - Hoàn Kiếm - Hà Nội",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": "53E - Hàng Bài",
                      "addressLocality": "Hoàn Kiếm - Hà Nội",
                      "addressRegion": "MB",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideMB6.getMeta()%>"        
                  }
                }</script>
                		
	</li>
	<li <c:if test="${activeMB == '7'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmb-thu-7.html">Thứ
			7</a>
	
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMB thứ 7",
                  "url": "https://xosohn.com/xsmb-thu-7.html",
                  "startDate": "<%=dateOfT7%>",
                  "location": {
                    "@type": "Place",
                    "name": "53E - Hàng Bài - Hoàn Kiếm - Hà Nội",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": "53E - Hàng Bài",
                      "addressLocality": "Hoàn Kiếm - Hà Nội",
                      "addressRegion": "MB",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideMB7.getMeta()%>"        
                  }
                }</script>
                		
	</li>
	<li <c:if test="${activeMB == '8'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmb-chu-nhat.html">CN</a>
		
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMB chủ nhật",
                  "url": "https://xosohn.com/xsmb-chu-nhat.html",
                  "startDate": "<%=dateOfT8%>",
                  "location": {
                    "@type": "Place",
                    "name": "53E - Hàng Bài - Hoàn Kiếm - Hà Nội",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": "53E - Hàng Bài",
                      "addressLocality": "Hoàn Kiếm - Hà Nội",
                      "addressRegion": "MB",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideMB8.getMeta()%>"        
                  }
                }</script>
                </li>
</ul>