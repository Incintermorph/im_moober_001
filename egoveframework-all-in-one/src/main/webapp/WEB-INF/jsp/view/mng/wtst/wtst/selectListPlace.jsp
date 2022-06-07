<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>


<script type="text/javascript" >


var REQ = {
	orderYn: false,	
	req : {
		wstslist : null,
		modify : null,
		regist : null,
		del : null,
	},
	init : function(){

		this.req.wstslist = imRequest();
		this.req.wstslist.cfg.formId = "FormPageDetail";
		this.req.wstslist.cfg.url    = "<c:url value="/mng/wtst/selectList.do"/>";
	
		
		
		this.req.regist = imRequest("layer");
		this.req.regist.cfg.formId = "FormReq";
		this.req.regist.cfg.url    = "<c:url value="/mng/wtstPlace/regist/layer.do"/>";
		this.req.regist.cfg.options.scroll="hidden";
		this.req.regist.cfg.options.title="시험장 추가";
		this.req.regist.cfg.options.width=600;
		this.req.regist.cfg.options.height=470;
		
		this.req.modify = imRequest("layer");
		this.req.modify.cfg.formId = "FormReq";
		this.req.modify.cfg.url    = "<c:url value="/mng/wtstPlace/modify/layer.do"/>";

		this.req.modify.cfg.options.scroll="hidden";
		this.req.modify.cfg.options.title="정보 변경";
		this.req.modify.cfg.options.width=600;
		this.req.modify.cfg.options.height=470;
		
		
		this.req.del = imRequest("ajax",{formId: "FormList"});
		this.req.del.cfg.type   = "json";
		this.req.del.cfg.url    =  "<c:url value="/mng/wtstPlace/deletelist.do"/>";
		this.req.del.cfg.message.confirm="<spring:message code="common.delete.msg" />";
		this.req.del.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.reload();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.del.validator.set({
	    	message : "<spring:message code="im.common.msg.delete.choice" />",
            name : "checkIndexs",
            data : ["!null"]
        });
	
	},
	regist :  function(){
		var form =  document.getElementById(this.req.regist.cfg.formId  );
		form.elements["wtstId"].value= '${param["wtstId"]}';
		this.req.regist.go();
	},
	modify :  function(mapDataPks){
		COMMT.copyMapDataToFormReset(mapDataPks, this.req.modify.cfg.formId);
		this.req.modify.go();
	},
	del : function(){
		IMGLBObject.request=this;
		this.req.del.go();
	},
	wstslist : function(){
		
		REQ.req.wstslist.go();
	},
	wtstPlaceAplcntlist : function(wtstPlaceId){
		var form =  document.getElementById(this.req.wstslist.cfg.formId  );
		form.elements["wtstId"].value='${param['wtstId']}';
		form.elements["wtstPlaceId"].value=wtstPlaceId;
		form.elements["_pageName"].value='시험장 > 자격심사';
		this.req.wstslist.cfg.url    = "<c:url value="/mng/wtst/wtstPlace/03/selectListAplcnt.do"/>";
		REQ.req.wstslist.go();
	},
	reload: function(){
		TAB.list('02','시험장');
	},
	upRows : function(){
		var check = $("#FormList").find(":input[name=checkIndexs]").filter(":checked");
    	if(check.length==0){
    		COMMT.message('변경할 정보를 선택하세요.');
    		return;
    	}
    	if(check.length>1){
    		COMMT.message('하나의 데이터만 선택하세요.');
    		return;
    	}
		$("#FormList").find("tr").each(function(index) {
			
            var $this = jQuery(this);
            var $checkbox = $this.find(":input[name=checkIndexs]").filter(":checked");
            
            if ($checkbox.length > 0) {
                var $prev = $this.prev("tr");
                if($prev.length > 0) {
                    if ($prev.find(":input[name=checkIndexs]").filter(":checked").length == 0) {
                        $this.insertBefore($prev);
                    }
                }
                REQ.orderYn=true;
            }
        });
	},
    /**
     * 아래로
     */
    downRows: function() {
    	var check = $("#FormList").find(":input[name=checkIndexs]").filter(":checked");
    	if(check.length==0){
    		COMMT.message('변경할 정보를 선택하세요.');
    		return;
    	}
    	if(check.length>1){
    		COMMT.message('하나의 데이터만 선택하세요.');
    		return;
    	}
        $("#FormList").find("tr").each(function(index) {
            var $this = jQuery(this);
            
            var $checkbox = $this.find(":input[name=checkIndexs]").filter(":checked");
            
            if ($checkbox.length > 0) {
                var $next = $this.next("tr");
                if($next.length > 0) {
                    if ($next.find(":input[name=checkIndexs]").filter(":checked").length == 0) {
                        $this.insertAfter($next);
                    }
                }
                REQ.orderYn=true;
            }
        });
    },
    saveOrder : function(){
    	<c:if test="${empty pageInfo.list}">
    	COMMT.message('시험장 등록 후 가능합니다.');
		return;
    	</c:if>
    	<c:if test="${!empty pageInfo.list}">
    	
    	if(!REQ.orderYn){
    		COMMT.message('순서가 변경된 정보가 없습니다.');
    		return;
    		
    	}
    	
    	var req = imRequest("ajax",{formId: "FormList"});
		req.cfg.type   = "json";
		req.cfg.url    =  "<c:url value="/mng/wtstPlace/udpateOrderlist.do"/>";
		req.cfg.message.confirm="<spring:message code="common.save.msg" />";
		req.cfg.fn.complete = function(act, data) {
	        if (data != null && data.result > 0) { 
	        	REQ.reload();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };
	    req.go();
	    </c:if>
    }
}


$(document).ready(function(){
	REQ.init();
});


</script>

<form name="FormReq" id="FormReq" method="post" onsubmit="return false;">
<input type="hidden" name="wtstPlaceId"   value=""/>
<input type="hidden" name="wtstId"   value="${param['wtstId']}"/>
<input type="hidden" name="imCallBack" value="REQ.reload"  />
</form>
	
	
<%@ include file="incWtst.jsp" %>




<%--목록 상단 --%>
<div class="cb_bar right">
	<div> 
	 
	<a href="#" onclick="REQ.regist();return false;" class="bt_new bk">추가</a> 
	<a href="#" onclick="REQ.del();return false;" class="bt_del red">삭제</a> 
	<a href="#" onclick="REQ.upRows();return false;" class="bt_new bk">위로</a>
	<a href="#" onclick="REQ.downRows();return false;" class="bt_new bk">아래로</a>
	<a href="#" onclick="REQ.saveOrder();return false;" class="ad_btn green">순서저장</a>
	<a href="#" onclick="REQ.wstslist();return false;" class="ad_btn bk">목록</a>
	</div>
</div>
<%--목록 내용  --%>
<form name="FormList" id="FormList" method="post" onsubmit="return false;">
<input type="hidden" name="wtstId"   value="${param['wtstId']}"/>
<table class="tbl_col">
	<colgroup>
		<col style="width:50px;">
		<col style="width:50px;"/>
		<col style="width:100px;">
		<col style="width:150px;">
		<col style="width:200px;">
			<col/>

		<col style="width:150px;">
		<col style="width:100px;">
		<col style="width:100px;">
		<col style="width:100px;">
	</colgroup>
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)"></th>
			<th scope="col"><spring:message code="iMWtstPlace.ord"/></th>
			<th scope="col"><spring:message code="iMAgncy.area"/></th>
			<th scope="col"><spring:message code="iMAgncy.areaDtl"/></th>
			<th scope="col"><spring:message code="iMAgncy.placeNm"/></th>
	<th scope="col"><spring:message code="iMWtstPlace.accno"/></th>
	<th scope="col"><spring:message code="iMWtstPlace.cntpnt"/></th>
			<th scope="col">신청자</th>
	<th scope="col"><spring:message code="iMWtstPlace.prsnl"/></th>
	<th scope="col"><spring:message code="iMWtstPlace.prgrsStts"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list}" var="row" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}">
			<%--삭제시 활용할 키 세팅  --%>
			<input type="hidden" name="wtstPlaceIds"  value="${row.wtstPlace.wtstPlaceId}"/>
			</td>
			<td><c:out value="${i.count}"/></td>
			<td><im:cd type="print" codeId="IM0006" selectedCode="${row.agncy.areaCdvRgn}"  /></td>
			<td><im:cd type="print" codeId="IM0007" selectedCode="${row.agncy.areaCdv}"  /></td>
			<td  class="al">
				<a href="#" onclick="REQ.modify({'wtstPlaceId' : '${row.wtstPlace.wtstPlaceId}'});return false;">
				<c:out value="${row.agncy.agncyNm}"/>
				</a>
			</td>
			<td class="al">
				
				<im:cd type="print" codeId="IM0005" selectedCode="${row.wtstPlace.bnkCdv}"/>
				&nbsp;<c:out value="${row.wtstPlace.accno}"/>
				&nbsp;<c:out value="${row.wtstPlace.acchdr}"/>
			</td>
			<td><c:out value="${row.wtstPlace.cntpnt}"/></td>
			<td class="ar"><c:out value="${row.applyCnt}"/></td>
			<td class="ar"><c:out value="${row.wtstPlace.prsnl}"/></td>
			<td>
				<c:if test="${row.wtstPlace.prgrsSttsCdv eq '01'}">
				<a href="#" onclick="REQ.wtstPlaceAplcntlist('${row.wtstPlace.wtstPlaceId}');return false;">자격심사</a>
				</c:if>
				<c:if test="${row.wtstPlace.prgrsSttsCdv ne '01'}">
				<im:cd type="print" codeId="IM0020" selectedCode="${row.wtstPlace.prgrsSttsCdv}"/>
				</c:if>
				
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty pageInfo.list}">
		<tr>
			<td colspan="10"><spring:message code="im.common.msg.nodata" /></td>
		</tr>			
		</c:if>
	</tbody>
</table>
</form>