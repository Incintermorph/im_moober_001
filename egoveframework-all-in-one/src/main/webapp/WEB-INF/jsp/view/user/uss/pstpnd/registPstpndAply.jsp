<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<c:set var="ATCH_FILE_ID" value="file_id"/>

<script type="text/javascript" >


var REQ = {
	atchInfo : {
			uploadFolder : 'pstpndAply',
			fileInputId : '${ATCH_FILE_ID}'
	},
	req : {
		list : null,
		save : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/pstpndAply/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMPstpndAply"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/user/pstpndAply/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.cfg.fn.before = function() {
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
				 REQ.req.save.go("continue");
	    	}else{
	    	 COMMT.uploadCommonFileUser(REQ.atchInfo,function(data){
    			 if (data.result > 0) {
    				 if(data.result==1){
    					 //첨부파일 정보 성공인 경우 
	    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
	    		    	 form.elements["evddocId"].value = data.atchFileId;
    				 }
    			 }
    			 REQ.req.save.go("continue");
	    	 });
	    	}
    			 
	    };
	    this.req.save.validator.set(function(){
	    	var form = document.iMPstpndAply;
	    	return validateIMPstpndAply(form);
	    });
	    
	    this.req.save.validator.set(function(){
 	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
 	    		COMMT.message('보수교육 유예 사유를 증빙할 수 있는 서류를 첨부하세요.');
 	    		return false;
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
	    	if(!$('#agreeYN_06_01').prop("checked")){
				 COMMT.message('민감정보 수집 및 이용동의는 필수 입니다.');
				 return false;
			 }	else{
				 return true;
			 }
	    });
	    
	  //jquery ui datepicker
		$("[data-role='datepicker']" ).datepicker({
			showOn:"both",
			buttonText: '',
			dateFormat: 'yy-mm-dd',
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

<%@ include file="incPstpndAply.jsp" %>


<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imZipCode.jsp"/>

<validator:javascript formName="iMPstpndAply" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<div class="add">
		<a href="javascript:;" onclick="REQ.list()"  class="c_btn l_gray">목록</a>		
	</div>
</div>

<c:set var="pstpndRsnCdvArr">01=본인의 질병,02=그 밖의 불가피한 사유,03=기타</c:set>
<c:set var="trgtYearArr">1=1년,2=2년,3=3년</c:set>
<form:form commandName="iMPstpndAply" name="iMPstpndAply" method="post" onsubmit="return false;">
	<table class="tbl_row al">
		<colgroup>
			<col style="width:17%;">
			<col>
		</colgroup>
			<tbody>
			<tr>
				<th scope="row">취득시기</th>
				<td>
				<form:hidden path="dvsnCdv"/>
				<form:hidden path="sttsCdv"/>
				<form:hidden path="crsGrdCdv"/><form:hidden path="lcncEndYmd"/>
				<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${iMPstpndAply.dvsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">자격증 발급번호</th>
				<td><form:hidden path="lcncIssuCode"/>${iMPstpndAply.lcncIssuCode}</td>
			</tr>
			<tr>
				<th scope="row">자격증번호</th>
				<td><form:hidden path="qlfcRsltCode"/>${iMPstpndAply.qlfcRsltCode}</td>
			</tr>
			<tr>
				<th scope="row">자격취득일자</th>
				<td>
				<im:dt yyyyMMddHHmmss="${iMPstpndAply.lcncAcqsYmd}"/>
				<form:hidden path="lcncAcqsYmd"/></td>
			</tr>
			<tr>
				<th scope="row">이름</th>
				<td>${IMLoginUser.name}</td>
			</tr>
			<tr>
					<th scope="row">핸드폰번호 <span class="c_red">*</span></th>
					<td>
					<input type="text" name="mmbrTelno1"  size="3" maxlength="3" value="${detailHstry.mmbrHstry.mblTelno1}"/>-
					<input type="text" name="mmbrTelno2"  size="4" maxlength="4" value="${detailHstry.mmbrHstry.mblTelno2}" />-
					<input type="text" name="mmbrTelno3"  size="4" maxlength="4" value="${detailHstry.mmbrHstry.mblTelno3}" />
					</td>
			</tr>
			<tr>
				<th scope="row">생년월일 <span class="c_red">*</span></th>
				<td>
						<div class="c_date">
							<input type="text" name="brdt"  value="${detailHstry.mmbrHstry.brdtFormat}" title="생년월일" data-role="datepicker">
						</div>
				
				</td>
			</tr>
			<tr>
				<th scope="row">대상연도 <span class="c_red">*</span></th>
				<td>
					<im:cd type="radio" codeId="${trgtYearArr}" name="trgtYear"/>
					<br>
					<span style="color: red;font-size: 13px;">
					※ 대상연도: 자격증을 취득한 이후 소요된  기간을 의미함  (예시 : 2021년에 자격증 취득 > 현재 2022년이라면 대상연도 1년)
               		</span>
				</td>
			</tr>
			<tr>
					<th scope="row">주소<span class="c_red">*</span></th>
					<td>
						<input type="text" class="wd_sm" title="우편번호" id="_postcode_id"  name="zipc" readonly>
						<a href="javascript:;" class="c_btn bk" onclick="callPostcode()">우편번호 찾기</a>
						<input type="text" name="addr"  id="_address_id" readonly="readonly" class="wide mts" title="주소">
						<input type="text" name="addrDtl" id="_detailAddress_id"  maxlength="150" class="wide mts" title="상세 주소">
					</td>
			</tr>
			<tr>
				<th scope="row">유예사유 <span class="c_red">*</span></th>
				<td>
				<select name="pstpndRsnCdv">
				<option value="">선택</option>
				<im:cd type="option" codeId="${pstpndRsnCdvArr}"/>
				</select>
				</td>
			</tr>
			<tr>
				<th scope="row">유예사유 비고 <span class="c_red">*</span> </th>
				<td>
				<textarea name="pstpndRsn" id="" cols="" rows="" title="유예사유 비고"></textarea>
				</td>
			</tr>
			<tr>
				<th scope="row">첨부파일<span class="c_red">*</span></th>
				<td><form:hidden path="evddocId"/>
				보수교육 유예 사유를 증빙할 수 있는 서류를 첨부하세요.<br> 
				<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="evddocIdFile" title="파일찾기" accept="${imExtensionsFiles}" />
				
				</td>
			</tr>

	</tbody>
</table>
<p class="c_title">민감정보 수집 및 이용동의  (필수)</p>
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
									<td><span class="term_str">보수교육 유예사유 증명서류</span></td>
									<td>환경 교육사 보수교육 유예사유 확인</td>
									<td><span class="term_str">보수교육 유예기간 종료 후 3개월까지</span></td>
								</tr>
							</tbody>
						</table>
						<div class="ct_box">
							<ul class="ref">
								<li>위의 개인정보 수집・이용에 대한 동의를 거부할 권리가 있습니다.</li>
							</ul>
							<p>그러나, 동의를 거부할 경우 유예신청을 할 수 없습니다.</p>
						</div>
						<!-- //약관 -->
					</div>
					<div class="ag_term">
						<input type="checkbox" id="agreeYN_06_01">
						<label for="agreeYN_06_01">동의합니다.</label>
					</div>
</form:form>
		<div class="cb_bar">
			<div class="ct_box">
				<p class="p_noti"><span class="c_red"><i class="m_icon_out circle_notifications"></i>[안내]</span></p>
				<ul class="radio">
					<li>임시저장인 경우에는 추가 수정이 가능하나 유예 여부를 심사하지는 않습니다.</li>
					<li>유예 신청을 바로 하시려면 저장하시면 됩니다. 저장 후에는 수정이 불가합니다.</li>
				</ul>
			</div>
		</div>
	<div class="b_box">
			<a href="javascript:;" onclick="REQ.save('99')"  class="c_btn green mid">임시 저장</a>
			<a href="javascript:;" onclick="REQ.save('01')"  class="c_btn green mid">저장</a>
			<a href="javascript:;" onclick="REQ.list()" class="c_btn gray mid">목록</a>
		</div>