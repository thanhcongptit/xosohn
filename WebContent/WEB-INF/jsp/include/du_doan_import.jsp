<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="list-news-item txt-heading">
	<h2>Dự đoán xổ số</h2>
	<ul class="news">
		<c:forEach items="${listNews}" var="news" >
		
			<li><a href="<%= request.getContextPath() %>/du-doan/${news.url}-${news.id}.html">
					<div class="img">
						<img src="${pageContext.servletContext.contextPath}/images/news/${news.imageUrl}" alt="" title="">
					</div>
					<div class="txt">
						<h3>${news.title}</h3>
						<span><c:forTokens items="${news.datePost}" var="date" delims="," begin="0" end="0">
                                                                  ${date}
                                                            </c:forTokens></span>
						<p>${news.description}</p>
					</div>
			</a></li>
		</c:forEach>
		
	</ul>
	<ul class="pagination">
		<li class="<c:if test="${1==iPage}">disabled</c:if>">
			<a href="<%= request.getContextPath() %>/du-doan${code}/1.html">&laquo;</a>
		</li>
		
		<c:set var="index" value="1"/>
		<c:forEach var="i" begin="1" end="${totalPage}" varStatus="loop">
			<c:if test="${index < iPage + 4 && index > iPage - 4}">
				<li class="<c:if test="${i==iPage}">active</c:if>"><a
					href="<%= request.getContextPath() %>/du-doan${code}/${i}.html">${i}</a></li>
			</c:if>
			<c:set var="index" value="${index+1}" />
		</c:forEach>
		
		<li class="<c:if test="${totalPage==iPage}">disabled</c:if>">
			<a href="<%= request.getContextPath() %>/du-doan${code}/${totalPage}.html">&raquo;</a>
		</li>
	</ul>
</div>