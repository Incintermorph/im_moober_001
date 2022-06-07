<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>

<c:set var="ATCH_FILE_ID" value="file_id"/>
<script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js?t=B37D54V'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var REQ = {
	fileInfo : {
			uploadFolder : 'crs',
			fileInputId : '${ATCH_FILE_ID}'
	},
	req : {
		list : null,
		save : null,
		del : null,
	},
	init : function(){
		
		
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/crs/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMCrs"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/crs/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
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
	    
	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMCrs";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/mng/crs/delete.do"/>";
	    this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
	    this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.page(1);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    
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
	del :  function(){
		IMGLBObject.request=this;
		this.req.del.go();
	},
	defaultdate :  function(aId){
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
	
	//filebrowserUploadUrl: '${pageContext.request.contextPath}/utl/wed/insertImage.do', // 파일 업로드를 처리 할 경로 설정.
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
	<im:pageBtn type="M" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMCrs" name="iMCrs" method="post" onsubmit="return false;">
<form:hidden path="crsMstrId" />
<form:hidden path="crsId"/>
<table class="tbl_row">
	<caption>과정(운영과정) 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMCrs.crsNm"/><span class="c_red">*</span></th>
				<td >
				<im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.ttnfee"/><span class="c_red">*</span></th>
				
				<td><form:input path="ttnfee" cssStyle="text-align: right;"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduHrs"/><span class="c_red">*</span></th>
				<td><form:input path="eduHrs" cssStyle="text-align: right;"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsMstr.atndQlfcStndrd"/></th>
				<td><input type="hidden" name="cmmnDescRefNms" value="atndQlfcStndrd"/>
					<textarea name="atndQlfcStndrd" id="" cols="" rows="" style="height:100px;">${cmmmDescMap['atndQlfcStndrd']}</textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsMstr.qlfcLmt"/></th>
				<td>
					<input type="hidden" name="cmmnDescRefNms" value="qlfcLmt"/>
					<textarea name="qlfcLmt" id="" cols="" rows="" style="height:100px;">${cmmmDescMap['qlfcLmt']}</textarea>
				</td>
			</tr>
			<tr>
				<th scope="row">교육개요</th>
				<td><input type="hidden" name="cmmnDescRefNms" value="eduOutline"/>
					<textarea name="eduOutline" id="" cols="" rows="" style="height:100px;">${cmmmDescMap['eduOutline']}</textarea>					
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsMstr.evlPlan"/></th>
				<td><input type="hidden" name="cmmnDescRefNms" value="evlPlan"/>
					<textarea name="evlPlan" id="" cols="" rows="" style="height:100px;">${cmmmDescMap['evlPlan']}</textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsMstr.cmpltnStndrd"/></th>
				<td><input type="hidden" name="cmmnDescRefNms" value="cmpltnStndrd"/>
					<textarea name="cmpltnStndrd" id="" cols="" rows="" style="height:100px;">${cmmmDescMap['cmpltnStndrd']}</textarea>
				</td>
			</tr>
				
			<tr>
					<th scope="row"><spring:message code="iMCrs.slctnHow"/><span class="c_red">*</span></th>
					<td><form:hidden  path="slctnHowCdv"/>
					<span id="slctnHow">
					<im:cd type="print" codeId="IM0008" selectedCode="${iMCrs.slctnHowCdv}"/>
					</span>
					</td>
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
				<td><form:hidden path="agncyId"/>
				${detail.agncy.agncyNm}
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
						<im:cd type="option" codeId="IM0005" selectedCode="${iMCrs.bnkCdv}"/>
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
				<c:forEach begin="${imStartYear}" var="num" end="${imNowYear+1}">
				<option value="${num}"
				<c:if test="${num eq  iMCrs.eduYear}">
					selected="selected"
					</c:if> 
				>${num}</option>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduRnd"/><span class="c_red">*</span></th>
				<td>
				<select name="eduRnd" >
				<option value="">선택</option>
				<c:forEach begin="${imEduRndStart}" var="num" end="${imEduRndEnd}">
				<option value="${num}"
				<c:if test="${num eq  iMCrs.eduRnd}">
					selected="selected"
					</c:if> 
				>${num}</option>
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
						<input type="text" name="eduAplTerm_bgnDt"  value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['eduAplTerm'].bgnDt}"/>" placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="eduAplTerm_endDt" value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['eduAplTerm'].endDt}"/>"  placeholder="종료일" data-role="datepicker">
					</div>
					(모든 기간정보는 홈페이지에 노출됨)  
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduTerm"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="eduTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="eduTerm_bgnDt" value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['eduTerm'].bgnDt}"/>"  placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="eduTerm_endDt" value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['eduTerm'].endDt}"/>"  placeholder="종료일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.slctnTerm"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="slctnTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="slctnTerm_bgnDt" value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}"/>"  placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="slctnTerm_endDt" value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}"/>"  placeholder="종료일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.payTerm"/></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="payTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="payTerm_bgnDt"  value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].bgnDt}"/>" placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="payTerm_endDt" value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].endDt}"/>"  placeholder="종료일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.fnshYmd"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="fnshYmd"/>
				<div class="ad_datepicker">
						<input type="text" name="fnshYmd_bgnDt"  value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['fnshYmd'].bgnDt}"/>"  placeholder="<spring:message code="iMCrs.fnshYmd"/>" data-role="datepicker">
					</div>  
						(중요 정보 : 수료증에 노출되는 정보)		
				</td>
			</tr>
			
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.olfcfnshYmd"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="olfcfnshYmd"/>
					<div class="ad_datepicker">
						<input type="text" name="olfcfnshYmd_bgnDt" value="<im:dt yyyyMMddHHmmss="${cmmmDtMap['olfcfnshYmd'].bgnDt}"/>"   placeholder="<spring:message code="iMCrs.olfcfnshYmd"/>" data-role="datepicker">
					</div>  
						(중요 정보 : 자격증에 노출되는 정보)		
				</td>
			</tr>
			
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.prsnl"/><span class="c_red">*</span></th>
				<td><form:input path="prsnl" maxlength="3"  cssStyle="text-align: right;"  onkeyup="REQ.calPrsnl()"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.prsnlLmt"/><span class="c_red">*</span></th>
				<td><form:input path="prsnlLmt" maxlength="5" cssStyle="text-align: right;" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.atchFile"/></th>
				<td>
				<c:forEach items="${fileList}" var="row">
				<div id="__file_<c:out value="${row.atchFileId}"/>_<c:out value="${row.fileSn}"/>">
				<a href="javascript:COMMT.fn_egov_downFile('<c:out value="${row.atchFileId}"/>','<c:out value="${row.fileSn}"/>')">
					<c:out value="${row.orignlFileNm}"/>&nbsp;[<c:out value="${imfunc:fileSizeView(row.fileMg)}"/>]
				</a> 
				<a href="javascript:COMMT.deleteFile('<c:out value="${row.atchFileId}"/>','<c:out value="${row.fileSn}"/>')">
					[<spring:message code="button.delete"/>]
				</a>	
				</div>
				</c:forEach>
				
				<form:hidden path="atchFileId"/>
				<input type="file" id="${ATCH_FILE_ID}" value="${ATCH_FILE_ID}" name="FileId" title="파일찾기" accept="${imExtensionsFiles}" />
				
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrs.stts"/><span class="c_red">*</span></th>
				<td><im:cd type="radio" codeId="IM0003"  defaultSelectedCode="STTS_01" selectedCode="${iMCrs.sttsCdv}"  name="sttsCdv"/>
				
				
				</td>
			</tr>
	</tbody>
</table>

</form:form>
<div class="b_box right">
	<im:pageBtn type="M" reqName="REQ"></im:pageBtn>
</div>