<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		updateAply : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/crsAplcnt/"/>${aplyStts}/selectList.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/crsAplcnt/"/>${aplyStts}/selectList.do";
		

		
		this.req.updateAply = imRequest("ajax",{formId: "FormList"});
		this.req.updateAply.cfg.type   = "json";
		this.req.updateAply.cfg.url    =  "<c:url value="/mng/crsAplcnt/APLY/updateAplylist.do"/>";
		this.req.updateAply.cfg.message.confirm="변경하시겠습니까?";
		this.req.updateAply.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	IMGLBObject.request.page(1);
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
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	
	search : function(){
		REQ.req.search.go();
	},
	updateAply : function(chageStts){
		IMGLBObject.request=this;		
		var form =  document.getElementById(this.req.updateAply.cfg.formId);
		form.elements["chageStts"].value= chageStts;
		
		var req = imRequest("script", {formId : "FormList"}); 
    	req.cfg.fn.exec  = function() {
    		REQ.selectMemo(chageStts);
        };

        req.validator.set({
        	message : "변경할 정보를 선택하세요",
            name : "checkIndexs",
            data : ["!null"]
        });
		
        req.go();
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
	selectMemo:function(chageStts){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/crsAplcnt/select/memoLayer.do"/>";
		req.cfg.options.title="변경 사유 등록";
		req.cfg.formId = "FormModifyAlyStts";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=330;
		var form =  document.getElementById(req.cfg.formId);
		form.elements["chageStts"].value= chageStts;
		req.go();
			
	},
	setMemo:function(chageMsg){
		var form =  document.getElementById(REQ.req.updateAply.cfg.formId);
		form.elements["chageMsg"].value= chageMsg;
		REQ.req.updateAply.cfg.message.confirm=null;
		REQ.req.updateAply.go();
	},
	selectCmmnStts:function(scTblRefId){
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/crsAplcnt/cmmnStts/APLY/selectListLayer.do"/>";
		req.cfg.options.title="변경 이력";
		req.cfg.formId = "FormHisAlyStts";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=1024;
		req.cfg.options.height=730;
		var form =  document.getElementById(req.cfg.formId);
		form.elements["scTblRefId"].value= scTblRefId;
		req.go();
	},
	tab :  function(aplyStts){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= 1;
		this.req.list.cfg.url    = "<c:url value="/mng/crsAplcnt/"/>"+aplyStts+"/selectList.do";
		this.req.list.go();
	},
}

$(document).ready(function(){
	REQ.init();
});


</script>


<form name="FormHisAlyStts" id="FormHisAlyStts" method="post" onsubmit="return false;">
<input type="hidden" name="scTblRefId"   />
</form>
<form name="FormModifyAlyStts" id="FormModifyAlyStts" method="post" onsubmit="return false;">
<input type="hidden" name="chageStts"   />
<input type="hidden" name="sttsCdv" value="APLY"   />
<input type="hidden" name="imCallBack" value="REQ.setMemo"  />
</form>
	
	
<%@ include file="incCrsAplcnt.jsp" %>



<c:set var="arrTabs">A=신청,03=신청취소,02=교육결과</c:set>

<im:cd type="set" codeId="${arrTabs}" var="arrTagCode"/>


<ul class="nav_tabs">
	<c:forEach items="${arrTagCode}" var="trow">
	<li <c:if test="${aplyStts eq trow.code }"> class="on"</c:if>><a href="#" onclick="REQ.tab('${trow.code}')">${trow.codeNm}</a></li>
	</c:forEach>
</ul>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mberNm=이름,eml=이메일,mblTelno=휴대전화번호,crsAplcntId=<spring:message code="iMCrsAplcnt.crsAplcntId" /></c:set>
	<div class="sch_box">
		<div class="group">
		     <c:if test="${empty  loginUserAgncyId}">
			<select name="scAgncyId" onchange="REQ.search()">
					<option value=""><spring:message code="iMCrs.agncyNm"/></option>
					<c:forEach items="${agncyList}" var="row">
					<option value="${row.agncy.agncyId}"
					<c:if test="${row.agncy.agncyId eq  condition.scAgncyId}">
					selected="selected"
					</c:if> 
					>${row.agncy.agncyNm}</option>
					</c:forEach>
			</select>
		     </c:if>
		     <select name="scEduYear" onchange="REQ.search()" >
				<option value=""><spring:message code="iMCrs.eduYear"/></option>
				<c:forEach begin="${imStartYear}" var="num" end="${imEduYearEnd}">
				<option value="${num}"
					<c:if test="${num eq  condition.scEduYear}">
					selected="selected"
					</c:if> 
					>${num}</option>
				</c:forEach>
			</select>
			<select name="scEduRnd" onchange="REQ.search()" >
				<option value=""><spring:message code="iMCrs.eduRnd"/></option>
				<c:forEach begin="${imEduRndStart}" var="num" end="${imEduRndEnd}">
				<option value="${num}"
					<c:if test="${num eq  condition.scEduRnd}">
					selected="selected"
					</c:if> 
					>${num}</option>
				</c:forEach>
			</select>
			<select name="scCrsGrdCdv" onchange="REQ.search()" >
				<option value=""><spring:message code="iMCrsMstr.crsGrd"/></option>
				<im:cd type="option" codeId="IM0001"  selectedCode="${condition.scCrsGrdCdv}"/>
			</select>
			<select name="scCrsDvsnCdv"  onchange="REQ.search()">
				<option value=""><spring:message code="iMCrsMstr.crsDvsn"/></option>
				<im:cd type="option" codeId="IM0002" selectedCode="${condition.scCrsDvsnCdv}"/>
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
	<c:set var="scOrderInfo">2=<spring:message code="iMCrsAplcnt.mberId"/>,3=<spring:message code="iMCrsAplcnt.mberNm"/>,1=<spring:message code="im.common.word.regDt" /></c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:if test="${aplyStts eq 'A'}">
	<div>
	 <a href="#"  onclick="REQ.updateAply('01')"   class="bt_new bk"><spring:message code="iMCrsAplcnt.btn.01"/></a>
	 <a href="#"  onclick="REQ.updateAply('03')"   class="bt_new bk"><spring:message code="iMCrsAplcnt.btn.03"/></a>
	 <a href="#"  onclick="REQ.updateAply('02')"  class="bt_new bk"><spring:message code="iMCrsAplcnt.btn.02"/></a>
	 <a href="#"  onclick="REQ.updateAply('04')"  class="bt_new bk"><spring:message code="iMCrsAplcnt.btn.04"/></a>
	 <a href="#"  onclick="REQ.updateAply('05')"  class="bt_new bk"><spring:message code="iMCrsAplcnt.btn.05"/></a>
	 </div>
	 </c:if>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<input type="hidden" name="chageStts" />
<input type="hidden" name="chageMsg" />
<table class="tbl_col">
	<colgroup>
	<c:if  test="${aplyStts eq 'A'}">
		<col style="width:50px;">
    </c:if>		
		<col style="width:168px;">
		<col style="width:100px;">
		<col style="width:90px;">
		<col style="width:168px;">
		<col style="width:108px;">
		<col style="width:150px;">
	
		<col style="width:90px;">
		<col style="width:90px;">
		<col style="width:120px;">
	<c:if  test="${aplyStts eq 'A'}">

		<col style="width:100px;">
		<col style="width:100px;">
		<col style="width:100px;">
		<col style="width:110px;">
		</c:if>
	</colgroup>
	<c:if  test="${aplyStts eq '03'}">
		<col style="width:90px;">
		<col style="width:100px;">
		<col style="width:110px;">
	</c:if>
	<c:if  test="${aplyStts eq '02'}">
		<col style="width:100px;">
		<col style="width:110px;">
	</c:if>
				
	<thead>
		<tr>
			<c:if  test="${aplyStts eq 'A'}">
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			</c:if>
			<th scope="col"><spring:message code="iMCrsAplcnt.crsAplcntId" /></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberId"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberNm"/></th>
	<th scope="col"><spring:message code="iMCrsAplcnt.mberEmailAdres"/></th>
	<th scope="col"><spring:message code="iMCrsAplcnt.mmbrTelno"/></th>
	<th scope="col"><spring:message code="iMCrs.agncyNm"/></th>
	<th scope="col"><spring:message code="iMCrs.eduYear"/></th>
				<th scope="col"><spring:message code="iMCrs.eduRnd"/></th>
				<th scope="col"><spring:message code="iMCrs.crsNm"/></th>
			<c:if  test="${aplyStts eq 'A'}">
				<th scope="col"><spring:message code="iMCrsAplcnt.aplyStts"/></th>
				<th scope="col"><spring:message code="iMCrsAplcnt.dpstYn"/></th>
				<th scope="col"><spring:message code="iMCrsAplcnt.regDt" /></th>
				<th scope="col"><spring:message code="im.common.word.mng" /></th>
			</c:if>
			<c:if  test="${aplyStts eq '03'}">
				<th scope="col"><spring:message code="iMCrsAplcnt.aplyStts"/></th>
				<th scope="col"><spring:message code="im.common.word.canceldate" /></th>
				<th scope="col"><spring:message code="im.common.word.mng" /></th>
			</c:if>
			
			<c:if  test="${aplyStts eq '02'}">
				<th scope="col">수료상태</th>
				<th scope="col">자격취득상태</th>
		    </c:if>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<c:if  test="${aplyStts eq 'A'}">
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="crsAplcntIds"  value="${row.crsAplcnt.crsAplcntId}"/>
			</td>
			</c:if>
			<td>
			${row.crsAplcnt.crsAplcntId}
			</td>
			<td class="al"><c:out value="${row.crsAplcnt.mberIdMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mberNmMask}"/></td>
			<td  class="al"><c:out value="${row.crsAplcnt.mberEmailAdresMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mmbrTelnoMask}"/></td>
			<td  class="al"><c:out value="${row.agncy.agncyNm}"/></td>
			<td><c:out value="${row.crs.eduYear}"/></td>
			<td><c:out value="${row.crs.eduRnd}"/></td>
			<td>
			<im:cd type="print" codeId="IM0001" selectedCode="${row.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${row.crsMstr.crsDvsnCdv}"/>
			</td>	
			<c:if  test="${aplyStts eq 'A'}">
			<td>
				<c:set var="APLY">
				<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="APLY" />
				</c:set>
				<im:cd type="print" codeId="IM0012" selectedCode="${APLY}"/>
			</td>
			<td>
			<c:set var="DPST">
				<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="DPST" />
				</c:set>
			
				<im:cd type="print" codeId="IMYENO" selectedCode="${DPST}"/>
			</td>
			<td><im:dt yyyyMMddHHmmss="${row.crsAplcnt.frstRegDt}"/></td>
			<td>
			<a href="javascript:REQ.view('<c:out value="${row.crsAplcnt.mmbrEsntlId}"/>');">
			[이력관리]
			</a>
			<br>
			<a href="javascript:REQ.selectCmmnStts('<c:out value="${row.crsAplcnt.crsAplcntId}"/>');">
			[상태변경이력]
			</a>
			</td>
			</c:if>
			<c:if  test="${aplyStts eq '03'}">
			<td>
				<c:set var="APLY">
				<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="APLY" />
				</c:set>
				<c:set var="APLY_ID">
				<im:cd  codeId="${row.sttDdvIds}" type="print" selectedCode="APLY" />
				</c:set>
				<c:set var="APLY_DT">
				<im:cd  codeId="${row.sttDdvDts}" type="print" selectedCode="APLY" />
				</c:set>
				<c:if test="${APLY_ID ne  row.crsAplcnt.mmbrEsntlId}">
				관리자 취소
				</c:if>
				<c:if test="${APLY_ID eq row.crsAplcnt.mmbrEsntlId}">
				사용자 취소
				</c:if>
			</td>
			<td><im:dt yyyyMMddHHmmss="${APLY_DT}"/></td>
			<td>
			<a href="javascript:REQ.selectCmmnStts('<c:out value="${row.crsAplcnt.crsAplcntId}"/>');">
			[상태변경이력]
			</a>
			</td>
			</c:if>
			<c:if  test="${aplyStts eq '02'}">
			<td>
					<c:set var="FNSH">
						<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="FNSH" />
					</c:set>
					<im:cd type="print" codeId="IM0013" selectedCode="${FNSH}"/>
				</td>
				<td>
					<c:set var="QLFC">
						<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="QLFC" />
					</c:set>
					<im:cd type="print" codeId="IM0014" selectedCode="${QLFC}"/>
				</td>
			
			</c:if>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<c:if  test="${aplyStts eq 'A'}">
			<td colspan="14"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
			<c:if  test="${aplyStts eq '03'}">
			<td colspan="12"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
			<c:if  test="${aplyStts eq '02'}">
			<td colspan="11"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
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