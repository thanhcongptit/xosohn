<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="again mb20">
	<form>
		<div class="text-center">
			<ul>
				<li><label> <input type="radio" name="optradio"
						<c:if test="${choice eq '1'}"> checked="checked"</c:if> value="1"
						onclick="handleOptions(1);">Quay thử theo miền
				</label></li>
				<li><label> <input type="radio" name="optradio"
						<c:if test="${choice eq '2'}"> checked="checked"</c:if> value="2"
						onclick="handleOptions(2);">Quay thử theo tỉnh
				</label></li>
			</ul>
		</div>
		<div class="text-center">
			<ul>
				<form id="trial_form" method="post" role="form">
					<li><select id="province_code1" class="form-control"
						name="code" onchange="handelCodeChange(1);">
							<c:if test="${code == 'xsmb'}">
								<option value="xsmb_mien-bac" selected="selected">Miền
									Bắc</option>
								<option value="xsmt_mien-trung">Miền Trung</option>
								<option value="xsmn_mien-nam">Miền Nam</option>
							</c:if>
							<c:if test="${code=='xsmt'}">
								<option value="xsmb_mien-bac">Miền Bắc</option>
								<option value="xsmt_mien-trung" selected="selected">Miền
									Trung</option>
								<option value="xsmn_mien-nam">Miền Nam</option>
							</c:if>
							<c:if test="${code=='xsmn'}">
								<option value="xsmb_mien-bac">Miền Bắc</option>
								<option value="xsmt_mien-trung">Miền Trung</option>
								<option value="xsmn_mien-nam" selected="selected">Miền
									Nam</option>
							</c:if>

					</select> <select id="province_code2" name="code" class="form-control"
						style="display: none;" onchange="handelCodeChange(2);">

							<c:forEach items="${lotteryCompanies}" var="company">

								<option value="${company.code}_${company.companyLink}" <c:if test="${code == company.codeLowerCase}"> selected </c:if> >${company.company}</option>

							</c:forEach>
					</select></li>
					<li>
						<button class="btn" onclick='reloadUrl();'>Quay thử</button>
					</li>
				</form>
			</ul>
		</div>
	</form>
</div>