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
		this.req.list.cfg.url    = "<c:url value="/mng/wtstPlace/selectList.do"/>";
		
		this.req.save = imRequest("ajax",{formId: "iMWtstPlace"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/wtstPlace/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
			 if (data != null && data.result > 0) { 
				 REQ.list();
		        } else {
		        	COMMT.errorMessage();
		        }
	    };	
	    this.req.save.validator.set(function(){
	    	var form = document.iMWtstPlace;
	    	return validateIMWtstPlace(form);
	    });
	
	},
	save :  function(){
		<c:if test="${detail.rcptProcType eq 'W'}">
		REQ.req.save.go();
		</c:if>
		<c:if test="${detail.rcptProcType ne 'W'}">
		COMMT.message('원서접수 기간 전에만 수정 가능합니다.');
		</c:if>
	},
	changAgncy :  function(aId){
		if(aId==""){
			return;
		}
		var req = imRequest("ajax");
	    req.cfg.formId = "iMWtstPlace";
	    req.cfg.type   = "json";
	    req.cfg.asynchronous   = true;
		req.cfg.message.waiting="<spring:message code="im.common.msg.call.proc" />";
	    req.cfg.url    = "<c:url value="/mng/agncy/selectOne/json.do"/>";
	    req.cfg.fn.complete = function(act, data) {
	        if (data != null ) { 
	        	var form =  document.getElementById("iMWtstPlace");
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
	calPrsnl:function(){
		var form =  document.getElementById("iMWtstPlace");
		var prsnl=form.elements["prsnl"].value;
		if(prsnl==""){
			form.elements["prsnlLmt"].value = "";
			 return;
		}
		
		if(isNaN(prsnl)){
			 $.dialog("alert", {message : '정원에 숫자를 입력하세요.'});
			 form.elements["prsnl"].value="";
			 return;
		}
		var  prsnlLmt = Math.ceil(parseInt(prsnl, 10)*1.5);
		
		form.elements["prsnlLmt"].value = prsnlLmt;
		
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	list :  function(){
		this.req.list.go();
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>

<%@ include file="incWtstPlace.jsp" %>

<validator:javascript formName="iMWtstPlace" staticJavascript="false" xhtml="true" cdata="false"/>


<div class="b_box right">
	<div> 
	<a href="#" onclick="REQ.list();return false;" class="ad_btn bk">목록</a>
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	</div>
</div>

<form:form commandName="iMWtstPlace" name="iMWtstPlace" method="post" onsubmit="return false;">
<form:hidden path="wtstPlaceId"/>
<table class="tbl_row">
	<caption>필기시험장소 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			
			<tr>
				<th scope="row"><spring:message code="iMCrs.agncyNm"/><span class="c_red">*</span></th>
				<td><form:hidden path="agncyId"/>
					${detail.agncy.agncyNm}
				</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtstPlace.cntpnt"/><span class="c_red">*</span></th>
				<td><form:input path="cntpnt"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtstPlace.bnkCdv"/><span class="c_red">*</span></th>
				<td>
					<select name="bnkCdv">
						<option value="">선택</option>
						<im:cd type="option" codeId="IM0005" selectedCode="${iMWtstPlace.bnkCdv}"/>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtstPlace.accno"/><span class="c_red">*</span></th>
				<td><form:input path="accno"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtstPlace.acchdr"/><span class="c_red">*</span></th>
				<td><form:input path="acchdr"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtstPlace.prsnl"/><span class="c_red">*</span></th>
				<td>
				<form:input path="prsnl" maxlength="3" cssStyle="text-align: right;" onkeyup="REQ.calPrsnl()"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="iMWtstPlace.prsnlLmt"/><span class="c_red">*</span></th>
				<td><form:input path="prsnlLmt" maxlength="5" cssStyle="text-align: right;"/></td>
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