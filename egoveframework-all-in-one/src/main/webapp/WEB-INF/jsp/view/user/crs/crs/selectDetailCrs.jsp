<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<c:set var="retureUrlVal">
/cmmn/crs/${crsType}/${crsGrd}/selectDetail.do?_paramMenuNo=${nowMenuNo}&crsId=${param['crsId']}
</c:set>


<c:if test="${!empty imLogin_uniqId}">
<c:set var="_msg" value=""/>
<c:if test="${empty detailMmbrHstry}">
<c:set var="_msg">이력관리에 등록된 정보가 없습니다. 이력관리 정보를 등록을 하시겠습니까?</c:set>
</c:if>

<c:if test="${!empty detailMmbrHstry}">
<c:if test="${detailMmbrHstry.mmbrHstry.aplyGrdCdv ne detail.crsMstr.crsGrdCdv}">
<c:set var="_msg">신청희망 자격등급에 등록된 정보와 교육신청 자격등급이 다릅니다. 이력관리 정보를 수정 하시겠습니까?</c:set>
</c:if>	
</c:if>


</c:if>
<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		apply : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/cmmn/crs/"/>${crsType}/${crsGrd}/selectList.do";
		


		<c:if test="${!empty detailMmbrHstry}">
	    
	    REQ.validator('${detailMmbrHstry.mmbrHstry.workDvsnCdv}');
		</c:if>
		$("#_copyArea").show();
		
		GREQ.loginReturnUrl("/cmmn/crs/${crsType}/${crsGrd}/selectDetail.do?_paramMenuNo=${nowMenuNo}&crsId=${param['crsId']}");
		
	},
	list :  function(){
		this.req.list.go();
	},
	applyResult :  function(crsAplcntId){
		var form =  document.getElementById(REQ.req.list.cfg.formId);
		form.elements["crsAplcntId"].value= crsAplcntId;
		REQ.req.list.cfg.url    = "<c:url value="/user/crs/"/>${crsGrd}/apply/result.do";
		REQ.req.list.go();
	},
	copyUrl :  function(){
		
		COMMT.copyURL("<c:url value="/cmmn/crs/"/>${crsType}/${crsGrd}/selectDetailOpen.do?_paramMenuNo=${nowMenuNo}&crsIdOpn=<c:out value="${imfunc:encryptString(param['crsId'])}"/>");
	},
	manage : function(msg){
		$("#_manage_desc").html(msg);
		$("#manage").show();
	},
	manageClose : function(){
		$("#manage").hide();
	},
	apply : function(){
		<c:if test="${empty imLogin_uniqId}">
		GREQ.loginChek('<c:out value="${retureUrlVal}" escapeXml="false" />');
		</c:if>
		<c:if test="${!empty imLogin_uniqId}">
		<c:if test="${empty _msg}">
			//REQ.req.apply.go();
			REQ.applyShow();
		</c:if>
		<c:if test="${!empty _msg}">
			REQ.manage('${_msg}');
		</c:if>
		</c:if>
		
	}

	<c:if test="${!empty detailMmbrHstry}">
		,applySave : function(){
			REQ.req.apply.go();
		}
		,applyShow : function(){
			<c:if test="${CRS_DVSN_002CHEK eq 'NO_PASS2'}">
			COMMT.message('실무교육 대상자가 아닙니다.<br>실무교육은 필기시험 합격자만 수강신청 가능합니다.');
			return;
			</c:if>
			<c:if test="${CRS_DVSN_002CHEK eq 'NO_PASS_DATE2'}">
			COMMT.message('실무교육 대상자가 아닙니다.<br>필기시험 유효기간(합격일자로 부터 2년)이 만료 되었습니다.');
			return;
			</c:if>
			<c:if test="${CRS_DVSN_002CHEK eq 'NO_PASS3'}">
			COMMT.message('보수교육 대상자가 아닙니다.보수교육 대상 여부는 마이페이지에서 확인 가능합니다.');
			return;
			</c:if>
			<c:if test="${CRS_DVSN_002CHEK eq 'NO_PASS_DATE3'}">
			COMMT.message('보수교육 대상자가 아닙니다.보수교육 대상 여부는 마이페이지에서 확인 가능합니다.');
			return;
			</c:if>
			
			$("#add_apply").show();
		}
		,applyClose : function(){
			$("#add_apply").hide();
		},
		validator : function(workDvsnCdv){
			REQ.req.apply = imRequest("ajax",{formId: "FormApply"});

			REQ.req.apply.cfg.formId = "FormApply";
		    REQ.req.apply.cfg.type   = "json";
		    REQ.req.apply.cfg.url    = "<c:url value="/user/crsAplcnt/apply.do"/>";
		    REQ.req.apply.cfg.message.confirm="교육신청 하시겠습니까?";
		    REQ.req.apply.cfg.fn.complete = function(act, data) {
		    	REQ.applyClose();
		        if (data != null) { 
		        	if(data.result == 1){
		        		REQ.applyResult(data.resultVO.crsAplcntId);
		        	}else{
		        		if(data.result==-1000){
		        			COMMT.message('신청하신 교육 입니다.');
		        		}
		        	}
		        } else {
		        	COMMT.errorMessage();
		        }
		    };	
			
			REQ.req.apply.validator.set({
		    	title  : "구분",
	            name : "workDvsnCdv",
	            data : ["!null"]
	        });
			   
			
		    if(workDvsnCdv!='3001' && workDvsnCdv!='999'){
		    	REQ.req.apply.validator.set({
			    	title  : "현재 재직기관",
		            name : "agncyNm",
		            data : ["!null"]
		        });
	    	}
		},
	openPotalAgncyMain : function(){
				var form =  document.getElementById('FormPortalOrg');
				form.elements["REQ"].value= "REQ";
				form.elements["scWord"].value= "";
				$("#find_org").show();
				
			},
			setOrg : function(key){
				var form =  document.getElementById('FormApply');
				form.elements["agncyNm"].value=$("#_potal_name_"+key).html();
				form.elements["agncyCode"].value=key;
				$("#find_org").hide();
			},
			delPotalAgncyMain : function(){
				var form =  document.getElementById('FormApply');
				form.elements["agncyNm"].value='';
				form.elements["agncyCode"].value='';
			},
			searchPotalOrg : function(){

				var req  = imRequest("ajax",{formId: "FormPortalOrg"});
				req.cfg.type        = "html";
				req.cfg.url    = "<c:url value="/user/epInstitution/selectList.do"/>";
				req.cfg.containerId = "listPotalOrg";
				req.cfg.fn.complete = function(act, data) {
					
		        };
		        req.validator.set({
			    	message : "검색어를 입력하세요.",
		            name : "searchWord",
		            data : ["!null"]
		        });
		        req.go();
		        
			},
		</c:if>	
}


$(document).ready(function(){
	REQ.init();
});


</script>




<%@ include file="incCrs.jsp" %>




<div class="ct_box bg bot">
			<span class="p_noti red"><i class="m_icon_out circle_notifications"></i>중요 </span>: 환경교육사 사이트 신규 운영에 따라 <a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="high">이력관리</a> 재등록이 필요합니다.
			<ul class="radio">
				<li>교육에 대한 자세한 사항은 하단의 공고문을 다운로드 받아서 확인하여 주시기 바랍니다.</li>
			</ul>
		</div>
		<div class="cb_bar right">
			<div class="add"> 
				<a href="javascript:;" onclick="REQ.list()" class="c_btn l_gray">목록</a>
				<a href="<c:url value="/cmmn/page/intro06/page.do"/>"   class="c_btn l_gray">자격취득 안내</a>
				<a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="c_btn d_green em">이력관리</a>
				<c:if test="${detail.procType eq 'I'}">
				<a href="javascript:;" onclick="REQ.apply()" class="c_btn bk">교육신청</a>
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
					<th scope="row">과정명</th>
					<td colspan="3"><im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/></td>
				</tr>
				<tr>
					<th scope="row">양성기관</th>
					<td colspan="3"><c:out value="${agncyDetail.agncy.agncyNm}"/></td>
				</tr>
				<tr>
					<th scope="row">양성기관 주소</th>
					<td colspan="3"><c:out value="${agncyDetail.agncy.addr}"/> <c:out value="${agncyDetail.agncy.addrDtl}"/></td>
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
					<th scope="row">모집인원</th>
					<td><im:numberFormat value="${detail.crs.prsnl}"/> 명</td>
				</tr>
				<tr>
					<th scope="row">참가자격 기준</th>
					<td colspan="3">
						<div class="tb_info">
							<c:out value="${imfunc:textToBr(cmmmDescMap['atndQlfcStndrd'])}" escapeXml="false"/>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">자격 제한</th>
					<td colspan="3">
						<div class="tb_info">
							<c:out value="${imfunc:textToBr(cmmmDescMap['qlfcLmt'])}" escapeXml="false"/>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">교육개요</th>
					<td colspan="3">
						<div class="tb_info">
							<c:out value="${cmmmDescMap['eduOutline']}" escapeXml="false"/>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">교육과정 및 평가계획 </th>
					<td colspan="3">
						<div class="tb_info">
							<c:out value="${imfunc:textToBr(cmmmDescMap['evlPlan'])}" escapeXml="false"/>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">수료기준</th>
					<td colspan="3">
						<c:out value="${imfunc:textToBr(cmmmDescMap['cmpltnStndrd'])}" escapeXml="false"/>
					</td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${detail.crs.ttnfee != 0 }">
							<th scope="row">선정기간</th>
							<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}"/></td>
							<th scope="row">납부기간</th>
							<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].endDt}"/></td>
						</c:when>
						<c:otherwise>
							<th scope="row">선정기간</th>
							<td colspan="3"><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<th scope="row">문의처</th>
					<td colspan="3"><c:out value="${detail.crs.cntpnt}"/></td>
				</tr>
				<tr>
					<th scope="row">공고문</th>
					<td colspan="3">
						<c:forEach items="${fileList}" var="row">
						<c:set var="file_Key" value="${row.atchFileId}=${row.fileSn}"/>
						<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')" class="file_link" >
							<c:out value="${row.orignlFileNm}"/>
						</a>	
						</c:forEach>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="cb_bar right mt">
			<div class="add"> 
				<a href="javascript:;" onclick="REQ.list()" class="c_btn l_gray">목록</a>
				<a href="<c:url value="/cmmn/page/intro06/page.do"/>"   class="c_btn l_gray">자격취득 안내</a>
				<a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="c_btn d_green em">이력관리</a>
				<c:if test="${detail.procType eq 'I'}">
				<a href="javascript:;" onclick="REQ.apply()" class="c_btn bk">교육신청</a>
				</c:if>
			</div>
		</div>	
		
		
<div class="ct_dialog alert" id="manage" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:300px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">Confirm</p>
						<a href="#" onclick="REQ.manageClose()"  class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<p class="at_desc" id="_manage_desc">이력관리에 등록된 정보가 없습니다.</p>
					<div class="fb_box">
						<a href="#" onclick="GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"  class="c_btn d_green">확인</a>
						<a href="#" onclick="REQ.manageClose()"  class="c_btn wt">취소</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>
<c:if test="${!empty detailMmbrHstry}">

<form name="FormApply" id="FormApply" method="post" onsubmit="return false;">
<input type="hidden" name="crsId" value="${param['crsId']}" />
<input type="hidden" name="crsAplcntId"  />
<div class="ct_dialog" id="add_apply" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:600px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">교육신청</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<ul class="item_li bot">
						<li>재직현황을 확인하여 주시기 바랍니다.</li>
					</ul>
					<table class="tbl_row al">
						<colgroup>
							<col style="width:30%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">구분(*)</th>
								<td>
									<im:cd codeId="${workDvsnArr}" onclick="REQ.validator(this.value);" type="radio" name="workDvsnCdv" selectedCode="${detailMmbrHstry.mmbrHstry.workDvsnCdv}" />
								</td>
							</tr>
							<tr>
								<th scope="row">현재 재직기관</th>
								<td><div class="search_area">
									<input type="text" name="agncyNm" value="${detailMmbrHstry.mmbrHstry.agncyNm}" placeholder="재직기관을 선택하여 주세요" title="기관명" readonly="readonly">
									<input type="hidden" name="agncyCode" value="${detailMmbrHstry.mmbrHstry.agncyCode}">
									<a href="javascript:;" onclick="REQ.delPotalAgncyMain()"  class="del" title="삭제"><span class="sr_only">삭제</span></a>
									</div>
									<a href="javascript:;" onclick="REQ.openPotalAgncyMain()" class="c_btn bk">찾기</a>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="b_box">
						<a href="javascript:;" onclick="REQ.applySave();" class="c_btn bk">교육신청</a>
						<a href="javascript:;" onclick="REQ.applyClose();" class="c_btn wt">취소</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>

</form>

<%@ include file="/WEB-INF/jsp/inc/imPotalOrg.jspf" %>
</c:if>