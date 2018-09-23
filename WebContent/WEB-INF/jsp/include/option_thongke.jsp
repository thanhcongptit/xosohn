<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="again again-list mb20">
	<form method="post">
		<strong>Chọn tỉnh và biên độ cần xem</strong>
		<div class="text-center clearfix">
			<ul>
				<li>
				<select id="province_code_soicau" class="form-control" onchange = 'handelCodeChangeSoiCau();'>
						<option value="MIEN-BAC_XSMB" <c:if test="${code == 'XSMB'}"> selected="selected"</c:if> >Miền bắc</option>
						<c:forEach items="${lotteryCompanies}" var="company">
							<option value="${company.companyLink}_${company.code}" <c:if test="${code == company.code}"> selected </c:if> >${company.company}</option>
						</c:forEach>
				</select>
				</li>
				<li><input type="text" name="biendo" value="${biendo}" class="form-control"
					placeholder="Biên độ"></li>
				<li>
					<button type="submit" class="btn" >Xem kết quả</button>
				</li>

			</ul>
		</div>
	</form>
</div>