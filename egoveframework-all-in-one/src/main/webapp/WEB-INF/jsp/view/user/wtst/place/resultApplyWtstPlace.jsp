<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>



<c:set var="retureUrlVal">
/cmmn/wtstPlace/${crsGrd}/selectDetail.do?_paramMenuNo=${nowMenuNo}&wtstPlaceId=${param['wtstPlaceId']}
</c:set>

<c:set var="_msg" value=""/>
<c:if test="${empty detailMmbrHstry}">
<c:set var="_msg">이력관리에 등록된 정보가 없습니다. 이력관리 정보를 등록을 하시겠습니까?</c:set>
</c:if>	

<c:set var="ATCH_FILE_ID" value="convEvddocfile"/>
<c:set var="PHT_FILE_ID" value="phtfile"/>
<c:set var="ATCH_EXEMP_FILE_ID" value="convEXEMPdocfile"/>

<script type="text/javascript" >



var REQ = {
	req : {
		list : null,
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/cmmn/wtstPlace/"/>${crsGrd}/selectList.do";	
		
	},
	list :  function(){
		this.req.list.go();
	}
	
}


$(document).ready(function(){
	REQ.init();
});


</script>

<%@ include file="incWtstPlace.jsp" %>
<table class="tbl_row al">
	<colgroup>
		<col style="width:15%;">
		<col>
		<col style="width:15%;">
		<col>
	</colgroup>
		<tr>
			<th scope="row">필기시험명(등급)</th>
			<td>
			환경교육사 <im:cd type="print" codeId="IM0001" selectedCode="${detail.wtst.crsGrdCdv}"/> 필기시험
			</td>
			<th scope="row">연도 / 차수 </th>
			<td><c:out value="${detail.wtst.eduYear}"/>년/<c:out value="${detail.wtst.eduRnd}"/>차
			</td>
		</tr>
		<tr>
			<th scope="row">시험일자(응시시간) </th>
			<td><im:dt yyyyMMddHHmmss="${detail.wtst.tstYmd}"/> (${detail.wtst.tstBgngHrs}~${detail.wtst.tstEndHrs})</td>
			<th scope="row">응시료</th>
			<td><im:numberFormat value="${detail.wtst.tstfee}"/> 원</td>
		</tr>
		<tr>
			<th scope="row">권역/지역</th>
			<td><im:cd type="print" codeId="IM0006" selectedCode="${detail.agncy.areaCdvRgn}"  />/<im:cd type="print" codeId="IM0007" selectedCode="${detail.agncy.areaCdv}"  /></td>
			<th scope="row">시험장소</th>
			<td><c:out value="${detail.agncy.agncyNm}"/></td>
		</tr>
		
		<tr>
			<th scope="row">원서접수 상태</th>
			<td colspan="3">
			<im:cd type="print" codeId="IM0021" selectedCode="${aplyStatusCode}"/>
			</td>
		</tr>
</table>	

 
 <div class="app_guide top">
			<div class="head">
				<p class="title">원서접수가 완료되었습니다. </p>
				<p class="sub">응시 확정은 기관에서 응시자격 확인 후 알려드립니다.</p>
			</div>
			<div class="desc">
				<p>수험대상자로 확정된 사항은  마이페이지 > 응시현황에서  확인하실 수 있습니다</p>
				<ul class="info">
					<li>
						<span class="tit">선정기간 : </span>
						<span>
							<span class="value"><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].bgnDt}" pattern="yyyy년 MM월 dd일"/></span> ~ <span class="value"><im:dt yyyyMMddHHmmss="${cmmmDtMap['slctnTerm'].endDt}" pattern="yyyy년 MM월 dd일"/></span>
						</span>
					</li>
					<c:if test="${detail.wtst.tstfee > 0}">
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
							<span class="value"><c:out value="${detail.wtstPlace.cntpnt}"/></span>
						</span>
					</li>
				</ul>
				
			</div>
		</div>
		<div class="b_box">
		
			<a href="${imSiteUserHome}"  class="c_btn l_gray mid">홈</a>
			<a href="<c:url value="/user/uss/mypage/wtstAplcnt/selectList.do"/>" class="c_btn bk mid">응시현황</a>
			<a href="#" onclick="REQ.list();"  class="c_btn l_gray mid">목록</a>
		</div>