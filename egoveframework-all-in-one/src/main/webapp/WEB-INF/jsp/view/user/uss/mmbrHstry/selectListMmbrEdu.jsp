<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>
			<table class="tbl_data">
				<caption>학력사항</caption>
				<colgroup>
					<col style="width:15%;">
					<col>
					<col style="width:12%;">
					<col style="width:12%;">
					<col style="width:12%;">
					<col style="width:10%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">학교명</th>
						<th scope="col">과정(학과, 전공)</th>
						<th scope="col">학위</th>
						<th scope="col">학위 취득일</th>
						<th scope="col">학위증명서</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${selectEdu}" var="row">
					<tr>
						<td>
							<div class="th">학교명</div>
							<div class="td"><c:out value="${row.sclNm}"/></div>
						</td>
						<td>
							<div class="th">과정(학과, 전공)</div>
							<div class="td"><c:out value="${row.mjrNm}"/>&nbsp;<c:out value="${row.specNm}"/> </div>
						</td>
						<td>
							<div class="th">학위</div>
							<div class="td">
							<im:cd type="print" codeId="${dgeCdvArr}" selectedCode="${row.dgeCdv}" />
							
							</div>
						</td>
						<td>
							<div class="th">학위 취득일</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.dgeYmd}" pattern="yyyy.MM.dd"/></div>
						</td>
						<td>
							<div class="th">학위증명서</div>
							<div class="td">
							<c:if test="${empty row.dgeFileId}">
							-
							</c:if>
							<c:if test="${!empty row.dgeFileId}">
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
								<a href="javascript:;" onclick="REQ.modifyMmbrEdu('${row.mmbrEduId}')" class="c_btn sm b_green">수정</a>
							</div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty selectEdu}">
					<tr>
						<td colspan="6"><div class="no_info">등록된 정보가 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			
						