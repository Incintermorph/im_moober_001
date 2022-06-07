<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/cmmn/crs/"/>${crsGrd}/selectList.do";	
		
	},
	list :  function(){
		this.req.list.go();
	}
	
}


$(document).ready(function(){
	REQ.init();
});


</script>

<%@ include file="incCrs.jsp" %>

<table class="tbl_row al">
			<colgroup>
				<col style="width:15%;">
				<col>
				<col style="width:15%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">과정명</th>
					<td colspan="3"><im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/></td>
				</tr>
				<tr>
					<th scope="row">양성기관</th>
					<td colspan="3"><c:out value="${detail.agncy.agncyNm}"/></td>
				</tr>
				<tr>
					<th scope="row">교육신청기간</th>
					<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['eduAplTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['eduAplTerm'].endDt}"/></td>
					<th scope="row">교육기간</th>
					<td><im:dt yyyyMMddHHmmss="${cmmmDtMap['eduTerm'].bgnDt}"/>~<im:dt yyyyMMddHHmmss="${cmmmDtMap['eduTerm'].endDt}"/></td>
				</tr>
				<tr>
					<th scope="row">교육 수수료</th>
					<td><im:numberFormat value="${detail.crs.ttnfee}"/> 원</td>
					<th scope="row">모집인원</th>
					<td><im:numberFormat value="${detail.crs.prsnl}"/> 명</td>
				</tr>
			</tbody>
		</table>
		<div class="app_guide top">
			<div class="head">
				<p class="title">교육이 신청 되었습니다. </p>
				<p class="sub">교육대상자는 기관에서 선발하여 알려드립니다.</p>
			</div>
			<div class="desc">
				<p>교육대상자로 선발된 사항은 마이페이지 > 교육신청현황에서 확인하실 수 있습니다</p>
				<ul class="info">
					<li>
						<span class="tit">선정기간 : </span>
						<span>
							<span class="value"><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}" pattern="yyyy년 MM월 dd일"/></span> ~ <span class="value"><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}" pattern="yyyy년 MM월 dd일"/></span>
						</span>
					</li>
					<c:if test="${detail.crs.ttnfee != 0}">
					<li>
						<span class="tit">납부기간 : </span>
						<span>
							<span class="value"><im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].bgnDt}"  pattern="yyyy년 MM월 dd일"/></span> ~ <span class="value"><im:dt yyyyMMddHHmmss="${cmmmDtMap['payTerm'].endDt}"  pattern="yyyy년 MM월 dd일"/></span>
						</span>
					</li>
					</c:if>
					<li>
						<span class="tit">문의처 : </span>
						<span>
							<span class="value"><c:out value="${detail.crs.cntpnt}"/></span>
						</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="b_box">
		
			<a href="${imSiteUserHome}"  class="c_btn l_gray mid">홈</a>
			<a href="<c:url value="/user/uss/mypage/crsAplcnt/selectList.do"/>" class="c_btn bk mid">교육신청현황</a>
			<a href="#" onclick="REQ.list();"  class="c_btn l_gray mid">목록</a>
		</div>
