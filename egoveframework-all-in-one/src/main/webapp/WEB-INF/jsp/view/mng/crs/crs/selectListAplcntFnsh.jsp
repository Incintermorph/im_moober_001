<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	excelType : null,
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
		this.req.updateAply.cfg.asynchronous = true; // 인 경우 asynchronous
		this.req.updateAply.cfg.message.waiting = "처리중입니다.";
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
		<c:if test="${detail.crs.prgrsSttsCdv eq '04'}">
		IMGLBObject.request=this;
		if($("#sttsCdv_"+sttsCd).val()==''){
			COMMT.message('변경할 상태를 선택하세요.');
			return;
		}
		var chageStts = $("#sttsCdv_"+sttsCd).val(); 
		REQ.req.updateAply.cfg.url    =  "<c:url value="/mng/crsAplcnt/"/>"+sttsCd+"/updateAplylist.do";
		var form =  document.getElementById(this.req.updateAply.cfg.formId);
		form.elements["chageStts"].value= chageStts;
		REQ.req.updateAply.go();
		</c:if>
		<c:if test="${detail.crs.prgrsSttsCdv ne '04'}">
		COMMT.message('입금확인은 통보 상태에서만 처리해야합니다.');
		</c:if>
	},
	search : function(){
		var serchform =  document.getElementById("FormSearch");
		var form =  document.getElementById(REQ.req.list.cfg.formId);
		form.elements["aplcnt.scKey"].value= serchform.elements["aplcnt.scKey"].value;
		form.elements["aplcnt.scWord"].value= serchform.elements["aplcnt.scWord"].value;
		form.elements["aplcnt.scSttsCdvFNSH"].value= serchform.elements["aplcnt.scSttsCdvFNSH"].value;

	 	<c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
		form.elements["aplcnt.scSttsCdvQLFC"].value= serchform.elements["aplcnt.scSttsCdvQLFC"].value;
		</c:if>
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
	excel : function(excelResult){
		excelType = REQ.excelType;
		var req = imRequest();
		req.cfg.formId = "FormPageDetail";
		
		var form =  document.getElementById(req.cfg.formId);

		form.elements["crsId"].value= '${param["crsId"]}';
		form.elements["excelType"].value= excelType;
		form.elements["excelResult"].value= excelResult;
		form.elements["downloadName"].value= "출석부_ ${detail.agncy.agncyNm}_${detail.crs.eduYear}년_<im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>_<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/>_${detail.crs.eduRnd}차";
		if(excelType=='Attendance'){
			form.elements["downloadName"].value= "출석부_ ${detail.agncy.agncyNm}_${detail.crs.eduYear}년_<im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>_<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/>_${detail.crs.eduRnd}차";
		}else if(excelType=='Graduate'){
			form.elements["downloadName"].value= "수료자_ ${detail.agncy.agncyNm}_${detail.crs.eduYear}년_<im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>_<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/>_${detail.crs.eduRnd}차";
		}else if(excelType=='GraduateQlfc'){
			form.elements["downloadName"].value= "자격취득자_ ${detail.agncy.agncyNm}_${detail.crs.eduYear}년_<im:cd type="print" codeId="IM0001" selectedCode="${detail.crsMstr.crsGrdCdv}"/>_<im:cd type="print" codeId="IM0002" selectedCode="${detail.crsMstr.crsDvsnCdv}"/>_${detail.crs.eduRnd}차";
		}else{
			return ;
		}
		
		
		req.cfg.url    = "<c:url value="/mng/crs/${param['tabCode']}/selectListAplcntExcel.do"/>";
		req.go();
	},
	selectMemo:function(excelType){
		REQ.excelType=excelType;
		var req = imRequest("layer");
		req.cfg.url    = "<c:url value="/mng/ussMngr/select/memoLayer.do"/>";
		req.cfg.options.title="개인조회 사유 등록";
		req.cfg.formId = "FormPageDetail";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=300;
		req.go();
			
	}
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

	
	
<c:set var="dpstArr">Y=예,N=아니오</c:set>
	
<im:form  condition="${condition}" formName="FormSearch"  type="search"> <%--검색인 경우 타입 --%>
<%--기본 검색 키 값 정의 키=값,키=값 으로 정의함 --%>
<c:set var="scKey">mberNm=<spring:message code="iMCrsAplcnt.mberNm"/>,mberId=<spring:message code="iMCrsAplcnt.mberId"/>,crsAplcntId=<spring:message code="iMCrsAplcnt.crsAplcntId" /></c:set>
	<div class="sch_box">
		<div class="group">
		 <select name="aplcnt.scSttsCdvFNSH" onchange="REQ.search()">
		 <option value="">수료상태</option>
		 <im:cd type="option" codeId="IM0013" selectedCode="${condition.aplcnt.scSttsCdvFNSH}"/>
		 </select>
		 <c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
			 <select name="aplcnt.scSttsCdvQLFC" onchange="REQ.search()">
			 <option value="">자격취득상태</option>
			 <im:cd type="option" codeId="IM0014" selectedCode="${condition.aplcnt.scSttsCdvQLFC}"/>
			 </select>
		 </c:if>
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
	<c:set var="scOrderInfo">4=<spring:message code="iMCrsAplcnt.crsAplcntId" />,2=<spring:message code="iMCrsAplcnt.mberId"/>,3=<spring:message code="iMCrsAplcnt.mberNm"/>,6=수료일자,7=자격취득일자</c:set>
	<c:set var="noPageYn" value="Y"/>
	<c:set var="condition" value="${condition.aplcnt}"/>
	<im:cd type="set" codeId="${scOrderInfo}" var="orderInfo"/>
	<%@ include file="/WEB-INF/jsp/inc/imPagListOrder.jspf" %>
	<c:set var="condition" value="${condition}"/>
	
	 <div class="cb_bar right">
	<div>
	
	
	<c:if test="${!empty  loginUserAgncyId}"> <%--양성기관 전용 기능  --%>
	 <select id="sttsCdv_FNSH">
	 <option value="">수료상태</option>
	 <im:cd type="option" codeId="IM0013"/>
	 </select>
	 <a href="#"  onclick="REQ.updateAply('FNSH')"  class="bt_new bk">변경</a>
	 
	 <c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
		 <select id="sttsCdv_QLFC">
		 <option value="">자격취득상태</option>
		 <im:cd type="option" codeId="IM0014"/>
		 </select>
		 <a href="#"  onclick="REQ.updateAply('QLFC')"  class="bt_new bk">변경</a>
	 </c:if>
	</c:if> 
	 <a href="#" onclick="REQ.selectMemo('Attendance');return false;" class="bt_new bk">출석부 엑셀</a>
	 <a href="#" onclick="REQ.selectMemo('Graduate');return false;" class="bt_new bk">수료자 엑셀</a>
	 <c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
	 <a href="#" onclick="REQ.selectMemo('GraduateQlfc');return false;" class="bt_new bk">자격취득자 엑셀</a>
	 </c:if>
	 <a href="#" onclick="REQ.crslist();return false;" class="ad_btn bk">목록</a>
	 </div>
	</div>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<input type="hidden" name="chageStts" />
<input type="hidden" name="chageMsg" />
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:50px;">
		<col style="width:168px;">
		<col/>
		<col/>
		<col/>
		<col/>
		<col style="width:100px;">
		<col style="width:100px;">
	 	<c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
		<col style="width:100px;">
		<col style="width:100px;">
		</c:if>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col">번호</th>
			<th scope="col"><spring:message code="iMCrsAplcnt.crsAplcntId" /></th>			
			<th scope="col"><spring:message code="iMCrsAplcnt.mberId"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberNm"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mberEmailAdres"/></th>
			<th scope="col"><spring:message code="iMCrsAplcnt.mmbrTelno"/></th>
			<c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
			<th scope="col">수료상태</th>
			<th scope="col">자격취득상태</th>
			<th scope="col">수료일자</th>
			<th scope="col">자격취득일자</th>
			</c:if>
			<c:if test="${detail.crsMstr.crsDvsnCdv ne 'CRS_DVSN_002' }">
			<th scope="col">수료상태</th>
			<th scope="col">수료일자</th>
			</c:if>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="crsAplcntIds"  value="${row.crsAplcnt.crsAplcntId}"/>
			</td>
			<td class="ar">${i.count}</td>
			<td>${row.crsAplcnt.crsAplcntId}</td>
			<td><c:out value="${row.crsAplcnt.mberIdMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mberNmMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mberEmailAdresMask}"/></td>
			<td><c:out value="${row.crsAplcnt.mmbrTelnoMask}"/></td>
			<c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
				<td>
					<c:set var="FNSH">
						<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="FNSH" />
					</c:set>
					<im:cd type="print" codeId="IM0013" selectedCode="${FNSH}"/>
				</td>
				<td>
					<c:set var="QLFC">
						<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="QLFC" />
					</c:set>
					<im:cd type="print" codeId="IM0014" selectedCode="${QLFC}"/>
				</td>
				<td>
				<c:if test="${FNSH eq '02'}">
					<im:dt yyyyMMddHHmmss="${row.crsAplcnt.fnshYmd}"/>
				</c:if>
				</td>
				<td>
				<c:if test="${QLFC eq '02'}">
					<im:dt yyyyMMddHHmmss="${row.crsAplcnt.qlfcAcqsYmd}"/>
				</c:if>
				</td>
			</c:if>
			<c:if test="${detail.crsMstr.crsDvsnCdv ne 'CRS_DVSN_002' }">
				<td>
					<c:set var="FNSH">
						<im:cd  codeId="${row.sttDdvs}" type="print" selectedCode="FNSH" />
					</c:set>
					<im:cd type="print" codeId="IM0013" selectedCode="${FNSH}"/>
				</td>
				<td>
				<c:if test="${FNSH eq '02'}">
					<im:dt yyyyMMddHHmmss="${row.crsAplcnt.fnshYmd}"/>
				</c:if>
				</td>
			</c:if>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<c:if test="${detail.crsMstr.crsDvsnCdv eq 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
			<td colspan="11"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
			<c:if test="${detail.crsMstr.crsDvsnCdv ne 'CRS_DVSN_002' }"><%--실무 과정인 경우 --%>
			<td colspan="9"><spring:message code="im.common.msg.nodata" /></td>
			</c:if>
		</tr>			
		</c:if>
	</tbody>
</table>
</form>
		