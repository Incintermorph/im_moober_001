<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		cancel : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/uss/mypage/crsAplcnt/selectList.do"/>";
		


	    this.req.cancel = imRequest("ajax");
	    this.req.cancel.cfg.formId = "FormPageDetail";
	    this.req.cancel.cfg.type   = "json";
	    this.req.cancel.cfg.url    = "<c:url value="/user/crsAplcnt/cancel.do"/>";
	    this.req.cancel.cfg.message.confirm="신청 취소 하시겠습니까?";
	    this.req.cancel.cfg.fn.complete = function(act, data) {
	    	if (data != null ) {
	        	if(data.result > 0){	
	        		REQ.page(1);
	        	}else{
	        		COMMT.errorMessageCode(data.result);	
	        	}
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	

		
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
	},
	cancel : function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.cancel.cfg.formId);
		REQ.req.cancel.go();
	},
	printComplete : function(appId){ <%--수료증--%>
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailCrsPrint.do"/>?crsAplcntId='+appId,1260,850);

	},
	printCompletePass : function(appId){ <%--이수증--%>
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailCrsPrintPass.do"/>?crsAplcntId='+appId,1260,850);
	
	},
}


$(document).ready(function(){
	REQ.init();
});


</script>


<%@ include file="incMypageCrsAplcnt.jsp" %>

		<div class="cb_bar right">
			<div class="add">
				<a href="javascript:;" onclick="REQ.list()"  class="c_btn l_gray">목록</a>
				<c:if test="${cmmmDtStts['FNSH'] eq '02'}"> <%--수료인 경우만 노출  --%>
				<c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_003' }">
				<a href="javascript";" onclick="REQ.printCompletePass('${detailApply.crsAplcnt.crsAplcntId}');return false;"  class="c_btn bk">이수증 출력</a>
				</c:if>
				<c:if test="${detail.crsMstr.crsDvsnCdv ne 'CRS_DVSN_003' }">
				<a href="javascript";" onclick="REQ.printComplete('${detailApply.crsAplcnt.crsAplcntId}');return false;"  class="c_btn bk">수료증 출력</a>
				</c:if>
				<%-- 
				<a href="javascript:COMMT.ready()" class="c_btn bk">자격증 출력</a>
				--%>
				</c:if>
				<c:if test="${cmmmDtStts['APLY'] eq '01'}">
				<a href="javascript";" onclick="REQ.cancel({'crsAplcntId' : '${detailApply.crsAplcnt.crsAplcntId}'});return false;"  class="c_btn bk">신청 취소</a>
				</c:if>
			</div>
		</div>
		<table class="tbl_row al">
			<colgroup>
				<col style="width:15%;">
				<col>
				<col style="width:15%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">접수번호</th>
					<td colspan="3"><c:out value="${detailApply.crsAplcnt.crsAplcntId}"/></td>
				</tr>
				<tr>
					<th scope="row">과정명</th>
					<td colspan="3"><im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/></td>
				</tr>
				<tr>
					<th scope="row">양성기관</th>
					<td colspan="3">${agncyDetail.agncy.agncyNm}</td>
				</tr>
				<tr>
					<th scope="row">양성기관 주소</th>
					<td colspan="3"><c:out value="${agncyDetail.agncy.addr}"/> <c:out value="${agncyDetail.agncy.addrDtl}"/></td>
				</tr>
				<tr>
					<th scope="row">구분</th>
					<td>
					<im:cd codeId="${workDvsnArr}" type="print" selectedCode="${detailApply.crsAplcnt.workDvsnCdv}"/>
					</td>
					<th scope="row">현재 재직기관</th>
					<td>${detailApply.crsAplcnt.agncyNm}</td>
				</tr>
				<tr>
					<th scope="row">교육신청기간</th>
					<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['eduAplTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['eduAplTerm'].endDt}"/></td>
					<th scope="row">교육기간</th>
					<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['eduTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['eduTerm'].endDt}"/></td>
				</tr>
				<tr>
					<th scope="row">교육 수수료</th>
					<td><im:numberFormat value="${detail.crs.ttnfee}"/> 원</td>
					<th scope="row">선정기간</th>
					<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}"/></td>
				</tr>
				<tr>
					<th scope="row">상태</th>
					<td>
					<im:cd type="print" codeId="IM0012" selectedCode="${cmmmDtStts['APLY']}"/>
					</td>
					<th scope="row">문의처</th>
					<td><c:out value="${detail.crs.cntpnt}"/></td>
				</tr>
					<c:choose>
						<c:when test="${detail.crs.ttnfee ne '0' }">
							<tr>
								<th scope="row">납부기간</th>
								<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].endDt}"/></td>
								<th scope="row">은행명</th>
								<td><im:cd type="print" codeId="IM0005" selectedCode="${detail.crs.bnkCdv}"/></td>
							</tr>
							<tr>
								<th scope="row">계좌번호</th>
								<td>${detail.crs.accno}</td>
								<th scope="row">예금주</th>
								<td>${detail.crs.acchdr}</td>
							</tr>
							<tr>
								<th scope="row">접수일</th>
								<td colspan="3"><im:dt yyyyMMddHHmmss="${detailApply.crsAplcnt.frstRegDt}" pattern="yyyy.MM.dd"/></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th scope="row">접수일</th>
								<td colspan="3"><im:dt yyyyMMddHHmmss="${detailApply.crsAplcnt.frstRegDt}" pattern="yyyy.MM.dd"/></td>
							</tr>
						</c:otherwise>
					</c:choose>
				<tr>
					<th scope="row">교육과정 및 평가계획 </th>
					<td colspan="3">
						<div class="tb_info">
							<c:out value="${imfunc:textToBr(cmmmDescMap['evlPlan'])}" escapeXml="false"/>
						</div>
					</td>
				</tr>
			</tbody>
		</table>