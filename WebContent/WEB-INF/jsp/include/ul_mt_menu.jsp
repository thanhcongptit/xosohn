<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="ul list-date">

	<li <c:if test="${empty activeMT}"> class="active" </c:if>><a
		href="${pageContext.servletContext.contextPath}/xsmt-xo-so-mien-trung.html"><small>MIỀN
			TRUNG</small></a>
			
	</li>

	<li <c:if test="${activeMT == '2'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmt-thu-2.html">Thứ
			2</a>
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMT thứ 2",
                  "url": "https://xosohn.com/xsmt-thu-2.html",
                  "startDate": "<%=dateOfT2%>",
                  "location": {
                    "@type": "Place",
                    "name": " Thừa Thiên Huế, Phú Yên",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Thừa Thiên Huế, Phú Yên",
                      "addressLocality": " Thừa Thiên Huế, Phú Yên",
                      "addressRegion": "MT",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEventMT2.getMeta()%>"        
                  }
                }</script>		
	</li>
	<li <c:if test="${activeMT == '3'}">class="active"</c:if> ><a 
		href="${pageContext.servletContext.contextPath}/xsmt-thu-3.html">Thứ
			3</a>
		<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMT thứ 3",
                  "url": "https://xosohn.com/xsmt-thu-3.html",
                  "startDate": "<%=dateOfT3%>",
                  "location": {
                    "@type": "Place",
                    "name": " Đắc Lắc, Quảng Nam",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Đắc Lắc, Quảng Nam",
                      "addressLocality": " Đắc Lắc, Quảng Nam",
                      "addressRegion": "MT",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEventMT3.getMeta()%>"        
                  }
                }</script>		
	</li>
	<li <c:if test="${activeMT == '4'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmt-thu-4.html">Thứ
			4</a>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMT thứ 4",
                  "url": "https://xosohn.com/xsmt-thu-4.html",
                  "startDate": "<%=dateOfT4%>",
                  "location": {
                    "@type": "Place",
                    "name": " Đà Nẵng, Khánh Hòa",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Đà Nẵng, Khánh Hòa",
                      "addressLocality": " Đà Nẵng, Khánh Hòa",
                      "addressRegion": "MT",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEventMT4.getMeta()%>"        
                  }
                }</script>
	</li>
	<li <c:if test="${activeMT == '5'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmt-thu-5.html">Thứ
			5</a>
			<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMT thứ 5",
                  "url": "https://xosohn.com/xsmt-thu-5.html",
                  "startDate": "<%=dateOfT5%>",
                  "location": {
                    "@type": "Place",
                    "name": " Bình Định, Quảng Trị, Quảng Bình",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Bình Định, Quảng Trị, Quảng Bình",
                      "addressLocality": " Bình Định, Quảng Trị, Quảng Bình",
                      "addressRegion": "MT",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEventMT5.getMeta()%>"        
                  }
                }</script>
       </li>
	<li <c:if test="${activeMT == '6'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmt-thu-6.html">Thứ
			6</a>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMT thứ 6",
                  "url": "https://xosohn.com/xsmt-thu-6.html",
                  "startDate": "<%=dateOfT6%>",
                  "location": {
                    "@type": "Place",
                    "name": " Gia Lai, Ninh Thuận",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Gia Lai, Ninh Thuận",
                      "addressLocality": " Gia Lai, Ninh Thuận",
                      "addressRegion": "MT",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEventMT6.getMeta()%>"        
                  }
                }</script>		
	</li>
	<li <c:if test="${activeMT == '7'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmt-thu-7.html">Thứ
			7</a>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMT thứ 7",
                  "url": "https://xosohn.com/xsmt-thu-7.html",
                  "startDate": "<%=dateOfT7%>",
                  "location": {
                    "@type": "Place",
                    "name": " Đà Nẵng, Quảng Ngãi, Đắc Nông",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Đà Nẵng, Quảng Ngãi, Đắc Nông",
                      "addressLocality": " Đà Nẵng, Quảng Ngãi, Đắc Nông",
                      "addressRegion": "MT",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEventMT7.getMeta()%>"        
                  }
                }</script>
	</li>
	<li <c:if test="${activeMT == '8'}">class="active"</c:if>><a 
		href="${pageContext.servletContext.contextPath}/xsmt-chu-nhat.html">CN</a>
	<script type="application/ld+json">{
                  "@context": "http://schema.org",
                  "@type": "Event",
                  "name": "XSMT chủ nhật",
                  "url": "https://xosohn.com/xsmt-chu-nhat.html",
                  "startDate": "<%=dateOfT8%>",
                 "location": {
                    "@type": "Place",
                    "name": " Khánh Hòa, Kon Tum",
                    "address": {
                      "@type": "PostalAddress",
                      "streetAddress": " Khánh Hòa, Kon Tum",
                      "addressLocality": " Khánh Hòa, Kon Tum",
                      "addressRegion": "MT",
                      "addressCountry": "VN"
                    },
                    "description": "<%=metaGuideEventMT8.getMeta()%>"        
                  }
                }</script>
	</li>
</ul>