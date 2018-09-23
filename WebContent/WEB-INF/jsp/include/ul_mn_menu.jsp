<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<ul class="ul list-date">

	<li <c:if test="${empty activeMN}"> class="active" </c:if>><a
		href="${pageContext.servletContext.contextPath}/xsmn-xo-so-mien-nam.html">MIỀN
			NAM</a></li>

	<li <c:if test="${activeMN == '2'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmn-thu-2.html">Thứ
			2</a>
			<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMN thứ 2",
                  "url": "https://xosohn.com/xsmn-thu-2.html",
                  "startDate": "<%=dateOfT2%>",
                  "location": {
                    "@type": "Place",
                    "name": " TP Hồ Chí Minh, Đồng Tháp, Cà Mau",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " TP Hồ Chí Minh, Đồng Tháp, Cà Mau",
                      "addressLocality": " TP Hồ Chí Minh, Đồng Tháp, Cà Mau",
                      "addressRegion": "MN",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEvent2.getMeta()%>"        
                  }
                }</script>
	</li>
	<li <c:if test="${activeMN == '3'}">class="active"</c:if> ><a 
		href="${pageContext.servletContext.contextPath}/xsmn-thu-3.html">Thứ
			3</a>
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMN thứ 3",
                  "url": "https://xosohn.com/xsmn-thu-3.html",
                  "startDate": "<%=dateOfT3%>",
                  "location": {
                    "@type": "Place",
                    "name": " Bến Tre, Vũng Tàu, Bạc Liêu",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Bến Tre, Vũng Tàu, Bạc Liêu",
                      "addressLocality": " Bến Tre, Vũng Tàu, Bạc Liêu",
                      "addressRegion": "MN",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEvent3.getMeta()%>"        
                  }
                }</script>
	</li>
	<li <c:if test="${activeMN == '4'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmn-thu-4.html">Thứ
			4</a>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMN thứ 4",
                  "url": "https://xosohn.com/xsmn-thu-4.html",
                  "startDate": "<%=dateOfT4%>",
                  "location": {
                    "@type": "Place",
                    "name": " Đồng Nai, Cần Thơ, Sóc Trăng",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Đồng Nai, Cần Thơ, Sóc Trăng",
                      "addressLocality": " Đồng Nai, Cần Thơ, Sóc Trăng",
                      "addressRegion": "MN",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEvent4.getMeta()%>"        
                  }
                }</script>		
	</li>
	<li <c:if test="${activeMN == '5'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmn-thu-5.html">Thứ
			5</a>
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMN thứ 5",
                  "url": "https://xosohn.com/xsmn-thu-5.html",
                  "startDate": "<%=dateOfT5%>",
                  "location": {
                    "@type": "Place",
                    "name": " Tây Ninh, An Giang, Bình Thuận",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Tây Ninh, An Giang, Bình Thuận",
                      "addressLocality": " Tây Ninh, An Giang, Bình Thuận",
                      "addressRegion": "MN",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEvent5.getMeta()%>"        
                  }
                }</script>		
	</li>
	<li <c:if test="${activeMN == '6'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmn-thu-6.html">Thứ
			6</a>
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMN thứ 6",
                  "url": "https://xosohn.com/xsmn-thu-6.html",
                  "startDate": "<%=dateOfT6%>",
                  "location": {
                    "@type": "Place",
                    "name": " Vĩnh Long, Bình Dương, Trà Vinh",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Vĩnh Long, Bình Dương, Trà Vinh",
                      "addressLocality": " Vĩnh Long, Bình Dương, Trà Vinh",
                      "addressRegion": "MN",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEvent6.getMeta()%>"        
                  }
                }</script>		
	</li>
	<li <c:if test="${activeMN == '7'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmn-thu-7.html">Thứ
			7</a>
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMN thứ 7",
                  "url": "https://xosohn.com/xsmn-thu-7.html",
                  "startDate": "<%=dateOfT7%>",
                  "location": {
                    "@type": "Place",
                    "name": " TP Hồ Chí Minh, Long An, Bình Phước, Hậu Giang",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " TP Hồ Chí Minh, Long An, Bình Phước, Hậu Giang",
                      "addressLocality": " TP Hồ Chí Minh, Long An, Bình Phước, Hậu Giang",
                      "addressRegion": "MN",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEvent7.getMeta()%>"        
                  }
                }</script>		
	</li>
	<li <c:if test="${activeMN == '8'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmn-chu-nhat.html">CN</a>
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMN chủ nhật",
                  "url": "https://xosohn.com/xsmn-chu-nhat.html",
                  "startDate": "<%=dateOfT8%>",
                 "location": {
                    "@type": "Place",
                    "name": " Tiền Giang, Kiên Giang, Đà Lạt",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Tiền Giang, Kiên Giang, Đà Lạt",
                      "addressLocality": " Tiền Giang, Kiên Giang, Đà Lạt",
                      "addressRegion": "MN",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEvent8.getMeta()%>"        
                  }
                }</script>
	</li>
</ul>