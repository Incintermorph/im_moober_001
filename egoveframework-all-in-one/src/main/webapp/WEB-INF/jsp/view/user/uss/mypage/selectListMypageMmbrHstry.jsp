<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	init : function(){
		
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>

<div class="cb_bar right">
			<a href="<c:url value='/user/uss/mypage/crsAplcnt/selectList.do'/>" class="c_btn green xs_wide">교육신청현황 되돌아가기</a>
		</div>
		<div class="t_bar">
			<div class="count">
				총 <span class="num">${selectEduHisCnt}</span>건
			</div>
		</div>
		<div class="tbl_rps_data">
			<table class="tbl_data">
				<caption>교육신청 목록</caption>
				<colgroup>
					<col style="width:11%;">
					<col>
					<col style="width:20%;">
					<col style="width:9%;">
					<col style="width:9%;">
					<col style="width:15%;">
					<col style="width:9%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">접수번호</th>
						<th scope="col">과정명</th>
						<th scope="col">양성기관</th>
						<th scope="col">접수일</th>
						<th scope="col">상태</th>
						<th scope="col">교육기간</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${selectEduHis}" var="row">
					<tr>
						<td>
							<div class="th">접수번호</div>
							<div class="td">${row.eduAplyHstry.rcptCode}</div>
						</td>
						<td class="al">
							<div class="th">과정명</div>
							<div class="td">${row.eduAplyHstry.crsNm}</div>
						</td>
						<td class="al">
							<div class="th">양성기관</div>
							<div class="td">${row.eduAplyHstry.agncyNm}</div>
						</td>
						<td>
							<div class="th">접수일</div>
							<div class="td">${row.eduAplyHstry.rcptYmd}</div>
						</td>
						<td>
							<div class="th">상태</div>
							<div class="td">${row.eduAplyHstry.stts}</div>
						</td>
						<td>
							<div class="th">교육기간</div>
							<div class="td">
								${row.eduAplyHstry.eduYmdInfo}
							</div>
						</td>
						<td>
							<div class="th"></div>
							<div class="td">
								<c:if test="${row.eduAplyHstry.sttsCode eq '4002' }">
								<a href="#" onclick="COMMT.ready();" class="c_btn bk sm">수료증출력</a>
								</c:if>
								<c:if test="${row.eduAplyHstry.sttsCode eq '4003' }">
								<a href="#" onclick="COMMT.ready();" class="c_btn bk sm">자격증출력</a>
								</c:if>
							</div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty  selectEduHis}">
					<tr>
						<td colspan="7"><div class="no_info">이전교육 정보가 없습니다.</div></td>
					</tr>	
					</c:if>
				</tbody>
			</table>
		</div>
		
		
<c:set var="retureUrlVal">
/user/uss/mypage/selectListMmbrHstry.do?_paramMenuNo=${nowMenuNo}
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
						<li>이전교육현황은 법 개정(22년 1월 6일) 이전에 환경교육 포털에서 교육신청하신 내역을 조회하는 메뉴입니다.</li>
						<li>이력관리에서 본인인증 및 기본정보를 입력하시면 이전교육현황을 조회 하실 수 있습니다.</li>
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