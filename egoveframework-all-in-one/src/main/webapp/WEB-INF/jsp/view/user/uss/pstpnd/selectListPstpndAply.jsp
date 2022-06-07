<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		modify : null,
		detail : null,
		regist : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/pstpndAply/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/user/pstpndAply/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/user/pstpndAply/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/user/pstpndAply/modify.do"/>";
		
		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/user/pstpndAply/selectDetail.do"/>";
		
		
		
	
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
	detail :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.detail.cfg.formId);
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
	},
	listcrs : function(crsGrd){
		
		var req = imRequest();
		req.cfg.formId = "FormListCrs";
		req.cfg.url    = "<c:url value="/cmmn/crs/R/A/selectList.do"/>";
		var form =  document.getElementById(req.cfg.formId);
		form.elements["scCrsGrdCdv"].value= crsGrd;
		req.go();
	},
	print : function(pstpndAplyId){ 
		
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailPstpndAplyPrint.do"/>?pstpndAplyId='+pstpndAplyId,1260,850);
	},
}


$(document).ready(function(){
	REQ.init();
});


</script>


<form name="FormListCrs" id="FormListCrs" method="post" onsubmit="return false;">
<input type="hidden" name="scCrsGrdCdv"   />
</form>

<%@ include file="incPstpndAply.jsp" %>
		<div class="cb_bar dbot">
			<div class="ct_box nm">
<!-- 				<p class="p_noti"><span class="c_red"><i class="m_icon_out circle_notifications"></i>[보수교육 수강신청 안내]</span></p> -->
				<ul class="radio">
					<li>보수교육은 등급에 맞는 교육신청 (3급, 2급 1급) 메뉴에서 신청해 주시기 바랍니다.</li>
					<li>보수교육일정은 연간교육일정에서 확인하실 수 있습니다.</li>
				</ul>
			</div>
			<a href="<c:url value="/cmmn/crsPlan/selectList.do"/>" class="c_btn green xs_wide">연간일정</a>
		</div>
		<div class="tbl_state">
			<p class="title">교육신청 중인 보수교육 현황</p>
			<div class="data cols">
				<div class="group">
					<div class="top">
						<h4>3급</h4>
						<a href="javascript:;" onclick="REQ.listcrs('CRS_GRD_3')" class="more">바로가기</a>
					</div>
					<div class="state">
						<span class="num">${listCrCnt3}</span> 건
					</div>
				</div>
				<div class="group">
					<div class="top">
						<h4>2급</h4>
						<a href="javascript:;" onclick="REQ.listcrs('CRS_GRD_2')" class="more">바로가기</a>
					</div>
					<div class="state">
						<span class="num">${listCrCnt2}</span> 건
					</div>
				</div>
				<div class="group">
					<div class="top">
						<h4>1급</h4>
						<a href="javascript:;" onclick="REQ.listcrs('CRS_GRD_1')" class="more">바로가기</a>
					</div>
					<div class="state">
						<span class="num">${listCrCnt1}</span> 건
					</div>
				</div>
			</div>
		</div>
		
		<div class="tbl_rps_data">
			<table class="tbl_data">
				<caption>자격증 발급·재발급 신청 목록</caption>
				<colgroup>
					<col style="width:10%;">
					<col>
					<col style="width:12%;">
					<col style="width:12%;">
					<col style="width:12%;">
					<col style="width:12%;">
					<col style="width:12%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">취득시기</th>
						<th scope="col">자격증 번호</th>
						<th scope="col">자격취득일자</th>
						<th scope="col">유효기간 만료일</th>
						<th scope="col">보수교육 회수</th>
						<th scope="col">유예상태</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
				<c:set  var="vew_cnt" value="0" />
					<c:forEach items="${listMmbrQlfc}" var="row">
					<c:if test="${!empty row.lcncAcqsYmd && !empty row.qlfcRsltCode}">
					<c:set  var="vew_cnt" value="${vew_cnt+1}" />
					<c:set  var="applyMapKey" value="${row.crsGrdCdv}" />
					<c:set  var="applyMapVO" value="${applyMap[applyMapKey]}" />
					<tr>
						<td>
							<div class="th">취득시기</div>
							<div class="td"><im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${row.dvsnCdv}"/></div>
						</td>
						<td class="al">
							<div class="th">자격증 번호</div>
							<div class="td">
							<%-- 마지막 데이터 나 현재 업데이트 된 데이터 조회 되도록 처리  --%>
							<c:if test="${empty applyMapVO}">								
								${row.qlfcRsltCode}
							</c:if>
							<c:if test="${!empty applyMapVO}">
							<a href="#" onclick="REQ.detail({'pstpndAplyId':'${applyMapVO.pstpndAplyId}'});return false;" class="tb_link">
							${row.qlfcRsltCode}
							</a>
							</c:if>
								
							</div>
						</td>
						<td>
							<div class="th">자격취득일자</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.lcncAcqsYmd}" pattern="yyyy.MM.dd"/></div>
						</td>
						<td>
							<div class="th">유효기간<br>만료일</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.lcncEndYmd}" pattern="yyyy.MM.dd"/></div>
						</td>
						<td class="ar">
							<div class="th">보수교육 회수</div>
							<div class="td">${row.vlatCntDefault} 건</div>
						</td>
						<td>
							<div class="th">유예상태</div>
							<div class="td">
									<c:if test="${!empty applyMapVO}">
									<im:cd type="print" codeId="IM0024" selectedCode="${applyMapVO.sttsCdv}"/>
									</c:if>
							</div>
						</td>
						<td>
							<div class="th">관리</div>
							<div class="td">
							<c:if test="${row.diffDay>0 && row.diffDay<=90}">	
								<c:if test="${empty applyMapVO}">	
								<a href="#" onclick="REQ.regist({'dvsnCdv':'${row.dvsnCdv}','qlfcRsltCode' : '${row.qlfcRsltCode}','crsGrdCdv':'${row.crsGrdCdv}'});return false;" class="c_btn bk sm">유예신청</a>
								</c:if>
								<c:if test="${!empty applyMapVO}">
								
									<c:choose>
										<c:when test="${applyMapVO.sttsCdv eq '02'}">
											<a href="#" onclick="REQ.print('${applyMapVO.pstpndAplyId}');" class="c_btn bk sm">확인증 출력</a>
										</c:when>
										<c:when test="${applyMapVO.sttsCdv eq '99' || applyMapVO.sttsCdv eq '05'}">
											<a href="#" onclick="REQ.modify({'pstpndAplyId':'${applyMapVO.pstpndAplyId}'});return false;" class="c_btn bk sm">신청 수정</a>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="REQ.detail({'pstpndAplyId':'${applyMapVO.pstpndAplyId}'});return false;" class="c_btn bk sm">신청 조회</a>
										</c:otherwise>
									</c:choose>
									</c:if>
							</c:if>	
							</div>
						</td>
					</tr>
					</c:if>
					</c:forEach>
					<c:if test="${vew_cnt eq 0}">
					<tr>
						<td colspan="7"><div class="no_info">취득 정보가 없습니다.</div></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="note_box top">
			<ul class="item_li">
				<li>자격취득 이력이 있는 경우에만 유예신청이 가능합니다.</li>
				<li>유예신청은  유효기간 만료일 3개월 전부터 가능합니다.</li>
				<%--
				<li>조회된 내역이 없으신 licence@epa.or.kr로 문의 바랍니다.</li>
				 --%>
				<li>보수교육 유예신청에 대한 문의 및 이의 신청은<a href="javascript:GREQ.loginChekShow('/user/objcAply/selectList.do');" class="tb_link"> 문의하기</a>에서 가능합니다.</li>
			</ul>
		</div>
		
		
		
		
<c:set var="retureUrlVal">
/user/pstpndAply/selectList.do?_paramMenuNo=${nowMenuNo}
</c:set>
		
<%--이력 등록이 없는 경우 안내 페이지 --%>		
<c:if test="${empty IMLoginUser.aplyGrdCdv}"> 
<script>
var doCloseMmbrHstryNoticeDiv= function(){
	$("#_mmbrHstryNoticeDiv").hide();
}
</script>
<div class="ct_dialog" id="_mmbrHstryNoticeDiv" style="display:block;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:690px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">이력관리 등록 안내</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<ul class="item_li bot">
						<li>보수교육 신청·유예는 법 개정(22년 1월 6일) 이전에 환경교육 포털에서 취득하신 자격증 내역과 신규로 취득하신 자격증 내역 모두를 조회하는 메뉴입니다.</li>
						<li>이력관리에서 본인인증 및 기본정보를 입력하시면 이전자격취득현황을 조회 하실 수 있습니다.</li>
						<li>이력관리 정보를 등록 하시겠습니까?</li>
					</ul>
					<div class="b_box">
						<a href="javascript:;" onclick="GREQ.loginChekShow('/user/mmbrHstry/modify.do?retureUrlVal=<c:out value="${imfunc:encryptString(retureUrlVal)}"/>');return false;" class="c_btn bk">이력관리</a>
						<a href="javascript:;" onclick="doCloseMmbrHstryNoticeDiv()" class="c_btn wt">취소</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>	
</c:if>	