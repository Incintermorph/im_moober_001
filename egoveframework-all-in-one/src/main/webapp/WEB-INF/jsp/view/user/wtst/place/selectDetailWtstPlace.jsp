<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>



<c:set var="retureUrlVal">
/cmmn/wtstPlace/${crsGrd}/selectDetail.do?_paramMenuNo=${nowMenuNo}&wtstPlaceId=${param['wtstPlaceId']}
</c:set>

<c:set var="_msg" value=""/>
<c:if test="${empty detailMmbrHstry}">
<c:set var="_msg">이력관리에 등록된 정보가 없습니다. 이력관리 정보를 등록을 하시겠습니까?</c:set>
</c:if>	

<c:set var="ATCH_FILE_ID" value="convEvddocfile"/>
<c:set var="PHT_FILE_ID" value="phtfile"/>
<c:set var="ATCH_EXEMP_FILE_ID" value="convEXEMPdocfile"/>

<script type="text/javascript" >

var REQ = {

	<c:if test="${!empty imLogin_uniqId}">
	chekinfo : {
		applyCnt : <c:out value="${detail.applyCnt}"/>,
		prsnlLmt : <c:out value="${detail.wtstPlace.prsnlLmt}"/>,
		fishCount001 : <c:out value="${fishCount001}"/>,
		overCnt : <c:out value="${overCnt}"/>
	},
	</c:if>
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
	req : {
		list : null,
		save : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/cmmn/wtstPlace/"/>${crsGrd}/selectList.do";
				
		

		<c:if test="${!empty detailMmbrHstry}">
	    
	    REQ.validator('${detailMmbrHstry.mmbrHstry.workDvsnCdv}');
		</c:if>
	    
	},
	validator : function(workDvsnCdv){
		this.req.save = imRequest("ajax",{formId: "iMWtstAplcnt"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/wtstAplcnt/insert.do"/>";
		this.req.save.cfg.message.confirm="신청 하시겠습니까?";
		this.req.save.cfg.fn.complete = function(act, data) {
			if (data != null) { 
	        	if(data.result == 1){
	        		REQ.applyResult(data.resultVO.wtstAplcntId);
	        	}else{
	        		if(data.result==-1000){
	        			COMMT.message('신청하신 필기시험 입니다.');
	        		}
	        	}
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
	
	    
	    <%--
	    this.req.save.validator.set({
	    	title  : "성별",
            name : "sexdstnCdv",
            data : ["!null"]
        });
       --%>
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
	    	if(!COMMT.chkAttachFile('${PHT_FILE_ID}')){
	    		COMMT.message('사진을 첨부해 주세요.');
	    		return false;
	    	}else{
	    		return true;
	    	}
	    });

	    
	    this.req.save.validator.set(function(){
	    	var form =  document.getElementById(REQ.req.save.cfg.formId);
			if(form.elements["convPvsnYn"].value=='N'){
				return true;
			}else{
		    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
		    		COMMT.message('편의제공 증빙 자료를 첨부해 주세요.');
		    		return false;
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

	    this.req.save.validator.set(function(){
				 if(!$('#agreeYN_WT_01').prop("checked")){
					 COMMT.message('개인정보 수집 및 이용동의는 필수 입니다.');
					 return false;
				 }else{
					 return true;
				 }
	    });
	    this.req.save.validator.set(function(){
	    	var form =  document.getElementById(REQ.req.save.cfg.formId);
			if(form.elements["convPvsnYn"].value=='N'){
				return true;
			}else{
				 if(!$('#agreeYN_WT_02').prop("checked")){
					 COMMT.message('민감정보 수집 및 이용동의는 필수 입니다.');
					 return false;
				 }else{
					 return true;
				 }
			}
	    });
		
		
		
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
	},
	save :  function(){
		IMGLBObject.request=this;
		this.req.save.go();
	},
	manage : function(msg){
		$("#_manage_desc").html(msg);
		$("#manage").show();
	},
	manageClose : function(){
		$("#manage").hide();
	},
	apply : function(){
		//rcptProcType
		<c:if test="${detail.rcptProcType eq 'I'}">
		
		<c:if test="${empty imLogin_uniqId}">
		GREQ.loginChek('<c:out value="${retureUrlVal}" escapeXml="false" />');
		</c:if>
		<c:if test="${!empty imLogin_uniqId}">
			<c:if test="${!empty imLogin_uniqId}">
			<c:if test="${empty _msg}">
				//REQ.applyForm();
				REQ.registChoice();
			</c:if>
			<c:if test="${!empty _msg}">
				REQ.manage('${_msg}');
			</c:if>
			</c:if>
		</c:if>

		</c:if>
		<c:if test="${detail.rcptProcType ne 'I'}">
			COMMT.message('접수기간이 아닙니다.');
		</c:if>
		
	},
	applyForm : function(){
		<c:if test="${!empty imLogin_uniqId}">
		if(REQ.chekinfo.overCnt>0){
			COMMT.message('신청하신 필기시험 입니다.');
			return;
		}
		if(REQ.chekinfo.prsnlLmt<=REQ.chekinfo.applyCnt){
			COMMT.message('원서접수 가능한 정원이 되었습니다.');
			return;
		}
		<c:if test="${crsGrd eq 1}">
		if(REQ.chekinfo.fishCount001==0){
			COMMT.message('필기시험 대상자가 아닙니다.<br>필기시험은 <b>기본과정 수료자</b>만 접수 가능합니다.',388);
			return;
		}
		</c:if>
		
		
		$("#add_applyForm").show();
		var formreg =  document.getElementById("iMWtstAplcnt");
		if(formreg.elements["convPvsnYn"].value=='Y'){
			$("#convPvsn_file").show();
			$("#convPvsn_ag_term").show();
		} else{
			$("#convPvsn_file").hide();
			$("#convPvsn_ag_term").hide();
		}
		</c:if>
	},
	applyFormClose : function(){
		$("#add_applyForm").hide();
	},
	applyResult : function(wtstAplcntId){
		var form =  document.getElementById(REQ.req.list.cfg.formId);
		form.elements["wtstAplcntId"].value= wtstAplcntId;
		form.elements["wtstPlaceId"].value= '<c:out value="${param['wtstPlaceId']}"/>';
		REQ.req.list.cfg.url    = "<c:url value="/user/wtstPlace/"/>${crsGrd}/apply/result.do";
		REQ.req.list.go();
	},registChoice : function(){
		
		<c:if test="${!empty imLogin_uniqId}">
		if(REQ.chekinfo.overCnt>0){
			COMMT.message('신청하신 필기시험 입니다.');
			return;
		}
		if(REQ.chekinfo.prsnlLmt<=REQ.chekinfo.applyCnt){
			COMMT.message('원서접수 가능한 정원이 되었습니다.');
			return;
		}
		<c:if test="${crsGrd eq 1}">
		if(REQ.chekinfo.fishCount001==0){
			COMMT.message('필기시험 대상자가 아닙니다.<br>필기시험은 <b>기본과정 수료자</b>만 접수 가능합니다.',388);
			return;
		}
		</c:if>
		
		
		$("#dvsnCdvDiv").show();
		</c:if>
		
	},
	registChoiceCancel : function(){
		$("#dvsnCdvDiv").hide();
	},
	regist :  function(){
		var req = imRequest("script", {formId : "FormStts"}); 
		req.cfg.fn.exec  = function() {
			var form =  document.getElementById("FormStts");
			var formreg =  document.getElementById("iMWtstAplcnt");
			formreg.elements["convPvsnYn"].value = form.elements["convPvsnYn1"].value;
			$("#dvsnCdvDiv").hide();
			REQ.applyForm();
			//REQ.req.regist.go();
	    };

	    req.validator.set({
	        title : "편의제공 여부",
	        name : "convPvsnYn",
	        data : ["!null"]
	    });
	    req.go();

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
}


$(document).ready(function(){
	REQ.init();
});

</script>


<div class="ct_box bg bot">
	<span class="p_noti red"><i class="m_icon_out circle_notifications"></i>중요 </span>: 환경교육사 사이트 신규 운영에 따라  <a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="high">이력관리</a> 재등록이 필요합니다.
	<ul class="radio">
		<li>환경교육사 기본 과정 수료자는 추가 증빙 없이 원서 접수가 가능합니다.</li>
		<li>유사과목 면제자는 <a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="high">이력관리</a>에서 유사면제과목을  등록하여야 자격심사 절차를 거쳐  필기평가에 응시 할 수 있습니다.</li>
		<li>단짝 수료자는 이력관리에서 단짝 수료증을 등록하여야 자격심사 절차를 거쳐  필기평가에 응시 할 수 있습니다.</li>
		<li>원서접수는 정원과 대기자를 포함하여 마감되며 대기자의 경우에는 결원이 없는 경우 응시하실 수 없을 수도 있습니다.</li>
	</ul>
</div>

<%@ include file="incWtstPlace.jsp" %>
<div class="cb_bar right">
			<div class="add">
				<a href="javascript:;" onclick="REQ.list()" class="c_btn l_gray">목록</a>
				<a href="<c:url value="/cmmn/page/intro06/page.do"/>"   class="c_btn l_gray">자격취득 안내</a>
				<a href="javascript:;" onclick="showConvenience()"   class="c_btn l_gray">편의 제공 안내</a>
				<a href="javascript:;" onclick="REQ.apply()" class="c_btn bk">원서접수</a>
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
			<td colspan="3"><c:out value="${detail.agncy.addr}"/>&nbsp;&nbsp;<c:out value="${detail.agncy.addrDtl}"/></td>
		</tr>
		<tr>
			<th scope="row">문의처</th>
			<td colspan="3"><c:out value="${detail.wtstPlace.cntpnt}"/></td>
		</tr>
</table>	
<div class="cb_bar right mt">
			<div class="add">
				<a href="javascript:;" onclick="REQ.list()" class="c_btn l_gray">목록</a>
				<a href="<c:url value="/cmmn/page/intro06/page.do"/>"   class="c_btn l_gray">자격취득 안내</a>
				<a href="javascript:;" onclick="showConvenience()"   class="c_btn l_gray">편의 제공 안내</a>
				<a href="javascript:;" onclick="REQ.apply()" class="c_btn bk">원서접수</a>
			</div>
		</div>
 
 
<div class="ct_dialog" id="add_applyForm" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:1024px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">원서 접수</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<ul class="item_li bot">
						<li>유사과목 면제자는 이력관리에서 유사면제과목을  등록하여야 자격심사 절차를 거쳐  필기평가에 응시 할 수 있습니다.</li>
						<li>단짝 수료자는 이력관리에서 단짝 수료증을 등록하여야 자격심사 절차를 거쳐  필기평가에 응시 할 수 있습니다.</li>
						<li>자료 미비로 불이익이 없도록 원서접수 전  이력관리 정보를 확인하시기 바랍니다.</li>
						<c:if test="${fishCount001 eq '0'}">
						<li>${crsGrd}급 기본과정 수료정보가 없는 경우는 면제 정보를 작성 해야 합니다.</li>
						</c:if>
					</ul>
					<form id="iMWtstAplcnt" name="iMWtstAplcnt" method="post" onsubmit="return false;" >
					<c:if test="${!empty detailMmbrHstry}">
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
								${imLogin_name}
								<input type="hidden" name="wtstPlaceId"  value="${param['wtstPlaceId']}"   />
								
								<c:if test="${fishCount001 eq '0'}">
								<input type="hidden" name="exempDvsnCdv"  value="Y"   />
								</c:if>
								<c:if test="${fishCount001 ne '0'}">
								<input type="hidden" name="exempDvsnCdv"  value="N"   />
								</c:if>
								</td>
								<%--
								<th scope="row">성별</th>
								<td>
									<c:set var="sexdstnCdvArr">M=남,F=여</c:set>
									<input type="hidden" name="sexdstnCdv"  value="${detailMmbrHstry.mmbrHstry.sexdstnCdv}"   />
									<im:cd codeId="${sexdstnCdvArr}" type="print" selectedCode="${detailMmbrHstry.mmbrHstry.sexdstnCdv}" />
								</td>
								 --%>
							</tr>
							<tr>
								<th scope="row">생년월일</th>
								<td>
								<div class="c_date">${detailMmbrHstry.mmbrHstry.brdtFormat}
									
									<input type="hidden" name="brdt"  value="${detailMmbrHstry.mmbrHstry.brdt}"   />
								</div>
								</td>
								<th scope="row">휴대전화번호(*)</th>
								<td>
								<input type="text" name="mblTelno1"  style="width: 60px;" size="3" maxlength="3" value="${detailMmbrHstry.mmbrHstry.mblTelno1}"/>-
								<input type="text" name="mblTelno2"  style="width: 60px;"  size="4" maxlength="4" value="${detailMmbrHstry.mmbrHstry.mblTelno2}" />-
								<input type="text" name="mblTelno3"  style="width: 60px;"  size="4" maxlength="4" value="${detailMmbrHstry.mmbrHstry.mblTelno3}" />
								</td>
							</tr>
							<tr>
								<th scope="row">구분(*)</th>
								<td colspan="3">
								<im:cd codeId="${workDvsnArr}" type="radio" onclick="REQ.validator(this.value);" name="workDvsnCdv" selectedCode="${detailMmbrHstry.mmbrHstry.workDvsnCdv}" />
								</td>
							</tr>
							<tr>	
								<th scope="row">현재 재직기관</th>
								<td colspan="3"><div class="search_area">
										<input type="text" name="agncyNm" value="${detailMmbrHstry.mmbrHstry.agncyNm}" placeholder="재직기관을 선택하여 주세요" title="기관명" readonly="readonly">
										<input type="hidden" name="agncyCode" value="${detailMmbrHstry.mmbrHstry.agncyCode}">
										<a href="javascript:;" onclick="REQ.delPotalAgncyMain()"  class="del" title="삭제"><span class="sr_only">삭제</span></a>
									</div>												
									<a href="javascript:;" onclick="REQ.openPotalAgncyMain()" class="c_btn bk">찾기</a>
								
								</td>
							</tr>
							
							
							
							<tr>
								<th scope="row">이메일(*)</th>
								<td colspan="3">
								<input type="text" name="mberEmailAdres" value="${detailMmbrHstry.mmbrHstry.eml}" />
								</td>
							</tr>
							<tr>
								<th scope="row">사진첨부(*)</th>
								<td colspan="3">
								<input type="hidden" name="phtFileId"  />
									<input type="file" id="${PHT_FILE_ID}" value="파일찾기" name="${PHT_FILE_ID}" title="파일찾기" accept="${imExtensionsImages}" onchange="COMMT.previewImg(this, 'pht_view', 120, 160)">
									<div id="pht_view" style="width:120px;height:160px;margin-bottom:10px;margin-left:10px;"></div>
									권장 사이즈 : 120px * 160px (권장 비율  3*4)
								</td>
							</tr>
							<tr style="display: none;">
								<th scope="row">편의제공 여부(*)</th>
								<td colspan="3">
									<c:set var="convPvsnYnArr">Y=예,N=아니오</c:set>
									<im:cd codeId="${convPvsnYnArr}" type="radio" name="convPvsnYn" defaultSelectedCode="N"  />
								</td>
							</tr>
							<tr id="convPvsn_file">
								<th scope="row">편의제공 증빙(*)</th>
								<td colspan="3">
								<input type="hidden" name="convEvddocId" />
									<input type="file" id="${ATCH_FILE_ID}"   title="파일찾기" accept="${imExtensionsPdfFiles}" />
								</td>
							</tr>
						</tbody>
					</table>
					
					<c:if test="${fishCount001 eq '0'}">
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
					<p class="c_title top">개인정보 수집 및 이용동의(필수)</p>
					<div class="termbox">
						<!-- 약관 -->
						<table class="tbl_data line mo_sm">
							<caption>개인정보 수집 및 이용동의</caption>
							<colgroup>
								<col>
								<col>
								<col style="width:25%;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">수집・이용하는 개인정보의 항목</th>
									<th scope="col">개인정보의 수집・이용 목적</th>
									<th scope="col">보유 및 이용 기간</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>사진, 수험번호</td>
									<td>환경교육사 자격시험 응시자 본인 확인 및 자격⦁이력 관리</td>
									<td><span class="term_str">준영구</span></td>
								</tr>
							</tbody>
						</table>
						<div class="ct_box">
							<ul class="ref">
								<li>위의 개인정보 수집・이용에 대한 동의를 거부할 권리가 있습니다.</li>
							</ul>
							<p>그러나, 동의를 거부할 경우 환경교육사 필기시험 원서접수를 할 수 없습니다.</p>
						</div>
						<!-- //약관 -->
					</div>
					
					<div class="ag_term">
						<input name="cmmnCdvRefNms" type="hidden" value="agreeYN_WT_01"/>
						<input type="checkbox" id="agreeYN_WT_01" name="agreeYN_WT_01">
						<label for="agreeYN_WT_01">동의합니다.</label>
					</div>
					
					<div id="convPvsn_ag_term">
					<p class="c_title top">민감정보 수집 및 이용동의(필수)</p>
					<div class="termbox">
						<!-- 약관 -->
						<table class="tbl_data line mo_sm">
							<caption>민감정보 수집 및 이용동의</caption>
							<colgroup>
								<col>
								<col>
								<col style="width:25%;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">수집・이용하는 개인정보의 항목</th>
									<th scope="col">개인정보의 수집・이용 목적</th>
									<th scope="col">보유 및 이용 기간</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><span class="term_str">편의제공 대상자 증명서류</span></td>
									<td>필기시험 편의제공 대상자 확인</td>
									<td><span class="term_str">필기시험일 후 30일까지</span></td>
								</tr>
							</tbody>
						</table>
						<div class="ct_box">
							<ul class="ref">
								<li>위의 개인정보 수집・이용에 대한 동의를 거부할 권리가 있습니다.</li>
							</ul>
							<p>그러나, 동의를 거부할 경우 환경교육사 필기시험 시 편의제공을 받을 수 없습니다.</p>
						</div>
						<!-- //약관 -->
					</div>
					<div class="ag_term"><input name="cmmnCdvRefNms" type="hidden" value="agreeYN_WT_02"/>
						<input type="checkbox" name="agreeYN_WT_02" id="agreeYN_WT_02">
						<label for="agreeYN_WT_02">동의합니다.</label>
					</div>
					<div class="ct_box">
						<ul class="ref">
							<li>민감정보 수집 및 이용 동의를 거부하시는 경우에는 편의제공 불필요로 신청해 주세요.</li>
						</ul>
					</div>
					</div>
					<div class="b_box">
						<a href="#" onclick="REQ.applyFormClose()" class="c_btn wt">취소</a>
						<a href="#" onclick="REQ.save()" class="c_btn bk">신청</a>
					</div>
					</c:if>
					</form>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
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



<div class="ct_dialog" id="dvsnCdvDiv" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:600px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">편의제공 선택</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<ul class="item_li bot">
						<li>편의제공에 대한 안내가 필요하시면 편의제공 안내문을 확인하여 주시기 바랍니다</li> 
						<li>편의제공 선택 시 증빙 서류를 추가하셔야 합니다.</li>
						<li>편의제공은 장애유형별 등 편의 제공을 의미합니다.(가산점 해당 없음)</li>
					</ul>
					<form name="FormStts" id="FormStts" method="post" onsubmit="return false;">
					<table class="tbl_row al">
						<colgroup>
							<col style="width:35%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">편의제공 여부 (*)</th>
								<td><c:set var="convPvsnYnArr1">Y=예,N=아니오</c:set>
									<im:cd type="radio" codeId="${convPvsnYnArr1}" name="convPvsnYn1"/>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
					<div class="b_box">
						<a href="javascript:;" onclick="REQ.regist();return false;" class="c_btn bk">확인</a>
						<a href="javascript:;" onclick="REQ.registChoiceCancel();return false;" class="c_btn wt">취소</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>	


<%@ include file="/WEB-INF/jsp/inc/imPotalOrg.jspf" %>

<%@ include file="/WEB-INF/jsp/inc/imConvenienceDiv.jspf" %>