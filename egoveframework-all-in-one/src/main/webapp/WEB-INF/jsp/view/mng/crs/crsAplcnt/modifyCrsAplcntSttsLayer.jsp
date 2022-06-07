<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<script type="text/javascript" >


var REQ = {
	req : {		
			save : null
	},
	init : function(){
		this.req.save = imRequest("ajax",{formId: "FormSave"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "${pageContext.request.contextPath}/mng/crsAplcnt/stts/${crsAplcntStts}/update.do";
		this.req.save.cfg.message.confirm="<spring:message code="common.update.msg" />";
		this.req.save.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) {
	        	var callback = COMMT.getLayerCallback(parent, "<c:out value="${param['imCallBack']}"/>");
	            if (typeof callback === "function") {
	                callback.call(this);
	            }
	        	COMMT.callCloseLayer(parent);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    <c:if  test="${crsAplcntStts eq 'AGNCY_SRNG'}">
	    this.req.save.validator.set({
	    	title : "교육수수료",
            name : "ttnfee",
            data : ["number"],
            check : {  
                maxlength : 6 
            }
        });
		var form =  document.getElementById('FormSave');
	    REQ.onchangeChgCd(form.elements["ttnfeeChgCdv"].value);
	    </c:if>
	    
	    this.req.save.validator.set({
	    	title : "상태",
            name : "sttsCdv",
            data : ["!null"]
        });
	    this.req.save.validator.set({
	    	title : "비고",
            name : "sttsRmks" ,
            check : {  
                maxlength : 300 
            }
        });
	    
	    
		
	
	},
	save :  function(){
		REQ.req.save.go();
	},
	close:  function(){
		COMMT.callCloseLayer(parent);
	},
	onchangeChgCd: function(cd){
		if(cd=='01'){
			$("#crsTtnfee").show();
			$("#crsAplCntTtnfee").hide();
		}else{
			$("#crsTtnfee").hide();
			$("#crsAplCntTtnfee").show();
			
		}
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>
<form name="FormSave" id="FormSave" method="post" onsubmit="return false;">
<input type="hidden" name="crsAplcntId"  value="${detail.crsAplcnt.crsAplcntId}"   />
<table class="tbl_row">
	<caption> 상세정보</caption>
	<colgroup>
		<col style="width:120px;">
		<col>
		<col style="width:120px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">회원 아이디</th>
				<td><c:out value="${detail.crsAplcnt.mberIdMask}"/></td>
				<th scope="row">이름</th>
				<td><c:out value="${detail.crsAplcnt.mberNmMask}"/></td>
			</tr>
			<c:if  test="${crsAplcntStts eq 'AGNCY_SRNG'}">
			<tr>
				<th scope="row">수수료변경사유</th>
				<td>
					<c:set var="ttnfeeChgCdvArr">01=변경없음,02=유사과목면제 할인,03=기관자체 할인,99=기타</c:set>
					<select name="ttnfeeChgCdv" onchange="REQ.onchangeChgCd(this.value)">
						<im:cd type="option" codeId="${ttnfeeChgCdvArr}" selectedCode="${detail.crsAplcnt.ttnfeeChgCdv}"/>
					</select>	
				</td>
				<th scope="row">교육수수료</th>
				<td>				 
				<span id="crsTtnfee" style="display: none;">
				<input type="text"   readonly="readonly" 
				value="<c:out value="${detail.crs.ttnfee}"/>"
				 style="text-align: right;" size="5" max="5"/>
				</span>
				<span id="crsAplCntTtnfee" style="display: none;">
				<input type="text" name="ttnfee" 
				value="<c:out value="${detail.crsAplcnt.ttnfee}"/>"
				 style="text-align: right;" size="5" max="5"/>
				 </span>
				</td>
			</tr>
			</c:if>
			<tr>
				<th scope="row">상태</th>
				<td colspan="3">	
					<select name="sttsCdv" >
						<im:cd type="option" codeId="IM0017" selectedCode="${detailStts.sttsCdv}"/>
					</select>	
				</td>
			</tr>
			<tr>
				<th scope="row">비고</th>
				<td colspan="3">
				<textarea name="sttsRmks" id="" cols="" rows="" style="height:100px;">${detailStts.sttsRmks}</textarea>
				</td>
			</tr>
	</tbody>
</table>
</form>	
<div class="b_box">
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	<a href="#" onclick="REQ.close();return false;" class="ad_btn bk">닫기</a>
</div>