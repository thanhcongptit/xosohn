<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="list-news-item txt-heading">
	<h2>Soi cầu 99</h2>
	<ul class="news">
		<c:forEach items="${listNews}" var="dream" >
		
			<li><a href="<%= request.getContextPath() %>/soi-cau-99/${dream.url}-${dream.id}.html">
					<div class="img">
						<c:if test="${empty dream.imageUrl}">
						    <img src="${pageContext.servletContext.contextPath}/resources/img/thongke.jpeg" alt="" title="">
						</c:if>
						<c:if test="${not empty dream.imageUrl}">
						    <img src="${pageContext.servletContext.contextPath}/images/news_99/${dream.imageUrl}" alt="" title="">
						</c:if>
						
					</div>
					<div class="txt">
						<h3>${dream.title}</h3>
						<span><c:forTokens items="${dream.datePost}" var="date" delims="," begin="0" end="0">
                                                                  ${date}
                                                            </c:forTokens></span>
						<p>${dream.description}</p>
					</div>
			</a></li>
		</c:forEach>
		
	</ul>
	<ul class="pagination">
		<li class="<c:if test="${1==iPage}">disabled</c:if>">
			<a href="<%= request.getContextPath() %>/soi-cau-99/1.html">&laquo;</a>
		</li>
		
		<c:set var="index" value="1"/>
		<c:forEach var="i" begin="1" end="${totalPage}" varStatus="loop">
			<c:if test="${index < iPage + 4 && index > iPage - 4}">
				<li class="<c:if test="${i==iPage}">active</c:if>"><a
					href="<%= request.getContextPath() %>/soi-cau-99/${i}.html">${i}</a></li>
			</c:if>
			<c:set var="index" value="${index+1}" />
		</c:forEach>
		
		<li class="<c:if test="${totalPage==iPage}">disabled</c:if>">
			<a href="<%= request.getContextPath() %>/soi-cau-99/${totalPage}.html">&raquo;</a>
		</li>
	</ul>
</div>