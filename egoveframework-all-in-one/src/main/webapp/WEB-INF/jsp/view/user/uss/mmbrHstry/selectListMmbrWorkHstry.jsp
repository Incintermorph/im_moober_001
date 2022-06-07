<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ include file="incMmberCode.jspf" %>
<script type="text/javaScript" language="javascript">
			
			var AJAXLIST = {
					init : function(){
						<c:if test="${!empty selectWorkHstryDiffSum}">
						$("#c_cyan_id").html (' : <span class="c_cyan">총 <im:numberFormat value="${selectWorkHstryDiffSum.diffYear}" pattern="##"/>년 <im:numberFormat value="${selectWorkHstryDiffSum.diffMonth}" pattern="##"/>개월</span>');						
						</c:if>
						<c:if test="${empty selectWorkHstryDiffSum}">
						$("#c_cyan_id").html ('');						
						</c:if>
						
					}
			}
			
</script>







<table class="tbl_data">
				<caption>근무이력</caption>
				<colgroup>
					<col style="width:10%;">
					<col>
					<col style="width:15%;">
					<col style="width:15%;">
					<col style="width:10%;">
					<col style="width:15%;">
					<col style="width:8%;">
					<col style="width:8%;">
					<col style="width:9%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">구분</th>
						<th scope="col">기관명</th>
						<th scope="col">사회환경교육기관<br>지정여부</th>
						<th scope="col">근무기간</th>
						<th scope="col">직급</th>
						<th scope="col">대상업무</th>
						<th scope="col">지정서</th>
						<th scope="col">재직증명서</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${selectWorkHstry}" var="row">
					<tr>
						<td>
							<div class="th">구분</div>
							<div class="td">
							<im:cd codeId="${workDvsnArr}" type="print" selectedCode="${row.workDvsnHstryCdv}"/>
							</div>
						</td>
						<td>
							<div class="th">기관명</div>
							<div class="td">
							${row.agncyNm}
							</div>
						</td>
						<td>
							<div class="th">사회환경교육기관<br>지정여부</div>
							<div class="td">
							<im:cd type="print" codeId="IMYENO" selectedCode="${row.eduDsgnYn}"/>
							</div>
						</td>
						<td>
							<div class="th">근무기간</div>
							<div class="td">
							<im:dt yyyyMMddHHmmss="${row.workBgnYmd}"/>-
							<im:dt yyyyMMddHHmmss="${row.workEndYmd}"/>
							</div>
						</td>
						<td>
							<div class="th">직급</div>
							<div class="td">${row.pstn}</div>
						</td>
						<td>
							<div class="th">대상업무</div>
							<div class="td">							
							<im:cd codeId="${workTrgtCdvArr}" type="print" selectedCode="${row.workTrgtCdv}"/>
							</div>
						</td>
						<td>
							<div class="th">첨부<br>지정서</div>
							<div class="td">
							<c:if test="${empty row.evddocFileId}">
							-
							</c:if>
							<c:if test="${!empty row.evddocFileId}">
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
							<div class="th">첨부<br>재직증명서</div>
							<div class="td">
							<c:if test="${empty row.dsgnFileId}">
							-
							</c:if>
							<c:if test="${!empty row.dsgnFileId}">
									<c:forEach items="${row.fileList2}" var="frow">
									<c:set var="file_Key" value="${frow.atchFileId}=${frow.fileSn}"/>
									<a href="javascript:COMMT.fn_egov_downFileEnChkEncfile('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
									<img src="${imWebStatic}/common/images/www/icon/ic_attach_file.png" alt="파일 첨부">
									</a>
									<c:if test="${adminMode eq 'Y' &&  frow.fileExtsnLower eq 'pdf'}">
									<a  href="javascript:;" class="mls" onclick="COMMT.fn_viewPefFileEncChkEncfile('<c:out value="${imfunc:encryptString(file_Key)}"/>')">
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
								<a href="javascript:;" onclick="REQ.modifyMmbrWorkHstry('${row.mmbrWorkHstryId}')" class="c_btn sm b_green">수정</a>
							</div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty  selectWorkHstry}">
					<tr>
						<td colspan="9"><div class="no_info">등록된 정보가 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			
						