<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script src="${imWebStatic}/common/js/jquery/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="${imWebStatic}/common/js/jquery/jquery.timepicker.css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		save : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/wtst/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMWtst"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/wtst/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMWtst;
	    	return validateIMWtst(form);
	    });
	    
	    
	    $("[data-role='datepicker']" ).datepicker({
			showOn:"both",
			buttonText: '',
			dateFormat: '${imdatepickerDateType}',
			changeYear: true,
			yearRange: "1900:+nn"
		});
	    
	    $(".timeCHEK").timepicker({
	    	step: 5,            //시간간격 : 5분
	    	timeFormat: "H:i"    //시간:분 으로표시
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
	defaultdate :  function(){
		var form =  document.getElementById("iMWtst");
		if(form.elements["pbancTerm_bgnDt"].value===""){
			alert('공고기산 시작일을 선택하세요.');
			return;
		}
		var req = imRequest("ajax");
	    req.cfg.formId = "iMWtst";
	    req.cfg.type   = "json";
	    req.cfg.asynchronous   = true;
		req.cfg.message.waiting="<spring:message code="im.common.msg.call.proc" />";
	    req.cfg.url    = "<c:url value="/mng/wtst/defaultdate/json.do"/>";
	    req.cfg.fn.complete = function(act, data) {
	        if (data != null ) {
	        	form.elements["pbancTerm_endDt"].value= data.iMWtst.pbancTerm_endDt;
	        	form.elements["rcptTerm_bgnDt"].value= data.iMWtst.rcptTerm_bgnDt;
	        	form.elements["rcptTerm_endDt"].value= data.iMWtst.rcptTerm_endDt;
	        	form.elements["tstYmd"].value= data.iMWtst.tstYmd;
	        	form.elements["passTerm_bgnDt"].value= data.iMWtst.passTerm_bgnDt;
	        	form.elements["payTerm_bgnDt"].value= data.iMWtst.payTerm_bgnDt;
	        	form.elements["payTerm_endDt"].value= data.iMWtst.payTerm_endDt;
	        	form.elements["slctnTerm_bgnDt"].value= data.iMWtst.slctnTerm_bgnDt;
	        	form.elements["slctnTerm_endDt"].value= data.iMWtst.slctnTerm_endDt;
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    req.go();
	},
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incWtst.jsp" %>


<validator:javascript formName="iMWtst" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMWtst" name="iMWtst" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption>필기시험 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMWtst.crsGrdCdv"/><span class="c_red">*</span></th>
				<td><im:cd type="radio" codeId="IM0001" name="crsGrdCdv"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.eduYear"/><span class="c_red">*</span></th>
				<td>
				<select name="eduYear" >
				<c:forEach begin="${imNowYear}" var="num" end="${imNowYear+1}">
				<option value="${num}">${num}</option>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.eduRnd"/><span class="c_red">*</span></th>
				<td>
				<select name="eduRnd" >
				<option value="">선택</option>
				<c:forEach begin="${imEduRndStart}" var="num" end="${imEduRndEnd}">
				<option value="${num}">${num}</option>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.addtnYn"/><span class="c_red">*</span></th>
				<td>
				<im:cd type="radio" codeId="${addtnYnArr}" name="addtnYn"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.pbancTerm"/><span class="c_red">*</span>
				<a herf="javascript:;" onclick="REQ.defaultdate(); ">[계산]</a>
				</th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="pbancTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="pbancTerm_bgnDt"  placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="pbancTerm_endDt"  placeholder="종료일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.rcptTerm"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="rcptTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="rcptTerm_bgnDt"  placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="rcptTerm_endDt"  placeholder="종료일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.tstYmd"/><span class="c_red">*</span></th>
				<td>
				<div class="ad_datepicker">
				<input type="text" name="tstYmd"  placeholder="<spring:message code="iMWtst.tstYmd"/>" data-role="datepicker">
				</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.tstHrs"/><span class="c_red">*</span></th>
				<td>
						<input type="text" name="tstBgngHrs" value="09:00" maxlength="5" class="timeCHEK" style="width: 60px;">
						~
						<input type="text" name="tstEndHrs" value="18:00" maxlength="5" class="timeCHEK" style="width: 60px;">
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.passTerm"/><span class="c_red">*</span></th>
				<td>
				<input type="hidden" name="cmmnDtRefNms" value="passTerm"/>
				<div class="ad_datepicker">
						<input type="text" name="passTerm_bgnDt"  placeholder="시작일" data-role="datepicker">
					</div>  
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.slctnTerm"/><span class="c_red">*</span></th>
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
				<th scope="row"><spring:message code="iMWtst.payTerm"/><span class="c_red">*</span></th>
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
				<th scope="row"><spring:message code="iMWtst.tstfee"/><span class="c_red">*</span></th>
				<td><form:input path="tstfee" maxlength="5" cssStyle="text-align: right;"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.ntce"/></th>
				<td>
				<input type="hidden" name="cmmnDescRefNms" value="ntce"/>
				<textarea name="ntce" id="" cols="" rows="" style="height:100px;"></textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtst.brng"/></th>
				<td>
				<input type="hidden" name="cmmnDescRefNms" value="brng"/>
				<textarea name="brng" id="" cols="" rows="" style="height:100px;"></textarea>
				</td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMWtst.stts"/><span class="c_red">*</span></th>
				<td><im:cd type="radio" codeId="IM0003" defaultSelectedCode="STTS_01" name="sttsCdv"/></td>
			</tr>

	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>