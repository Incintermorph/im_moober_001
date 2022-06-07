<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>
			<table class="tbl_data">
				<caption>기타사항</caption>
				<colgroup>
					<col>
					<col>
					<col style="width:10%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">기타항목</th>
						<th scope="col">기타증빙</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${selectEtc}" var="row">
					
					<tr>
						<td>
							<div class="th">기타증빙 내용</div>
							<div class="td"><c:out value="${row.etcItm}"/></div>
						</td>
						<td>
							<div class="th">기타증빙</div>
							<div class="td">
							<c:if test="${empty row.etcEvddocId}">
							-
							</c:if>
							<c:if test="${!empty row.etcEvddocId}">
								<c:forEach items="${row.fileList}" var="frow">
									<c:set var="file_Key" value="${frow.atchFileId}=${frow.fileSn}"/>
									<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
									<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
									</a>
									<c:if test="${adminMode eq 'Y' &&  frow.fileExtsnLower eq 'pdf'}">
									<a  href="javascript:;" class="mls" onclick="COMMT.fn_viewPefFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')">
									<img src="${imWebStatic}/common/images/www/icon/ic_pdf_view.png" alt="PDF">
									</a>
									</c:if>
								</c:forEach>
							</c:if>	
							</div>
						</td>
						<td>
							<div class="th">관리</div>
							<div class="td">
								<a href="javascript:;" onclick="REQ.modifyMmbrEtc('${row.mmbrEtcId}')" class="c_btn sm b_green">수정</a>
							</div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty selectEtc }">
					<tr>
						<td colspan="3"><div class="no_info">등록된 정보가 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>