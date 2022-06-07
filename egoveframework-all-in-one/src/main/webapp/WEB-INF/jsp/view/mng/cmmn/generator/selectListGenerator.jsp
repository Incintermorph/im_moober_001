<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 


<script type="text/javascript" >


var REQ = {
	req : {
		download : null,
		save : null
	},
	init : function(){
		
		this.req.download = imRequest();
		this.req.download.cfg.formId = "FormDownload";
		this.req.download.cfg.url    = "<c:url value="/mng/generator/download.do"/>";
		
		this.req.save = imRequest("ajax",{formId: "FormSave"});
		this.req.save.cfg.type   = "json";
		this.req.save.cfg.url    =  "<c:url value="/mng/generator/insert.do"/>";
		this.req.save.cfg.message.confirm = "생성하시겠습니까?"; 
		this.req.save.cfg.message.waiting = "처리중입니다";
		this.req.save.cfg.fn.complete = function(act, data) {
			 if(data.generatedPath != null && data.generatedPath != ""){
				 var form =  document.getElementById(IMGLBObject.request.req.download.cfg.formId);
				form.elements["generatedPath"].value= data.generatedPath;
				IMGLBObject.request.req.download.go();
	        } else {
	        	COMMT.errorMessage();
	        }
	    };	
	    this.req.save.validator.set({
	    	message : "생성할 테이블정보 를 선택하세요.",
            name : "checkIndexs",
            data : ["!null"]
        });
	
	},
	save : function(){
		IMGLBObject.request=this;
		this.req.save.go();
	}
}


$(document).ready(function(){
	REQ.init();
});


</script>
 <form id="FormDownload" name="FormDownload" method="post" onsubmit="return false;">
 <input type="hidden" name="generatedPath" value="" />
        </form>

 <form id="FormSave" name="FormSave" method="post" onsubmit="return false;">
			<table class="tbl_col">
				<colgroup>
					<col style="width:50px;">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">선택</th>
						<th scope="col">table name</th>
						<th scope="col">table commnet</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listTable}" var="tablNm" varStatus="i">
					<tr>
						<td><input type="checkbox" name="checkIndexs" title="선택" value="${i.index}"></td>
						<td>
						<input type="text" name="tableNames" value="${tablNm}" readonly="readonly"/>
						</td>
						<td><input type="text" name="tableComments"/></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<table class="tbl_col">
            <colgroup>
                <col style="width:100px" />
                <col style="width:auto" />
                <col style="width:100px" />
                <col style="width:auto" />
                <col style="width:100px" />
                <col style="width:auto" />
                <col style="width:100px" />
                <col style="width:auto" />
            </colgroup>
            <tbody>
                <tr>
                    <th>Project</th>
                    <td><input type="text" name="projectName" value="egoveframework-all-in-one" style="width:90%;"></td>
                    <th>Package</th>
                    <td><input type="text" name="packageName" value="com.intermorph" style="width:90%;"></td>
                    <th>Component</th>
                    <td><input type="text" name="componentName" value="crs.crsMstr" style="width:90%;"></td>
                    <th>생성자</th>
                    <td><input type="text" name="authorName" value="sungyong2" style="width:90%;" ></td>
                </tr>
                <tr>
                    <th>생성파일</th>
                    <td colspan="7">
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.vo.xml" checked="checked"> UI VO</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.resultset.xml" checked="checked"> ResultSet VO</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.condition.xml" checked="checked"> Condition VO</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.controller.xml" checked="checked"> Controller</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.controller.mng.xml" checked="checked"> MNG Controller</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.controller.user.xml" checked="checked"> USER Controller</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.mapper.xml" checked="checked"> Mapper</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.service.xml" checked="checked"> Service</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.service.impl.xml" checked="checked"> Service Impl</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.messge.xml" checked="checked"> messge</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.jsp.reg.xml" checked="checked"> jsp reg</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.jsp.modify.xml" checked="checked"> jsp modify</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.jsp.inc.xml" checked="checked"> jsp inc</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="java.jsp.select.xml" checked="checked"> jsp select</div>
                        <div class="files"><input type="checkbox" name="generateFiles" value="sql.cubrid.xml" checked="checked">cubrid Sql</div> <%-- value : database 에 맞게 적용할 것 --%>
                    </td>
                </tr>
            </tbody>
            </table>
		</form>
		
		<div class="b_box right">
				<a href="javascript:;" onclick="REQ.save();"  class="ad_btn green mid">생성</a>
			</div>