<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>

<div class="breck clearfix">
	<ul>
		<c:if test="${advertisingSites!=null}">
			<c:forEach items="${advertisingSites}" var="advertisingSite">
				              <c:if test="${advertisingSite.index > 0}" >
				              		<li><a href="${advertisingSite.link}" target="blank"><img
									src="${pageContext.servletContext.contextPath}/resources/img/new.gif"
									alt="" title="">${advertisingSite.title}</a></li>
				              		
				              </c:if>
			              </c:forEach>
		
		</c:if>
	</ul>
</div>