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
		this.req.wtstPlacelist.cfg.url    = "<c:url value="/mng/wtst/selectListPlace.do"/>";
		
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetailTab";
		this.req.list.cfg.url    = "<c:url value="/mng/wtst/wtstPlace/"/>${tabSubCode}/selectListAplcnt.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormPageDetailTab";
		this.req.search.cfg.url    =  "<c:url value="/mng/wtst/wtstPlace/"/>${tabSubCode}/selectListAplcnt.do";
		

		this.req.updateAply = imRequest("ajax",{formId: "FormList"});
		this.req.updateAply.cfg.type   = "json";
		this.req.updateAply.cfg.url    =  "<c:url value="/mng/wtstAplcnt/APLY/updateAplylist.do"/>";
		this.req.updateAply.cfg.message.confirm="변경하시겠습니까?";
		this.req.updateAply.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.reload();
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
		form.elements["aplcnt.scExempDvsnCdv"].value= serchform.elements["aplcnt.scExempDvsnCdv"].value;
		form.elements["aplcnt.scFnshYn"].value= serchform.elements["aplcnt.scFnshYn"].value;
		form.elements["wtstId"].value='${param['wtstId']}';
		form.elements["wtstPlaceId"].value='${param['wtstPlaceId']}';;
		form.elements["_pageName"].value='시험장 > 자격심사';
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
		var form =  document.getElementById(this.req.wtstPlacelist.cfg.formId  );
		form.elements["wtstId"].value='${param['wtstId']}';
		form.elements["_pageName"].value='시험장';
		REQ.req.wtstPlacelist.go();
	},
	reload : function(){
		var form =  document.getElementById(REQ.req.list.cfg.formId );

		form.elements["wtstId"].value='${param['wtstId']}';
		form.elements["wtstPlaceId"].value='${param['wtstPlaceId']}';;
		form.elements["_pageName"].value='시험장 > 자격심사';
		REQ.req.list.go();
	},
	updateAply : function(sttsCd){
		<c:if test="${detail.wtstPlace.prgrsSttsCdv eq '01'}">
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
		<c:if test="${detail.wtstPlace.prgrsSttsCdv ne '01'}">
		COMMT.message('심사 전 상태에서만 원서접수 상태 변경이 가능합니다.<br>선정확정 이후에는  원서접수 상태 변경을  원서접수 메뉴에서 하실 수 있습니다',350);
		</c:if>
	},
	modifyStts : function(wtstAplcntId){
		<c:if test="${detail.wtstPlace.prgrsSttsCdv eq '01'}">
		var req = imRequest("layer");
		req.cfg.options.title="특이사항 관리";
		req.cfg.formId = "FormModifyStts";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=700;
		req.cfg.options.height=410;
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["wtstAplcntId"].value= wtstAplcntId;
		req.cfg.url    = "${pageContext.request.contextPath}/mng/wtstAplcnt/stts/OPSECT_SRNG/modify.do";
		req.go();

		</c:if>
		<c:if test="${detail.wtstPlace.prgrsSttsCdv ne '01'}">
		COMMT.message('심사 전 상태에서만 원서접수 상태 변경이 가능합니다.<br>선정확정 이후에는  원서접수 상태 변경을  원서접수 메뉴에서 하실 수 있습니다',350);
		</c:if>
	},	
	updateCrsAply : function(){
		<c:if test="${detail.wtstPlace.prgrsSttsCdv eq '01'}">
		 var req = imRequest("ajax",{formId: "FormPageDetail"});
			req.cfg.type   = "json";			
			req.cfg.url    =  "<c:url value="/mng/wtstPlace/04/updatePrgrsSttsCdv.do"/>";
			req.cfg.message.confirm="모든 대상자에 대한 자격심사가 완료되었는지 다시 한번 확인해 주세요<br>통보을 하시겠습니까?";
			req.cfg.fn.complete = function(act, data) {
				
				REQ.wtstPlacelist();
			}
			var form =  document.getElementById(req.cfg.formId );
			form.elements["wtstPlaceId"].value= '${param["wtstPlaceId"]}';
			
			req.go();
		 
		</c:if>
		<c:if test="${detail.wtstPlace.prgrsSttsCdv ne '01'}">
		COMMT.message('선정완료는 심사 전 상태에서만 처리해야합니다.<br>선정확정 이후에는  원서접수 상태 변경을  원서접수 메뉴에서 하실 수 있습니다',350);
		</c:if>
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


<div class="tb_info">
	<ul>
		<li>${detail.agncy.agncyNm}</li>
		<li>${detail.wtst.eduYear} 년</li>
		<li><im:cd type="print" codeId="IM0001" selectedCode="${detail.wtst.crsGrdCdv}"/></li>
		<li>${detail.wtst.eduRnd}차</li>
		<li>정원 : ${detail.wtstPlace.prsnl}명</li>
		<c:if test="${detail.wtst.addtnYn eq 'Y'}">
		<li>추가모집</li>
		</c:if>
		<li><im:cd type="print" codeId="IM0020" selectedCode="${detail.wtstPlace.prgrsSttsCdv}"/></li>
	</ul>
</div>
<c:set var="exempDvsnCdvArr">Y=예,N=아니오</c:set>

<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mberNm=이름,eml=이메일,mblTelno=휴대전화번호,wtstAplcntId=원서접수아이디</c:set>
	<div class="sch_box">
		<div class="group">
		
			<select name="aplcnt.scExempDvsnCdv" onchange="REQ.search()" >
				<option value="">기본과정 면제 유형</option>
				<im:cd type="option" codeId="${exempDvsnCdvArr}"  selectedCode="${condition.aplcnt.scExempDvsnCdv}"/>
			</select>	
			<select name="aplcnt.scFnshYn" onchange="REQ.search()" >
				<option value="">기본과정 수료여부</option>
				<im:cd type="option" codeId="${ARR_IMYENO}"  selectedCode="${condition.aplcnt.scFnshYn}"/>
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
	<c:set var="scOrderInfo">2=회원아이디,3=이름,4=대기번호,1=접수일자</c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<c:set var="noPageYn" value="Y"/>
	<c:set var="condition" value="${condition.aplcnt}"/>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:set var="condition" value="${condition}"/>
	<div class="cb_bar right">
	<div>
	 <select id="sttsCdv_">
	 <option value="">필기시험 자격심사 상태</option>
	 <im:cd type="option" codeId="${ARR_IM0017}" except="04"/>
	 </select>
	 <a href="#"  onclick="REQ.updateAply('OPSECT_SRNG')"  class="bt_new bk">변경</a>
	 <%--
	 <a href="#" onclick="COMMT.ready();return false;" class="bt_new bk">엑셀</a>
	  --%>
	 <a href="#"  onclick="REQ.updateCrsAply()"   class="ad_btn green">통보</a>
	 <a href="#" onclick="REQ.wtstPlacelist();return false;" class="ad_btn bk">뒤로가기</a>
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
		<col style="width:168px;">
		<col style="width:108px;">
		<col style="width:90px;">
		<col style="width:108px;">
		<col style="width:90px;">
		<col style="width:100px;">
		<col style="width:100px;">
		<col style="width:100px;">
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
			<th scope="col">대기번호</th>		
			<th scope="col">편의제공 여부</th>
			<th scope="col">수험생<br>원서접수상태</th>
			<th scope="col">필기시험 <br>자격심사 상태</th>
			<th scope="col">기본과정<br>수료여부</th>
			<th scope="col">증빙대상</th>
			<th scope="col">관리</th>
			
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
			<td><c:out value="${row.wtstAplcnt.wtnOrd}"/></td>
			
			<td><im:cd type="print" codeId="IMYENO" selectedCode="${row.wtstAplcnt.convPvsnYn}"/></td>
			
			
			<td>			
				<c:set var="APLY">
				<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="APLY" />
				</c:set>
				<im:cd type="print" codeId="${ARR_IM0021}" selectedCode="${APLY}"/>
			</td>	
			<td>			
				<c:set var="OPSECT_SRNG">
				<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="OPSECT_SRNG" />
				</c:set>
				<im:cd type="print" codeId="${ARR_IM0017}" selectedCode="${OPSECT_SRNG}"/>
			</td>
			<td>
			<im:cd type="print" codeId="${ARR_IMYENO}" selectedCode="${row.fnshYn}"/>
			</td>
			<td>
				<c:set var="exempDvsnCdvArr">02=온라인수료,03=교육 수강자,04=유사과목 인정과목,Y=예,N=아니오</c:set>
			 <im:cd type="print" codeId="${exempDvsnCdvArr}" selectedCode="${row.wtstAplcnt.exempDvsnCdv}"/>
			</td>
			<td>
				<a href="javascript:REQ.view('<c:out value="${row.wtstAplcnt.mmbrEsntlId}"/>');">
				[이력관리]
				</a>
				<br>
				<a href="javascript:REQ.modifyStts('<c:out value="${row.wtstAplcnt.wtstAplcntId}"/>');">
				[특이사항관리]
				</a>
			</td>			
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="13"><spring:message code="im.common.msg.nodata" /></td>			
		</tr>			
		</c:if>
	</tbody>
</table>
</form>
			