<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<c:set var="retureUrlVal">
/cmmn/wtstPlace/${crsGrd}/selectList.do?_paramMenuNo=${nowMenuNo}
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
		this.req.list.cfg.url    = "<c:url value="/cmmn/wtstPlace/"/>${crsGrd}/selectList.do";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/cmmn/wtstPlace/"/>${crsGrd}/selectList.do";
		

		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/cmmn/wtstPlace/"/>${crsGrd}/selectDetail.do";
		
		
		
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		REQ.req.list.go();
	},
	detail :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.detail.cfg.formId);
		//COMMT.ready();
		REQ.req.detail.go();
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

<div class="ct_box bg bot">
	<span class="p_noti red"><i class="m_icon_out circle_notifications"></i>중요 </span>: <b>환경교육사 사이트 신규 운영에 따라</b>  <a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="high">이력관리</a> <b>재등록이 필요합니다.</b>
	<ul class="radio">
		<li><b>환경교육사 기본 과정 수료자</b>는 추가 증빙 없이 원서 접수가 가능합니다.</li>
		<li><b>유사과목 면제자</b>는 <a href="javascript:GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');"   class="high">이력관리</a>에서 유사면제과목을  등록하여야 자격심사 절차를 거쳐  필기평가에 응시 할 수 있습니다.</li>
		<li><b>단짝 수료자</b>는 이력관리에서 단짝 수료증을 등록하여야 자격심사 절차를 거쳐  필기평가에 응시 할 수 있습니다.</li>
		<li>원서접수는 정원과 대기자를 포함하여 마감되며 대기자의 경우에는 결원이 없는 경우 응시하실 수 없을 수도 있습니다.</li>
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


<%@ include file="incWtstPlace.jsp" %>

<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
	<%--
	<div class="form_box">
		<div class="inner">
			<div class="comb">
				<input type="hidden" name="scKey" value="agncyNm"/>
				<input type="text" class="key" name="scWord" value="${condition.scWord}" placeholder="양성기관명을 입력하세요" onKeyup="COMMT.enterCallFunc(event, REQ.search);" title="검색어">
				<a href="#"  onclick="REQ.search()" class="c_btn bk">검색</a>
			</div>
		</div>
	</div>
	 --%>
	<div class="t_bar">
		<div class="count">
			<c:if test="${!empty pageInfo.list}">
				총 <span class="num">${pageInfo.condition.totalRecordCount}</span>건
			</c:if>
		</div>
	</div>
</im:form>
<div class="tbl_rps_data">
	<table class="tbl_data">
		<caption>교육신청 목록</caption>
		<colgroup>
			<col style="width:80px;"/>
			<col style="width:80px;"/>
			<col style="width:210px;"/>			
			<col style="width:110px;"/>
			<col style="width:130px;"/>
			<col style="width:100px;"/>
			<col style="width:120px;"/>			
			<col/>
			<col style="width:100px;"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">연도</th>
				<th scope="col">차수</th>
				<th scope="col">접수기간</th>
				<th scope="col">시험일자</th>
				<th scope="col">신청자/정원</th>
				<th scope="col">응시료</th>
				<th scope="col">지역</th>
				<th scope="col">시험장소</th>				
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
				<tr>			
					<td class="al">
						<div class="th">연도</div>
						<div class="td">
							<c:out value="${row.wtst.eduYear}"/>
						</div>
					</td>
					<td>
						<div class="th">차수</div>
						<div class="td">
							<c:out value="${row.wtst.eduRnd}"/>
						</div>
					</td>
					<td>
						<div class="th">접수기간</div>
						<div class="td">
							<im:dt yyyyMMddHHmmss="${row.wtst.rcptTerm_bgnDt}"/>~<im:dt yyyyMMddHHmmss="${row.wtst.rcptTerm_endDt}"/>
						</div>
					</td>	
					<td>
						<div class="th">시험일자</div>
						<div class="td">
							<im:dt yyyyMMddHHmmss="${row.wtst.tstYmd}"/>
						</div>
					</td>	
					<td>
						<div class="th">신청자/정원</div>
						<div class="td">
							<c:out value="${row.applyCnt}"/>/<c:out value="${row.wtstPlace.prsnl}"/>
						</div>
					</td>
					<td class="ar">
						<div class="th">응시료</div>
						<div class="td">
							<im:numberFormat value="${row.wtst.tstfee}"/> 원
						</div>
					</td>
					<td>
						<div class="th">지역</div>
						<div class="td">
							<im:cd type="print" codeId="IM0007" selectedCode="${row.agncy.areaCdv}"  />
						</div>
					</td>
					
					<td class="al">
						<div class="th">시험장소</div>
						<div class="td">
							<c:out value="${row.agncy.agncyNm}"/>
						</div>
					</td>
					<td>
						<div class="th">지역</div>
						<div class="td">
							<a href="#" onclick="REQ.detail({'wtstPlaceId' : '${row.wtstPlace.wtstPlaceId}'});return false;"   class="c_btn bk sm">원서접수</a>
						</div>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty pageInfo.list}">
			<tr>
				<td colspan="9"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
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



