<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<im:cd codeId="IM0008" type="set" var="CD_IM0008" />


<c:set var="ATCH_FILE_ID" value="file_id"/>
<script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js?t=B37D54V'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var CD_IM0008 = {};
<c:forEach var="row" items="${CD_IM0008}">
CD_IM0008["<c:out value="${row.code}"/>"] = "<c:out value="${row.codeNm}"/>";
</c:forEach>


var REQ = {
	fileInfo : {
		    maxSizeMB : 100,
			uploadFolder : 'crs',
			fileInputId : '${ATCH_FILE_ID}'
	},
	req : {
		list : null,
		save : null,
	},
	init : function(){	
		
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/crs/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMCrs"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/crs/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		 this.req.save.cfg.fn.before = function() {
		    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
		    		return true;
		    	}else{
		    		COMMT.uploadCommonFile(REQ.fileInfo,function(data){
		    			 if (data.result > 0) {
		    				 if(data.result==1){
		    					 //첨부파일 정보 성공인 경우 
			    				 var form =  document.getElementById(REQ.req.save.cfg.formId);
			    		    	 form.elements["atchFileId"].value = data.atchFileId;
		    				 }
		    				 REQ.req.save.go("continue");
		                    } else {
		                    	COMMT.errorMessage();
		                   }
		    		});
		    		
		    	}
		 }
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMCrs;
	    	return validateIMCrs(form);
	    });
	    
	    
	    this.req.save.validator.set(function(){
	    	if(!COMMT.chkAttachFile('${ATCH_FILE_ID}')){
	    		return true;
	    	}else{
	    		if(!COMMT.chkAttachFileSize(REQ.fileInfo)){
	    			return false;	
	    		}else{
	    			return true;
	    		}
	    	}
	    });
	    
	    var form =  document.getElementById(REQ.req.save.cfg.formId);
   	 	if(form.elements["ttnfee"].value!='0'){
		    this.req.save.validator.set({
		    	title  : "납부기간",
	            name : "payTerm_bgnDt",
	            data : ["!null"]
	      	});
		    
		    this.req.save.validator.set({
		    	title  : "납부기간",
	            name : "payTerm_endDt",
	            data : ["!null"]
	      	});
   	 	}
	    
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
	save :  function(){
		REQ.init();
		IMGLBObject.request=this;
		CKEDITOR.instances.eduOutline.updateElement();
		this.req.save.go();
	},
	changCrsMstr :  function(mstId){
		if(mstId==""){
			return;
		}
		var req = imRequest("ajax");
	    req.cfg.formId = "iMCrs";
	    req.cfg.type   = "json";
	    req.cfg.asynchronous   = true;
		req.cfg.message.waiting="<spring:message code="im.common.msg.call.proc" />";
	    req.cfg.url    = "<c:url value="/mng/crsMstr/selectOne/json.do"/>";
	    req.cfg.fn.complete = function(act, data) {
	        if (data != null ) { 
	        	$("#_crsNm").html(data.detail.crsGrdCdvNm+' ' + data.detail.crsDvsnCdvNm);
	        	
	        	var form =  document.getElementById("iMCrs");
	    		form.elements["ttnfee"].value= data.detail.imCrsMstr.ttnfee;
	    		form.elements["eduHrs"].value= data.detail.imCrsMstr.eduHrs;
	    		

				form.elements["slctnHowCdv"].value= "02";
	    		if(data.detail.imCrsMstr.crsDvsnCdv && typeof data.detail.imCrsMstr.crsDvsnCdv ==="string"){
	    			if(data.detail.imCrsMstr.crsDvsnCdv=='CRS_DVSN_001' || data.detail.imCrsMstr.crsDvsnCdv=='CRS_DVSN_002'){
	    				form.elements["slctnHowCdv"].value= "01";
	    			}
	    		}
	    		
	    		$("#slctnHow").html(CD_IM0008[form.elements["slctnHowCdv"].value]);
	    		
	        	if(data.cmmmDescMap && data.cmmmDescMap.atndQlfcStndrd && typeof data.cmmmDescMap.atndQlfcStndrd== "string"){
	        		form.elements["atndQlfcStndrd"].value= data.cmmmDescMap.atndQlfcStndrd;	
	        	}else{
	        		form.elements["atndQlfcStndrd"].value= "";
	        	}
	        	
	        	if(data.cmmmDescMap && data.cmmmDescMap.cmpltnStndrd && typeof data.cmmmDescMap.cmpltnStndrd== "string"){
	        		form.elements["cmpltnStndrd"].value= data.cmmmDescMap.cmpltnStndrd;	
	        	}else{
	        		form.elements["cmpltnStndrd"].value= "";
	        	}
	        	
	        	if(data.cmmmDescMap && data.cmmmDescMap.qlfcLmt && typeof data.cmmmDescMap.qlfcLmt== "string"){
	        		form.elements["qlfcLmt"].value= data.cmmmDescMap.qlfcLmt;	
	        	}else{
	        		form.elements["qlfcLmt"].value= "";
	        	}
	        	if(data.cmmmDescMap && data.cmmmDescMap.evlPlan && typeof data.cmmmDescMap.evlPlan== "string"){
	        		form.elements["evlPlan"].value= data.cmmmDescMap.evlPlan;	
	        	}else{
	        		form.elements["evlPlan"].value= "";
	        	}
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    req.go();
	},
	changAgncy :  function(aId){
		if(aId==""){
			return;
		}
		var req = imRequest("ajax");
	    req.cfg.formId = "iMCrs";
	    req.cfg.type   = "json";
	    req.cfg.asynchronous   = true;
		req.cfg.message.waiting="<spring:message code="im.common.msg.call.proc" />";
	    req.cfg.url    = "<c:url value="/mng/agncy/selectOne/json.do"/>";
	    req.cfg.fn.complete = function(act, data) {
	        if (data != null ) { 
	        	var form =  document.getElementById("iMCrs");
	    		form.elements["cntpnt"].value= data.detail.agncy.telno;
	    		form.elements["bnkCdv"].value= data.detail.agncy.bnkCdv;
	    		form.elements["accno"].value= data.detail.agncy.accno;
	    		form.elements["acchdr"].value= data.detail.agncy.acchdr;
	    		$("#_agncyNm").html(data.detail.agncy.agncyNm);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    req.go();
	},
	defaultdate :  function(){
		var form =  document.getElementById("iMCrs");
		if(form.elements["eduAplTerm_bgnDt"].value===""){
			alert('교육신청기간 시작일을 선택하세요.');
			return;
		}
		var req = imRequest("ajax");
	    req.cfg.formId = "iMCrs";
	    req.cfg.type   = "json";
	    req.cfg.asynchronous   = true;
		req.cfg.message.waiting="<spring:message code="im.common.msg.call.proc" />";
	    req.cfg.url    = "<c:url value="/mng/crs/defaultdate/json.do"/>";
	    req.cfg.fn.complete = function(act, data) {
	        if (data != null ) {
	        	form.elements["eduAplTerm_endDt"].value= data.iMCrs.eduAplTerm_endDt;
	        	form.elements["eduTerm_bgnDt"].value= data.iMCrs.eduTerm_bgnDt;
	        	form.elements["eduTerm_endDt"].value= data.iMCrs.eduTerm_endDt;
	        	form.elements["fnshYmd_bgnDt"].value= data.iMCrs.fnshYmd_bgnDt;
	        	form.elements["olfcfnshYmd_bgnDt"].value= data.iMCrs.olfcfnshYmd_bgnDt;
	        	form.elements["payTerm_bgnDt"].value= data.iMCrs.payTerm_bgnDt;
	        	form.elements["payTerm_endDt"].value= data.iMCrs.payTerm_endDt;
	        	form.elements["slctnTerm_bgnDt"].value= data.iMCrs.slctnTerm_bgnDt;
	        	form.elements["slctnTerm_endDt"].value= data.iMCrs.slctnTerm_endDt;
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    req.go();
	},
	calPrsnl:function(){
		var form =  document.getElementById("iMCrs");
		var prsnl=form.elements["prsnl"].value;
		var slctnHowCdv = form.elements["slctnHowCdv"].value;
		if(prsnl==""){
			form.elements["prsnlLmt"].value = "";
			 return;
		}
		if(slctnHowCdv==""){
			 $.dialog("alert", {message : '마스터 과정을 선택하세요.'});
			 return;
		}
		
		if(isNaN(prsnl)){
			 $.dialog("alert", {message : '정원에 숫자를 입력하세요.'});
			 form.elements["prsnl"].value="";
			 return;
		}
		var  prsnlLmt = Math.ceil(parseInt(prsnl, 10)*1.5);
		
		form.elements["prsnlLmt"].value = prsnlLmt;
		
	}
}


$(document).ready(function(){
	REQ.init();
 	<c:if test="${!empty  loginUserAgncyId}">
 	REQ.changAgncy('${loginUserAgncyId}');
 	</c:if>
 	
	var ckeditor_config = {
			filebrowserImageUploadUrl: '${pageContext.request.contextPath}/ckUploadImage', // 파일 업로드를 처리 할 경로 설정(CK필터).
			toolbarGroups : [ { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] }, 
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] }, { name: 'links', groups: [ 'links' ] }, { name: 'insert', groups: [ 'insert' ] }, '/', { name: 'clipboard', groups: [ 'clipboard', 'undo' ] }, { name: 'styles', groups: [ 'styles' ] }, { name: 'colors', groups: [ 'colors' ] }, { name: 'tools', groups: [ 'tools' ] }, { name: 'others', groups: [ 'others' ] }, { name: 'about', groups: [ 'about' ] }, { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] }, { name: 'document', groups: [ 'mode', 'document', 'doctools' ] }, { name: 'forms', groups: [ 'forms' ] }, ], 
		removeButtons : 'Image,Table,Cut,Copy,Paste,PasteText,PasteFromWord,Find,SelectAll,Scayt,Replace,Save,NewPage,ExportPdf,Print,Templates,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CreateDiv,Blockquote,BidiLtr,BidiRtl,Language,RemoveFormat,CopyFormatting,Anchor,Flash,PageBreak,About,JustifyLeft,JustifyCenter,JustifyRight,JustifyBlock,ShowBlocks,Maximize,Subscript,Superscript,Iframe', 
		};
			
	
	CKEDITOR.replace('eduOutline',ckeditor_config);
});

</script>

<%@ include file="incCrs.jsp" %>


<validator:javascript formName="iMCrs" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMCrs" name="iMCrs" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption>과정(운영과정) 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMCrs.crsMstr"/><span class="c_red">*</span></th>
				<td>
				<select name="crsMstrId" onchange="REQ.changCrsMstr(this.value)">
					<option value="">선택</option>
					<c:forEach items="${crsMstrList}" var="row">
					<option value="${row.imCrsMstr.crsMstrId}">
					<im:cd type="print" codeId="IM0001" selectedCode="${row.imCrsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${row.imCrsMstr.crsDvsnCdv}"/>
					</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.crsNm"/><span class="c_red">*</span></th>
				<td id="_crsNm">
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.ttnfee"/><span class="c_red">*</span></th>
				<td><form:input path="ttnfee" cssStyle="text-align: right;"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduHrs"/><span class="c_red">*</span></th>
				<td><form:input path="eduHrs" cssStyle="text-align: right;"/></td>
			</tr>
			
			
			<tr>
			<th scope="row"><spring:message code="iMCrsMstr.atndQlfcStndrd"/></th>
			<td><input type="hidden" name="cmmnDescRefNms" value="atndQlfcStndrd"/>
				<textarea name="atndQlfcStndrd" id="" cols="" rows="" style="height:100px;"></textarea>
			</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsMstr.qlfcLmt"/></th>
				<td>
					<input type="hidden" name="cmmnDescRefNms" value="qlfcLmt"/>
					<textarea name="qlfcLmt" id="" cols="" rows="" style="height:100px;"></textarea>
				</td>
			</tr>
			<tr>
				<th scope="row">교육개요</th>
				<td><input type="hidden" name="cmmnDescRefNms" value="eduOutline"/>
					<textarea name="eduOutline" id="" cols="" rows="" style="height:100px;"></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsMstr.evlPlan"/></th>
				<td><input type="hidden" name="cmmnDescRefNms" value="evlPlan"/>
					<textarea name="evlPlan" id="" cols="" rows="" style="height:100px;"></textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsMstr.cmpltnStndrd"/></th>
				<td><input type="hidden" name="cmmnDescRefNms" value="cmpltnStndrd"/>
					<textarea name="cmpltnStndrd" id="" cols="" rows="" style="height:100px;"></textarea>
				</td>
			</tr>
				
			<tr>
					<th scope="row"><spring:message code="iMCrs.slctnHow"/><span class="c_red">*</span></th>
					<td><form:hidden path="slctnHowCdv"/><span id="slctnHow"></span></td>
			</tr>
	</tbody>
</table>			
<table class="tbl_row">
	<caption>과정(운영과정) 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>	
			<th scope="row"><spring:message code="iMCrs.agncyNm"/><span class="c_red">*</span></th>
				<td>
		     	<c:if test="${empty  loginUserAgncyId}">
				<select name="agncyId" onchange="REQ.changAgncy(this.value)">
					<option value="">선택</option>
					<c:forEach items="${agncyList}" var="row">
					<option value="${row.agncy.agncyId}">${row.agncy.agncyNm}</option>
					</c:forEach>
				</select>
				</c:if>
		     	<c:if test="${!empty  loginUserAgncyId}">
		     	<input type="hidden" name="agncyId" value="${loginUserAgncyId}"/>
		     	<span id="_agncyNm"></span>
		     	</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.cntpnt"/><span class="c_red">*</span></th>
				<td><form:input path="cntpnt" maxlength="100"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.bnk"/></th>
				<td>
					<select name="bnkCdv">
						<option value="">선택</option>
						<im:cd type="option" codeId="IM0005"/>
					</select>
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.accno"/></th>
				<td><form:input path="accno" maxlength="15"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.acchdr"/></th>
				<td><form:input path="acchdr" maxlength="30"/></td>
			</tr>
			
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduYear"/><span class="c_red">*</span></th>
				<td>
				<select name="eduYear" >
				<c:forEach begin="${imNowYear}" var="num" end="${imNowYear+1}">
				<option value="${num}">${num}</option>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduRnd"/><span class="c_red">*</span></th>
				<td>
				<select name="eduRnd" >
				<option value="">선택</option>
				<c:forEach begin="${imEduRndStart}" var="num" end="${imEduRndEnd}">
				<option value="${num}">${num}</option>
				</c:forEach>
			</select>
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduAplTerm"/><span class="c_red">*</span>
				<%--
				<a herf="javascript:;" onclick="REQ.defaultdate(); ">[계산]</a>
				 --%>
				</th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="eduAplTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="eduAplTerm_bgnDt"  placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="eduAplTerm_endDt"  placeholder="종료일" data-role="datepicker">
					</div>
					(모든 기간정보는 홈페이지에 노출됨)
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduTerm"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="eduTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="eduTerm_bgnDt"  placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="eduTerm_endDt"  placeholder="종료일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.slctnTerm"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="slctnTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="slctnTerm_bgnDt"  placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="slctnTerm_endDt"  placeholder="종료일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.payTerm"/></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="payTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="payTerm_bgnDt"  placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="payTerm_endDt"  placeholder="종료일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.fnshYmd"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="fnshYmd"/>
				<div class="ad_datepicker">
						<input type="text" name="fnshYmd_bgnDt"  placeholder="<spring:message code="iMCrs.fnshYmd"/>" data-role="datepicker">
					</div> 
					(중요 정보 : 수료증에 노출되는 정보)					 
				</td>
			</tr>
			
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.olfcfnshYmd"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="olfcfnshYmd"/>
					<div class="ad_datepicker">
						<input type="text" name="olfcfnshYmd_bgnDt"  placeholder="<spring:message code="iMCrs.olfcfnshYmd"/>" data-role="datepicker">
					</div>
					(중요 정보 : 자격증에 노출되는 정보)					  
				</td>
			</tr>
			
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.prsnl"/><span class="c_red">*</span></th>
				<td><form:input path="prsnl" maxlength="3" cssStyle="text-align: right;" onkeyup="REQ.calPrsnl()"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.prsnlLmt"/><span class="c_red">*</span></th>
				<td><form:input path="prsnlLmt" maxlength="5" cssStyle="text-align: right;"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.atchFile"/></th>
				<td><form:hidden path="atchFileId"/>
				<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="FileId" title="파일찾기" accept="${imExtensionsFiles}" />
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.stts"/><span class="c_red">*</span></th>
				<td><im:cd type="radio" codeId="IM0003" defaultSelectedCode="STTS_01" name="sttsCdv"/></td>
			</tr>

	</tbody>
</table>

</form:form>
<div class="b_box right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>