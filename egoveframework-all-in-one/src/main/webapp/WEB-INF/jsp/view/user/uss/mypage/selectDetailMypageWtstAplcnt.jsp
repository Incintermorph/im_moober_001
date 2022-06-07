<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>



<c:set var="retureUrlVal">
/user/uss/mypage/wtstAplcnt/selectDetail.do?_paramMenuNo=${nowMenuNo}&wtstAplcntId=${detailApply.wtstAplcnt.wtstAplcntId}
</c:set>

<c:set var="_msg" value=""/>
<c:if test="${empty detailMmbrHstry}">
<c:set var="_msg">이력관리에 등록된 정보가 없습니다. 이력관리 정보를 등록을 하시겠습니까?</c:set>
</c:if>	

<c:set var="ATCH_FILE_ID" value="convEvddocfile"/>
<c:set var="PHT_FILE_ID" value="phtfile"/>
<c:set var="ATCH_EXEMP_FILE_ID" value="convEXEMPdocfile"/>

<script type="text/javascript" >


<c:set var="ATCH_FILE_ID" value="convEvddocfile"/>
<c:set var="PHT_FILE_ID" value="phtfile"/>
<c:set var="ATCH_EXEMP_FILE_ID" value="convEXEMPdocfile"/>

var REQ = {
	req : {
		list : null,
		cancel : null,
		save : null,
		detail : null,
	},

	imgInfo : {
		uploadFolder : 'wtst_aplcnt_pht',
		fileInputId : '${PHT_FILE_ID}'
	},
	atchInfo : {
		uploadFolder : 'wtst_aplcnt',
		fileInputId : '${ATCH_FILE_ID}'
	},
	atchInfo2 : {
		uploadFolder : 'wtst_aplcnt',
		fileInputId : '${ATCH_EXEMP_FILE_ID}'
	},
	init : function(){
		
		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/user/uss/mypage/wtstAplcnt/selectDetail.do"/>";
		
		
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/uss/mypage/wtstAplcnt/selectList.do"/>";
		
	

	    this.req.cancel = imRequest("ajax");
	    this.req.cancel.cfg.formId = "FormPageDetail";
	    this.req.cancel.cfg.type   = "json";
	    this.req.cancel.cfg.url    = "<c:url value="/user/wtstAplcnt/cancel.do"/>";
	    this.req.cancel.cfg.message.confirm="접수 취소 하시겠습니까?";
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
	
		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/user/uss/mypage/wtstAplcnt/selectDetail.do"/>";
		REQ.validator('${detailApply.wtstAplcnt.workDvsnCdv}');
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
	save :  function(){
		REQ.req.save.go();
	},
	reload :  function(){
		var form =  document.getElementById(REQ.req.detail.cfg.formId);
		form.elements["wtstAplcntId"].value= '${detailApply.wtstAplcnt.wtstAplcntId}';
		REQ.req.detail.go()
	},
	applyForm : function(){
		$("#add_applyForm").show();
	},
	applyFormClose : function(){
		$("#add_applyForm").hide();
	}
	,openPotalAgncyMain : function(){
		var form =  document.getElementById('FormPortalOrg');
		form.elements["REQ"].value= "REQ";
		form.elements["scWord"].value= "";
		$("#find_org").show();
		
	},
	setOrg : function(key){
		var form =  document.getElementById('iMWtstAplcnt');
		form.elements["agncyNm"].value=$("#_potal_name_"+key).html();
		form.elements["agncyCode"].value=key;
		$("#find_org").hide();
	},
	delPotalAgncyMain : function(){
		var form =  document.getElementById('iMWtstAplcnt');
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
	validator : function(workDvsnCdv){
		this.req.save = imRequest("ajax",{formId: "iMWtstAplcnt"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/wtstAplcnt/update.do"/>";
		this.req.save.cfg.message.confirm="수정 하시겠습니까?";
		this.req.save.cfg.fn.complete = function(act, data) {
			if (data != null) {
	        	REQ.reload();
	        	
	        } else {
	        	COMMT.errorMessage();
	        }
	    };
	    
	    this.req.save.cfg.fn.before = function() {
	    	COMMT.uploadImageFileUser(REQ.imgInfo,function(data){
    			 if (data.result > 0) {
    				 if(data.result==1){
    					 //첨부파일 정보 성공인 경우 
	    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
	    		    	 form.elements["phtFileId"].value = data.atchFileId;
    				 }
    					 
    					 COMMT.uploadCommonFileUser(REQ.atchInfo,function(data){
    		    			 if (data.result > 0) {
    		    				 if(data.result==1){
    		    					 //첨부파일 정보 성공인 경우 
    			    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
    			    		    	 form.elements["convEvddocId"].value = data.atchFileId;
    		    				 }


    		 					
    		    				 REQ.req.save.go("continue");
    		                    } else {
    		                    	COMMT.errorMessage();
    		                   }
    		    		});
    				 
                    } else {
                    	COMMT.errorMessage();
                   }
    		});
       }
	    

	    this.req.save.validator.set({
	    	title  : "휴대전화번호",
            name : "mblTelno1",
            data : ["!null","number"] ,
            check : {  
                maxlength : 4 
            }  
        });
	    this.req.save.validator.set({
	    	title  : "휴대전화번호",
            name : "mblTelno2",
            data : ["!null","number"] ,
            check : {  
                maxlength : 4 
            }  
        });
	    this.req.save.validator.set({
	    	title  : "휴대전화번호",
            name : "mblTelno3",
            data : ["!null","number"] ,
            check : {  
                maxlength : 4 
            }
        });
	    
	    this.req.save.validator.set({
	    	title  : "구분",
            name : "workDvsnCdv",
            data : ["!null"]
        });
		   
		
	    if(workDvsnCdv!='3001' && workDvsnCdv!='999'){
	    	this.req.save.validator.set({
		    	title  : "현재 재직기관",
	            name : "agncyNm",
	            data : ["!null"]
	        });
    	}
	    
	    
	    this.req.save.validator.set({
	    	title  : "이메일",
            name : "eml",
            data : ["!null","email"] ,
            check : {  
                maxlength : 30 
            }  

        });
	    
	    

	    this.req.save.validator.set(function(){
	    	var form =  document.getElementById(REQ.req.save.cfg.formId);
	    	
	    	 if(form.elements["phtFileId"].value==""){
			    	if(!COMMT.chkAttachFile('${PHT_FILE_ID}')){
			    		COMMT.message('사진을 첨부해 주세요.');
			    		return false;
			    	}else{
			    		return true;
			    	}
	    	 }else{
	    		 return true;
	    	 }
	    });

	    
	    
		
	    this.req.save.validator.set(function(){
	    	var form =  document.getElementById(REQ.req.save.cfg.formId);
			if(form.elements["convPvsnYn"].value=='N'){
				return true;
			}else{

		    	 if(form.elements["convEvddocId"].value==""){
			    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
			    		COMMT.message('편의제공 증빙 자료를 첨부해 주세요.');
			    		return false;
			    	}else{
			    		return true;
			    	}
		    	 }else{
		    		 return true;
		    	 }
			}
	    });
	    
  		 this.req.save.validator.set(function(){
  			 if(COMMT.chkAttachFile(REQ.imgInfo.fileInputId)){
  			    	if(!COMMT.chkAttachFileSize(REQ.imgInfo)){
  			    			return false;	
  			    	}else{
  			    			return true;
  			    	}
  		    	}else{
  		    		return true;
  		    	}
  		    });
  		 this.req.save.validator.set(function(){
  			 if(COMMT.chkAttachFile(REQ.atchInfo.fileInputId)){
  			    	if(!COMMT.chkAttachFileSize(REQ.atchInfo)){
  			    			return false;	
  			    	}else{
  			    			return true;
  			    	}
  		    	}else{
  		    		return true;
  		    	}
  		    });
	    
	   
	},
	printAdmissionTicket: function(){ 
		<c:if test="${!empty detailApply.wtstAplcnt.phtFileId}">
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailExamPrint.do"/>?wtstAplcntId=${detailApply.wtstAplcnt.wtstAplcntId}',1260,850);
		</c:if>
		<c:if test="${empty detailApply.wtstAplcnt.phtFileId}">
		REQ.req.detail.cfg.message.confirm="수험표에 사진첨부가 필요합니다.<br>사진 등록하는 화면으로 이동 하시겠습니까?";

		var form =  document.getElementById(REQ.req.detail.cfg.formId);
		form.elements["modifyYn"].value= "Y";
		form.elements["wtstAplcntId"].value= "${detailApply.wtstAplcnt.wtstAplcntId}";
		REQ.req.detail.go();
		</c:if>
		
	},
	viewScore : function(){
		$("#view_score").show();
	},
	hideScore : function(){
		$("#view_score").hide();
	}
}


$(document).ready(function(){
	REQ.init();
	<c:if test="${param['modifyYn'] eq 'Y'}">
	REQ.applyForm();
	</c:if>
});


</script>

<%@ include file="incMypageWtstAplcnt.jsp" %>
		<div class="cb_bar right">
			<div class="add">
				<a href="javascript:;" onclick="REQ.list()"  class="c_btn l_gray">목록</a>
				<a href="javascript:;" onclick="showConvenience()"   class="c_btn l_gray">편의 제공 안내</a>
				<c:if test="${detail.rcptProcType eq 'I'}">
				<a href="javascript:;" onclick="REQ.applyForm();return false;"  class="c_btn bk">수정</a>
					<c:if test="${cmmmDtStts['APLY'] eq '01'}">
					<a href="javascript:;" onclick="REQ.cancel({'wtstAplcntId' : '${detailApply.wtstAplcnt.wtstAplcntId}'});return false;"  class="c_btn bk">접수 취소</a>
					</c:if>
				</c:if>
				<c:if test="${cmmmDtStts['APLY'] eq '02' && cmmmDtStts['DPST'] eq 'Y' && !empty detailApply.wtstAplcnt.tktstno}">
				<a href="javascript:;" onclick="REQ.printAdmissionTicket();return false;"  class="c_btn bk">수험표출력</a>
				<c:if test="${cmmmDtStts['FNSH'] eq '01'}">
				<a href="javascript:;" onclick="COMMT.message('성적 처리 준비중 입니다.');return false;"  class="c_btn bk">성적열람</a>
				</c:if>
				<c:if test="${cmmmDtStts['FNSH'] eq '04'}">
				<a href="javascript:;" onclick="COMMT.message('미응시 처리 되었습니다.');return false;"  class="c_btn bk">성적열람</a>
				</c:if>
				<c:if test="${cmmmDtStts['FNSH'] eq '02' || cmmmDtStts['FNSH'] eq '03'}">
				<a href="javascript:;" onclick="REQ.viewScore();return false;"  class="c_btn bk">성적열람</a>
				</c:if>
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
		<tr>
			<th scope="row">필기시험명(등급)</th>
			<td>
			환경교육사 <im:cd type="print" codeId="IM0001" selectedCode="${detail.wtst.crsGrdCdv}"/> 필기시험
			</td>
			<th scope="row">연도 / 차수 </th>
			<td><c:out value="${detail.wtst.eduYear}"/>년/<c:out value="${detail.wtst.eduRnd}"/>차
			</td>
		</tr>
		<tr>
			<th scope="row">원서접수기간</th>
			<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['rcptTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['rcptTerm'].endDt}"/></td>
			<th scope="row">시험일자(응시시간) </th>
			<td><im:dt yyyyMMddHHmmss="${detail.wtst.tstYmd}"/> (${detail.wtst.tstBgngHrs}~${detail.wtst.tstEndHrs})</td>
		</tr>
		<tr>
			<th scope="row">신청자/정원</th>
			<td><c:out value="${detail.applyCnt}"/>/<c:out value="${detail.wtstPlace.prsnl}"/></td>
			<th scope="row">응시료</th>
			<td><im:numberFormat value="${detail.wtst.tstfee}"/> 원</td>
		</tr>
		<tr>
			<c:if test="${detail.wtst.tstfee > 0 }">
			<th scope="row">선정기간</th>
			<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}"/></td>
			<th scope="row">납부기간</th>
			<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].endDt}"/></td>
			</c:if>
			<c:if test="${detail.wtst.tstfee < 1 }">
			<th scope="row">선정기간</th>
			<td colspan="3"><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}"/></td>
			</c:if>
		</tr>
		<tr>
			<th scope="row">권역/지역</th>
			<td><im:cd type="print" codeId="IM0006" selectedCode="${detail.agncy.areaCdvRgn}"  />/<im:cd type="print" codeId="IM0007" selectedCode="${detail.agncy.areaCdv}"  /></td>
			<th scope="row">시험장소</th>
			<td><c:out value="${detail.agncy.agncyNm}"/></td>
		</tr>
		<tr>
			<th scope="row">시험장 주소</th>
			<td colspan="3"><c:out value="${detail.agncy.addr}"/>&nbsp&nbsp<c:out value="${detail.agncy.addrDtl}"/></td>
		</tr>
		<tr>
			<th scope="row">문의처</th>
			<td colspan="3"><c:out value="${detail.wtstPlace.cntpnt}"/></td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMWtst.ntce"/></th>
			<td colspan="3">
			<c:out value="${imfunc:textToBr(cmmmDescMap['ntce'])}" escapeXml="false"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="iMWtst.brng"/></th>
			<td colspan="3">
			<c:out value="${imfunc:textToBr(cmmmDescMap['brng'])}" escapeXml="false"/>
			</td>
		</tr>
	</table>
	<div class="cb_bar top">
		<p class="bl_title">신청자 정보</p>
	</div>
	<table class="tbl_row al">
		<colgroup>
			<col style="width:15%;">
			<col>
			<col style="width:15%;">
			<col>
		</colgroup>
		<tr>
			<th scope="row">이름</th>
			<td colspan="3">
			${detailApply.wtstAplcnt.mberNm}
			</td>
			<%--
			<th scope="row">성별</th>
			<td>
				<c:set var="sexdstnCdvArr">M=남,F=여</c:set>
				<im:cd codeId="${sexdstnCdvArr}" type="print" selectedCode="${detailApply.wtstAplcnt.sexdstnCdv}" />
			</td>
			 --%>
		</tr>
		<tr>
			<th scope="row">생년월일</th>
			<td>${detailApply.wtstAplcnt.brdt}
			</td>
			<th scope="row">휴대전화번호</th>
			<td>${detailApply.wtstAplcnt.mmbrTelno}</td>
		</tr>
		
		<tr>
			<th scope="row">구분</th>
			<td>
			<im:cd codeId="${workDvsnArr}" type="print" selectedCode="${detailApply.wtstAplcnt.workDvsnCdv}"/>
			</td>
			<th scope="row">현재 재직기관</th>
			<td>${detailApply.wtstAplcnt.agncyNm}</td>
		</tr>
		<tr>
			<th scope="row">이메일</th>
			<td colspan="3">
			${detailApply.wtstAplcnt.mberEmailAdres}
			</td>
		</tr>
		<tr>
			<th scope="row">사진</th>
			<td colspan="3">
				<div id="pht_view22" style="width:120px;height:160px;margin-bottom:10px;margin-left:10px;">
				<c:if test="${!empty detailApply.wtstAplcnt.phtFileId}">
				<img id="img_view_img" src="<c:url value="/im/cmmn/getImage.do?atchFileId="/>${detailApply.wtstAplcnt.phtFileId}&thumb=3" style="width:120px;height:160px;" alt="이미지">
				</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row">편의제공 여부(*)</th>
			<td colspan="3">
				<im:cd codeId="IMYENO" type="print" name="convPvsnYn" selectedCode="${detailApply.wtstAplcnt.convPvsnYn}" />
			</td>
		</tr>
		<tr>
			<th scope="row">편의제공 증빙</th>
			<td colspan="3">
				<c:forEach items="${detailApply.fileList2}" var="frow">
						<c:set var="file_Key" value="${frow.atchFileId}=${frow.fileSn}"/>
						<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<c:out value="${frow.orignlFileNm}"/>&nbsp;[<c:out value="${imfunc:fileSizeView(frow.fileMg)}"/>]
						</a>
				</c:forEach>
			</td>
		</tr>
	</table>	
	
	<c:if test="${!empty detailApply.wtstAplcnt.exempDvsnCdv}">
	<div class="cb_bar top">
		<p class="bl_title">추가증빙대상</p>
		</div>	
		<div class="ct_box bg bot">
			<ul class="radio">
				<li>이전 교육 수료 및 기본과정 수료 정보가 없습니다.</li>
				<li><a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="high">이력관리</a>에서 기본과정(단짝등) 수료증 혹은 유사과목 인정과목을 등록하여 주시기 바랍니다</li>
			</ul>
		</div>			
	</c:if>	
	
	<div class="cb_bar top">
		<p class="bl_title">심사정보</p>
	</div>
	<table class="tbl_row al">
		<colgroup>
			<col style="width:15%;">
			<col>
			<col style="width:15%;">
			<col>
		</colgroup>
		<tr>
			<th scope="row">원서접수상태</th>
			<td><im:cd type="print" codeId="IM0021" selectedCode="${aplyStatusCode}"/></td>
			<th scope="row">원서접수일자</th>
			<td><im:dt yyyyMMddHHmmss="${detailApply.wtstAplcnt.frstRegDt}"/></td>
		</tr>
		<c:if test="${!empty sttsMap['APLY'].sttsRmks}">
		<tr>
			<th scope="row">심사결과</th>
			<td colspan="3">${sttsMap['APLY'].sttsRmks}</td>
		</tr>
		</c:if>
	</table>	
	<c:if test="${aplyStatusCode eq '02' && detail.wtst.tstfee > 0 }"> <%--선정된 경우만 처리함  --%>
	<div class="cb_bar top">
		<p class="bl_title">결제 정보</p>
	</div>
	<table class="tbl_row al">
		<colgroup>
			<col style="width:15%;">
			<col>
			<col style="width:15%;">
			<col>
		</colgroup>
		<tr>
			<th scope="row">응시료</th>
			<td>
				<im:numberFormat value="${detail.wtst.tstfee}"/> 원
			</td>
			<th scope="row">계좌정보</th>
			<td><im:cd type="print" codeId="IM0005" selectedCode="${detail.wtstPlace.bnkCdv}" />&nbsp; ${detail.wtstPlace.accno} </td>
		</tr>
		<tr>
			<th scope="row">예금주</th>
			<td>${detail.wtstPlace.acchdr}</td>
			<th scope="row">납부기간</th>
			<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].endDt}"/></td>
		</tr>
		<tr>
			<th scope="row">문의처</th>
			<td><c:out value="${detail.wtstPlace.cntpnt}"/></td>
			<th scope="row">입금확인여부</th>
			<td>
			<im:cd type="print" codeId="${dpstYnArr}" selectedCode="${sttsMap['DPST'].sttsCdv}"/>
			</td>
		</tr>
	</table>
		</c:if>
		
	
	
	
	
	
<div class="ct_dialog" id="add_applyForm" style="display: none;">
<c:if test="${detail.rcptProcType eq 'I' || param['modifyYn'] eq 'Y'}">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:1024px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">원서 접수 정보 수정</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<ul class="item_li bot">
						<li>수험표 출력 전 반드시 사진 첨부 여부를 확인하여 주시기 바랍니다.</li>
						<li>자료 미비로 불이익이 없도록 신청 완료 후 이력관리 정보를 확인 바랍니다.</li>
<%-- 						<c:if test="${!empty detailApply.wtstAplcnt.exempDvsnCdv}"> --%>
<%-- 						<li><im:cd type="print" codeId="IM0001" selectedCode="${detail.wtst.crsGrdCdv}"/> 기본과정 수료정보가 없는 경우는 면제 정보를 작성 해야 합니다.</li> --%>
<%-- 						</c:if> --%>
					</ul>
					<form id="iMWtstAplcnt" name="iMWtstAplcnt" method="post" onsubmit="return false;" >
				<input type="hidden" name="wtstAplcntId"  value="${detailApply.wtstAplcnt.wtstAplcntId}"   />
				<input type="hidden" name="modifyYn"  value="${param['modifyYn']}" />
					<table class="tbl_row al">
						<colgroup>
							<col style="width:17%;">
							<col style="width:30%;">
							<col style="width:16%;">
							<col>
						</colgroup>
							<tr>
								<th scope="row">이름</th>
								<td colspan="3">
								${detailApply.wtstAplcnt.mberNm}
								</td>
								<%--
								<th scope="row">성별</th>
								<td>
									<im:cd codeId="${sexdstnCdvArr}" type="print" selectedCode="${detailApply.wtstAplcnt.sexdstnCdv}" />
								</td>
								 --%>
							</tr>
							<tr>
								<th scope="row">생년월일</th>
								<td>
								${detailApply.wtstAplcnt.brdtFormat}
								</td>
								<th scope="row">휴대전화번호(*)</th>
								<td>
								<input type="text" name="mblTelno1"  style="width: 60px;" size="3" maxlength="3" value="${detailApply.wtstAplcnt.mblTelno1}"/>-
								<input type="text" name="mblTelno2"  style="width: 60px;"  size="4" maxlength="4" value="${detailApply.wtstAplcnt.mblTelno2}" />-
								<input type="text" name="mblTelno3"  style="width: 60px;"  size="4" maxlength="4" value="${detailApply.wtstAplcnt.mblTelno3}" />
								</td>
							</tr>
							<tr>
								<th scope="row">구분</th>
								<td colspan="3">
								<im:cd codeId="${workDvsnArr}" type="radio" onclick="REQ.validator(this.value);" name="workDvsnCdv" selectedCode="${detailApply.wtstAplcnt.workDvsnCdv}" />
								</td>
							</tr>
							<tr>	
								<th scope="row">현재 재직기관</th>
								<td colspan="3"><div class="search_area">
										<input type="text" name="agncyNm" value="${detailApply.wtstAplcnt.agncyNm}" placeholder="재직기관을 선택하여 주세요" title="기관명" readonly="readonly">
										<input type="hidden" name="agncyCode" value="${detailApply.wtstAplcnt.agncyCode}">
										<a href="javascript:;" onclick="REQ.delPotalAgncyMain()"  class="del" title="삭제"><span class="sr_only">삭제</span></a>
									</div>										
												<a href="javascript:;" onclick="REQ.openPotalAgncyMain()" class="c_btn bk">찾기</a>
								
								</td>
							</tr>
							<tr>
								<th scope="row">이메일(*)</th>
								<td colspan="3">
								<input type="text" name="mberEmailAdres" value="${detailApply.wtstAplcnt.mberEmailAdres}" />
								</td>
							</tr>
							<tr>
								<th scope="row">사진첨부(*)</th>
								<td colspan="3">
								<input type="hidden" name="phtFileId"  value="${detailApply.wtstAplcnt.phtFileId}" />
									<input type="file" id="${PHT_FILE_ID}" value="파일찾기" name="${PHT_FILE_ID}" title="파일찾기" accept="${imExtensionsImages}" onchange="COMMT.previewImg(this, 'pht_view', 120, 160)">
									<div id="pht_view" style="width:120px;height:160px;margin-bottom:10px;margin-left:10px;">
									<c:if test="${!empty detailApply.wtstAplcnt.phtFileId}">
									<img id="img_view_img" src="<c:url value="/im/cmmn/getImage.do?atchFileId="/>${detailApply.wtstAplcnt.phtFileId}&thumb=3" style="width:120px;height:160px;" alt="이미지">
									</c:if>
									</div>
									권장 사이즈 : 120px * 160px (권장 비율  3*4)
								</td>
							</tr>
							<tr>
								<th scope="row">편의제공 여부(*)</th>
								<td colspan="3">
									<c:set var="convPvsnYnArr">Y=예,N=아니오</c:set>
									<im:cd codeId="${convPvsnYnArr}" type="radio" name="convPvsnYn" selectedCode="${detailApply.wtstAplcnt.convPvsnYn}"  />
								</td>
							</tr>
							<tr>
								<th scope="row">편의제공 증빙</th>
								<td colspan="3">
								<input type="hidden" name="convEvddocId" value="${detailApply.wtstAplcnt.convEvddocId}"  />
									<input type="file" id="${ATCH_FILE_ID}"   title="파일찾기" accept="${imExtensionsPdfFiles}" />
									
									<c:forEach items="${detailApply.fileList2}" var="frow">
											<c:set var="file_Key" value="${frow.atchFileId}=${frow.fileSn}"/>
											<br><a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
											<c:out value="${frow.orignlFileNm}"/>&nbsp;[<c:out value="${imfunc:fileSizeView(frow.fileMg)}"/>]
											</a>
									</c:forEach>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="exempDvsnCdv"  value="${detailApply.wtstAplcnt.exempDvsnCdv}"   />
					<c:if test="${!empty detailApply.wtstAplcnt.exempDvsnCdv}">
					<div class="cb_bar top">
					<p class="bl_title">추가증빙대상</p>
					</div>	
					<div class="ct_box bg bot">
						<ul class="radio">
							<li>이전 교육 수료 및 기본과정 수료 정보가 없습니다.</li>
							<li><a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="high">이력관리</a>에서 기본과정(단짝등) 수료증 혹은 유사과목 인정과목을 등록하여 주시기 바랍니다</li>
						</ul>
					</div>
					
					</c:if>
					<div class="b_box">
						<a href="javascript:;" onclick="REQ.applyFormClose()" class="c_btn wt">취소</a>
						<a href="javascript:;" onclick="REQ.save()" class="c_btn bk">저장</a>
					</div>
					</form>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
	</c:if>
</div>	
	
		

		
		
		
<%@ include file="/WEB-INF/jsp/inc/imScoreLayer.jspf" %>

<%@ include file="/WEB-INF/jsp/inc/imPotalOrg.jspf" %>

<%@ include file="/WEB-INF/jsp/inc/imConvenienceDiv.jspf" %>

