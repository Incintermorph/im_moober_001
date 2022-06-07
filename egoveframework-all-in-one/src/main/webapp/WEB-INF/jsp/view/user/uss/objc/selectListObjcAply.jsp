<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		detail : null,
		regist : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/objcAply/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/user/objcAply/selectList.do"/>";
		

		this.req.regist = imRequest();
		this.req.regist.cfg.formId = "FormPageDetail";
		this.req.regist.cfg.url    = "<c:url value="/user/objcAply/regist.do"/>";
		
		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/user/objcAply/selectDetail.do"/>";
			
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
		this.req.search.go();
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
	registChoice : function(){
		$("#dvsnCdvDiv").show();
	},
	registChoiceCancel : function(){
		$("#dvsnCdvDiv").hide();
	},
	regist :  function(){
		var req = imRequest("script", {formId : "FormStts"}); 
		req.cfg.fn.exec  = function() {
			var form =  document.getElementById("FormStts");
			var formreg =  document.getElementById("FormPageDetail");
			formreg.elements["dvsnCdv"].value = form.elements["dvsnCdv"].value;
			
			REQ.req.regist.go();
	    };

	    req.validator.set({
	        title : "구분",
	        name : "dvsnCdv",
	        data : ["!null"]
	    });
	    req.go();

	},
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incObjcAply.jsp" %>



<div class="cb_bar">
			<div class="ct_box">
<!-- 				<p class="p_noti"><span class="c_red"><i class="m_icon_out circle_notifications"></i>[안내]</span></p> -->
				<ul class="radio">
					<li>교육과정, 필기시험, 자격증, 보수교육 유예신청에 대한 문의가 있는 경우 신청 바랍니다.</li>
					<li>답변에는 약 7일 소요 예정입니다.</li>
				</ul>
			</div>
			<a href="javascript:;" onclick="REQ.registChoice()" class="c_btn green xs_wide">문의하기</a>
		</div>
		<div class="t_bar">
			<div class="count">
				총 <span class="num">${pageInfo.totalRecordCount}</span>건
			</div>
		</div>
<div class="tbl_rps_data">
	<table class="tbl_data">
	<colgroup>
		<col style="width:150px;">
		<col style="width:100px;">
		<col style="width:100px;">
			<col/>
		<col style="width:150px;">
		<col style="width:100px;">
		<col style="width:120px;">
		<col style="width:100px;">
	</colgroup>
	<thead>
		<tr>	<th scope="col">구분</th>
				<th scope="col">년도</th>
				<th scope="col">등급</th>
				<th scope="col">제목</th>
				<th scope="col">문의구분</th>
				<th scope="col">상태</th>
				<th scope="col">신청일자</th>
				<th scope="col"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td>
				<div class="th">구분</div>
				<div class="td">
					<im:cd type="print" codeId="${dvsnCdvArr}" selectedCode="${row.objcAply.dvsnCdv}"/>
				</div>
			</td>
			<td>
				<div class="th">년도</div>
				<div class="td">
					${row.objcAplyDtl.eduYear}
				</div>
			</td>
			<td>
				<div class="th">등급</div>
				<div class="td">
					<im:cd type="print" codeId="IM0001" selectedCode="${row.objcAplyDtl.crsGrdCdv}"/>
				</div>
			</td>
			<td class="al">
				<div class="th">제목</div>
				<div class="td">
					<a href="#" onclick="REQ.detail({'objcAplyId' : '${row.objcAply.objcAplyId}'});return false;">
						${row.objcAply.aplyNm}
					</a>
				</div>
			</td>
			<td>
				<div class="th">문의구분</div>
				<div class="td">
					<im:cd type="print" codeId="${objcDvsnCdvArr}" selectedCode="${row.objcAply.objcDvsnCdv}"/>
				</div>
			</td>
			<td>
				<div class="th">상태</div>
				<div class="td">
					<im:cd type="print" codeId="${sttsCdvArr}" selectedCode="${row.objcAply.sttsCdv}"/>
				</div>
			</td>
			<td>
				<div class="th">신청일자</div>
				<div class="td">
					<im:dt yyyyMMddHHmmss="${row.objcAply.frstRegDt}"/>
				</div>
			</td>
			<td>
				<div class="th">신청일자</div>
				<div class="td">
					<a href="#" onclick="REQ.detail({'objcAplyId' : '${row.objcAply.objcAplyId}'});return false;"   class="c_btn bk sm">
							상세보기
					</a>
				</div>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="8"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
		</tr>			
		</c:if>
	</tbody>
</table>
</div>	

<div class="ct_dialog" id="dvsnCdvDiv" style="display:none;">
	<div class="tb">
		<div class="inner">
			<div class="outer" style="max-width:690px;"><!-- max-width 값 필수 -->
				<div class="top">
					<div class="bowl">
						<p class="title">문의하기 구분 선택</p>
						<a href="#" class="p_close"><span class="sr_only">닫기</span></a>
					</div>
				</div>
				<div class="ct">
					<!-- 콘텐츠 -->
					<ul class="item_li bot">
						<li>구분에 따라 이의 신청해야 하는 양식이 달라집니다.</li>
						<li>기본과정, 실무과정, 보수교육의 경우에는 교육과정을 선택하여 주시고 필기시험(원서접수)의 경우에는 필기시험을 선택하여 주세요.</li>
					</ul>
					<form name="FormStts" id="FormStts" method="post" onsubmit="return false;">
					<table class="tbl_row al">
						<colgroup>
							<col style="width:25%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">구분</th>
								<td>
									<im:cd type="radio" codeId="${dvsnCdvArr}" name="dvsnCdv"/>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
					<div class="b_box">
						<a href="javascript:;" onclick="REQ.regist()" class="c_btn bk">문의하기</a>
						<a href="javascript:;" onclick="REQ.registChoiceCancel()" class="c_btn wt">취소</a>
					</div>
					<!-- //콘텐츠 -->
				</div>
			</div>
		</div>
	</div>
</div>		
<%--페이징 처리 --%>
<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imUserPagination.jsp">
	<c:param name="pageInfo" value="pageInfo"/>
	<c:param name="jsFunction" value="REQ.page"/>
</c:import>







	
<c:set var="retureUrlVal">
/user/objcAply/selectList.do?_paramMenuNo=${nowMenuNo}
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
						<li>문의하기는 문의 사항, 이의 신청 및 답변 확인을 위한 메뉴입니다.</li>
						<li>추가 회신 시 정보 확인을 위해 이력관리에서 본인인증 및 기본정보 입력이 필요합니다.</li>
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