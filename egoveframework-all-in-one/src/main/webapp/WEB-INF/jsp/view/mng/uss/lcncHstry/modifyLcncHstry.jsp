<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

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
		this.req.list.cfg.url    = "<c:url value="/mng/lcncHstry/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMLcncHstry"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/lcncHstry/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
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
		IMGLBObject.request=this;
		this.req.save.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incLcncHstry.jsp" %>


<validator:javascript formName="iMLcncHstry" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
		<div> 
		<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a> 
		<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a> 
		</div>
</div>

<form:form commandName="iMLcncHstry" name="iMLcncHstry" method="post" onsubmit="return false;">
<form:hidden path="lcncHstryId"/>
<table class="tbl_row">
	<caption>자격증 이력 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.lcncNo"/><span class="c_red">*</span></th>
				<td><form:input path="lcncNo"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.acqsYear"/><span class="c_red">*</span></th>
				<td><form:input path="acqsYear"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.lcncRsltCode"/><span class="c_red">*</span></th>
				<td><form:input path="lcncRsltCode"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.crsGrd"/><span class="c_red">*</span></th>
				<td><form:input path="crsGrd"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.lcncAcqsDt"/><span class="c_red">*</span></th>
				<td><form:input path="lcncAcqsDt"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.sexdstn"/><span class="c_red">*</span></th>
				<td><form:input path="sexdstn"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.rgn"/><span class="c_red">*</span></th>
				<td><form:input path="rgn"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.area"/><span class="c_red">*</span></th>
				<td><form:input path="area"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.dtlArea"/><span class="c_red">*</span></th>
				<td><form:input path="dtlArea"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.brdt"/><span class="c_red">*</span></th>
				<td>
				
					<im:dt yyyyMMddHHmmss="19000101000000" addDate="32105"/>
				<div class="ad_datepicker">
						<input type="text" name="brdt" value="${iMLcncHstry.brdt}"  placeholder="종료일" data-role="datepicker">
					</div> 
				</td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.agncyNm"/><span class="c_red">*</span></th>
				<td><form:input path="agncyNm"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.mmbrNm"/><span class="c_red">*</span></th>
				<td><form:input path="mmbrNm"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.telno"/><span class="c_red">*</span></th>
				<td><form:input path="telno"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.eml"/><span class="c_red">*</span></th>
				<td><form:input path="eml"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.yn_2018"/><span class="c_red">*</span></th>
				<td><form:input path="yn_2018"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.yn_2019"/><span class="c_red">*</span></th>
				<td><form:input path="yn_2019"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.yn_2020"/><span class="c_red">*</span></th>
				<td><form:input path="yn_2020"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.yn_2021"/><span class="c_red">*</span></th>
				<td><form:input path="yn_2021"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.agncyCode"/><span class="c_red">*</span></th>
				<td><form:input path="agncyCode"/></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.bgnDt"/><span class="c_red">*</span></th>
				<td>
				
				<div class="ad_datepicker">
						<input type="text" name="bgnDt" value="${iMLcncHstry.bgnDt}"  placeholder="시작일시" data-role="datepicker">
					</div>
				
				</td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.endDt"/><span class="c_red">*</span></th>
				<td>
				<div class="ad_datepicker">
						<input type="text" name="endDt" value="${iMLcncHstry.bgnDt}"  placeholder="종료일시" data-role="datepicker">
					</div>
				
				</td></td>
			</tr>
	<tr>
				<th scope="row"><spring:message code="iMLcncHstry.cntneduCnt"/><span class="c_red">*</span></th>
				<td><form:input path="cntneduCnt"/></td>
			</tr>

	</tbody>
</table>
</form:form>
<div class="b_box right">
		<div> 
		<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a> 
		<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a> 
		</div>
</div>