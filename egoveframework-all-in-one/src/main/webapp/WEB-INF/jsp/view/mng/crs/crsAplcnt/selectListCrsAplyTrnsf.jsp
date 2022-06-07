<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null,
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/crsAplyTrnsf/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/crsAplyTrnsf/selectList.do"/>";
	
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/crsAplyTrnsf/modify.do"/>";
		
		
		this.req.del = imRequest("ajax",{formId: "FormList"});
		this.req.del.cfg.type   = "json";
		this.req.del.cfg.url    =  "<c:url value="/mng/crsAplyTrnsf/deletelist.do"/>";
		this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
		this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.page(1);
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.del.validator.set({
	    	message : "<spring:message code="im.common.msg.delete.choice" />",
            name : "checkIndexs",
            data : ["!null"]
        });
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		REQ.req.modify.go();
	},
	search : function(){
		REQ.req.search.go();
	},
	del : function(){
		IMGLBObject.request=this;
		this.req.del.go();
	},
	changePerPage : function(recordCountPerPage){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["recordCountPerPage"].value= recordCountPerPage;
		if(recordCountPerPage==-1){
			form.elements["sercharAll"].value= "1";
		}else{
			form.elements["sercharAll"].value= "0";
		}
		this.page(1);
	},
	changeOrdeBy : function(orderbyKey){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["orderbyKey"].value= orderbyKey;
		this.page(1);
	},
	pageload :  function(){
		var form =  document.getElementById(REQ.req.list.cfg.formId);
		form.elements["currentPageNo"].value= 1;
		REQ.req.list.go();
	},
	regist:function(){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/crsAplyTrnsf/registExcelLayer.do"/>";
		req.cfg.options.title="일괄 등록";
		req.cfg.formId = "FormPageDetail";

		var form =  document.getElementById(req.cfg.formId);
		form.elements["imCallBack"].value= "REQ.pageload";
		
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=300;
		req.go();
			
	},
	migMember:function(){
		var req = imRequest("ajax",{formId: "FormPageDetail"});
		req.cfg.type   = "json";
		req.cfg.message.confirm="작업중 해당 창을 닫지 말고 기다려 주세요.  기존 데이터는 업데이트 됩니다. 자료를 이관 하시겠습니까?";
		req.cfg.url    =  "<c:url value="/mng/crsAplyTrnsf/updateMigMember.do"/>";
		req.cfg.asynchronous = true; // 인 경우 asynchronous
		req.cfg.message.waiting = "처리중입니다.";
		req.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.pageload();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    req.go();
	},
	migCrs:function(){
		var req = imRequest("ajax",{formId: "FormPageDetail"});
		req.cfg.type   = "json";
		req.cfg.message.confirm="과정신청 이관은 회원이관이 완료된 회원만 이관 됩니다.<br>이관된 회원은 실명인증되어야 수료처리됩니다.<br>해당기관의 코드가 일치한 과정이 생성 되어야합니다.<br>작업중 해당 창을 닫지 말고 기다려 주세요.   자료를 이관 하시겠습니까?";
		req.cfg.url    =  "<c:url value="/mng/crsAplyTrnsf/updateMigCrs.do"/>";
		req.cfg.asynchronous = true; // 인 경우 asynchronous
		req.cfg.message.waiting = "처리중입니다.";
		req.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.pageload();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    req.go();
	},
	migWtst:function(){
		var req = imRequest("ajax",{formId: "FormPageDetail"});
		req.cfg.type   = "json";
		req.cfg.message.confirm="시험응시 이관은 과정이관이 완료된 회원만 이관 됩니다.<br>사진은 학습자가 로그인해서 추가해야합니다.<br>이관된 회원은 실명인증되어야 수료처리됩니다.<br>작업중 해당 창을 닫지 말고 기다려 주세요.   자료를 이관 하시겠습니까?";
		req.cfg.url    =  "<c:url value="/mng/crsAplyTrnsf/updateMigWtst.do"/>";
		req.cfg.asynchronous = true; // 인 경우 asynchronous
		req.cfg.message.waiting = "처리중입니다.";
		req.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.pageload();
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
<%@ include file="incCrsAplyTrnsf.jsp" %>

<c:set var="trnsfYnArr">M=회원이관,C=과정이관,W=시험이관,N=미이관</c:set>


<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mmbrNm=<spring:message code="iMCrsAplyTrnsf.mmbrNm" />,mmbrId=<spring:message code="iMCrsAplyTrnsf.mmbrId" />,telno=<spring:message code="iMCrsAplyTrnsf.telno" />,crsAplyTrnsfId=<spring:message code="iMCrsAplyTrnsf.crsAplyTrnsfId" />,crsAplyTrnsfNo=<spring:message code="iMCrsAplyTrnsf.crsAplyTrnsfNo" /></c:set>
	<div class="sch_box">
		<div class="group">
			<select name="scTrnsfYn" onchange="REQ.search()">
					<option value="">이관여부</option>
					<im:cd type="option" codeId="${trnsfYnArr}" selectedCode="${condition.scTrnsfYn}"></im:cd>
			</select>
		</div>		     		
		<div class="group">
			<select name="scKey" >
				<im:cd type="option" codeId="${scKey}" selectedCode="${condition.scKey}"/>
			</select>
			<input type="text" name="scWord" value="<c:out value="${condition.scWord}"/>"  onKeyup="COMMT.enterCallFunc(event, REQ.search);" style="width:300px;">
			
			<button type="button" onclick="REQ.search()" class="ad_btn gray"><spring:message code="im.common.word.select" /></button>
		</div>
	</div>
</im:form>


<%--목록 상단 --%>
<div class="cb_bar">
	<c:set var="scOrderInfo">1=<spring:message code="iMCrsAplyTrnsf.crsAplyTrnsfNo" /></c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<div>  
	<a href="#" onclick="REQ.migMember();return false;" class="bt_new bk">회원이관</a>
	<a href="#" onclick="REQ.migCrs();return false;" class="bt_new bk">과정이관</a> 
	<a href="#" onclick="REQ.migWtst();return false;" class="bt_new bk">시험이관</a> 
	<a href="#" onclick="REQ.regist();return false;" class="bt_new bk">일괄등록</a> 
	</div>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>		
	<col/>
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><spring:message code="iMCrsAplyTrnsf.crsAplyTrnsfId"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.crsAplyTrnsfNo"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.crsNm"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.mmbrNm"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.mmbrId"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.telno"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.eml"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.brdt"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.agncyNm"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.agncyCode"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.eduYear"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.eduRnd"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.crsGrd"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.trnsfYn"/></th>
	<th scope="col"><spring:message code="iMCrsAplyTrnsf.trnsfAplyId"/></th>

			<th scope="col"><spring:message code="im.common.word.regDt" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
				<td><c:out value="${row.crsAplyTrnsf.crsAplyTrnsfId}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.crsAplyTrnsfNo}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.crsNm}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.mmbrNm}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.mmbrId}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.telno}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.eml}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.brdt}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.agncyNm}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.agncyCode}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.eduYear}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.eduRnd}"/></td>
				<td><c:out value="${row.crsAplyTrnsf.crsGrd}"/></td>
				<td>
				<im:cd  codeId="${trnsfYnArr}" selectedCode="${row.crsAplyTrnsf.trnsfYn}" type="print"/>
				</td>
				<td class="al">회원 : <c:out value="${row.idDto.uniqId}"/>
				<br>과정 : <c:out value="${row.idDto.crsAplcntId}"/>
				<br>응시 : <c:out value="${row.idDto.wtstAplcntId}"/>
				</td>
				<td><im:dt yyyyMMddHHmmss="${row.crsAplyTrnsf.frstRegDt}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="17"><spring:message code="im.common.msg.nodata" /></td>
		</tr>			
		</c:if>
	</tbody>
</table>
</form>
			
<%--페이징 처리 --%>
<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imPagination.jsp">
	<c:param name="pageInfo" value="pageInfo"/>
	<c:param name="jsFunction" value="REQ.page"/>
</c:import>