<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null,
		regist : null,
		del : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/mng/objcAply/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/mng/objcAply/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/mng/objcAply/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/mng/objcAply/modify.do"/>";
		
		
		this.req.del = imRequest("ajax",{formId: "FormList"});
		this.req.del.cfg.type   = "json";
		this.req.del.cfg.url    =  "<c:url value="/mng/objcAply/deletelist.do"/>";
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
	regist :  function(){
		this.req.regist.go();
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		this.req.modify.go();
	},
	search : function(){
		this.req.search.go();
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
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incObjcAply.jsp" %>



<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberId=회원아이디,mmbrNm=이름,eml=이메일,mblTelno=핸드폰</c:set>
	<div class="sch_box">	
		<div class="group">
			<select name="scDvsnCdv" onchange="REQ.search()" >
				<option value="">구분</option>
				<im:cd type="option" codeId="${dvsnCdvArr}"  selectedCode="${condition.scDvsnCdv}"/>
			</select>
			<select name="scObjcDvsnCdv" onchange="REQ.search()" >
				<option value="">문의구분</option>
				<im:cd type="option" codeId="${objcDvsnCdvArr}"  selectedCode="${condition.scObjcDvsnCdv}"/>
			</select>
			<select name="scSttsCdv" onchange="REQ.search()" >
				<option value="">상태</option>
				<im:cd type="option" codeId="${sttsCdvArr}"  selectedCode="${condition.scSttsCdv}"/>
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
	<c:set var="scOrderInfo">3=회원아이디,2=이름,1=신청일자,2=답변일자</c:set><%-- todo  정렬 조건 추가  --%>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:100px;">
		<col style="width:80px;">
		<col style="width:120px;">
		<col style="width:120px;">
		<col style="width:80px;">
		<col style="width:70px;">
		<col style="width:70px;">
		<col style="width:250px;">
		<col style="width:100px;">
		<col style="width:70px;">
		<col style="width:90px;">
		<col style="width:90px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><spring:message code="im.common.word.No"/></th>
			<th scope="col">회원 아이디</th>
			<th scope="col">이름</th>
			<th scope="col">이메일</th>
			<th scope="col">휴대전화번호</th>
			<th scope="col">구분</th>
			<th scope="col">년도</th>
			<th scope="col">등급</th>
			<th scope="col">제목</th>
			<th scope="col">문의구분</th>
			<th scope="col">상태</th>
			<th scope="col">신청일자</th>
			<th scope="col">답변일자</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td class="ar">${pageInfo.descIndex - i.index}</td> <%--페이징 역순  --%> <%--순번 : ${pageInfo.ascIndex + i.index} --%>
			<td class="al"><c:out value="${row.mberManage.mberIdMask}"/></td>
			<td><c:out value="${row.mberManage.mberNmMask}"/></td>
			<td  class="al"><c:out value="${row.mberManage.mberEmailAdresMask}"/></td>
			<td><c:out value="${row.mberManage.moblphonNoMask}"/></td>
			<td><im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${row.objcAply.dvsnCdv}"/></td>
			<td>${row.objcAplyDtl.eduYear}</td>
			<td><im:cd type="print" codeId="IM0001" selectedCode="${row.objcAplyDtl.crsGrdCdv}"/></td>
			<td class="al">
			<a href="#" onclick="REQ.modify({'objcAplyId' : '${row.objcAply.objcAplyId}'});return false;">
			${row.objcAply.aplyNm}
			</a>
			</td>
			<td>
			<im:cd type="print" codeId="${objcDvsnCdvArr}" selectedCode="${row.objcAply.objcDvsnCdv}"/>
			</td>
			<td>
				<im:cd type="print" codeId="${sttsCdvArr}" selectedCode="${row.objcAply.sttsCdv}"/>
			</td>			
			<td><im:dt yyyyMMddHHmmss="${row.objcAply.frstRegDt}"/></td>
			<td><im:dt yyyyMMddHHmmss="${row.objcAply.ansRegYmd}"/></td>
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
			
<%--페이징 처리 --%>
<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imPagination.jsp">
	<c:param name="pageInfo" value="pageInfo"/>
	<c:param name="jsFunction" value="REQ.page"/>
</c:import>