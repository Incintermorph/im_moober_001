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
		this.req.save.cfg.url    =  "${pageContext.request.contextPath}/mng/wtstAplcnt/stts/${sttsCdv}/update.do";
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
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>
<form name="FormSave" id="FormSave" method="post" onsubmit="return false;">
<input type="hidden" name="wtstAplcntId"  value="${detail.wtstAplcnt.wtstAplcntId}"   />
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
				<td><c:out value="${detail.wtstAplcnt.mberId}"/></td>
				<th scope="row">이름</th>
				<td><c:out value="${detail.wtstAplcnt.mberNm}"/></td>
			</tr>
			<tr>
				<th scope="row">상태</th>
				<td colspan="3">	
					<select name="sttsCdv" >
						<im:cd type="option" codeId="IM0017" selectedCode="${detailStts.sttsCdv}"/>
					</select>	
				</td>
			</tr>
			<tr>
				<th scope="row">편의제공여부</th>
				<td>
				<im:cd codeId="IMYENO" type="print" name="convPvsnYn" selectedCode="${detail.wtstAplcnt.convPvsnYn}" />
				</td>
				<th scope="row">편의제공증빙</th>
				<td>
						<c:forEach items="${detail.fileList2}" var="frow">
						<c:set var="file_Key" value="${frow.atchFileId}=${frow.fileSn}"/>
						<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<c:out value="${frow.orignlFileNm}"/>&nbsp;[<c:out value="${imfunc:fileSizeView(frow.fileMg)}"/>]
						</a>
						</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row">기본과정면제유형</th>
				<td>
				<c:set var="exempDvsnCdvArr">02=온라인수료,03=교육 수강자,04=유사과목 인정과목,=해당없음</c:set>
			 	<im:cd type="print" codeId="${exempDvsnCdvArr}" selectedCode="${detail.wtstAplcnt.exempDvsnCdv}"/>
				</td>
				<th scope="row">기본과정면제증빙</th>
				<td>
				<c:forEach items="${detail.fileList3}" var="frow">
						<c:set var="file_Key" value="${frow.atchFileId}=${frow.fileSn}"/>
						<a href="javascript:COMMT.fn_egov_downFileEnc('<c:out value="${imfunc:encryptString(file_Key)}"/>')"  >
						<c:out value="${frow.orignlFileNm}"/>&nbsp;[<c:out value="${imfunc:fileSizeView(frow.fileMg)}"/>]
						</a>
				</c:forEach>
				<c:if test="${empty detail.fileList3}">
				파일 없음
				</c:if>
				
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