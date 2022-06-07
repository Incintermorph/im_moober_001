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
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/crsPlan/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMCrsPlan"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/crsPlan/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMCrsPlan;
	    	return validateIMCrsPlan(form);
	    });
	    
	    
	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMCrsPlan";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/mng/crsPlan/delete.do"/>";
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
		IMGLBObject.request=this;
		this.req.save.go();
	},
	del :  function(){
		IMGLBObject.request=this;
		this.req.del.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incCrsPlan.jsp" %>


<validator:javascript formName="iMCrsPlan" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="M" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMCrsPlan" name="iMCrsPlan" method="post" onsubmit="return false;">
<form:hidden path="crsPlanId"/>
<table class="tbl_row">
	<caption>과정계획 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMCrs.agncyNm"/><span class="c_red">*</span></th>
				<td>
				<c:if test="${empty  loginUserAgncyId}">
				<select name="agncyId" >
					<option value="">선택</option>
					<c:forEach items="${agncyList}" var="row">
					<option value="${row.agncy.agncyId}"
					<c:if test="${row.agncy.agncyId eq  iMCrsPlan.agncyId}">
					selected="selected"
					</c:if> 
					>${row.agncy.agncyNm}</option>
					</c:forEach>
				</select>
				</c:if>
		     	<c:if test="${!empty  loginUserAgncyId}">
		     	<form:hidden path="agncyId"/>
		     	${detail.agncy.agncyNm}
		     	</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsPlan.eduYear"/><span class="c_red">*</span></th>
				<td>
				<select name="eduYear" >
				<c:forEach begin="${imStartYear}" var="num" end="${imNowYear+1}">
				<option value="${num}"
					<c:if test="${num eq  iMCrsPlan.eduYear}">
					selected="selected"
					</c:if> 
				>${num}</option>
				</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsPlan.crsGrdCdv"/><span class="c_red">*</span></th>
				<td><im:cd type="radio" codeId="IM0001" name="crsGrdCdv" selectedCode="${iMCrsPlan.crsGrdCdv}"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMCrsPlan.crsDvsnCdv"/><span class="c_red">*</span></th>
				<td><im:cd type="radio" codeId="IM0011" name="crsDvsnCdv" selectedCode="${iMCrsPlan.crsDvsnCdv}"/></td>
			</tr>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.eduAplTerm"/><span class="c_red">*</span>
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

	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="M" reqName="REQ"></im:pageBtn>
</div>