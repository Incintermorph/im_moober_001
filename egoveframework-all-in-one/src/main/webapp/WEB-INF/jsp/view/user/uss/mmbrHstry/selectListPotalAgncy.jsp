<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>
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
									<c:if test="${empty result || empty  result.institutionList}">
									
									<tr>
										<td colspan="3">
											검색 결과가 없습니다.
										</td>
									</tr>
									</c:if>
									<c:if test="${!empty result && !empty  result.institutionList}">
									<c:forEach items="${result.institutionList}" var="row">
									<tr>
										<td>
											<div class="th">기관유형</div>
											<div class="td"><c:out value="${row.biz_kind_name}"/></div>
										</td>
										<td>
											<div class="th">기관명</div>
											<div class="td" id="_potal_name_<c:out value="${row.inst_no}"/>">${row.org_name}</div>
										</td>
										<td>
											<div class="th">선택</div>
											<div class="td"><a href="javascript:;" onclick="AJAX.setOrg('<c:out value="${row.inst_no}"/>')" class="c_btn sm b_green">선택</a></div>
										</td>
									</tr>
									</c:forEach>
									</c:if>
							</tbody>
						</table>