var COMMT = {  
	contextPath : '/license',
    enterCallFunc : function(event, func) {
        if (event.keyCode == 13) {
            if (typeof func === "function") {
                func.apply(this);
            }
        } else {
            return;
        }
    },  
    /**
     * map data 의 값을 targetForm의 input 객체로 값을 복사 한다.
     * mapdata = {"key1" : "value", "key2" : "value"}
     * targetForm에 키값과 같은 이름에  input 객체가 존재해야한다.
     */
    copyMapDataToForm : function(map, targetForm) {
        if (typeof map === "undefined" || typeof targetForm === "undefined") {
            return;
        }
        if (typeof targetForm === "string") {
            $target = jQuery("#" + targetForm);
        } else {
            $target = targetForm;
        }
        jQuery(":input", $target).each(function () {
            if (typeof map[this.name] !== "undefined" ) {
                var type = this.type.toLowerCase();
                if ((type === "radio" || type === "checkbox") && this.value == map[this.name]) {
                    this.checked = true;
                } else {
                    jQuery(this).val(map[this.name]);
                }
            }
        });
    },
    /**
     * map data 의 값을 targetForm의 input 객체로 값을 복사 한다.
     * mapdata = {"key1" : "value", "key2" : "value"}
     * 복사 전에 form을  reset 함 
     * targetForm에 키값과 같은 이름에  input 객체가 존재해야한다.
     */
    copyMapDataToFormReset : function(map, targetForm) {
        if (typeof map === "undefined" || typeof targetForm === "undefined") {
            return;
        }
        if (typeof targetForm === "string") {
            $target = jQuery("#" + targetForm);
            var f = document.getElementById(targetForm);
        	f.reset();
        } else {
            $target = targetForm;
            targetForm.reset();
        }
        
        jQuery(":input", $target).each(function () {
            if (typeof map[this.name] !== "undefined" ) {
                var type = this.type.toLowerCase();
                if ((type === "radio" || type === "checkbox") && this.value == map[this.name]) {
                    this.checked = true;
                } else {
                    jQuery(this).val(map[this.name]);
                }
            }
        });
    },
	/**
	 * window 에서 callback 함수 찾기
	 */
	getLayerCallback: function(win, strCallback) {
	    var callback = strCallback.split(".");
	    var fn = win;
	    var len = callback.length;
	    if (len > 0) {
	        for(var i = 0; i < len; i++) {
	            if (i == len - 1) {
	                fn = fn[callback[i]];
	            } else {
	                fn = fn[callback[i]];
	                if (typeof fn === "undefined") {
	                    break;
	                }
	            }
	        }
	    }
	    return fn;
	},
	/**
	 * 레이어 닫기
	 */
	callCloseLayer: function(win) {
	    setTimeout(function() {
	    	win.IMGLBObject.closeLayer();
	    }, 100);
	},
	/**
     *  form의 checkbox를 일괄처리   
     *  예) <input type="checkbox" title="전체선택" onclick="COMMT.formCheckboxAllProc(this.form,'checkIndexs',this.checked)">
     */
    formCheckboxAllProc : function(form, checkboxName, checked) {
        var $form = null;
        if (typeof form === "string") {
            $form = jQuery("#" + form);
        } else {
            $form = form;
        }
        jQuery(":checkbox[name='" + checkboxName + "']", $form).each(function(i) {
                this.checked = checked;
        });
    },
    /**
     * 에러 메시지
     */
    errorMessage: function() {
        $.dialog("alert", {message : '오류가 발생했습니다.'});
    },
    /**
     * 에러 메시지 코드
     */
    errorMessageCode: function(cd) {
        $.dialog("alert", {message : '오류가 발생했습니다.오류코드 : '+ cd});
    },
    /**
     *  메시지
     */
    message: function(msg , w) {
    	w = (typeof w === 'undefined')?300:w;
    	
        $.dialog("alert", {message : msg, width:w});
    },
    /**
    *이미지 미리보기
    */
 	previewImg : function(a, b, c, d) {
	  var f = a.value;
	  var g = f.substring(f.lastIndexOf('.') + 1).toLowerCase();
	  var h = document.getElementById(b);
	  if (!/(jpg|png|gif|jpeg)$/i.test(g)) {
	    alert('이미지파일 형식은 jpg, png, gif 만 등록 가능합니다.');
	    return;
	  }
	  while (h.firstChild) {
	    h.removeChild(h.firstChild);
	  }
	  if (window.FileReader) {
	    var i = b + "_img";
	    var j = new FileReader();
	    var k = "";
	    j.onload = function(e) {
	      if(c>0){
	    	k = "<img id='" + i + "' src='" + e.target.result + "' style='width:auto;height:" + d + "px;max-width:" + c + "px;' alt='이미지'/>";
	      }else{
	      	k = "<img id='" + i + "' src='" + e.target.result + "' style='width:auto;height:" + d + "px;' alt='이미지'/>";
	      }
	      document.getElementById(b).innerHTML = k;
	    };
	    j.readAsDataURL(a.files[0]);
	  } else {
	    a.select();
	    a.blur();
	    var l = document.selection.createRange().text;
	    h.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src='" + l + "')";
	  }
	},
	/**
    *  파일 업로드 체크
    */
 	chkAttachFile : function(fileId) {
 		if($("#"+fileId)[0].files[0] == null){
        	return false;
        }else{
        	return true;
        }
 	},
	/**
    *  파일 업로드 체크
    */
 	chkAttachFileSize : function(imgObj) {
 		 var defaults = {
		        maxSizeMB: 10, 
		        uploadFolder: "temp"
		 };
		 imgObj = $.extend(true, defaults, imgObj);
		  
         var uploadFolder =imgObj.uploadFolder;
         var fileInputId =imgObj.fileInputId;
		 var maxSize  = imgObj.maxSizeMB * 1024 * 1024;
         if($("#"+fileInputId)[0].files[0] == null){
	        	return false;
         }
         alert(imgObj.maxSizeMB);
         alert(maxSize);
         alert($("#"+fileInputId)[0].files[0].size);
	     if(maxSize<$("#"+fileInputId)[0].files[0].size){
	       	 	COMMT.message('첨부파일 사이즈는'+imgObj.maxSizeMB +'MB 이내로 등록 가능합니다.');
	        	return false;
	     }else{
	        	return true;
	     }
 	},
 	/**
 	* 이미지 파일 업로드 
 	*/
 	uploadImageFile  : function (imgObj,callback){
        var form = new FormData();
        
        var defaults = {
		        maxSizeMB: 10, 
		        uploadFolder: "temp"
		    };
		
		imgObj = $.extend(true, defaults, imgObj);
		
        var uploadFolder =imgObj.uploadFolder;
        var fileInputId =imgObj.fileInputId;
        
        if($("#"+fileInputId)[0].files[0] == null){
          //빈값 리턴한다.
        	var  resultData = {'result' : 999, 'atchFileId':''}
        	 if (typeof callback === "function") {
                   callback.call(this, resultData);
               }
        	
        	return;
        };
        
         var maxSize  = imgObj.maxSizeMB * 1024 * 1024;
        
        if(maxSize<$("#"+fileInputId)[0].files[0].size){
       	 	var  resultData = {'result' : -99, 'atchFileId':''}
        	 if (typeof callback === "function") {
                   callback.call(this, resultData);
               }
        	return;
        }
        
        
        form.append( "imFile", $("#"+fileInputId)[0].files[0] );
         jQuery.ajax({
             url : COMMT.contextPath+"/mng/common/image/save.do?uploadFolder="+uploadFolder
           , type : "POST"
           , processData : false
           , contentType : false
           , data : form
           , success:function(response,data) {
        	   /**
        	   if(response.result==1){
        		   alert(response.result);
        	   }
        	   **/
        	   if (typeof callback === "function") {
                   callback.call(this, JSON.parse(response));
               }
               console.log(response);
           }
           ,error: function (jqXHR) 
           { 
               alert(jqXHR.responseText); 
           }
       });
	},
	/**
 	* 일반파일 업로드 
 	*/
 	uploadCommonFile  : function (imgObj,callback){
        var form = new FormData();
        var defaults = {
		        maxSizeMB: 10, 
		        encryption: false, 
		        uploadFolder: "temp"
		    };
		
		imgObj = $.extend(true, defaults, imgObj);
		
        var uploadFolder =imgObj.uploadFolder;
        var fileInputId =imgObj.fileInputId;
        
        if($("#"+fileInputId)[0].files[0] == null){
          //빈값 리턴한다.
        	var  resultData = {'result' : 999, 'atchFileId':''}
        	 if (typeof callback === "function") {
                   callback.call(this, resultData);
               }
        	
        	return;
        };
        
         var maxSize  = imgObj.maxSizeMB * 1024 * 1024;
        
        if(maxSize<$("#"+fileInputId)[0].files[0].size){
       	 	var  resultData = {'result' : -99, 'atchFileId':''}
        	 if (typeof callback === "function") {
                   callback.call(this, resultData);
               }
        	return;
        }
        
        form.append( "imFile", $("#"+fileInputId)[0].files[0] );
        var saveUrl=COMMT.contextPath+"/mng/common/file/save.do?uploadFolder="+uploadFolder;
        
        if(imgObj.encryption){
        	saveUrl=COMMT.contextPath+"/mng/common/file/save.do?encryptionYn=Y&uploadFolder="+uploadFolder;
        }
        
         jQuery.ajax({
             url : saveUrl
           , type : "POST"
           , processData : false
           , contentType : false
           , data : form
           , success:function(response,data) {
        	   /**
        	   if(response.result==1){
        		   alert(response.result);
        	   }
        	   **/
        	   if (typeof callback === "function") {
                   callback.call(this, JSON.parse(response));
               }
               console.log(response);
           }
           ,error: function (jqXHR) 
           { 
               alert(jqXHR.responseText); 
           }
       });
	},
	 fn_egov_downFile : function (atchFileId, fileSn){
		window.open(COMMT.contextPath+"/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn);
	},
	 fn_egov_downFileEnc : function (atchFileIdEnc){
		window.location.href=COMMT.contextPath+"/cmmn/common/FileDown.do?atchFileId="+atchFileIdEnc;
	},
	fn_egov_downFileEnChkEncfile : function (atchFileIdEnc){
		window.location.href=COMMT.contextPath+"/cmmn/common/FileDownEnc.do?atchFileId="+atchFileIdEnc;
	},
	fn_egov_deleteFile : function(atchFileId, fileSn){
		
        var form = new FormData();
        
        form.append( "atchFileId", atchFileId);
        form.append( "fileSn", fileSn);
        
        jQuery.ajax({
             url : COMMT.contextPath+"/mng/common/file/delete.do"
           , type : "POST"
           , processData : false
           , contentType : false
           , data : form
           , success:function(response,data) {
        	   /**
        	   if(response.result==1){
        		   alert(response.result);
        	   }
        	   **/
        	  $("#__file_"+atchFileId+"_"+fileSn).remove();
               //console.log(response);
           }
           ,error: function (jqXHR) 
           { 
               alert(jqXHR.responseText); 
           }
       });
	
	},
	fn_egov_deleteFileUser : function(atchFileId, fileSn){
		
        var form = new FormData();
        
        form.append( "atchFileId", atchFileId);
        form.append( "fileSn", fileSn);
        
        jQuery.ajax({
             url : COMMT.contextPath+"/user/common/file/delete.do"
           , type : "POST"
           , processData : false
           , contentType : false
           , data : form
           , success:function(response,data) {
        	   /**
        	   if(response.result==1){
        		   alert(response.result);
        	   }
        	   **/
        	  $("#__file_"+atchFileId+"_"+fileSn).remove();
               //console.log(response);
           }
           ,error: function (jqXHR) 
           { 
               alert(jqXHR.responseText); 
           }
       });
	
	},
	/**
 	* 일반파일 업로드 
 	*/
 	uploadCommonFileUser  : function (imgObj,callback){
        var form = new FormData();
        var defaults = {
		        maxSizeMB: 10, 
		        encryption: false, 
		        uploadFolder: "temp"
		    };
		
		imgObj = $.extend(true, defaults, imgObj);
		
        var uploadFolder =imgObj.uploadFolder;
        var fileInputId =imgObj.fileInputId;
        
        if($("#"+fileInputId)[0].files[0] == null){
          //빈값 리턴한다.
        	var  resultData = {'result' : 999, 'atchFileId':''}
        	 if (typeof callback === "function") {
                   callback.call(this, resultData);
               }
        	
        	return;
        };
        
        var maxSize  = imgObj.maxSizeMB * 1024 * 1024;
        
        if(maxSize<$("#"+fileInputId)[0].files[0].size){
       	 	var  resultData = {'result' : -99, 'atchFileId':''}
        	 if (typeof callback === "function") {
                   callback.call(this, resultData);
               }
        	return;
        }
        
        
        form.append( "imFile", $("#"+fileInputId)[0].files[0] );
        var saveUrl=COMMT.contextPath+"/user/common/file/save.do?uploadFolder="+uploadFolder;
        
        if(imgObj.encryption){
        	saveUrl=COMMT.contextPath+"/user/common/file/save.do?encryptionYn=Y&uploadFolder="+uploadFolder;
        }
        
        jQuery.ajax({
             url : saveUrl
           , type : "POST"
           , processData : false
           , contentType : false
           , data : form
           , success:function(response,data) {
        	   /**
        	   if(response.result==1){
        		   alert(response.result);
        	   }
        	   **/
        	   if (typeof callback === "function") {
                   callback.call(this, JSON.parse(response));
               }
               console.log(response);
           }
           ,error: function (jqXHR) 
           { 
               alert(jqXHR.responseText); 
           }
       });
	},
 	/**
 	* 이미지 파일 업로드 
 	*/
 	uploadImageFileUser  : function (imgObj,callback){
        var form = new FormData();
		var defaults = {
		        maxSizeMB: 10, 
		        uploadFolder: "temp"
		    };
		
		imgObj = $.extend(true, defaults, imgObj);
        
        
        var uploadFolder =imgObj.uploadFolder;
        var fileInputId =imgObj.fileInputId;
        
        if($("#"+fileInputId)[0].files[0] == null){
          //빈값 리턴한다.
        	var  resultData = {'result' : 999, 'atchFileId':''}
        	 if (typeof callback === "function") {
                   callback.call(this, resultData);
               }
        	
        	return;
        };
        
        var maxSize  = imgObj.maxSizeMB * 1024 * 1024;
        
        if(maxSize<$("#"+fileInputId)[0].files[0].size){
       	 	var  resultData = {'result' : -99, 'atchFileId':''}
        	 if (typeof callback === "function") {
                   callback.call(this, resultData);
               }
        	return;
        }
        
        
        form.append( "imFile", $("#"+fileInputId)[0].files[0] );
         jQuery.ajax({
             url : COMMT.contextPath+"/user/common/image/save.do?uploadFolder="+uploadFolder
           , type : "POST"
           , processData : false
           , contentType : false
           , data : form
           , success:function(response,data) {
        	   /**
        	   if(response.result==1){
        		   alert(response.result);
        	   }
        	   **/
        	   if (typeof callback === "function") {
                   callback.call(this, JSON.parse(response));
               }
               console.log(response);
           }
           ,error: function (jqXHR) 
           { 
               alert(jqXHR.responseText); 
           }
       });
	},
	deleteFile : function(atchFileId, fileSn){
        $.dialog("confirm", {
        	message : '해당파일을 삭제하시겠습니까?',
        	button1 : {callback:function(){
        	COMMT.fn_egov_deleteFile(atchFileId, fileSn);
        	}}
        	});
	},
	setCookie : function (name, value, expires) {
    	document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
	},
 	getCookie:function(Name) {
	    var search = Name + "=";
	    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
	        offset = document.cookie.indexOf(search);
	        if (offset != -1) { // 쿠키가 존재하면
	            offset += search.length;
	            // set index of beginning of value
	            end = document.cookie.indexOf(";", offset);
	            // 쿠키 값의 마지막 위치 인덱스 번호 설정
	            if (end == -1)
	                end = document.cookie.length;
	            return unescape(document.cookie.substring(offset, end));
	        }
	    }
    return "";
	},
	copyToClipboard : function(val) {
      var t = document.createElement("textarea");
      document.body.appendChild(t);
      t.value = val;
      t.select();
      document.execCommand('copy');
      document.body.removeChild(t);
    },
      copyURL : function(url) {
		var domainhost = "http://"
		if (document.location.protocol != 'http:') {
			 domainhost = "https://";
		}
		domainhost= domainhost+window.location.host+url;
		COMMT.copyToClipboard(domainhost);
		
        $.dialog("alert", {message : "바로가기 주소가 복사되었습니다."});
    
        return; 
    },
      ready : function() {
		
        $.dialog("alert", {message : "준비중입니다."});
    
        return; 
    },fn_viewPefFileEnc : function (atchFileIdEnc){
		var fileUrl = COMMT.contextPath+"/cmmn/common/FileDown.do?atchFileId="+atchFileIdEnc;
		var pdfViewUrl = COMMT.contextPath+"/static/3rdparty/pdfjs-2.13.216-legacy-dist/web/viewerPdf.jsp?path="+fileUrl;
		//window.open(pdfViewUrl);
		var req = imRequest("layer");
		req.cfg.url    = pdfViewUrl;
		req.cfg.options.title="PDF 파일";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=820;
		req.go();
	},
	fn_viewPefFileEncChkEncfile : function (atchFileIdEnc){
		var fileUrl = COMMT.contextPath+"/cmmn/common/FileDownEnc.do?atchFileId="+atchFileIdEnc;
		var pdfViewUrl = COMMT.contextPath+"/static/3rdparty/pdfjs-2.13.216-legacy-dist/web/viewerPdf.jsp?path="+fileUrl;
		//window.open(pdfViewUrl);
		var req = imRequest("layer");
		req.cfg.url    = pdfViewUrl;
		req.cfg.options.title="PDF 파일";
		req.cfg.options.scroll="hidden";
		req.cfg.options.width=600;
		req.cfg.options.height=820;
		req.go();
	},
	fn_Enc_Print : function (url,cw,ch){
			//cw = 1260;
			//스크린의 크기
			sw = screen.availWidth;
			sh = screen.availHeight;
			//ch = 850;
			//열 창의 포지션
			winPosLeft = (sw - cw) / 2;
			winPosTop = 10;
			window.open(url, 'popWinC', 'width=' + cw + ',height=' + ch + ',top=' + winPosTop + ',left=' + winPosLeft
					+ ',toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no');
		
	}
}