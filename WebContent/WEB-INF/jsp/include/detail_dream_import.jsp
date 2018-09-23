<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<div class="list-news-item txt-heading">
	<h1>Sổ mơ lô đề</h1>
	<div class="detail">
		<%-- <h1>${lotteryNews.title}</h1> --%>
		<h2>${lotteryNews.title}</h2>
		<span class="time"><c:forTokens items="${lotteryNews.datePost}"
				delims="," var="d" varStatus="status">
				<c:if test="${status.index==0}">${d}&nbsp;</c:if>
				<c:if test="${status.index==1}">
                                                               ${f:substring(d, 0, 2)}:${f:substring(d, 2, 4)}
                                                           </c:if>
			</c:forTokens> <%-- - Xem(${lotteryNews.viewCounter}) --%></span>
		<p>${lotteryNews.content}</p>

	</div>
	<div class="news-cre">
		<h3>Tin khác</h3>
		<c:if test="${listNews!=null}">

			<ul>
				<c:forEach items="${listNews}" var="news" begin="0" end="10">
					<c:if test="${news.id!=lotteryNews.id}">
						<li><a
							href="<%=request.getContextPath()%>/so-mo-lo-de/${news.url}-${news.id}.html">
								${news.title} <span><c:forTokens items="${news.datePost}"
										var="date" delims="," begin="0" end="0">
                                                                                            (${date})
                                                                        </c:forTokens></span>

						</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</c:if>
	</div>
</div>