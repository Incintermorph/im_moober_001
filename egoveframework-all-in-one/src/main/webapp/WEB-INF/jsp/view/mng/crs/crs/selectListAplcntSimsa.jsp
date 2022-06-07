<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {

	req : {
			crslist : null,
			list : null,
			updateAply : null
	},
	init : function(){

		this.req.crslist = imRequest();
		this.req.crslist.cfg.formId = "FormPageDetail";
		this.req.crslist.cfg.url    = "<c:url value="/mng/crs/selectList.do"/>";
	
		this.req.list = imRequest();
		this.req.list.cfg.formId = "FormPageDetailTab";
		this.req.list.cfg.url    = "<c:url value="/mng/crs/${tabCode}/selectListAplcnt.do"/>";
		

		this.req.updateAply = imRequest("ajax",{formId: "FormList"});
		this.req.updateAply.cfg.type   = "json";
		this.req.updateAply.cfg.url    =  "<c:url value="/mng/crsAplcnt/APLY/updateAplylist.do"/>";
		this.req.updateAply.cfg.message.confirm="변경하시겠습니까?";
		this.req.updateAply.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.req.list.go();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.updateAply.validator.set({
	    	message : "변경할 수강정보를 선택하세요",
            name : "checkIndexs",
            data : ["!null"]
        });
	},
	crslist : function(){
		REQ.req.crslist.go();
	},
	updateAply : function(sttsCd){

		<c:if test="${detail.crs.prgrsSttsCdv eq '02'}">
		IMGLBObject.request=this;
		if($("#sttsCdv_").val()==''){
			COMMT.message('변경할 상태를 선택하세요.');
			return;
		}
		var chageStts = $("#sttsCdv_").val(); 
		REQ.req.updateAply.cfg.url    =  "<c:url value="/mng/crsAplcnt/"/>"+sttsCd+"/updateAplylist.do";
		var form =  document.getElementById(this.req.updateAply.cfg.formId);
		form.elements["chageStts"].value= chageStts;
		REQ.req.updateAply.go();
		</c:if>
		<c:if test="${detail.crs.prgrsSttsCdv ne '02'}">
		
		COMMT.message('랜덤 선정 완료 상태에서만 처리해야합니다.');
		
		</c:if>
	},
	search : function(){
		var serchform =  document.getElementById("FormSearch");
		var form =  document.getElementById(REQ.req.list.cfg.formId);
		form.elements["aplcnt.scKey"].value= serchform.elements["aplcnt.scKey"].value;
		form.elements["aplcnt.scWord"].value= serchform.elements["aplcnt.scWord"].value;
		form.elements["aplcnt.scSttsCdvAGNCYSRNG"].value= serchform.elements["aplcnt.scSttsCdvAGNCYSRNG"].value;
		form.elements["aplcnt.scSttsCdvOPSECTSRNG"].value= serchform.elements["aplcnt.scSttsCdvOPSECTSRNG"].value;
		form.elements["aplcnt.scExptSbjYn"].value= serchform.elements["aplcnt.scExptSbjYn"].value;
		REQ.req.list.go();
	},
	changeOrdeBy : function(orderbyKey){
		var form =  document.getElementById(this.req.list.cfg.formId);
		form.elements["aplcnt.orderbyKey"].value= orderbyKey;
		REQ.req.list.go();
	},
	reload : function(){
		REQ.req.list.go();
	},
	view : function(esntlId){
		var req = imRequest("popup");
		
		req.cfg.options.width=2048;
		req.cfg.options.height=1024;
		
		req.cfg.formId = "FormPageDetail";
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["esntlId"].value= esntlId;
		req.cfg.url    = "${pageContext.request.contextPath}/mng/mmbrHstry/modify.do";
		req.go();
	},
	modifyStts : function(crsAplcntId){
		var req = imRequest("layer");
		req.cfg.options.title="특이사항 관리";
		req.cfg.formId = "FormModifyStts";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=370;
		
		var form =  document.getElementById(req.cfg.formId);
		form.elements["crsAplcntId"].value= crsAplcntId;
		req.cfg.url    = "${pageContext.request.contextPath}/mng/crsAplcnt/stts/AGNCY_SRNG/modify.do";
		<c:if test="${tabCode eq '04'}">
		req.cfg.url    = "${pageContext.request.contextPath}/mng/crsAplcnt/stts/OPSECT_SRNG/modify.do";
		</c:if>
		req.go();
	},
	excel : function(){
		var req = imRequest();
		req.cfg.formId = "FormPageDetail";
		
		var form =  document.getElementById(req.cfg.formId);

		form.elements["crsId"].value= '${param["crsId"]}';
		form.elements["downloadName"].value= "자격심사정보_ ${detail.agncy.agncyNm}_${detail.crs.eduYear}년_<im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>_<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/>_${detail.crs.eduRnd}차";
		req.cfg.url    = "<c:url value="/mng/crs/${param['tabCode']}/selectListAplcntExcel.do"/>";
		req.go();
	}
	<c:if test="${tabCode eq '04'}">
	,
	updateCrsAply : function(){
		<c:if test="${detail.crs.prgrsSttsCdv eq '03'}">
		 var req = imRequest("ajax",{formId: "FormPageDetail"});
			req.cfg.type   = "json";			
			req.cfg.url    =  "<c:url value="/mng/crs/04/updatePrgrsSttsCdv.do"/>";
			req.cfg.message.confirm="통보 처리 하시겠습니까?";
			req.cfg.fn.complete = function(act, data) {
				REQ.req.list.go();
			}
			var form =  document.getElementById(req.cfg.formId );
			form.elements["crsId"].value= '${param["crsId"]}';
			
			req.go();
		 
		</c:if>
		<c:if test="${detail.crs.prgrsSttsCdv ne '03'}">
		COMMT.message('통보는 선정완료 상태에서만 처리해야합니다.');
		</c:if>
	}
	
	</c:if>
	<c:if test="${tabCode eq '03'}"> 
	,
	updateCrsAplyAram : function(){
		<c:if test="${detail.crs.prgrsSttsCdv eq '04'}">
		
		</c:if>
		<c:if test="${detail.crs.prgrsSttsCdv ne '04'}">
		COMMT.message('알림발송은 통보 상태에서만 처리해야합니다.');
		</c:if>
	},
	updateCrsAply : function(){
		<c:if test="${detail.crs.prgrsSttsCdv eq '02'}">
		 
		 var req = imRequest("ajax",{formId: "FormPageDetail"});
			req.cfg.type   = "json";			
			req.cfg.url    =  "<c:url value="/mng/crs/03/updatePrgrsSttsCdv.do"/>";
			req.cfg.message.confirm="선정 확정 처리 하시겠습니까?";
			req.cfg.fn.complete = function(act, data) {
				REQ.req.list.go();
			}
			var form =  document.getElementById(req.cfg.formId );
			form.elements["crsId"].value= '${param["crsId"]}';
			
			req.go();
		 
		 
		</c:if>
		<c:if test="${detail.crs.prgrsSttsCdv ne '02'}">
		
		COMMT.message('선정 확정은 랜덤 선정 완료 상태에서만 처리해야합니다.');
		
		</c:if>
	},
	updateCrsAplyCancel : function(){
		<c:if test="${detail.crs.prgrsSttsCdv eq '03'}">
		 
		 var req = imRequest("ajax",{formId: "FormPageDetail"});
			req.cfg.type   = "json";			
			req.cfg.url    =  "<c:url value="/mng/crs/02/updatePrgrsSttsCdv.do"/>";
			req.cfg.message.confirm="선정 확정  취소 처리 하시겠습니까?";
			req.cfg.fn.complete = function(act, data) {
				REQ.req.list.go();
			}
			var form =  document.getElementById(req.cfg.formId );
			form.elements["crsId"].value= '${param["crsId"]}';
			
			req.go();
		 
		 
		</c:if>
		<c:if test="${detail.crs.prgrsSttsCdv ne '03'}">
		
		COMMT.message('선정 확정 상태에서만 처리해야합니다.');
		
		</c:if>
	}
	</c:if>
}


$(document).ready(function(){
	REQ.init();
});


</script>
<%@ include file="incCrs.jsp" %>


<form name="FormModifyStts" id="FormModifyStts" method="post" onsubmit="return false;">
<input type="hidden" name="crsAplcntId"   />
<input type="hidden" name="imCallBack" value="REQ.reload"  />
</form>
	
	
<c:set var="exptSbjYnArr">N=해당 없음,Y=해당</c:set>
	
	
<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberNm=<spring:message code="iMCrsAplcnt.mberNm"/>,mberId=<spring:message code="iMCrsAplcnt.mberId"/>,crsAplcntId=<spring:message code="iMCrsAplcnt.crsAplcntId" /></c:set>
	<div class="sch_box">
		   	
		<div class="group">
		  <select name="aplcnt.scSttsCdvAGNCYSRNG"  onchange="REQ.search()">
		 <option value="">양성기관자격심사상태</option>
		 <im:cd type="option" codeId="IM0017"  selectedCode="${condition.aplcnt.scSttsCdvAGNCYSRNG}"/>
		 </select>
		 <select name="aplcnt.scSttsCdvOPSECTSRNG" onchange="REQ.search()">
		 <option value="">운영사무국자격심사상태</option>
		 <im:cd type="option" codeId="IM0017"  selectedCode="${condition.aplcnt.scSttsCdvOPSECTSRNG}"/>
		 </select>
		 <select name="aplcnt.scExptSbjYn" onchange="REQ.search()">
		 <option value="">유사과목면제여부</option>
		 <im:cd type="option" codeId="${exptSbjYnArr}" selectedCode="${condition.aplcnt.scExptSbjYn}"/>
		 </select>
		</div>
		<div class="group">
			<select name="aplcnt.scKey" >
				<im:cd type="option" codeId="${scKey}" selectedCode="${condition.aplcnt.scKey}"/>
			</select>
			<input type="text" name="aplcnt.scWord" value="<c:out value="${condition.aplcnt.scWord}"/>"  onKeyup="COMMT.enterCallFunc(event, REQ.search);" style="width:300px;">
			<button type="button" onclick="REQ.search()" class="ad_btn gray"><spring:message code="im.common.word.select" /></button>
		</div>
	</div>
</im:form>

<%--목록 상단 --%>
<div class="cb_bar">
	<c:set var="scOrderInfo">4=<spring:message code="iMCrsAplcnt.crsAplcntId" />,2=<spring:message code="iMCrsAplcnt.mberId"/>,3=<spring:message code="iMCrsAplcnt.mberNm"/></c:set><%-- todo  정렬 조건 추가  --%>
	<c:set var="noPageYn" value="Y"/>
	<c:set var="condition" value="${condition.aplcnt}"/>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:set var="condition" value="${condition}"/>
	
	 <div class="cb_bar right">
	<div>
	<c:if test="${tabCode eq '03'}"> 
	 <select  id="sttsCdv_">
	 <option value="">양성기관자격심사상태</option>
	 <im:cd type="option" codeId="IM0017"/>
	 </select>
	 <a href="#"  onclick="REQ.updateAply('AGNCY_SRNG')"  class="bt_new bk">변경</a>
	 <%--
	 <a href="#" onclick="REQ.excel();return false;" class="bt_new bk">엑셀</a>
	  --%>
	 <a href="#"  onclick="REQ.updateCrsAply()"   class="ad_btn green">선정확정</a>
	 <a href="#"  onclick="REQ.updateCrsAplyCancel()"   class="ad_btn green">선정확정 취소</a>
	 <%--
	 <a href="#"  onclick="REQ.updateCrsAplyAram()"   class="ad_btn green">알림발송</a>
	  --%>
	</c:if>
	<c:if test="${tabCode eq '04'}"> 
	
	 <select id="sttsCdv_">
	 <option value="">운영사무국자격심사상태</option>
	 <im:cd type="option" codeId="IM0017"/>
	 </select>
	 <a href="#"  onclick="REQ.updateAply('OPSECT_SRNG')"  class="bt_new bk">변경</a>
	 <%--
	 <a href="#" onclick="REQ.excel();return false;" class="bt_new bk">엑셀</a>
	 --%>
	 
	 <a href="#"  onclick="REQ.updateCrsAply()"   class="ad_btn green">통보</a>
	</c:if>
	 <a href="#" onclick="REQ.crslist();return false;" class="ad_btn bk">목록</a>
	 </div>
	</div>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<input type="hidden" name="chageStts" />
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:50px;">
		<col style="width:168px;">
		<col/>
		<col/>
		<col/>
		<col/>
		<col style="width:90px;">
		<col style="width:90px;">
		<col style="width:90px;">
		<col style="width:90px;">
		<col style="width:90px;">
		<col style="width:90px;">
		<col style="width:100px;">
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col">번호</th>
			<th scope="col"><spring:message code="iMCrsAplcnt.crsAplcntId" /></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberId"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberNm"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberEmailAdres"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mmbrTelno"/></th>
			<th scope="col">랜덤상태</th>
			<th scope="col">양성기관<br>자격심사상태</th>
			<th scope="col">운영사무국<br>자격심사상태</th>
			<th scope="col">교육수수료</th>
			<th scope="col">신청희망<br>자격등급</th>
			<th scope="col">유사과목면제</th>
			<th scope="col"><spring:message code="im.common.word.mng" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<c:if  test="${aplyStts ne '03'}">
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="crsAplcntIds"  value="${row.crsAplcnt.crsAplcntId}"/>
			</td>
			</c:if>
			<td class="ar">${i.count}</td>
			<td>
			${row.crsAplcnt.crsAplcntId}
			</td>
			<td><c:out value="${row.crsAplcnt.mberIdMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mberNmMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mberEmailAdresMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mmbrTelnoMask}"/></td>
			<td>
				<c:set var="RNDM">
					<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="RNDM" />
				</c:set>
				<im:cd type="print" codeId="IM0017" selectedCode="${RNDM}"/>
			</td>
			<td>
				<c:set var="AGNCY_SRNG">
					<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="AGNCY_SRNG" />
				</c:set>
				<im:cd type="print" codeId="IM0017" selectedCode="${AGNCY_SRNG}"/>
			</td>
			<td>
				<c:set var="OPSECT_SRNG">
					<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="OPSECT_SRNG" />
				</c:set>
				<im:cd type="print" codeId="IM0017" selectedCode="${OPSECT_SRNG}"/>
			</td>
			<td class="ar">
			<im:numberFormat value="${row.crsAplcnt.ttnfee}"/>
			</td>
			<td>
			<im:cd type="print" codeId="IM0001"   selectedCode="${row.crsAplcnt.dsrAplyGrdCdv}"/>
			</td>
			<td><im:cd type="print" codeId="${exptSbjYnArr}"  selectedCode="${row.crsAplcnt.exptSbjYn}"/></td>
			<td>
			<a href="javascript:REQ.view('<c:out value="${row.crsAplcnt.mmbrEsntlId}"/>');">
			[이력관리]
			</a>
			<br><a href="javascript:REQ.modifyStts('<c:out value="${row.crsAplcnt.crsAplcntId}"/>');">
			[특이사항관리]
			</a>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="14"><spring:message code="im.common.msg.nodata" /></td>
		</tr>			
		</c:if>
	</tbody>
</table>
</form>
		