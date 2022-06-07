<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>
<script type="text/javascript" >


var REQ = {
	init : function(){
	
	},
	save:  function(){
		var req = imRequest("script", {formId : "FormMemo"}); 
    	req.cfg.fn.exec  = function() {
    		var callback = COMMT.getLayerCallback(parent, "<c:out value="${param['imCallBack']}"/>");
    		var form =  document.getElementById("FormMemo");
    		var returnValue = form.elements["selectMemo"].value;
            if (typeof callback === "function") {
                callback.call(this, returnValue);
            }
            COMMT.callCloseLayer(parent);
        };
        req.validator.set({
            title : "조회사유",
            name : "selectMemo",
            data : ["!null"]
        });
        req.go();
	},
	close:  function(){
		COMMT.callCloseLayer(parent);
	}
}


$(document).ready(function(){
	REQ.init();
});

</script>
<form name="FormMemo" id="FormMemo" method="post" onsubmit="return false;">
<table class="tbl_row">
	<caption> 상세정보</caption>
	<colgroup>
		<col style="width:120px;">
		<col>
	</colgroup>
	<tbody>
			<tr>
				<th scope="row">조회 사유<span class="c_red">*</span></th>
				<td>
					<textarea name="selectMemo" id="" cols="" rows="" style="height:100px;"></textarea>
				</td>
			</tr>
			<tr>
				<th scope="row">관리자 ID</th>
				<td><c:out value="${imLogin_id}" /></td>
			</tr>
	</tbody>
</table>	
</form>
<div class="b_box">
	<a href="#" onclick="REQ.save();return false;" class="ad_btn green">저장</a>
	<a href="#" onclick="REQ.close();return false;" class="ad_btn bk">닫기</a>
</div>