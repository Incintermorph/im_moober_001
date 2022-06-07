<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<c:set var="ATCH_FILE_ID" value="file_id"/>
<c:set var="FMLY_FILE_ID" value="fmly_file_id"/>
<script type="text/javascript" >


var REQ = {

	atchInfo : {
		uploadFolder : 'issyAply',
		encryption: true, 
		fileInputId : '${ATCH_FILE_ID}'
	},
	atchInfo2 : {
		uploadFolder : 'issyAplyFaly',
		encryption: true, 
		fileInputId : '${FMLY_FILE_ID}'
	},
	req : {
		list : null,
		save : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/issuAply/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMIssuAply"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/issuAply/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	        
	        
	    };	
	    
	    this.req.save.cfg.fn.before = function() {
	    	COMMT.uploadCommonFileUser(REQ.atchInfo,function(data){
    			 if (data.result > 0) {
	    				 if(data.result==1){
	    					 //첨부파일 정보 성공인 경우 
		    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
		    		    	 form.elements["evddocId"].value = data.atchFileId;
	    				 }
    					 
    					 COMMT.uploadCommonFileUser(REQ.atchInfo2,function(data){
    		    			 if (data.result > 0) {
    		    				 if(data.result==1){
    		    					 //첨부파일 정보 성공인 경우 
    			    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
    			    		    	 form.elements["fmlyEvddocId"].value = data.atchFileId;
    			    		    	 REQ.req.save.go("continue");
    		    				 }
    		    			 }else{
    		                    COMMT.errorMessage();
    		    			 }
    		    		});
    				 
                    } else {
                    	COMMT.errorMessage();
                   }
    		});
    }
	    
	    
	    
	    
	    this.req.save.validator.set(function(){
	    	var form = document.iMIssuAply;
	    	return validateIMIssuAply(form);
	    });
	    
	    this.req.save.validator.set({  
            title : "주민번호 앞자리", 
            name : "rrno1", 
            data : ["!null", "number"], 
            check : {  
            	minlength : 6 
            }  
        } );
	    
	    this.req.save.validator.set({  
            title : "주민번호 뒷자리", 
            name : "rrno2", 
            data : ["!null", "number"], 
            check : {  
            	minlength : 7 
            }  
        } );
	    
	    
	    <c:if test="${iMIssuAply.issuDvsnCdv eq '01'}">
	    this.req.save.validator.set(function(){
 	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
 	    		COMMT.message('자격요건을 갖추었음을 증명하는 서류을 첨부해 주세요.');
 	    		return false;
 	    	}else{
 	    		if(!COMMT.chkAttachFileSize(REQ.atchInfo)){
	    			return false;	
	    		}else{
	    			return true;
	    		}
 	    	}
 	    });
	    this.req.save.validator.set(function(){
 	    	if(!COMMT.chkAttachFile('${FMLY_FILE_ID}')){
 	    		COMMT.message('가족관계 기본 증명서 파일을 첨부해 주세요.');
 	    		return false;
 	    	}else{
 	    		if(!COMMT.chkAttachFileSize(REQ.atchInfo2)){
	    			return false;	
	    		}else{
	    			return true;
	    		}
 	    	}
 	    });
	    </c:if>
	    <c:if test="${iMIssuAply.issuDvsnCdv ne '01'}">
	    this.req.save.validator.set(function(){
 	    	if(!COMMT.chkAttachFile('${FMLY_FILE_ID}')){
 	    		COMMT.message('가족관계 기본 증명서 파일을 첨부해 주세요.');
 	    		return false;
 	    	}else{
 	    		return true;
 	    	}
 	    });
	    </c:if>
	    

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
			 if(COMMT.chkAttachFile(REQ.atchInfo2.fileInputId)){
			    	if(!COMMT.chkAttachFileSize(REQ.atchInfo2)){
			    			return false;	
			    	}else{
			    			return true;
			    	}
		    	}else{
		    		return true;
		    	}
		    });

	    this.req.save.validator.set(function(){
	    	if(!$('#agreeYN_05_01').prop("checked")){
				 COMMT.message('개인정보 수집 및 이용동의는 필수 입니다.');
				 return false;
			 }	else{
				 return true;
			 }
	    });
	    
	    

	    
	    
	    
	    

	    $("[data-role='datepicker']" ).datepicker({
			showOn:"both",
			buttonText: '',
			dateFormat: '${imdatepickerDateType}',
			changeYear: true,
			yearRange: "1900:+nn"
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
	save :  function(sttsCdv){
		IMGLBObject.request=this;
		var form =  document.getElementById(REQ.req.save.cfg.formId);
   	 	form.elements["sttsCdv"].value = sttsCdv;
		this.req.save.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>


<%@ include file="incIssuAply.jsp" %>
<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imZipCode.jsp"/>

<c:set var="cntneduBgnDt" />
<c:if test="${!empty  lastMmbrQlfc.cntneduBgnDt}">
<c:set var="cntneduBgnDt" >
<im:dt yyyyMMddHHmmss="${lastMmbrQlfc.cntneduBgnDt}"/>
</c:set>
</c:if>

<c:set var="cntneduEndDt" />
<c:if test="${!empty  lastMmbrQlfc.cntneduEndDt}">
<c:set var="cntneduEndDt" >
<im:dt yyyyMMddHHmmss="${lastMmbrQlfc.cntneduEndDt}"/>
</c:set>
</c:if>
<validator:javascript formName="iMIssuAply" staticJavascript="false" xhtml="true" cdata="false"/>
		<div class="ct_box bot">
			<p class="p_noti"><span class="c_red"><i class="m_icon_out circle_notifications"></i>[안내]</span></p>
			<ul class="radio">
				<li>법 개정(22년 1월 6일) 이전 자격증도 발급 신청도 가능합니다.</li>
				<li>자격이 정지 혹은 취소된 경우에는 자격증 발급 및 출력이 제한됩니다.</li>
			</ul>
		</div>
		
<div class="cb_bar right">
	<div class="add">
		<a href="javascript:;" onclick="REQ.list()"  class="c_btn l_gray">목록</a>
	</div>
</div>
		<form:form commandName="iMIssuAply" name="iMIssuAply" method="post" onsubmit="return false;">
		<table class="tbl_row al">
			<colgroup>
				<col style="width:17%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">발급 구분</th>
					<td><im:cd type="print" codeId="IM0022" name="issuDvsnCdv"  selectedCode="${iMIssuAply.issuDvsnCdv}"/>
						<form:hidden path="sttsCdv" />
						<form:hidden path="issuDvsnCdv"/>
						<form:hidden path="brdt"/>
					</td>
				</tr>
				<tr>
					<th scope="row">자격증번호</th>
					<td>
					${iMIssuAply.qlfcRsltCode}
					<input type="hidden"  name="qlfcRsltCode"    value="${iMIssuAply.qlfcRsltCode}">
					</td>
				</tr>
				<tr>
					<th scope="row">이름</th>
					<td>${IMLoginUser.name}</td>
				</tr>
				<tr>
					<th scope="row">이수 양성기관명</th>
					<td>
						<select name="agncyId">
						<option value="">선택</option>
						<c:forEach items="${agncyList}" var="row">
						<option value="${row.agncy.agncyId}"
						<c:if test="${lastMmbrQlfc.cntneduAgncyId eq row.agncy.agncyId}">
						selected
						</c:if>						
						>${row.agncy.agncyNm}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row">등급</th>
					<td>
					<input type="hidden"  name="crsGrdCdv"    value="${iMIssuAply.crsGrdCdv}">
					<im:cd type="print" name="crsGrdCdv" selectedCode="${iMIssuAply.crsGrdCdv}" codeId="IM0001"/>
					</td>
				</tr>
				<tr>
					<th scope="row">교육 기간</th>
					<td>
						<div class="c_date">
							<input type="text" name="eduBgnDt" value="${cntneduBgnDt}" title="시작일" data-role="datepicker">
						</div>
						~
						<div class="c_date">
							<input type="text" name="eduEndDt"  value="${cntneduEndDt}"  title="종료일" data-role="datepicker">
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">취득일자</th>
					<td>
						<div class="c_date">
							<im:dt yyyyMMddHHmmss="${lastMmbrQlfc.lcncAcqsYmd}"/>
							<input type="hidden" name="lcncAcqsYmd" value="${lastMmbrQlfc.lcncAcqsYmd}">
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">주민등록 번호</th>
					<td>
						<input type="text" name="rrno1" class="mo_wide ac" title="주민등록 번호"    size="6" maxlength="6"  >
						-
						<input type="password" name="rrno2" class="mo_wide ac" title="주민등록 번호"    size="7" maxlength="7"  >
						<br> ※ 주민등록번호, 범죄경력자료 수집 근거 : 환경교육의 활성화 및 지원에 관한 법률 시행령 제22조
					</td>
				</tr>
				<tr>
					<th scope="row">핸드폰번호</th>
					<td>
						<form:input path="mmbrTelno1" size="4" maxlength="4"/>-<form:input path="mmbrTelno2" size="4" maxlength="4"/>-<form:input path="mmbrTelno3" size="4" maxlength="4"/>
					</td>
				</tr>
				<tr>
					<th scope="row">주소</th>
					<td>
						<input type="text" class="wd_sm" title="우편번호" id="_postcode_id"  name="zipc" readonly>
						<a href="javascript:;" class="c_btn bk" onclick="callPostcode()">우편번호 찾기</a>
						<input type="text" name="addr"  id="_address_id" readonly="readonly" class="wide mts" title="주소">
						<input type="text" name="addrDtl" id="_detailAddress_id"  maxlength="200" class="wide mts" title="상세 주소">
					</td>
				</tr>
				<tr>
					<th scope="row">발급 사유 비고</th>
					<td>
						<textarea name="issuRsn" id="" cols="" rows="" title="발급 사유 비고"></textarea>
					</td>
				</tr>
				<c:if test="${iMIssuAply.issuDvsnCdv eq '01'}">
				<tr>
					<th scope="row">증빙 첨부</th>
					<td>
						<p class="tb_note md m_0">1. 자격요건을 갖추었음을 증명하는 서류</p>
						<form:hidden path="evddocId"/>
						<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="evddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
						<p class="tb_note md">2. 가족관계 기본 증명서</p>
						<form:hidden path="fmlyEvddocId"/>
						<input type="file" id="${FMLY_FILE_ID}" value="${FMLY_FILE_ID}" name="fmlyEvddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
						
					</td>
				</tr>
				</c:if>
				<c:if test="${iMIssuAply.issuDvsnCdv ne '01'}">
				<tr>
					<th scope="row">증빙 첨부</th>
					<td>
						<p class="tb_note md m_0">1. 가족관계 기본 증명서</p>
						<form:hidden path="fmlyEvddocId"/>
						<input type="file" id="${FMLY_FILE_ID}" value="${FMLY_FILE_ID}" name="fmlyEvddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
						<p class="tb_note md">2.환경교육사 자격증</p>
						<form:hidden path="evddocId"/>
						<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="evddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
						<br/><span style="color: red;">*환경교육사 자격증 분실 시 제외 가능</span>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		
		<p class="c_title">개인정보 수집 및 이용동의(필수)</p>
					<div class="termbox">
						<!-- 약관 -->
						<table class="tbl_data line mo_sm">
							<caption>개인정보 수집 및 이용동의</caption>
							<colgroup>
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">개인정보를 제공받는 자</th>
									<th scope="col">제공하는 개인정보의 항목</th>
									<th scope="col">개인정보를 제공받는 자의<br>개인정보 이용목적</th>
									<th scope="col">개인정보를 제공받는 자의<br>개인정보 이용기간 및 보유기간</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>환경교육사 양성기관, 보수교육 운영기관</td>
									<td>성명, 성별, 주소, 생년월일, 휴대폰번호, 이메일, 재직기관(구분, 재직기관명, 직급), 학력사항(학교명, 과정(학과,전공), 학위, 학위취득일, 학위증명서), 환경교육 경력사항(활동분야, 재직기간, 활동시간, 확인기관, 활동내역, 확인서, 정관)</td>
									<td>본인실명확인, 교육 참가자격 요건</td>
									<td>당사자가 이용중지 요청 시까지</td>
								</tr>
							</tbody>
						</table>
						<!-- //약관 -->
					</div>
					<div class="ag_term">
						<input type="checkbox" id="agreeYN_05_01">
						<label for="agreeYN_05_01">동의합니다.</label>
					</div>
		</form:form>
		<div class="cb_bar">
			<div class="ct_box">
				<p class="p_noti"><span class="c_red"><i class="m_icon_out circle_notifications"></i>[안내]</span></p>
				<ul class="radio">
					<li>임시저장인 경우에는 추가 수정이 가능하나 자격증 발급 가능 여부를 심사하지는 않습니다.</li>
					<li>자격증 발급 신청을 바로 하시려면 저장하시면 됩니다. 저장 후에는 수정이 불가합니다.
				</ul>
			</div>
		</div>
		<div class="b_box">
			<a href="javascript:;" onclick="REQ.save('99')"  class="c_btn green mid">임시저장</a>
			<a href="javascript:;" onclick="REQ.save('01')"  class="c_btn green mid">저장</a>
			<a href="javascript:;" onclick="REQ.list()" class="c_btn gray mid">목록</a>
		</div>
		
