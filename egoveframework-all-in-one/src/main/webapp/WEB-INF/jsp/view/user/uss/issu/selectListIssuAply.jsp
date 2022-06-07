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
		this.req.list.cfg.url    = "<c:url value="/user/issuAply/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/user/issuAply/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/user/issuAply/regist.do"/>";
		
		this.req.modify = imRequest();
		this.req.modify.cfg.formId = "FormPageDetail";
		this.req.modify.cfg.url    = "<c:url value="/user/issuAply/modify.do"/>";
		
		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/user/issuAply/selectDetail.do"/>";
		
		
		
	
	},
	page :  function(pageNo){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["currentPageNo"].value= pageNo;
		this.req.list.go();
	},
	regist :  function(){
		var req = imRequest("script", {formId : "FormStts"}); 
		req.cfg.fn.exec  = function() {
			var form =  document.getElementById("FormStts");
			var formreg =  document.getElementById("FormPageDetail");
			formreg.elements["issuDvsnCdv"].value = form.elements["issuDvsnCdv"].value;
			formreg.elements["qlfcRsltCode"].value = form.elements["qlfcRsltCode"].value;
			formreg.elements["crsGrdCdv"].value = form.elements["crsGrdCdv"].value;
			
			REQ.req.regist.go();
	    };

	    req.validator.set({
	        title : "발급 구분",
	        name : "issuDvsnCdv",
	        data : ["!null"]
	    });
	    req.go();
		
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

	registChoice : function(qlfcRsltCode,crsGrdCdv){

		var form =  document.getElementById('FormPageDetail');
		form.elements["qlfcRsltCode"].value= qlfcRsltCode;
		form.elements["crsGrdCdv"].value= crsGrdCdv;
		REQ.req.regist.go();
	},
	
	registChoiceCancel : function(){
		$("#dvsnCdvDiv").hide();
	},
	printLicense : function(appId){ <%--자격증--%>
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailCrsPrintLicense.do"/>?issuAplyId='+appId,1260,850);

	},
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incIssuAply.jsp" %>

		<div class="cb_bar">
			<div class="ct_box">
<!-- 				<p class="p_noti"><span class="c_red"><i class="m_icon_out circle_notifications"></i>[안내]</span></p> -->
				<ul class="radio">
					<li>법 개정(22년 1월 6일) 이전 자격증도 발급 신청도 가능합니다.</li>
					<li>자격이 정지 혹은 취소된 경우에는 자격증 발급 및 출력이 제한됩니다.</li>
				</ul>
			</div>
		</div>
	
		<div class="t_bar">
			<div class="count">
				총 <span class="num">${listMmbrQlfcCnt}</span>건
			</div>
		</div>
		<div class="tbl_rps_data">
			<table class="tbl_data">
				<caption>자격증 발급·재발급 신청 목록</caption>
				<colgroup>
					<col style="width:10%;">
					<col style="width:15%;">
					<col style="width:12%;">
					<col style="width:15%;">
					<col style="width:12%;">
					<col style="width:12%;">
					<col/>					
					<col/>					
				</colgroup>
				<thead>
					<tr>
						<th scope="col">취득일시</th>
						<th scope="col">자격증 번호</th>
						<th scope="col">자격 취득일</th>
						<th scope="col">보수교육 유효기간</th>
						<th scope="col">발급상태</th>
						<th scope="col">신청일자</th>
						<th scope="col">확인일자</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listMmbrQlfc}" var="row" varStatus="i">
					<tr>
						<td>
							<div class="th">취득시기</div>
							<div class="td"><im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${row.dvsnCdv}"/></div>
						</td>
						<td >
							<div class="th">자격증 번호</div>
							<div class="td">
							<c:if test="${empty applyMap[row.qlfcRsltCode]}">
							${row.qlfcRsltCode}
							</c:if>
							<c:if test="${!empty applyMap[row.qlfcRsltCode]}">
							<a href="#" onclick="REQ.detail({'issuAplyId' : '${applyMap[row.qlfcRsltCode].issuAplyId}'});return false;" class="tb_link">
							${row.qlfcRsltCode}
							</a>
							</c:if>
							</div>
						</td>
						<td>
							<div class="th">자격 취득일</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.lcncAcqsYmd}" pattern="yyyy.MM.dd"/></div>
						</td>
						<td>
							<div class="th">보수교육<br>유효기간</div>
							<div class="td"><im:dt yyyyMMddHHmmss="${row.lcncEndYmd}" pattern="yyyy.MM.dd"/></div>
						</td>
						<td>
							<div class="th">발급 상태</div>
							<div class="td">
							<c:if test="${!empty applyMap[row.qlfcRsltCode]}">
							<im:cd type="print" codeId="IM0023" selectedCode="${applyMap[row.qlfcRsltCode].sttsCdv}" />
							</c:if>							
							</div>
						</td>
						<td>
							<div class="th">신청일자</div>
							<div class="td">
							<c:if test="${!empty applyMap[row.qlfcRsltCode]}">
							<im:dt yyyyMMddHHmmss="${applyMap[row.qlfcRsltCode].aplyYmd}"/>
							</c:if>
							</div>
						</td>
						<td>
							<div class="th">확인 일자</div>
							<div class="td">							
							<c:if test="${!empty applyMap[row.qlfcRsltCode]}">
							<im:dt yyyyMMddHHmmss="${applyMap[row.qlfcRsltCode].idntyYmd}"/>
							</c:if>
							</div>
						</td>
						<td>
							<div class="th">관리</div>
							<div class="td">
								<c:if test="${!empty applyMap[row.qlfcRsltCode]}">
									<c:choose>
										<c:when test="${applyMap[row.qlfcRsltCode].sttsCdv eq '02'}">
											<a href="#" onclick="REQ.printLicense('${applyMap[row.qlfcRsltCode].issuAplyId}');" class="c_btn bk sm">자격증 출력</a>
										</c:when>
										<c:when test="${applyMap[row.qlfcRsltCode].sttsCdv eq '99' || applyMap[row.qlfcRsltCode].sttsCdv eq '05'}">
											<a href="#" onclick="REQ.modify({'issuAplyId' : '${applyMap[row.qlfcRsltCode].issuAplyId}'});return false;" class="c_btn bk sm">신청 수정</a>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="REQ.detail({'issuAplyId' : '${applyMap[row.qlfcRsltCode].issuAplyId}'});return false;" class="c_btn bk sm">신청 조회</a>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${empty applyMap[row.qlfcRsltCode]}">
								<a href="#" onclick="REQ.registChoice('${row.qlfcRsltCode}','${row.crsGrdCdv}');" class="c_btn bk sm">발급 신청</a>
								</c:if>
							</div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty listMmbrQlfc}">
					<tr>
						<td colspan="8">
							<div class="no_info">자격증 정보가 없습니다.</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		

		<div class="note_box top">
			<ul class="item_li">
				<li>자격취득 이력이 있는 경우에만 발급·재발급이 가능합니다.</li>
				<%--
				<li>조회된 내역이 없으신 licence@epa.or.kr로 문의 바랍니다.</li>
				 --%>
				<li>자격증 발급·재발급에 대한 문의 및 이의 신청은 <a href="javascript:GREQ.loginChekShow('/user/objcAply/selectList.do');" class="tb_link"> 문의하기</a>에서 가능합니다.</li>
			</ul>
		</div>
<div class="ct_dialog" id="dvsnCdvDiv" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:600px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">발급신청구분</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<ul class="item_li bot">
						<li>구분에 따라 이의 신청해야 하는 양식이 달라집니다.</li>
						<li>22년 1월 6일 이전에 취득하신 자격증이 있으신 분은 재발급으로 신청해 주세요.</li>
					</ul>
					<form name="FormStts" id="FormStts" method="post" onsubmit="return false;">
					<input type="hidden" name="qlfcRsltCode">
					<input type="hidden" name="crsGrdCdv">
					<table class="tbl_row al">
						<colgroup>
							<col style="width:25%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">발급 구분</th>
								<td>
									<im:cd type="radio" codeId="IM0022" name="issuDvsnCdv" />
								</td>
							</tr>
						</tbody>
					</table>
					</form>
					<div class="b_box">
						<a href="javascript:;" onclick="REQ.regist()" class="c_btn bk">발급 신청</a>
						<a href="javascript:;" onclick="REQ.registChoiceCancel()" class="c_btn wt">취소</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>





<c:set var="retureUrlVal">
/user/issuAply/selectList.do?_paramMenuNo=${nowMenuNo}
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
						<li>자격증 발급·재발급은 법 개정(22년 1월 6일) 이전에 환경교육 포털에서 취득하신 자격증 내역과 신규로 취득하신 자격증 내역 모두를 조회하는 메뉴입니다.</li>
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