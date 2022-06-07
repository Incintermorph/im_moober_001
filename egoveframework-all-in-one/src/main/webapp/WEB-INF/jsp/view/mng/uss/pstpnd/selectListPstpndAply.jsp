<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null,
		updateAply : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/pstpndAply/${stts}/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/pstpndAply/${stts}/selectList.do"/>";
		

		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/pstpndAply/${stts}/modify.do"/>";
		
		
		this.req.updateAply = imRequest("ajax",{formId: "FormList"});
		this.req.updateAply.cfg.type   = "json";
		this.req.updateAply.cfg.url    =  "<c:url value="/mng/pstpndAply/updateAplylist.do"/>";
		this.req.updateAply.cfg.message.confirm="변경하시겠습니까?";
		this.req.updateAply.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.page(1);
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
	excel : function(){
		COMMT.ready();
	},
	updateAply : function(sttsCdv){
		var form =  document.getElementById(REQ.req.updateAply.cfg.formId);
		form.elements["sttsCdv"].value= sttsCdv;
		REQ.req.updateAply.go();
	},
   	tab :  function(aplyStts){
   		var form =  document.getElementById(this.req.list.cfg.formId);
   		form.elements["currentPageNo"].value= 1;
   		this.req.list.cfg.url    = "<c:url value="/mng/pstpndAply/"/>"+aplyStts+"/selectList.do";
   		this.req.list.go();
   	},
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incPstpndAply.jsp" %>


<c:set var="arrTabs">A=신청,03=신청취소</c:set>
<im:cd type="set" codeId="${arrTabs}" var="arrTagCode"/>


<ul class="nav_tabs">
	<c:forEach items="${arrTagCode}" var="trow">
	<li <c:if test="${stts eq trow.code }"> class="on"</c:if>><a href="#" onclick="REQ.tab('${trow.code}')">${trow.codeNm}</a></li>
	</c:forEach>
</ul>


<c:set var="regTypeArr">01=사용자,02=관리자</c:set>

<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mberNm=이름,moblphonNo=휴대전화번호</c:set>
	<div class="sch_box">		
		<div class="group">
			
			<select name="scDvsnCdv" onchange="REQ.search()" >
				<option value="">취득시기</option>
				<im:cd type="option" codeId="${dvsnCdvArr}"  selectedCode="${condition.scDvsnCdv}"/>
			</select>
			
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
			 <select name="scEduYear" onchange="REQ.search()" >
				<option value=""><spring:message code="iMCrs.eduYear"/></option>
				<c:forEach begin="2015" var="num" end="${imEduYearEnd}">
				<option value="${num}"
					<c:if test="${num eq  condition.scEduYear}">
					selected="selected"
					</c:if> 
					>${num}</option>
				</c:forEach>
			</select>
			
			<select name="scCrsGrdCdv" onchange="REQ.search()" >
				<option value=""><spring:message code="iMIssuAply.crsGrdCdv"/></option>
				<im:cd type="option" codeId="IM0001"  selectedCode="${condition.scCrsGrdCdv}"/>
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
	<c:set var="scOrderInfo">2=회원 아이디,3=이름,1=신청일자</c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:if test="${stts eq 'A'}">
	 <div>
	 <a href="#" onclick="REQ.excel();return false;" class="bt_new bk">엑셀</a>
	 <a href="#"  onclick="REQ.updateAply('03')"   class="bt_new bk">유예신청취소</a>
	 <a href="#"  onclick="REQ.updateAply('02')"  class="bt_new bk">유예 승인</a>
	 <a href="#"  onclick="REQ.updateAply('04')"  class="bt_new bk">유예 미승인</a>
	</div>
	</c:if>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<input type="hidden" name="sttsCdv">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:50px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:100px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:60px;"/>
		<col style="width:90px;"/>
		<col style="width:90px;"/>
		<col style="width:90px;"/>
		<col style="width:100px;"/>
		<col style="width:110px;"/>
		<col style="width:110px;"/>
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col"><spring:message code="im.common.word.No"/></th>
				<th scope="col">취득시기</th>
				<th scope="col">회원 아이디</th>
				<th scope="col">이름</th>
				<th scope="col">이메일</th>
				<th scope="col">휴대전화번호</th>
				<th scope="col">이수양성기관</th>
				<th scope="col">이수기간</th>
				<th scope="col">등급</th>
				<th scope="col">보수교육회수</th>
				<th scope="col">위반회수</th>
				<th scope="col">유예신청구분</th>
				<th scope="col">유예상태</th>
				<th scope="col">신청일자</th>
				<th scope="col">확인일자</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="pstpndAplyIds"  value="${row.pstpndAply.pstpndAplyId}"/>
			</td>
			<td class="ar">${pageInfo.descIndex - i.index}</td> <%--페이징 역순  --%> <%--순번 : ${pageInfo.ascIndex + i.index} --%>
			</td>
			<td>
				<c:if test="${stts eq 'A'}">
				<a href="#" onclick="REQ.modify({'pstpndAplyId':'${row.pstpndAply.pstpndAplyId}'});return false;" class="tb_link">
				<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${row.pstpndAply.dvsnCdv}"/>
				</a>
				</c:if>
				<c:if test="${stts ne 'A'}">
				<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${row.pstpndAply.dvsnCdv}"/>
				</c:if>
			</td>
			<td class="al"><c:out value="${row.mberManage.mberIdMask}"/></td>
			<td><c:out value="${row.mberManage.mberNmMask}"/></td>
			<td class="al"><c:out value="${row.mberManage.mberEmailAdresMask}"/></td>
			<td><c:out value="${row.pstpndAply.mmbrTelnoMask}"/></td>
			<td  class="al"></td>
			<td></td>
			<td><im:cd type="print" codeId="IM0001" selectedCode="${row.pstpndAply.crsGrdCdv}"/></td>
			<td class="ar"></td>
			<td class="ar"></td>
			<td>
			<c:if test="${row.pstpndAply.esntlId eq  row.pstpndAply.frstRegerId}">
			<im:cd  type="print" codeId="${regTypeArr}" selectedCode="01" />
			</c:if>
			<c:if test="${row.pstpndAply.esntlId ne  row.pstpndAply.frstRegerId}">
			<im:cd  type="print" codeId="${regTypeArr}" selectedCode="02" />
			</c:if>
			</td>
			<td><im:cd type="print" codeId="IM0024" selectedCode="${row.pstpndAply.sttsCdv}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.pstpndAply.aplyYmd}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.pstpndAply.idntyYmd}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="16"><spring:message code="im.common.msg.nodata" /></td>
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