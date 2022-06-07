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
		this.req.list.cfg.url    = "<c:url value="/mng/agncy/place/selectList.do"/>";
				
		
		this.req.save = imRequest("ajax",{formId: "iMAgncyPlace"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.asynchronous   = true;
		this.req.save.cfg.url    =  "<c:url value="/mng/agncy/place/insert.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.regist.msg" />";
		this.req.save.cfg.message.waiting="<spring:message code="im.common.msg.proc" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.list();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    
	
	    this.req.save.validator.set(function(){
	    	var form = document.iMAgncyPlace;
	    	return validateIMAgncyPlace(form);
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



<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imZipCode.jsp"/>



<%@ include file="incAgncy.jsp" %>


<validator:javascript formName="iMAgncyPlace" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="cb_bar right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>

<form:form commandName="iMAgncyPlace" name="iMAgncyPlace" id="iMAgncyPlace" method="post" onsubmit="return false;" >
<table class="tbl_row">
	<caption>양성기관 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row"><spring:message code="iMAgncy.placeNm"/><span class="c_red">*</span></th>
				<td><form:input path="agncyNm"/>
				<input type="hidden" name="agncyDvsnCdv" value="AGNCYDVSN_03">
				<input type="hidden" name="brno" value="AGNCYDVSN_03">
				<input type="hidden" name="crsGrdCdv" value="AGNCYDVSN_03">
				</td>
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
				<td><form:input path="addrDtl" id="_detailAddress_id" maxlength="200"/></td>
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
				<th scope="row"><spring:message code="iMAgncy.useYn"/><span class="c_red">*</span></th>
				<td>
				<im:cd type="radio" codeId="${arrYesNo}"   name="dsgnYn"/>
				</td>
			</tr>
	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="R" reqName="REQ"></im:pageBtn>
</div>


