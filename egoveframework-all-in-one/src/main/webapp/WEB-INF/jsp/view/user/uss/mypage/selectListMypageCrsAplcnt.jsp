<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	req : {
		list : null,
		search : null,
		detail : null,
		cancel : null
	},
	init : function(){
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetail";
		this.req.list.cfg.url    = "<c:url value="/user/uss/mypage/crsAplcnt/selectList.do"/>";
		
		this.req.search = imRequest();
		this.req.search.cfg.formId = "FormSearch";
		this.req.search.cfg.url    = "<c:url value="/user/uss/mypage/crsAplcnt/selectList.do"/>";
		

		this.req.detail = imRequest();
		this.req.detail.cfg.formId = "FormPageDetail";
		this.req.detail.cfg.url    = "<c:url value="/user/uss/mypage/crsAplcnt/selectDetail.do"/>";
		
		

	    this.req.cancel = imRequest("ajax");
	    this.req.cancel.cfg.formId = "FormPageDetail";
	    this.req.cancel.cfg.type   = "json";
	    this.req.cancel.cfg.url    = "<c:url value="/user/crsAplcnt/cancel.do"/>";
	    this.req.cancel.cfg.message.confirm="신청 취소 하시겠습니까?";
	    this.req.cancel.cfg.fn.complete = function(act, data) {
	        if (data != null ) {
	        	if(data.result > 0){	
	        		REQ.page(1);
	        	}else{
	        		COMMT.errorMessageCode(data.result);	
	        	}
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    
		
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
	searchAplyStts : function(aplyStts){
		var form =  document.getElementById(this.req.search.cfg.formId);
		form.elements["scAplyStts"].value= aplyStts;
		REQ.req.search.go();
	},
	cancel : function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.cancel.cfg.formId);
		REQ.req.cancel.go();
	},
	hisList : function(){
		var req = imRequest();
		req.cfg.formId = "FormMenuParam";
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["_paramMenuName"].value= '이전교육현황';
		req.cfg.url    = "<c:url value="/user/uss/mypage/selectListMmbrHstry.do"/>";
		req.go();
	},
	printComplete : function(appId){ <%--수료증--%>
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailCrsPrint.do"/>?crsAplcntId='+appId,1260,850);
	
	},
	printCompletePass : function(appId){ <%--이수증--%>
		COMMT.fn_Enc_Print('<c:url value="/user/uss/mypage/selectDetailCrsPrintPass.do"/>?crsAplcntId='+appId,1260,850);
	
	},
}


$(document).ready(function(){
	REQ.init();
});


</script>


<%@ include file="incMypageCrsAplcnt.jsp" %>

	<div class="position-relative">
		<div class="cb_bar al_c">
			<p class="p_noti">
				<span class="c_red"><i class="m_icon_out circle_notifications"></i>안내 :</span> 법 개정 전(22년 1월 6일 이전 ) 교육신청 현황은 <a href="javascript:;" onclick="REQ.hisList();" class="high" style="background-color : #098a5a; color :#fff">이전교육현황</a> 에서 확인하여 주세요
			</p>
		</div>
		<div class="cb_bar">
			<div class="ct_box">
				<ul class="radio">
					<li>수강신청 취소는 교육신청 기간에만 가능합니다.</li>
					<li>수료여부와 수료증 출력은 아래의 학습완료 탭에서 확인하실 수 있습니다.</li>
					<li>자격증 발급 신청 및 출력 및 발급 신청은 <a href="javascript:GREQ.loginChekShow('/user/issuAply/selectList.do');" class="high"> 자격증 발급·재발급</a> 메뉴에서 확인하여 주세요.</li>
				</ul>
			</div>
		</div>
		<a href="javascript:;" onclick="REQ.hisList();" class="c_btn green xs_wide">이전교육현황</a>
	</div>
		<ul class="nav_tabs">
			<li <c:if test="${condition.scAplyStts eq '01' }"> class="on"</c:if>><a href="#c_01" onclick="REQ.searchAplyStts('01')">수강신청<span class="count">(${cnt01}건)</span></a></li>
			<li <c:if test="${condition.scAplyStts eq '03' }"> class="on"</c:if>><a href="#c_02" onclick="REQ.searchAplyStts('03')">신청취소<span class="count">(${cnt03}건)</span></a></li>
			<li <c:if test="${condition.scAplyStts eq '020405' }"> class="on"</c:if>><a href="#c_02" onclick="REQ.searchAplyStts('020405')">신청결과<span class="count">(${cnt020405}건)</span></a></li>
			<li <c:if test="${condition.scAplyStts eq '02A' }"> class="on"</c:if>><a href="#c_02" onclick="REQ.searchAplyStts('02A')">학습완료<span class="count">(${cnt02A}건)</span></a></li>
			
		</ul>
		<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
		<input type="hidden" name="scAplyStts"      value="<c:out value="${condition.scAplyStts}"/>" />
		<div class="t_bar">
			<div class="count">
				총 <span class="num">${condition.totalRecordCount}</span>건
			</div>
		</div>
		</im:form>
		<div class="tbl_rps_data">
			<table class="tbl_data">
				<caption>교육신청 목록</caption>
				<colgroup>
					
					<c:if test="${condition.scAplyStts ne '02A' }">
					<col style="width:10%;">
					<col >
					<col style="width:9%;">
					<col style="width:9%;">
					<col style="width:9%;">
					<col style="width:16%;">
					<c:if test="${condition.scAplyStts eq '020405' }">
					<col style="width:9%;">
					</c:if>
					<col style="width:16%;">
					</c:if>
					<c:if test="${condition.scAplyStts eq '02A' }">
					<col style="width:10%;">
					<col >
					<col style="width:16%;">
					<col style="width:9%;">
					<col style="width:9%;">
					<col style="width:9%;">
					<col style="width:9%;">
					<col style="width:9%;">					
					<col style="width:10%;">
					</c:if>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">과정명</th>
						<th scope="col">양성기관</th>
						
						<c:if test="${condition.scAplyStts ne '02A' }">
							<th scope="col">신청일자</th>
							<th scope="col">상태</th>
							<th scope="col">교육수수료</th>
							<th scope="col">납부기간</th>
							<c:if test="${condition.scAplyStts eq '020405' }">
							<th scope="col">입금확인여부</th>
							</c:if>
						</c:if>
						
						<th scope="col">교육기간</th>
						<c:if test="${condition.scAplyStts eq '02A' }">
						<th scope="col">상태</th>
						<th scope="col">자격상태</th>
						<th scope="col">수료일자</th>
						<th scope="col">자격취득일자</th>
						<th scope="col">유효기간</th>
						<th scope="col">관리</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
					<tr>
						<td class="ac">
							<div class="th">과정명</div>
							<div class="td">							
							<c:if test="${condition.scAplyStts eq '03' }">
							<im:cd type="print" codeId="IM0001" selectedCode="${row.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${row.crsMstr.crsDvsnCdv}"/>
							</c:if>
							<c:if test="${condition.scAplyStts ne '03' }">							
							<a href="#" onclick="REQ.detail({'crsAplcntId' : '${row.crsAplcnt.crsAplcntId}'});return false;"  class="tb_link">
							<im:cd type="print" codeId="IM0001" selectedCode="${row.crsMstr.crsGrdCdv}"/>&nbsp;<im:cd type="print" codeId="IM0002" selectedCode="${row.crsMstr.crsDvsnCdv}"/>
							</a>
							</c:if>
							</div>
						</td>
						<td class="al">
							<div class="th">양성기관</div>
							<div class="td">${row.agncy.agncyNm}</div>
						</td>
						<c:if test="${condition.scAplyStts ne '02A' }">
						<td>
							<div class="th">신청일자</div>
							<div class="td">
							<im:dt yyyyMMddHHmmss="${row.crsAplcnt.frstRegDt}" pattern="yyyy.MM.dd"/>
							</div>
						</td>
						<td>
							<div class="th">상태</div>
							<div class="td">
							<c:set var="APLY">
							<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="APLY" />
							</c:set>
							<im:cd type="print" codeId="IM0012" selectedCode="${APLY}"/>
							</div>
						</td>
						<td class="ar">
							<div class="th">교육수수료</div>
							<div class="td">
							<im:numberFormat value="${row.crs.ttnfee}"/>원
							</div>
						</td>
						<td>
							<div class="th">입금기간</div>
							<div class="td">
							<c:set var="payTerm_bgnDt">
							<im:cd  codeId="${row.crsDts}" type="print" selectedCode="payTerm_bgnDt" />
							</c:set>
							<c:set var="payTerm_endDt">
							<im:cd  codeId="${row.crsDts}" type="print" selectedCode="payTerm_endDt" />
							</c:set>
							<im:dt yyyyMMddHHmmss="${payTerm_bgnDt}" pattern="yyyy.MM.dd"/>
							~
							<im:dt yyyyMMddHHmmss="${payTerm_endDt}" pattern="yyyy.MM.dd"/>
							</div>
						</td>
						<c:if test="${condition.scAplyStts eq '020405' }">
						<td>
							<div class="th">입금확인여부</div>
							<div class="td">
							<c:set var="DPST">
								<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="DPST" />
							</c:set>
							<im:cd type="print" codeId="IMYENO" selectedCode="${DPST}"/>
							</div>
						</td>
						</c:if>
						</c:if>
						<td>
							<div class="th">교육기간</div>
							<div class="td">
							<c:set var="eduTerm_bgnDt">
							<im:cd  codeId="${row.crsDts}" type="print" selectedCode="eduTerm_bgnDt" />
							</c:set>
							<c:set var="eduTerm_endDt">
							<im:cd  codeId="${row.crsDts}" type="print" selectedCode="eduTerm_endDt" />
							</c:set>
							<im:dt yyyyMMddHHmmss="${eduTerm_bgnDt}" pattern="yyyy.MM.dd"/>
							~
							<im:dt yyyyMMddHHmmss="${eduTerm_endDt}" pattern="yyyy.MM.dd"/>
							</div>
						</td>
						<c:if test="${condition.scAplyStts eq '02A' }">
						<td>
							<div class="th">상태</div>
							<div class="td">
								<c:set var="FNSH">
									<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="FNSH" />
								</c:set>
								<im:cd type="print" codeId="IM0013" selectedCode="${FNSH}"/>
							</div>
						</td>
						<td>
							<div class="th">자격상태</div>
							<div class="td">							
							<c:set var="QLFC">
								<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="QLFC" />
							</c:set>
							<c:if test="${row.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }">
							<im:cd type="print" codeId="IM0014" selectedCode="${QLFC}"/>
							</c:if>
							</div>
						</td>
						<td>
							<div class="th">수료일자</div>
							<div class="td">
								<c:if test="${FNSH eq '02'}">
									<im:dt yyyyMMddHHmmss="${row.crsAplcnt.fnshYmd}"/>
								</c:if>
							</div>
						</td>
						<td>
							<div class="th">자격취득일자</div>
							<div class="td">
								<c:if test="${row.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }">
								<c:if test="${QLFC eq '02'}">									
									<im:dt yyyyMMddHHmmss="${row.crsAplcnt.qlfcAcqsYmd}"/>
								</c:if>
								</c:if>
							</div>
						</td>
						<td>
							<div class="th">유효기간</div>
							<div class="td">
								<c:if test="${row.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }">
								<c:if test="${QLFC eq '02'}">
									<im:dt yyyyMMddHHmmss="${row.crsAplcnt.qlfcVldYmd}"/>
								</c:if>
								</c:if>
							</div>
						</td>
						<td>
							<div class="th">관리</div>
							<div class="td">
								<c:if test="${FNSH eq '02' }">
								<c:if test="${row.crsMstr.crsDvsnCdv eq 'CRS_DVSN_003' }">
								<a href="javascript";" onclick="REQ.printCompletePass('${row.crsAplcnt.crsAplcntId}');return false;"  class="c_btn bk sm">이수증 출력</a>
								</c:if>
								<c:if test="${row.crsMstr.crsDvsnCdv ne 'CRS_DVSN_003' }">
								<a href="javascript";" onclick="REQ.printComplete('${row.crsAplcnt.crsAplcntId}');return false;"  class="c_btn bk sm">수료증 출력</a>
								</c:if>
								</c:if>
							</div>
						</td>
						</c:if>
					</tr>
					</c:forEach>
					<c:if test="${empty pageInfo.list}">
					<tr>
						<c:choose>
							<c:when test="${condition.scAplyStts eq '02A' }">
							<td colspan="9"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
							</c:when>
							<c:when test="${condition.scAplyStts eq '020405'}">
							<td colspan="8"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
							</c:when>
							<c:otherwise>
							<td colspan="7"><div class="no_info"><spring:message code="im.common.msg.nodata" /></div></td>
							</c:otherwise>
						</c:choose>
						
					</tr>
					</c:if>
					
				</tbody>
			</table>
		</div>
		<%--페이징 처리 --%>
		
<%--페이징 처리 --%>
<c:import charEncoding="utf-8"  url="/WEB-INF/jsp/inc/imUserPagination.jsp">
	<c:param name="pageInfo" value="pageInfo"/>
	<c:param name="jsFunction" value="REQ.page"/>
</c:import>



		<div class="note_box top">
			<ul class="item_li">
				<li>교육과정과 교육신청에 대한 문의 및 이의 신청은  <a href="javascript:GREQ.loginChekShow('/user/objcAply/selectList.do');" class="tb_link"> 문의하기</a>에서 가능합니다.</li>
			</ul>
		</div>