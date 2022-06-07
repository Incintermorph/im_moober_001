<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/imConfigTaglib.jspf" %>

<script type="text/javascript" >
/**
 * jquery.learning.simple.api.js 
 * author : jkk5246@gmail.com 
 * version : 1.0.0 
 * created : 2012.01.26
 */
var _fileinfo_ = {
    version : "4.2.0"
};
var API_ERROR = {
    NO_ERROR : {code : 0, string : '정상처리 되었습니다'}, // 정상처리 되었습니다
    INITIALIZE_FAILURE : {code : 100, string : '초기화에 실패하였습니다'}, // 초기화에 실패하였습니다
    INITIALIZE_AFTER_INITIALIZE : {code : 101, string : '이미 초기화 되었습니다'}, // 이미 초기화 되었습니다
    INITIALIZE_AFTER_TERMINATE : {code : 102, string : '이미 종료되었습니다'}, // 이미 종료되었습니다
    INITIALIZE_ARGUMENT : {code : 103, string : 'Argument가 정확하지 않습니다'}, // Argument가 정확하지 않습니다
    
    TERMINATE_FAILURE : {code : 200, string : '정상적인 종료가 되지않았습니다'}, // 정상적인 종료가 되지않았습니다
    TERMINATE_BEFORE_INITIALIZE : {code : 201, string : '초기화되기 전 상태입니다'}, // 초기화되기 전 상태입니다
    TERMINATE_AFTER_TERMINATE : {code : 202, string : '이미 종료되었습니다'}, // 이미 종료되었습니다
    TERMINATE_ARGUMENT : {code : 203, string : 'Argument가 정확하지 않습니다'}, // Argument가 정확하지 않습니다

    COMMIT_FAILURE : {code : 300, string : '서버 또는 네트워크 상태가 불안정합니다.<br>종료 후 다시 시도하십시오'}, // 서버 또는 네트워크 상태가 불안정합니다.<br>종료 후 다시 시도하십시오
    COMMIT_BEFORE_INITIALIZE : {code : 301, string : '초기화되기 전 상태입니다'}, // 초기화되기 전 상태입니다
    COMMIT_AFTER_TERMINATE : {code : 302, string : '이미 종료되었습니다'}, // 이미 종료되었습니다
    COMMIT_ARGUMENT : {code : 303, string : 'Argument가 정확하지 않습니다'}, // Argument가 정확하지 않습니다
    
    GETVALUE_FAILURE : {code : 400, string : '학습데이터 읽기 오류가 발생하였습니다'}, // 학습데이터 읽기 오류가 발생하였습니다
    GETVALUE_BEFORE_INITIALIZE : {code : 401, string : '초기화되기 전 상태입니다'}, // 초기화되기 전 상태입니다
    GETVALUE_AFTER_TERMINATE : {code : 402, string : '이미 종료되었습니다'}, // 이미 종료되었습니다
    GETVALUE_ARGUMENT : {code : 403, string : 'Argument가 정확하지 않습니다'}, // Argument가 정확하지 않습니다
    
    SETVALUE_FAILURE : {code : 500, string : '학습데이터 쓰기 오류가 발생하였습니다'}, // 학습데이터 쓰기 오류가 발생하였습니다
    SETVALUE_BEFORE_INITIALIZE : {code : 501, string : '초기화되기 전 상태입니다'}, // 초기화되기 전 상태입니다
    SETVALUE_AFTER_TERMINATE : {code : 502, string : '이미 종료되었습니다'}, // 이미 종료되었습니다
    SETVALUE_ARGUMENT : {code : 503, string : 'Argument가 정확하지 않습니다'} // Argument가 정확하지 않습니다
};

var API = {
    status : "",
    debug : {
        on : false,
        callback : null
    },
    url : {
        initialize : "${pageContext.request.contextPath}/user/study/cnts/initialize.do",
        terminate : "${pageContext.request.contextPath}/cmmn/study/cnts/terminate.do",
        commit : "${pageContext.request.contextPath}/cmmn/study/cnts/commit.do",
        restore :"${pageContext.request.contextPath}/cmmn/study/cnts/restore.do",
        othersData : "${pageContext.request.contextPath}/cmmn/study/cnts/get/others/data.do"
    },
    timer : null,
    timerInterval : 1000 * 60 * 3, // 3분
    dataModel : null,
    learningData : {
    	cntsId : "",
    	crsAplcntId : ""
    },
    exception : { 
        callback : function() {
            if (API.debug.on == false) {
                self.close();
            }
        }
    },
    error : API_ERROR.NO_ERROR,
    Initialize : function(param) {
        if (API.status === "exception") {
            return "false";
        }
        if (API.status === "terminated") {
            API.setError(API_ERROR.INITIALIZE_AFTER_TERMINATE, "[Initialize]");
            return "false";
        }
        if (API.status === "initialized") {
            API.setError(API_ERROR.INITIALIZE_AFTER_INITIALIZE, "[Initialize]");
            return "false";
        }
        if (typeof param !== "undefined" && param != "") {
            API.setError(API_ERROR.INITIALIZE_ARGUMENT, "[Initialize]");
            return "false";
        }
        var parameters = {
            url : API.url.initialize,
            type : 'initialize',
            data : API.getJsonParameters("", API.learningData)
        };
        API.ajax(parameters);
        return "true";
        
    },
    Terminate : function(param) {
        
        if(API.url.terminate == ''){
            return "true";
        }
        
        if (API.status === "exception") {
            return "false";
        }
        if (API.status === "terminated") {
            API.setError(API_ERROR.TERMINATE_AFTER_TERMINATE, "[Terminate]");
            return "false";
        }
        if (API.status !== "initialized") {
            API.setError(API_ERROR.TERMINATE_BEFORE_INITIALIZE, "[Terminate]");
            return "false";
        }
        if (typeof param !== "undefined" && param != "") {
            API.setError(API_ERROR.TERMINATE_ARGUMENT, "[Terminate]");
            return "false";
        }
        API.SetValue("cmi.exit", "exit");
        
        var parameters = {
            url : API.url.terminate,
            type : 'terminate',
            data : API.getJsonParameters("", API.dataModel) + "&" + API.getJsonParameters("", API.learningData)
        };

        API.ajax(parameters);
        return "true";
       
    },
    Commit : function(param) {
        
        if(API.url.commit == ''){
            return "true";
        }
        
        if (API.status === "exception") {
            return "false";
        }
        if (API.status === "terminated") {
            API.setError(API_ERROR.COMMIT_AFTER_TERMINATE, "[Commit]");
            return "false";
        }
        if (API.status !== "initialized") {
            API.setError(API_ERROR.COMMIT_BEFORE_INITIALIZE, "[Commit]");
            return "false";
        }
        if (typeof param !== "undefined" && param != "") {
            API.setError(API_ERROR.COMMIT_ARGUMENT, "[Commit]");
            return "false";
        }
        
        var parameters = {
            url : API.url.commit,
            type : 'commit',
            data : API.getJsonParameters("", API.dataModel) + "&" + API.getJsonParameters("", API.learningData)
        };
        API.ajax(parameters);
        return "true";
        
    },
    GetValue : function(dataModelElement) {
        if (API.status === "terminated") {
            API.setError(API_ERROR.GETVALUE_AFTER_TERMINATE, "[GetValue]");
            return "false";
        }
        if (API.status !== "initialized") {
            API.setError(API_ERROR.GETVALUE_BEFORE_INITIALIZE, "[GetValue]");
            return "false";
        }
        if (typeof dataModelElement === "undefined" || dataModelElement == "") {
            API.setError(API_ERROR.GETVALUE_ARGUMENT, "[GetValue]");
            return "false";
        }
        
        var result = "unknown";
        try {
            result = API.getDataModel(dataModelElement);
            API.setError(API_ERROR.NO_ERROR, "[GetValue]", dataModelElement + "=" + result);
        } catch (e) {
            API.setError(API_ERROR.GETVALUE_FAILURE, "[GetValue]", dataModelElement + "=" + result);
        }
        return result;
    },
    SetValue : function(dataModelElement, dataModelValue) {
        if (API.status === "terminated") {
            API.setError(API_ERROR.SETVALUE_AFTER_TERMINATE, "[SetValue]");
            return "false";
        }
        if (API.status !== "initialized") {
            API.setError(API_ERROR.SETVALUE_BEFORE_INITIALIZE, "[SetValue]");
            return "false";
        }
        if (typeof dataModelElement === "undefined" || dataModelElement == "" || typeof dataModelValue === "undefined") {
            API.setError(API_ERROR.SETVALUE_ARGUMENT, "[SetValue]");
            return "false";
        }

        try {
            API.setDataModel(dataModelElement, dataModelValue);
            API.setError(API_ERROR.NO_ERROR, "[SetValue]", dataModelElement + "=" + dataModelValue);
            return "true";
        } catch (e) {
            API.setError(API_ERROR.SETVALUE_FAILURE, "[SetValue]", dataModelElement + "=" + dataModelValue);
            return "false";
        }
    },
    getOthersData : function(dataName) {
        
        if(API.url.commit == ''){
            return "true";
        }
        
        if (API.status === "terminated") {
            API.setError(API_ERROR.GETVALUE_AFTER_TERMINATE, "[GetOthersData]");
            return "false";
        }
        if (API.status !== "initialized") {
            API.setError(API_ERROR.GETVALUE_BEFORE_INITIALIZE, "[GetOthersData]");
            return "false";
        }
        if (typeof dataName === "undefined" || dataName == "") {
            API.setError(API_ERROR.GETVALUE_ARGUMENT, "[GetOthersData]");
            return "false";
        }
        
        var parameters = {
            url : API.url.othersData,
            type : 'othersData',
            data : API.getJsonParameters("", API.dataModel) + "&" + API.getJsonParameters("", API.learningData) + "&dataName=" + dataName
        };
        API.ajax(parameters);
        return "true";
        
    },
    GetLastError : function() {
        API.doDebug("[GetLastError] " + API.error.code);
        return API.error.code;
        
    },
    GetErrorString : function(errorCode) {
        API.doDebug("[GetErrorString] " + API.error.string);
        return API.error.string;
        
    },
    GetDiagnostic : function(errorCode) {
        API.doDebug("[GetDiagnostic] " + API.error.string);
        return API.error.string;
        
    },
    restore : function() {
        
        if(API.url.restore == ''){
            return "true";
        }
        
        var parameters = {
            url : API.url.restore,
            type : 'restore',
            data : API.getCookie()
        };
        if (parameters.data.length > 0) {
            API.ajax(parameters);
            return "true";
        }
    },
    setError : function(error, state, message) {
        API.error = error;
        if (API.debug.on && typeof API.debug.callback === "function") {
            API.debug.callback.call(this, {
                "state" : state,
                "success" : API.error == API_ERROR.NO_ERROR,
                "message" : state + " [" + API.error.code + "] - " + API.error.string + (typeof message === "string" ? "<br/>"+message : "")
            });
        }
    },
    doDebug : function(message) {
        if (API.debug.on && typeof API.debug.callback === "function") {
            API.debug.callback.call(this, {
                "message" : message
            });
        }
    },
    setDataModel : function(key, value) {
        if (API.dataModel == null) {
            API.dataModel = {};
        }
        API.dataModel[key] = value;
    },
    getDataModel : function(key) {
        if (API.dataModel == null) {
            API.dataModel = {};
        }
        if (typeof API.dataModel[key] === "undefined") {
            return "";
        } else {
            return API.dataModel[key];
        }
    },
    getJsonParameters : function(prefix, json) {
        var buffer = [];
        for(var property in json) {
            switch (typeof json[property]) {
            case "object" :
                var value = API.getJsonParameters(prefix + property + ".", json[property]);
                if (value.length > 0 ) {
                    buffer.push(value);
                }
                break;
            case "string" :
            case "number" :
            case "boolean" :
                buffer.push(prefix + property + "=" + json[property]);
                break;
            }
        }
        return buffer.join("&");
    },
    ajax : function(param) {
        var result = {};
        var asyncBoolean=false;
        if(param.type=='commit' || param.type=='terminate'){
        	asyncBoolean=true;
        }
        jQuery.ajax({
            url : param.url,
            data : param.data,
            type : "post",
            dataType : "json",
            cache : false,
            async : asyncBoolean,
            contentType : "application/x-www-form-urlencoded",
            error : function(req, status, error) {
                if (API.debug.on) {
                    API.doDebug("request [" + req.statusText + "] : " + error);
                }
                API.setCookie();
                result["result"] = false;
            },
            success : 
                function(data, textStatus, jqXHR) {
                    result = data;
                    API.AjaxRsult(param.type,param,result);
                }
        });
        return result;
    },
    AjaxRsult : function(calltype,parameters,returnValue) {
    	if(calltype=='initialize'){
	    	if ("true" == returnValue.result) {
	            API.status = "initialized";
	            API.dataModel = returnValue.dataModel;
	            var data = API.getJsonParameters("", API.dataModel) + "&" + API.getJsonParameters("", API.learningData);
	            API.setError(API_ERROR.NO_ERROR, "[Initialize]", data.split("&").join("\n"));
	            API.timer = setInterval("API.Commit('')", API.timerInterval);
	        } else {
	            API.status = "";
	            API.dataModel = null;
	            API.setError(API_ERROR.INITIALIZE_FAILURE, "[Initialize]");
	            API.quitAlert();
	        }
    	}else if(calltype=='terminate'){
    		 if ("true" == returnValue.result) {
                 API.status = "terminate";
                 API.dataModel = null;
                 API.setError(API_ERROR.NO_ERROR, "[Terminate]", parameters.data.split("&").join("\n"));

                
                 clearInterval(API.timer);
             } else {
                 API.setError(API_ERROR.TERMINATE_FAILURE, "[Terminate]", parameters.data.split("&").join("\n"));
                 API.quitAlert();
             }
    	}else if(calltype=='commit'){
    		if ("true" == returnValue.result) {
                API.delCookie();
                API.setError(API_ERROR.NO_ERROR, "[Commit]", parameters.data.split("&").join("\n"));
	            //API.dataModel = returnValue.dataModel;
	            //서버에서 저장되 데이터 유지 처리함 
	            API.dataModel['startTime'] = returnValue.dataModel['startTime'];
	            API.dataModel['studyTime'] = returnValue.dataModel['studyTime'];
	            API.dataModel['cmi.total_time'] = returnValue.dataModel['cmi.total_time'];
	            API.dataModel['cmi.session_time'] = returnValue.dataModel['cmi.session_time'];
	            API.dataModel['save_progress_measure'] = returnValue.dataModel['save_progress_measure'];
	            API.dataModel['resultCode'] = returnValue.dataModel['resultCode'];
	            $("#studyTime").html(returnValue.dataModel.studyTime/1000 + "초");
	            $("#progress_measure").html(Number(API.dataModel['save_progress_measure'])  );
            } else {
                API.setError(API_ERROR.COMMIT_FAILURE, "[Commit]", parameters.data.split("&").join("\n"));
                API.quitAlert();
            }
    	}else if(calltype=='othersData'){
    		if ("true" == returnValue.result) {
                API.setError(API_ERROR.NO_ERROR, "[GetOthersData]", parameters.data.split("&").join("\n"));
            } else {
                API.setError(API_ERROR.GETVALUE_FAILURE, "[GetOthersData]", parameters.data.split("&").join("\n"));
            }
    	}else if(calltype=='restore'){
    		if ("true" == returnValue.result) {
                if ("1" == returnValue.restore) {
                    jQuery.dialog("alert", {
                        message : '저장되지 않은 학습데이타를 복원하였습니다' // 저장되지 않은 학습데이타를 복원하였습니다
                    });
                    API.delCookie();
                }
            } else {
                API.delCookie();
            }
		}
    	
   },
    quitAlert : function() {
        API.status = "exception";
        jQuery("iframe").hide();
        jQuery.dialog("alert", {
            message : API.error.string,
            button1 : {
                callback : function() {
                    if (typeof API.exception.callback === "function") {
                        //API.exception.callback.apply(this);
                    }
                }
            }
        });
    },
    isInitialized : function() {
        return API.status == "initialized" ? true : false;
    },
    getCookie : function() {
    	/**
        var cookies = document.cookie.split(";");
        for (var i = 0; i < cookies.length; i++) {
            var cook = cookies[i].split("=");
            if (cook.length == 2) {
                cook[0] = cook[0].trim();
                cook[1] = cook[1].trim();
                if (cook[0] == "learningData") {
                    return unescape(cook[1]);
                }
            }
        }
        **/
        return "";
    },
    setCookie : function() {
    	/** 쿠키 사용 안함 
        var value = API.getJsonParameters("", API.dataModel) + "&" + API.getJsonParameters("", API.learningData);
        var date = new Date((new Date()).getTime() + 24 * 60 * 60 * 1000); // 1일
        var cookie = ("learningData=" + escape(value));
        cookie += ("; path=/; expires=" + date.toGMTString());
        document.cookie = cookie;
        API.doDebug("[setCookie] " + cookie);
        **/
    },
    delCookie : function() {
    	/**쿠키 사용 안함 
        var cookie = "learningData=; path=/; expires=;";
        document.cookie = cookie;
        API.doDebug("[delCookie] " + cookie);
        **/
    },
    LMSInitialize : function(param) {
        if (API.status === "initialized") {
            return "true";
        } else {
            return API.Initialize(param);
        }
    },
    LMSFinish : function(param) {
        if (API.status === "terminated") {
            return "true";
        } else {
            return API.Terminate(param);
        }
    },
    LMSGetValue : function(param) {
        return API.GetValue(param);
    },
    LMSSetValue : function(param1, param2) {
        return API.SetValue(param1, param2);
    },
    LMSCommit : function(param) {
        return API.Commit(param);
    },
    LMSGetLastError : function() {
        return API.GetLastError();
    },
    LMSGetErrorString : function(param) {
        return API.GetErrorString(param);
    },
};

</script>
<script type="text/javascript" >



var REQ = {
	console : null,
	init : function(){
		API.debug.on = true;
	    API.debug.callback = function(log) {
	    	REQ.logging(log);
	    };
	    REQ.console = $("#console");
	},
	setData : function(){
		API.SetValue("cmi.location","1page");
		
	},
	setDataProg : function(prog){
		API.SetValue("cmi.progress_measure",prog);
		
	},
	setPageProg : function(now,total){
		prog = now/total;

		API.SetValue("cmi.location",now); // 위치 저장 
		API.SetValue("cmi.progress_measure",prog); // 진도율 저장 
		
	},
	Initialize : function(){
		API.Initialize("");
		
	},
	commit : function(){
		API.Commit("");
		
	},
	Terminate : function(){
		API.Terminate("");
		
	},
	getData : function(){
		 API.GetValue("cmi.location");
		
	},
	logging : function(log) {
	        if (typeof log === "object") {
	            if (typeof log.message === "string") {
	                $("<div style='margin-top:10px;'>" + log.message.replaceAll("\n", "<br>") + "</div>").appendTo(REQ.console);
	                REQ.console.scrollTop(REQ.console.get(0).scrollHeight);
	            }
	        }
	    },
	    /**
	     * 로그 지우기
	     */
	  clearLog : function() {
		  REQ.console.empty();
	   }
}

var CMS_API = {
		
}


$(document).ready(function(){
	REQ.init();
});

</script>


<span onclick="REQ.Initialize()">시작</span>
<span onclick="REQ.commit()">저장</span>
<span onclick="REQ.Terminate()">종료</span>
<span onclick="REQ.clearLog()">로그지우기</span>
<br>
<span onclick="REQ.setDataProg('0.11')">진도율: 0.1</span>
<span onclick="REQ.setDataProg('0.32')">진도율: 0.3</span>
<span onclick="REQ.setDataProg('0.53')">진도율: 0.5</span>
<span onclick="REQ.setDataProg('0.72')">진도율: 0.7</span>
<span onclick="REQ.setDataProg('0.91')">진도율: 0.9</span>
<span onclick="REQ.setDataProg('1')">진도율: 1</span>
<span onclick="REQ.setDataProg('10')">진도율: 10</span><br>
<span onclick="REQ.setPageProg(1,7)">page 1</span>
<span onclick="REQ.setPageProg(2,7)">page 2</span>
<span onclick="REQ.setPageProg(3,7)">page 3</span>
<span onclick="REQ.setPageProg(4,7)">page 4</span>
<span onclick="REQ.setPageProg(5,7)">page 5</span>
<span onclick="REQ.setPageProg(6,7)">page 6</span>
<span onclick="REQ.setPageProg(7,7)">page 7</span>
<br>
저장 진도율 : <span id="progress_measure">0.0</span>
저장 학습시간 : <span id="studyTime">0</span>

<div id="status">
</div>
<div id="console" class="scroll-y" style="height:100%; padding:5px 2px !important; box-sizing: border-box;">
    </div>