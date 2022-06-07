<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<c:set var="retureUrlVal">
/cmmn/crs/${crsType}/${crsGrd}/selectList.do?_paramMenuNo=${nowMenuNo}
</c:set>
<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		detail : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/cmmn/crs/"/>${crsType}/${crsGrd}/selectList.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/cmmn/crs/"/>${crsType}/${crsGrd}/selectList.do";
		

		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/cmmn/crs/"/>${crsType}/${crsGrd}/selectDetail.do";
		
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	detail :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.detail.cfg.formId);
		this.req.detail.go();
	},	
	search : function(){
		REQ.req.search.go();
	},
	changeProcType : function(procType){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["scProcType"].value= procType;
		this.page(1);
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>

<%@ include file="incCrs.jsp" %>

<div class="ct_box bg bot">
	<span class="p_noti red"><i class="m_icon_out circle_notifications"></i>중요 </span>: 환경교육사 사이트 신규 운영에 따라 <a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="high">이력관리</a> 재등록이 필요합니다.
	<ul class="radio">
		<li>교육에 대한 자세한 사항은 하단의 공고문을 다운로드 받아서 확인하여 주시기 바랍니다.</li>
	</ul>
</div>
<div class="cb_bar right">
	<div class="add"> 
		<a href="<c:url value="/cmmn/page/intro06/page.do"/>"   class="c_btn l_gray">자격취득 안내</a>
		<a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="c_btn d_green em">이력관리</a>
		<c:if test="${detail.procType eq 'I'}">
		<a href="javascript:;" onclick="REQ.apply()" class="c_btn bk">교육신청</a>
		</c:if>
	</div>
</div>	

<ul class="nav_tabs">
			<li <c:if test="${condition.scProcType eq 'I' }"> class="on"</c:if>><a href="#" onclick="REQ.changeProcType('I')">접수 중</a></li>
			<li <c:if test="${condition.scProcType eq 'W' }"> class="on"</c:if>><a href="#"  onclick="REQ.changeProcType('W')">준비중</a></li>
			<li <c:if test="${condition.scProcType eq 'E' }"> class="on"</c:if>><a href="#"  onclick="REQ.changeProcType('E')">접수마감</a></li>
		</ul>
		<c:set var="scCrsGrdCdvArr">CRS_GRD_2=3급,CRS_GRD_2=2급,CRS_GRD_1=1급</c:set>
		<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
		<input type="hidden" name="scProcType"      value="<c:out value="${condition.scProcType}"/>" />
		<div class="form_box">
			<div class="inner">
				<div class="comb">
						<div class="ip_gp">
					<c:if test="${crsGrd eq 'A'}">
							<select name="scCrsGrdCdv">
								<option value=""><spring:message code="iMCrsMstr.crsGrd"/></option>
								<im:cd type="option" codeId="${scCrsGrdCdvArr}"  selectedCode="${condition.scCrsGrdCdv}"/>
							</select>
					</c:if>
					<select name="scAgncyId">
							<option value=""><spring:message code="iMCrs.agncyNm"/></option>
							<c:forEach items="${agncyList}" var="row">
							<option value="${row.agncy.agncyId}"
							<c:if test="${row.agncy.agncyId eq  condition.scAgncyId}">
							selected="selected"
							</c:if> 
							>${row.agncy.agncyNm}</option>
							</c:forEach>
					</select>
					</div>
					<%--
					<input type="hidden" name="scKey" value="agncyNm"/>
					<input type="text" class="key" name="scWord" value="${condition.scWord}" placeholder="양성기관명을 입력하세요" onKeyup="COMMT.enterCallFunc(event, REQ.search);" title="검색어">
					 --%>
					<a href="#"  onclick="REQ.search()" class="c_btn bk">검색</a>
				</div>
			</div>
		</div>
		<div class="t_bar">
			<div class="count">
				총 <span class="num">${pageInfo.condition.totalRecordCount}</span>건
			</div>
		</div>
		</im:form>
		<div class="tbl_rps_data">
			<table class="tbl_data">
				<caption>교육신청 목록</caption>
				<colgroup>
					<col style="width:10%;">
					<col />
					<col style="width:9%;">
					<col style="width:16%;">
					<col style="width:16%;">
					<c:if test="${condition.scProcType eq 'I' }">
					<col style="width:12%;">
					</c:if>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">과정명</th>
						<th scope="col">양성기관</th>
						<th scope="col">선발인원</th>
						<th scope="col">접수기간</th>
						<th scope="col">교육기간</th>
						<c:if test="${condition.scProcType eq 'I' }">
						<th scope="col"></th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
					<tr>
						<td class="al">
							<div class="th">과정명</div>
							<div class="td">
								<a href="#" onclick="REQ.detail({'crsId' : '${row.crs.crsId}'});return false;"  class="tb_link">
								<im:cd type="print" codeId="IM0001" selectedCode="${row.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${row.crsMstr.crsDvsnCdv}"/>
								</a>
							</div>
						</td>
						<td class="al">
							<div class="th">양성기관</div>
							<div class="td"><c:out value="${row.agncy.agncyNm}"/></div>
						</td>
						<td class="ar">
							<div class="th">선발인원</div>
							<div class="td"><c:out value="${row.crs.prsnl}"/></div>
						</td>
						<td>
							<div class="th">접수기간</div>
							<div class="td">
								<im:dt yyyyMMddHHmmss="${row.crs.eduAplTerm_bgnDt}"/>
								~
								<im:dt yyyyMMddHHmmss="${row.crs.eduAplTerm_endDt}"/>
							</div>
						</td>
						<td>
							<div class="th">교육기간</div>
							<div class="td">
								<im:dt yyyyMMddHHmmss="${row.crs.eduTerm_bgnDt}"/>
								~
								<im:dt yyyyMMddHHmmss="${row.crs.eduTerm_endDt}"/>
							</div>
						</td>
						<c:if test="${condition.scProcType eq 'I' }">
						<td>
							<div class="th">신청</div>
							<div class="td">
								<a href="#" onclick="REQ.detail({'crsId' : '${row.crs.crsId}'});return false;"   class="c_btn bk sm">
								교육신청
								</a>
							</div>
						</td>
						</c:if>
					</tr>
					</c:forEach>
					<c:if test="${empty pageInfo.list}">
					<tr>
						<c:if test="${condition.scProcType eq 'I' }">
						<td colspan="6"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
						</c:if>
						<c:if test="${condition.scProcType ne 'I' }">
						<td colspan="5"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
						</c:if>
					</tr>			
					</c:if>
				</tbody>
			</table>
		</div>
		
			
<%--페이징 처리 --%>
<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imUserPagination.jsp">
	<c:param name="pageInfo" value="pageInfo"/>
	<c:param name="jsFunction" value="REQ.page"/>
</c:import>