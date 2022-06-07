<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

			<table class="tbl_data sm">
							<caption>재직기관</caption>
							<colgroup>
								<col style="width:150px;">
								<col>
								<col style="width:150px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">기관유형</th>
									<th scope="col">기관명</th>
									<th scope="col">선택</th>
								</tr>
							</thead>
							<tbody>
									<c:if test="${empty pageInfo.list}">
									
									<tr>
										<td colspan="3">
											<div class="no_info">
											검색 결과가 없습니다.
											</div>
										</td>
									</tr>
									</c:if>
									<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
									<tr>
										<td>
											<div class="th">기관유형</div>
											<div class="td"><c:out value="${row.epInstitution.bizKindName}"/></div>
										</td>
										<td>
											<div class="th">기관명</div>
											<div class="td" id="_potal_name_<c:out value="${row.epInstitution.instNo}"/>">${row.epInstitution.orgName}</div>
										</td>
										<td>
											<div class="th">선택</div>
											<div class="td"><a href="javascript:;" onclick="${param['REQ']}.setOrg('<c:out value="${row.epInstitution.instNo}"/>')" class="c_btn sm b_green">선택</a></div>
										</td>
									</tr>
									</c:forEach>
							</tbody>
						</table>