<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


<c:set var="IMG_FILE_ID" value="logofile"/>
<c:set var="SEAL_FILE_ID" value="sealfile"/>

var REQ = {
	imgInfo : {
		uploadFolder : 'agncy',
		fileInputId : '${IMG_FILE_ID}'
	},
	sealInfo : {
		uploadFolder : 'agncy_seal',
		fileInputId : '${SEAL_FILE_ID}'
	},
	req : {
		list : null,
		save : null,
		del : null,
		overchek : null,
	},
	overchekbrno : null,
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/agncy/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMAgncy"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/agncy/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.cfg.fn.before = function() {
	    	if(!COMMT.chkAttachFile('${IMG_FILE_ID}') && !COMMT.chkAttachFile('${SEAL_FILE_ID}')){
	    		return true;
	    	}else{
		    	COMMT.uploadImageFile(REQ.imgInfo,function(data){
	    			 if (data.result > 0) {
	    				 if(data.result==1){
	    					 //첨부파일 정보 성공인 경우 
		    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
		    		    	 form.elements["logoFileId"].value = data.atchFileId;
	    				 }
	    				 
	    				 if(!COMMT.chkAttachFile('${SEAL_FILE_ID}')){
	    					 REQ.req.save.go("continue");
	    				 }else{
	    					 
	    					 COMMT.uploadImageFile(REQ.sealInfo,function(data){
	    		    			 if (data.result > 0) {
	    		    				 if(data.result==1){
	    		    					 //첨부파일 정보 성공인 경우 
	    			    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
	    			    		    	 form.elements["sealFileId"].value = data.atchFileId;
	    		    				 }
	    		    				 REQ.req.save.go("continue");
	    		    				 
	    		                    } else {
	    		                    	COMMT.errorMessage();
	    		                   }
	    		    		});
	    					 
	    				 }
	                    } else {
	                    	COMMT.errorMessage();
	                   }
	    		});
	    		return false;
	    	}
	    }
	    this.req.save.validator.set(function(){
	    	var form = document.iMAgncy;
	    	return validateIMAgncy(form);
	    });
	    
	    this.req.save.validator.set(function(){
	    	var form =  document.getElementById(REQ.req.save.cfg.formId);
	    	 if(form.elements["logoFileId"].value==""){
			    	if(!COMMT.chkAttachFile('${IMG_FILE_ID}')){
			    		COMMT.message('<spring:message code="im.common.msg.no.image" />');
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
	    	 if(form.elements["sealFileId"].value==""){
		    	if(!COMMT.chkAttachFile('${SEAL_FILE_ID}')){
		    		COMMT.message('직인 파일을 선택하세요.');
		    		return false;
		    	}else{
		    		return true;
		    	}
	    	 }else{
	    			return true;
	    	 }
	    });
	    
	    
	    this.req.save.validator.set({
	    	title : "<spring:message code="iMAgncy.brno"/>",
            name : "brno",
            data : ["!null","number"]
        });
	    
	    this.req.save.validator.set(function(){
	    	var form =  document.getElementById(REQ.req.save.cfg.formId);
	    	if(REQ.overchekbrno == form.elements["brno"].value){
	    		return true;
	    	}else{
	    		COMMT.message('사업자등록번호 중복 확인 필요합니다.');
	    		return false;
	    	}
	    });
	    
	    
	    
	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMAgncy";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/mng/agncy/delete.do"/>";
	    this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
	    this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.page(1);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    
	
	    
	    this.req.overchek = imRequest("ajax",{formId: "iMAgncy"});
		this.req.overchek.cfg.type   = "json";
		this.req.overchek.cfg.url    =  "<c:url value="/mng/agncy/overchek.do"/>";
		this.req.overchek.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result >= 0) { 
	        	if(data.result==0){
	        		var form =  document.getElementById(REQ.req.overchek.cfg.formId);
	        		REQ.overchekbrno=form.elements["brno"].value;
	        		COMMT.message('사용 가능한 사업자등록번호 입니다.');
	        	}else{
	        		REQ.overchekbrno=null;
	        		COMMT.message('이미 사용중인 사업자등록번호 입니다.');
	        	}
	        } else {
	        	COMMT.errorMessage();
	        }
	    };
	    
	    this.req.overchek.validator.set({
	    	title : "<spring:message code="iMAgncy.brno"/>",
            name : "brno",
            data : ["!null","number"]
        });
	    
	    REQ.overchekbrno='${iMAgncy.brno}';
	
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
	del :  function(){
		IMGLBObject.request=this;
		this.req.del.go();
	},
	overchek :  function(){
		IMGLBObject.request=this;
		this.req.overchek.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imZipCode.jsp"/>

<%@ include file="incAgncy.jsp" %>


<validator:javascript formName="iMAgncy" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="M" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMAgncy" name="iMAgncy" method="post" onsubmit="return false;">
<form:hidden path="agncyId"/>
<table class="tbl_row">
	<caption>양성기관 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.agncyNm"/><span class="c_red">*</span></th>
				<td><form:input path="agncyNm"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.agncyDvsn"/><span class="c_red">*</span></th>
				<td>
				<im:cd type="radio" codeId="IM0004"  except="AGNCYDVSN_03"  name="agncyDvsnCdv" selectedCode="${iMAgncy.agncyDvsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.brno"/><span class="c_red">*</span></th>
				<td><form:input path="brno" maxlength="12"/>
				<a href="#" onclick="REQ.overchek()" class="ml ad_btn gray">중복 확인</a>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.crsGrd"/><span class="c_red">*</span></th>
				<td><im:cd type="checkbox" codeId="IM0001" name="crsGrdCdv"  selectedCode="${iMAgncy.crsGrdCdv}" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.telno"/><span class="c_red">*</span></th>
				<td><form:input path="telno1" size="4" maxlength="4"/>-<form:input path="telno2" size="4" maxlength="4"/>-<form:input path="telno3" size="4" maxlength="4"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.fax"/></th>
				<td><form:input path="fax"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.bnk"/></th>
				<td>
					<select name="bnkCdv">
						<option value="">선택</option>
						<im:cd type="option" codeId="IM0005" selectedCode="${iMAgncy.bnkCdv}"/>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.accno"/></th>
				<td><form:input path="accno" maxlength="15"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.acchdr"/></th>
				<td><form:input path="acchdr"  maxlength="20"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.zipc"/><span class="c_red">*</span></th>
				<td><form:input path="zipc" id="_postcode_id" readonly="readonly"/>
				<a href="#" class="ml ad_btn gray" onclick="callPostcode()">주소찾기</a>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.addr"/><span class="c_red">*</span></th>
				<td><form:input path="addr" id="_address_id" readonly="readonly"/>				
				<form:hidden path="addrSido" id="_SIDO"/>
				<form:hidden path="addrSigungu" id="_SIGUNGU"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.addrDtl"/><span class="c_red">*</span></th>
				<td><form:input path="addrDtl" id="_detailAddress_id"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.area"/><span class="c_red">*</span></th>
				<td>
				<select name="areaCdv">
						<option value="">선택</option>
						<im:cd type="option" codeId="IM0007" selectedCode="${iMAgncy.areaCdv}"/>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.areaDtl"/><span class="c_red">*</span></th>
				<td><form:input path="areaDtl" maxlength="200"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.agncyUrl"/></th>
				<td><form:input path="agncyUrl"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.logo"/><span class="c_red">*</span></th>
				<td><form:hidden path="logoFileId"/>
				<input type="file" id="${IMG_FILE_ID}" value="파일찾기" name="${IMG_FILE_ID}" title="파일찾기" accept="${imExtensionsImages}" onchange="COMMT.previewImg(this, 'img_view', 0, 60)">
				<div id="img_view" style="width:auto;height:60px;margin-bottom:10px;margin-left:10px;">
				<c:if test="${!empty iMAgncy.logoFileId}">
				<img id="img_view_img" src="<c:url value="/im/cmmn/getImage.do?atchFileId="/>${iMAgncy.logoFileId}&thumb=1" style="width:auto;height:60px;" alt="이미지">
				</c:if>
				</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.seal"/><span class="c_red">*</span></th>
				<td><form:hidden path="sealFileId"/>
				<input type="file" id="${SEAL_FILE_ID}" value="파일찾기" name="${SEAL_FILE_ID}" title="파일찾기" accept="${imExtensionsImages}" onchange="COMMT.previewImg(this, 'seal_view', 0, 60)">
				<div id="seal_view" style="width:auto;height:60px;margin-bottom:10px;margin-left:10px;">
				<c:if test="${!empty iMAgncy.sealFileId}">
				<img id="img_view_img" src="<c:url value="/im/cmmn/getImage.do?atchFileId="/>${iMAgncy.sealFileId}&thumb=1" style="width:auto;height:60px;" alt="이미지">
				</c:if>
				</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.dsgnYn"/><span class="c_red">*</span></th>
				<td>
				<im:cd type="radio" codeId="${arrYesNo}"  selectedCode="${iMAgncy.dsgnYn}" name="dsgnYn"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.fnshCode"/><span class="c_red">*</span></th>
				<td><form:input path="fnshCode" maxlength="3"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.linkCode"/></th>
				<td><form:input path="linkCode" maxlength="18" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.agncyintro"/></th>
				<td>
					<input type="hidden" name="cmmnDescRefNms" value="agncyintro"/>
					<textarea name="agncyintro" id="" cols="" rows="" style="height:100px;">${cmmmDescMap['agncyintro']}</textarea>
				</td>
			</tr>
	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="M" reqName="REQ"></im:pageBtn>
</div>