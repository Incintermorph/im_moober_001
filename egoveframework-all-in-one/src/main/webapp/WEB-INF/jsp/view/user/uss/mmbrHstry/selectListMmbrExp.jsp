<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>
			<table class="tbl_data">
				<caption>환경교육 경력사항</caption>
				<colgroup>
					<col>
					<col style="width:18%;">
					<col style="width:10%;">
					<col style="width:14%;">
					<col style="width:10%;">
					<col style="width:10%;">
					<col style="width:10%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">활동분야</th>
						<th scope="col">재직기간</th>
						<th scope="col">활동시간</th>
						<th scope="col">확인기관</th>
						<th scope="col">확인서</th>
						<th scope="col">정관</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${selectExp}" var="row">
					<tr>
						<td>
							<div class="th">활동분야</div>
							<div class="td">
							<im:cd type="print" codeId="${workCtgryCdvArr}" selectedCode="${row.workCtgryCdv}"/>
							</div>
						</td>
						<td>
							<div class="th">재직기간</div>
							<div class="td">
							<im:dt yyyyMMddHHmmss="${row.workBgnYmd}"/>-
							<im:dt yyyyMMddHHmmss="${row.workEndYmd}"/>
							</div>
						</td>
						<td>
							<div class="th">활동시간</div>
							<div class="td"><c:out value="${row.workHrs}"/>시간</div>
						</td>
						<td>
							<div class="th">확인기관</div>
							<div class="td"><c:out value="${row.idntyAgncy}"/></div>
						</td>
						<td>
							<div class="th">확인서</div>
							<div class="td">
							<c:if test="${empty row.idntyFileId}">
							-
							</c:if>
							<c:if test="${!empty row.idntyFileId}">
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
							<div class="th">정관</div>
							<div class="td">
							<c:if test="${empty row.aoasFileId}">
							-
							</c:if>
							<c:if test="${!empty row.aoasFileId}">
									<c:forEach items="${row.fileList2}" var="frow">
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
								<a href="javascript:;" onclick="REQ.modifyMmbrExp('${row.mmbrExpId}')" class="c_btn sm b_green">수정</a>
							</div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty selectExp}">
					<tr>
						<td colspan="7"><div class="no_info">등록된 정보가 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			
						