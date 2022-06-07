<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null,
		regist : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/mmbrQlfc/${nowType}/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/mmbrQlfc/${nowType}/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/mng/mmbrQlfc/pstpndAply/${nowType}/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/mmbrQlfc/pstpndAply/${nowType}/modify.do"/>";
		
		
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	regist :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.regist.cfg.formId);
		REQ.req.regist.go();
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		REQ.req.modify.go();
	},
	search : function(){
		REQ.req.search.go();
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
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incMmbrQlfc.jsp" %>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mberNm=이름,,moblphonNo=휴대전화번호</c:set>
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
	<c:set var="scOrderInfo">3=회원 아이디,4=이름,5=자격취득일,2=유효기간만료일</c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;"/>
		<col style="width:50px;"/>
		<col style="width:120px;"/>
		<col style="width:80px;"/>
		<col style="width:100px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:120px;"/>
		<col style="width:60px;"/>
		<col style="width:90px;"/>
		<col style="width:80px;"/>
		<col style="width:100px;"/>
		<col style="width:100px;"/>
		<col style="width:80px;"/>
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
				<th scope="col">자격취득일</th>
				<th scope="col">유효기간<br>만료일</th>
				<th scope="col">관리</th>		
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="esntlIds"  value="${row.mmbrQlfc.esntlId}"/>
			<input type="hidden" name="crsGrdCdvs"  value="${row.mmbrQlfc.crsGrdCdv}"/>
			</td>
			<td class="ar">${pageInfo.descIndex - i.index}</td> <%--페이징 역순  --%> <%--순번 : ${pageInfo.ascIndex + i.index} --%>
				<td>
				<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${row.mmbrQlfc.dvsnCdv}"/>
				</td>
	<td class="al"><c:out value="${row.mberManage.mberIdMask}"/></td>
	<td><c:out value="${row.mberManage.mberNmMask}"/></td>
	<td class="al"><c:out value="${row.mberManage.mberEmailAdresMask}"/></td>
	<td><c:out value="${row.mberManage.moblphonNoMask}"/></td>
	<td class="ar">${row.mmbrHstry.agncyNm}</td>
	<td>
	<c:if test="${!empty  row.mmbrQlfc.cntneduBgnDt}">
	<im:dt yyyyMMddHHmmss="${row.mmbrQlfc.cntneduBgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.mmbrQlfc.cntneduEndDt}"/>
	</c:if>
	</td>
	<td><im:cd type="print" codeId="IM0001" selectedCode="${row.mmbrQlfc.crsGrdCdv}"/></td>
	<td class="al"><c:out value="${row.mmbrQlfc.vlatCnt}"/></td>
	<td class="al"><c:out value="${row.mmbrQlfc.cntneduCnt}"/></td>
	
			<td><im:dt yyyyMMddHHmmss="${row.mmbrQlfc.lcncAcqsYmd}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.mmbrQlfc.lcncEndYmd}"/></td>
			<td>
			<c:if test="${empty row.pstpndAply.pstpndAplyId}">	
				<a href="#" onclick="REQ.regist({'esntlId':'${row.mmbrQlfc.esntlId}','dvsnCdv':'${row.mmbrQlfc.dvsnCdv}','qlfcRsltCode' : '${row.mmbrQlfc.qlfcRsltCode}','crsGrdCdv':'${row.mmbrQlfc.crsGrdCdv}'});return false;" class="c_btn bk sm">유예신청</a>
			</c:if>
			<c:if test="${!empty row.pstpndAply.pstpndAplyId}">
				<a href="#" onclick="REQ.modify({'pstpndAplyId':'${row.pstpndAply.pstpndAplyId}'});return false;" class="c_btn bk sm">신청 조회</a>	
			</c:if>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="15"><spring:message code="im.common.msg.nodata" /></td>
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