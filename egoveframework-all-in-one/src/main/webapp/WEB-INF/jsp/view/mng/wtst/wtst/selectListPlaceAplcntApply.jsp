<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		wtstPlacelist : null,
		list : null,
		search : null,
		updateAply : null
	},
	init : function(){
		

		this.req.wtstPlacelist = imRequest();
		this.req.wtstPlacelist.cfg.formId = "FormPageDetail";
		this.req.wtstPlacelist.cfg.url    = "<c:url value="/mng/wtst/selectList.do"/>";
		
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetailTab";
		this.req.list.cfg.url    = "<c:url value="/mng/wtst/"/>${tabSubCode}/selectListAplcnt.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormPageDetailTab";
		this.req.search.cfg.url    =  "<c:url value="/mng/wtst/"/>${tabSubCode}/selectListAplcnt.do";
		

		this.req.updateAply = imRequest("ajax",{formId: "FormList"});
		this.req.updateAply.cfg.type   = "json";
		this.req.updateAply.cfg.url    =  "<c:url value="/mng/wtstAplcnt/APLY/updateAplylist.do"/>";
		this.req.updateAply.cfg.message.confirm="변경하시겠습니까?";
		this.req.updateAply.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.req.list.go();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.updateAply.validator.set({
	    	message : "변경할 정보를 선택하세요",
            name : "checkIndexs",
            data : ["!null"]
        });
	
	},
	search : function(){
		var serchform =  document.getElementById("FormSearch");
		var form =  document.getElementById(REQ.req.list.cfg.formId);
		form.elements["aplcnt.scKey"].value= serchform.elements["aplcnt.scKey"].value;
		form.elements["aplcnt.scWord"].value= serchform.elements["aplcnt.scWord"].value;		
		REQ.req.list.go();
	},
	changeOrdeBy : function(orderbyKey){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["aplcnt.orderbyKey"].value= orderbyKey;
		REQ.req.list.go();
	},
	view : function(esntlId){
		var req = imRequest("popup");
				
		req.cfg.options.width=2048;
		req.cfg.options.height=1024;
		
		req.cfg.formId = "FormPageDetail";
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["esntlId"].value= esntlId;
		req.cfg.url    = "${pageContext.request.contextPath}/mng/mmbrHstry/modify.do";
		req.go();
	},
	wtstPlacelist : function(){
		REQ.req.wtstPlacelist.go();
	},
	reload : function(){
		REQ.req.list.go();
	},	
	updateNumberAply : function(){
		 var req = imRequest("ajax",{formId: "FormPageDetail"});
			req.cfg.type   = "json";			
			req.cfg.url    =  "<c:url value="/mng/wtstAplcnt/updateNumberAplylist.do"/>";
			
			req.cfg.fn.complete = function(act, data) {
				TAB.list('${param['tabCode']}','${param['_pageName']}');
			}
			req.cfg.message.confirm="수험번호 생성하시겠습니까?";
			var form =  document.getElementById(req.cfg.formId );
			form.elements["wtstPlaceId"].value= '${param["wtstPlaceId"]}';
			req.go();
	},
	printAdmissionTicket: function(wtstAplcntId){ 
			COMMT.fn_Enc_Print('<c:url value="/mng/wtstAplcnt/selectDetailExamPrint.do"/>?wtstAplcntId='+wtstAplcntId,1260,850);
	},
	excel : function(excelResult){
		
		var req = imRequest();
		req.cfg.formId = "FormPageDetail";
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["wtstId"].value= '${param["wtstId"]}';
		form.elements["excelResult"].value= excelResult;
		form.elements["downloadName"].value= "응시생_${detailWtst.wtst.eduYear}년_<im:cd type="print" codeId="IM0001" selectedCode="${detailWtst.wtst.crsGrdCdv}"/>_${detailWtst.wtst.eduRnd}차";
		
		req.cfg.url    = "<c:url value="/mng/wtst/05/selectListAplcntExcel.do"/>";
		req.go();
	},

	selectMemo:function(){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/ussMngr/select/memoLayer.do"/>";
		req.cfg.options.title="개인조회 사유 등록";
		req.cfg.formId = "FormPageDetail";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=300;
		req.go();
			
	}
	
}


$(document).ready(function(){
	REQ.init();
});


</script>



<form name="FormModifyStts" id="FormModifyStts" method="post" onsubmit="return false;">
<input type="hidden" name="wtstAplcntId"   />
<input type="hidden" name="imCallBack" value="REQ.reload"  />
</form>


	

<%@ include file="incWtst.jsp" %>


<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mberNm=이름,eml=이메일,mblTelno=휴대전화번호,wtstAplcntId=원서접수아이디</c:set>
	<div class="sch_box">
		<div class="group">
			<select name="aplcnt.scKey" >
				<im:cd type="option" codeId="${scKey}" selectedCode="${condition.aplcnt.scKey}"/>
			</select>
			<input type="text" name="aplcnt.scWord" value="<c:out value="${condition.aplcnt.scWord}"/>"  onKeyup="COMMT.enterCallFunc(event, REQ.search);" style="width:300px;">
			<button type="button" onclick="REQ.search()" class="ad_btn gray"><spring:message code="im.common.word.select" /></button>
		</div>
	</div>
</im:form>
 

<%--목록 상단 --%>
<div class="cb_bar">
	<c:set var="scOrderInfo">2=회원아이디,3=이름,1=접수일자</c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<c:set var="noPageYn" value="Y"/>
	<c:set var="condition" value="${condition.aplcnt}"/>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:set var="condition" value="${condition}"/>
	<div class="cb_bar right">
	<div>
	 <a href="#" onclick="REQ.selectMemo();return false;" class="bt_new bk">응시생 다운로드</a>
	 
	 <%--
	 <a href="#" onclick="REQ.updateCrsAply()"   class="ad_btn green">통보</a>
	  --%>
	 <a href="#" onclick="REQ.updateNumberAply()"   class="ad_btn green">수험번호 생성</a>
	 <a href="#" onclick="REQ.wtstPlacelist();return false;" class="ad_btn bk">목록</a>
	 </div>
	</div>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<input type="hidden" name="chageStts" />
<input type="hidden" name="chageMsg" />
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:168px;">
		<col style="width:100px;">
		<col style="width:90px;">
		<col/>
		<col style="width:108px;">
		<col style="width:108px;">		
		<col style="width:108px;">		
		<col style="width:100px;">
		<col style="width:100px;">
		<col style="width:125px;">
		<col style="width:110px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col">원서접수아이디</th>
			<th scope="col">회원 아이디</th>
			<th scope="col">이름</th>
			<th scope="col">이메일</th>
			<th scope="col">휴대전화번호</th>
			<th scope="col">시험장명</th>		
			<th scope="col">편의제공 여부</th>
			<th scope="col">원서접수상태</th>
			<th scope="col">입금확인여부</th>
			<th scope="col">수험번호</th>
			<th scope="col">접수일자</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="wtstAplcntIds"  value="${row.wtstAplcnt.wtstAplcntId}"/>
			</td>
			<td>
			${row.wtstAplcnt.wtstAplcntId}
			</td>			
			<td  class="al"><c:out value="${row.wtstAplcnt.mberIdMask}"/></td>
			<td><c:out value="${row.wtstAplcnt.mberNmMask}"/></td>
			<td  class="al"><c:out value="${row.wtstAplcnt.mberEmailAdresMask}"/></td>
			<td><c:out value="${row.wtstAplcnt.mmbrTelnoMask}"/></td>
			<td class="al"><c:out value="${row.agncy.agncyNm}"/></td>
			<td><im:cd type="print" codeId="${ARR_IMYENO}" selectedCode="${row.wtstAplcnt.convPvsnYn}"/></td>
			<td>			
				<c:set var="APLY">
				<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="APLY" />
				</c:set>
				<im:cd type="print" codeId="${ARR_IM0021}" selectedCode="${APLY}"/>
			</td>	
			<td>			
				<c:set var="DPST">
					<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="DPST" />
				</c:set>
				<im:cd type="print" codeId="${ARR_IMYENO}" selectedCode="${DPST}"/>
			</td>	
			<td>
			<c:if test="${!empty  row.wtstAplcnt.tktstno}">
			<a href="javascript:;" onclick="REQ.printAdmissionTicket('${row.wtstAplcnt.wtstAplcntId}')" >${row.wtstAplcnt.tktstno}</a>
			</c:if>
			</td>		
			<td><im:dt yyyyMMddHHmmss="${row.wtstAplcnt.frstRegDt}"/></td>		
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="14"><spring:message code="im.common.msg.nodata" /></td>			
		</tr>			
		</c:if>
	</tbody>
</table>
</form>
			