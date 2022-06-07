<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		this.req.list.cfg.url    = "<c:url value="/mng/ussMngr/"/>${condition.scAuthorCode}/selectList.do";
				
		
		this.req.save = imRequest("ajax",{formId: "iMUssMngr"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/ussMngr/permit/update.do"/>";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.modify();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	
	   
	    this.req.save.validator.set({
	    	title : "접근제한",
	        name : "usePermitYn",
            data : ["!null"]
	    });
	    
	    
	    this.req.del = imRequest("ajax");
	    this.req.del.cfg.formId = "iMUssMngr";
	    this.req.del.cfg.type   = "json";
	    this.req.del.cfg.url    = "<c:url value="/mng/ussMngr/delete.do"/>";
	    this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
	    this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.page(1);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	
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
	reload :  function(){
		var req = imRequest();
		req.cfg.formId = "FormPageDetail";
		req.cfg.url    = "<c:url value="/mng/ussMngr/modifyPermit.do"/>";
		var form =  document.getElementById(req.cfg.formId );
		form.elements["esntlId"].value= '${param['esntlId']}';
		req.go(); 
	},
	modify :  function(){
		var req = imRequest();
		req.cfg.formId = "FormPageDetail";
		req.cfg.url    = "<c:url value="/mng/ussMngr/modify.do"/>";
		var form =  document.getElementById(req.cfg.formId );
		form.elements["esntlId"].value= '${param['esntlId']}';

		form.elements["_pageName"].value= '상세 수정';
		req.go(); 
	},
	 /**
     * IP 등록
     */
    addPermitAcessIpHTML: function(addIp) {
    	var nowHTML =$("#permitAcessipID").html();
		$("#permitAcessipID").html(nowHTML + '<div><input type="hidden" name="permitAcessips" value="'+addIp+'"/><span>'+addIp+'</span><span style="cursor: pointer;" onclick="REQ.delIp(this)">[삭제]</span>');
    },
    /**
     * IP 등록
     */
     addPermitAcessip: function() {
    	var req = imRequest("script", {formId : "iMUssMngr"}); 
    	req.cfg.fn.exec  = function() {
    		try{
    			var form =  document.getElementById(req.cfg.formId);
    			var addIp=form.elements["permitAcessip"].value;
    			REQ.addPermitAcessIpHTML(addIp); 
    			form.elements["permitAcessip"].value='';
    		}catch(e){
    			alert(e.message);
    		}
        };

        req.validator.set({
            title : "허용IP",
            name : "permitAcessip",
            data : ["!null"]
        });

        req.validator.set(function() {
        	var form =  document.getElementById(req.cfg.formId);
            var acessIps = form.elements["permitAcessips"];
            var addIp=form.elements["permitAcessip"].value; 
            if (typeof form.elements["permitAcessips"] === "undefined") {
            	return true;
            }else{
            	var $form = $(form);
            	var overChk=0;
            	$form.find(":input[name='permitAcessips']").each(function() {
            		 var $this = $(this);
                     if($this.val()==addIp){
                    	 overChk=1;	 
                     }
            	});
             
            	if(overChk>0){
            		$.dialog("alert", {
                        message : "이미 등록된 IP 입니다."
                    });
                    return false;	
            	}else{
                    return true;
            	}
            }
        });
        req.go();
    },
    /**
     * 삭제 IP
     */
    delIp: function(obj) {
    	 $(obj).parent().remove();
    },
    delPermitDate: function(obj) {
    	var form =  document.getElementById('iMUssMngr');
        form.elements["permitStart"].value="";
        form.elements["permitEnd"].value="";
    }
}


$(document).ready(function(){
	REQ.init();
	$("[data-role='datepicker']" ).datepicker({
		showOn:"both",
		buttonText: '',
		dateFormat: '${imdatepickerDateType}',
		changeYear: true,
		yearRange: "1900:+nn"
	});
});

</script>



<ul class="nav_tabs">
	<li ><a href="#" onclick="REQ.modify();return false;">상세 수정</a></li>
	<li class="on"><a href="#" >허용관리</a></li>
</ul>

<%@ include file="incUssMngr.jsp" %>


<div class="cb_bar right">
	<im:pageBtn type="M" reqName="REQ">
	</im:pageBtn>
</div>


<c:set var="arrYesNo">Y=있음,N=없음</c:set>
<form:form commandName="iMUssMngr" name="iMUssMngr" method="post" onsubmit="return false;">
<input name="password"   type="hidden" value="ex~Test#$12" />
<form:hidden path="esntlId"/>
<table class="tbl_row">
	<caption>업무사용자정보 상세정보</caption>
	<colgroup>
		<col style="width:200px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">접근제한<span class="c_red">*</span></th>
				<td><im:cd type="radio" name="usePermitYn" selectedCode="${ussPermit.usePermitYn}" codeId="${arrYesNo}"></im:cd>  </td>
			</tr>
			<tr>
				<th scope="row">접근기간<a href="javascript:REQ.delPermitDate()">[사용 안함]</a></th>
				<td>
					<div class="ad_datepicker">
						<input type="text" name="permitStart" value="${ussPermit.permitStart}" placeholder="시작일" data-role="datepicker">
					</div>
					~
					<div class="ad_datepicker">
						<input type="text" name="permitEnd" value="${ussPermit.permitEnd}" placeholder="종료일" data-role="datepicker">
					</div>  
					
					(시작일과 종료일을 등록한 경우에만 사용됨)
				</td>
			</tr>
			<tr>
				<th scope="row">허용IP</th>
				<td>
					<div id="permitAcessipID">
					<c:forEach items="${ussPermit.permitAcessips}" var="row">
					<div><input type="hidden" name="permitAcessips" value="${row}"/><span>${row}</span><span style="cursor: pointer;" onclick="REQ.delIp(this)">[삭제]</span></div>
					</c:forEach>
            		</div>
            		<input type="text" maxlength="16" name="permitAcessip"/>
            		<a href="javascript:REQ.addPermitAcessip()">[추가]</a>
				</td>
			</tr>
	</tbody>
</table>
</form:form>
<div class="b_box right">
	<im:pageBtn type="M" reqName="REQ">
	</im:pageBtn>
</div>