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
		this.req.wtstPlacelist.cfg.url    = "<c:url value="/mng/wtstPlace/selectList.do"/>";
		
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetailTab";
		this.req.list.cfg.url    = "<c:url value="/mng/wtstPlace/"/>${tabCode}/selectListAplcnt.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormPageDetailTab";
		this.req.search.cfg.url    =  "<c:url value="/mng/wtstPlace/"/>${tabCode}/selectListAplcnt.do";
		

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
		form.elements["aplcnt.scDpstStts"].value= serchform.elements["aplcnt.scDpstStts"].value;
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
	updateAply : function(sttsCd){
		<c:if test="${detail.wtstPlace.prgrsSttsCdv eq '02'}">
		IMGLBObject.request=this;
		
		if($("#sttsCdv_").val()==''){
			COMMT.message('변경할 상태를 선택하세요.');
			return;
		}
		var chageStts = $("#sttsCdv_").val(); 
		REQ.req.updateAply.cfg.url    =  "<c:url value="/mng/wtstAplcnt/"/>"+sttsCd+"/updateAplylist.do";
		
		var form =  document.getElementById(this.req.updateAply.cfg.formId);
		form.elements["chageStts"].value= chageStts;
		REQ.req.updateAply.go();
		</c:if>
		<c:if test="${detail.wtstPlace.prgrsSttsCdv ne '02'}">
		COMMT.message('선정확정  상태에서만 처리해야합니다.');
		</c:if>
	},
	updateCrsAply : function(){
		<c:if test="${detail.wtstPlace.prgrsSttsCdv eq '02'}">
		 var req = imRequest("ajax",{formId: "FormPageDetail"});
			req.cfg.type   = "json";			
			req.cfg.url    =  "<c:url value="/mng/wtstPlace/03/updatePrgrsSttsCdv.do"/>";
			req.cfg.message.confirm="입금완료 확인 처리 하시겠습니까?";
			req.cfg.fn.complete = function(act, data) {
				TAB.list('${param['tabCode']}','${param['_pageName']}');
			}
			var form =  document.getElementById(req.cfg.formId );
			form.elements["wtstPlaceId"].value= '${param["wtstPlaceId"]}';
			
			req.go();
		 
		</c:if>
		<c:if test="${detail.wtstPlace.prgrsSttsCdv ne '02'}">
		COMMT.message('입금완료는 선정확정  상태에서만 처리해야합니다.');
		</c:if>
	},
	updateAplySttsCD : function(sttsCd,chageStts){
		<c:if test="${detail.wtstPlace.prgrsSttsCdv eq '02'}">
		IMGLBObject.request=this; 
		REQ.req.updateAply.cfg.url    =  "<c:url value="/mng/wtstAplcnt/"/>"+sttsCd+"/updateAplylist.do";
		var form =  document.getElementById(this.req.updateAply.cfg.formId);
		form.elements["chageStts"].value= chageStts;
		
		
		
		var req = imRequest("script", {formId : "FormList"}); 
		req.cfg.fn.exec  = function() {
			REQ.selectMemo(sttsCd,chageStts);
        };
        
        
        req.validator.set({
        	message : "변경할 수강정보를 선택하세요",
            name : "checkIndexs",
            data : ["!null"]
        });
        req.go();		
		</c:if>
		<c:if test="${detail.wtstPlace.prgrsSttsCdv ne '02'}">
		COMMT.message('선정확정  상태에서만 처리해야합니다.');
		</c:if>
	},
	selectMemo:function(sttsCd,chageStts){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/wtstAplcnt/select/memoLayer.do"/>";
		req.cfg.options.title="변경 사유 등록";
		req.cfg.formId = "FormModifyAlyStts";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=330;
		var form =  document.getElementById(req.cfg.formId);
		form.elements["chageStts"].value= chageStts;
		form.elements["sttsCdv"].value= sttsCd;
		req.go();
			
	},
	setMemo:function(chageMsg){
		var form =  document.getElementById(REQ.req.updateAply.cfg.formId);
		form.elements["chageMsg"].value= chageMsg;
		REQ.req.updateAply.cfg.message.confirm=null;
		REQ.req.updateAply.go();
	},
	
}


$(document).ready(function(){
	REQ.init();
});


</script>



<form name="FormModifyStts" id="FormModifyStts" method="post" onsubmit="return false;">
<input type="hidden" name="wtstAplcntId"   />
<input type="hidden" name="imCallBack" value="REQ.reload"  />
</form>

<form name="FormModifyAlyStts" id="FormModifyAlyStts" method="post" onsubmit="return false;">
<input type="hidden" name="chageStts"   />
<input type="hidden" name="sttsCdv" value="APLY"   />
<input type="hidden" name="imCallBack" value="REQ.setMemo"  />
</form>

	

<%@ include file="incWtstPlace.jsp" %>


<c:set var="exempDvsnCdvArr">02=온라인수료,03=교육 수강자,04=유사과목 인정과목</c:set>

<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mberNm=이름,eml=이메일,mblTelno=휴대전화번호,wtstAplcntId=원서접수아이디</c:set>
	<div class="sch_box">
		<div class="group">
			<select name="aplcnt.scDpstStts" onchange="REQ.search()">
			 <option value="">입금확인여부</option>
			 <im:cd type="option" codeId="IMYENO" selectedCode="${condition.aplcnt.scDpstStts}"/>
			 </select>
		</div>
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
	<c:set var="scOrderInfo">2=회원아이디,3=이름,4=대기번호,5=입금확인일자</c:set>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<c:set var="noPageYn" value="Y"/>
	<c:set var="condition" value="${condition.aplcnt}"/>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:set var="condition" value="${condition}"/>
	<div class="cb_bar right">
	<div>
	 <select id="sttsCdv_">
	 <option value="">입금확인 여부</option>
	 <im:cd type="option" codeId="IMYENO" />
	 </select>
	 <a href="#"  onclick="REQ.updateAply('DPST')"  class="bt_new bk">변경</a>
	 <a href="#" onclick="COMMT.ready();return false;" class="bt_new bk">엑셀</a>
	 <a href="#"  onclick="REQ.updateCrsAply()"   class="ad_btn green">입금확인 완료</a>
	 <a href="#"  onclick="REQ.updateAplySttsCD('APLY','03')"   class="bt_new bk">접수취소</a>
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
		<col style="width:110px;">
		<col style="width:108px;">
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
			<th scope="col">입금 대상금액</th>
			<th scope="col">입금확인 여부</th>
			<th scope="col">입금 확인일자</th>
			
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
			<td class="ar">
				<im:numberFormat value="${row.wtst.tstfee}"/>
			</td>
			<td>
				<c:set var="DPST">
					<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="DPST" />
				</c:set>
				<im:cd type="print" codeId="IMYENO" selectedCode="${DPST}"/>
			</td>
			<td>
			<c:if test="${DPST eq 'Y'}">
				<c:set var="DPST_DT">
				<im:cd  codeId="${row.sttDdvDts}" type="print" selectedCode="DPST" />
				</c:set>
				<im:dt yyyyMMddHHmmss="${DPST_DT}"/>
			</c:if>
			</td>		
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="9"><spring:message code="im.common.msg.nodata" /></td>			
		</tr>			
		</c:if>
	</tbody>
</table>
</form>
			