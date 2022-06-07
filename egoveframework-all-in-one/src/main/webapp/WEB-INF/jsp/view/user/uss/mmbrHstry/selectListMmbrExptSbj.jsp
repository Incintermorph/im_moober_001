<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>

			<table class="tbl_data">
				<caption>면제과목</caption>
				<colgroup>
					<col>
					<col style="width:20%;">
					<col style="width:20%;">
					<col style="width:10%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">과목명</th>
						<th scope="col">수강희망여부</th>
						<th scope="col">증빙</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${selectExptSbj}" var="row">
					<tr>
						<td>
							<div class="th">과목명</div>
							<div class="td">
							<im:cd type="print" codeId="IM0019"  selectedCode="${row.certSbjCdv}"/>
							</div>
						</td>
						<td>
							<div class="th">수강희망여부</div>
							<div class="td">
							<im:cd type="print" codeId="${studyDsrYnArr}" name="studyDsrYn"  selectedCode="${row.studyDsrYn}"/>
							</div>
						</td>
						<td>
							<div class="th">증빙</div>
							<div class="td">
								<c:if test="${!empty row.fileId}">
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
								<a href="javascript:;" onclick="REQ.modifyMmbrExptSbj('${row.mmbrExptSbjId}')" class="c_btn sm b_green">수정</a>
							</div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty  selectExptSbj}">
					<tr>
						<td colspan="4"><div class="no_info">등록된 정보가 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		