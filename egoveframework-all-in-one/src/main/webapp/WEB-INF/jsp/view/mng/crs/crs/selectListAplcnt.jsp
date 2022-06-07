<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {

	req : {
			crslist : null,
			list : null,
			updateAply : null
	},
	init : function(){

		this.req.crslist = imRequest();
		this.req.crslist.cfg.formId = "FormPageDetail";
		this.req.crslist.cfg.url    = "<c:url value="/mng/crs/selectList.do"/>";
	
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetailTab";
		this.req.list.cfg.url    = "<c:url value="/mng/crs/${tabCode}/selectListAplcnt.do"/>";
		

		this.req.updateAply = imRequest("ajax",{formId: "FormList"});
		this.req.updateAply.cfg.type   = "json";
		this.req.updateAply.cfg.url    =  "<c:url value="/mng/crsAplcnt/APLY/updateAplylist.do"/>";
		this.req.updateAply.cfg.message.confirm="변경하시겠습니까?";
		this.req.updateAply.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.req.list.go();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.updateAply.validator.set({
	    	message : "변경할 수강정보를 선택하세요",
            name : "checkIndexs",
            data : ["!null"]
        });
	},
	crslist : function(){
		REQ.req.crslist.go();
	},
	updateAply : function(sttsCd,chageStts){
		IMGLBObject.request=this;
		REQ.req.updateAply.cfg.url    =  "<c:url value="/mng/crsAplcnt/"/>"+sttsCd+"/updateAplylist.do";
		var form =  document.getElementById(this.req.updateAply.cfg.formId);
		form.elements["chageStts"].value= chageStts;
		REQ.req.updateAply.go();
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
	updateCrsAplyRAM : function(){
		<c:if test="${detail.crs.prgrsSttsCdv eq '01'}">
		 
		 var req = imRequest("ajax",{formId: "FormPageDetail"});
			req.cfg.type   = "json";			
			req.cfg.url    =  "<c:url value="/mng/crs/02/updatePrgrsSttsCdv.do"/>";
			req.cfg.message.confirm="랜덤 선정 처리 하시겠습니까?";
			req.cfg.fn.complete = function(act, data) {
				REQ.req.list.go();
			}
			var form =  document.getElementById(req.cfg.formId );
			form.elements["crsId"].value= '${param["crsId"]}';
			
			req.go();
		 
		 
		</c:if>
		<c:if test="${detail.crs.prgrsSttsCdv ne '01'}">
		
		COMMT.message('이미 랜덤 선정 되었습니다.');
		
		</c:if>
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incCrs.jsp" %>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberNm=<spring:message code="iMCrsAplcnt.mberNm"/>,mberId=<spring:message code="iMCrsAplcnt.mberId"/>,crsAplcntId=<spring:message code="iMCrsAplcnt.crsAplcntId" /></c:set>
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
	<c:set var="scOrderInfo">4=<spring:message code="iMCrsAplcnt.crsAplcntId" />,2=<spring:message code="iMCrsAplcnt.mberId"/>,3=<spring:message code="iMCrsAplcnt.mberNm"/>,1=<spring:message code="iMCrsAplcnt.regDt" /></c:set><%-- todo  정렬 조건 추가  --%>
	<c:set var="noPageYn" value="Y"/>
	<c:set var="condition" value="${condition.aplcnt}"/>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:set var="condition" value="${condition}"/>
	
	 <div class="cb_bar right">
	<div>
	
	 <a href="#"  onclick="REQ.updateCrsAplyRAM()"   class="ad_btn green">랜덤선정</a>
	 <a href="#" onclick="REQ.crslist();return false;" class="ad_btn bk">목록</a>
	 </div>
	</div>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<input type="hidden" name="chageStts" />
<table class="tbl_col">
	<colgroup>
	<c:if test="${tabCode ne '02' }">
	<col style="width:50px;">
	</c:if>
	<col style="width:50px;">
	<col style="width:168px;">
	<col/>
	<col/>
	<col/>
	<col/>
	<col style="width:90px;">
	<col style="width:100px;">
	<thead>
		<tr>
			<c:if test="${tabCode ne '02' }">
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			</c:if>
			<th scope="col">번호</th>
			<th scope="col"><spring:message code="iMCrsAplcnt.crsAplcntId" /></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberId"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberNm"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberEmailAdres"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mmbrTelno"/></th>
			<th scope="col">랜덤선정상태</th>
			<th scope="col"><spring:message code="iMCrsAplcnt.regDt" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<c:if test="${tabCode ne '02' }">
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<input type="hidden" name="crsAplcntIds"  value="${row.crsAplcnt.crsAplcntId}"/>
			</td>
			</c:if>
			<td class="ar">${i.count}</td>
			<td>
			${row.crsAplcnt.crsAplcntId}
			</td>
			<td><c:out value="${row.crsAplcnt.mberIdMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mberNmMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mberEmailAdresMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mmbrTelnoMask}"/></td>
			<td>
				<c:set var="RNDM">
					<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="RNDM" />
				</c:set>
				<im:cd type="print" codeId="IM0017" selectedCode="${RNDM}"/>
			</td>
			<td><im:dt yyyyMMddHHmmss="${row.crsAplcnt.frstRegDt}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<c:if test="${tabCode ne '02' }">
			<td colspan="9"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
			<c:if test="${tabCode eq '02' }">
			<td colspan="8"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
		</tr>		
		</c:if>	
	</tbody>
</table>
</form>
		