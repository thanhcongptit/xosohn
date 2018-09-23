<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<div class="list-news txt-heading clearfix mb10">
	<h2>Bản tin xổ số</h2>
	<ul>
		<c:if test="${listLotteryNews!=null}">
	        <c:forEach items="${listLotteryNews}" var="news">
				<li><a href="<%=request.getContextPath()%>/du-doan/${news.url}-${news.id}.html" class="clearfix">
					<div class="img">
						<img
							src="${pageContext.servletContext.contextPath}/images/news/${news.imageUrl}"
							alt="" title="" />
					</div>
					<div class="txt">${news.title} - ngày 
						<c:forTokens items="${news.datePost}" var="date" delims="," begin="0" end="0">
	                        				${date}
	                    			</c:forTokens></div>
				</a></li>
			</c:forEach>
		</c:if>
		
	</ul>
	<a href="${pageContext.servletContext.contextPath}/du-doan.html" class="btn red"><img
		src="${pageContext.servletContext.contextPath}/resources/img/li3.png"
		alt="" title="">Xem thêm</a>
</div>