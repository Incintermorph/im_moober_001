/**
 * jquery.imui.request.js
 * author : sungyong.noh@gmail.com
 * created : 2022.02.11
 * modified :2022.02.11
 *  version : "1.0.0"
 *          - jquery 1.9.1 이상 버전 사용
 */
var IMGLBObject = {
    loadingbar : {
        styleClass : "loading",
        loader : null,
        icon : null
    },
    parameters : "", // 공통파라미터
    runningAction : null, 
    validator : null
};

(function($) {
    var publics = {
        getTopWindow : function() {
            var topWindow = self;
            while(topWindow.parent && topWindow.parent != topWindow) {
                try {
                    if (topWindow.parent.document.domain != document.domain) {
                        break;
                    }
                    if (topWindow.parent.document.getElementsByTagName("frameset").length > 0 ) {
                        break;
                    }
                } catch(e) {
                    break;
                }
                topWindow = topWindow.parent;
            }
            return topWindow;
        },
        ieObject : [],
        visibleIeObject : function(style) {
            var ua = window.navigator.userAgent;
            if (ua.indexOf("MSIE ") > 0 || ua.indexOf("Trident/") > 0) { // ie11 일경우 포함.
                if (style == "visible") {
                    for (var index = 0; index < publics.ieObject.length; index++) {
                        publics.ieObject[index].css("visibility", style);
                    }
                    publics.ieObject = [];
                } else if (style == "hidden") {
                    $("object").each(function() {
                        var $obj = $(this);
                        if ($obj.is(":visible")) {
                            $obj.css("visibility", style);
                            publics.ieObject.push($obj);
                        }
                    });
                    $("iframe").each(function() {
                        try {
                            var $iframeBody = $(this.contentWindow.document.body);
                            $iframeBody.find("object,iframe").each(function() {
                                var $obj = $(this);
                                if ($obj.is(":visible")) {
                                    $obj.css("visibility", style);
                                    publics.ieObject.push($obj);
                                }
                            });
                        } catch (e) {
                            // iframe src가 타 사이트일경우 access가 안된다.
                            var $obj = $(this);
                            if ($obj.is(":visible")) {
                                $obj.css("visibility", style);
                                publics.ieObject.push($obj);
                            }
                        }
                    });
                }
            }
        }
    };
    $.dialog = function(type, options) {
        var topWin = publics.getTopWindow();
        var $ = topWin.$;
        var kendo = topWin.kendo;
        var id = (new Date()).getTime();

        options = options || {};
        var text = {
            confirm: {
                title: IM_BUNDLE["Imui:Dialog:Confirm:Title"], // "확인"
                button1: IM_BUNDLE["Imui:Dialog:Confirm:Button:Ok"], // "확인"
                button2: IM_BUNDLE["Imui:Dialog:Confirm:Button:Cancel"] // "취소"
            },
            alert: {
                title: IM_BUNDLE["Imui:Dialog:Alert:Title"], // "알림"
                button1: IM_BUNDLE["Imui:Dialog:Alert:Button:Ok"] // "확인"
            },
            info: {
                title: IM_BUNDLE["Imui:Dialog:Info:Title"] // "알림"
            },
            layer: {
                title: options.title
            },
            loading: {
                title: IM_BUNDLE["Imui:Dialog:Confirm:Title"]
            }
        };
        
        publics.visibleIeObject("hidden"); // ie 일때 object나 iframe을 hidden 시킨다. 레이어가 뒤로 숨는 경우를 방지.
        
        var defaults = {
            title: (text[type].title || " "),
            modal: true,
            draggable : true,
            autoFocus: true,
            resizable: false,
            animation: false,
            width: 300,
            height: "auto",
            actions: ["Close"],
            hideTitlebar: false // custom option 타이틀바 감추기
        };
        
        var template = [];
        template.push("<div id='" + id + "'>");
        switch(type) {
        case "alert":
            if (typeof options.template === "string" && options.template != "") {
                template.push(options.template); // 템플릿 제작 : 메시지 영역에 #: message # 과 button 영역에 class='button1' 이 반드시 존재해야 한다. 
            } else {
                template.push("<div>#: message #</div>");
                template.push("<div style='text-align:center; margin-top:10px;'>");
                template.push("<button type='button' class='k-button button1'>" + text[type].button1 + "</button>");
                template.push("</div>");
            }
            break;
        case "confirm":
            if (typeof options.template === "string" && options.template != "") {
                template.push(options.template); // 템플릿 제작 : 메시지 영역에 #: message # 과 button 영역에 class='button1' 과 class='button2' 가 반드시 존재해야 한다. 
            } else {
                template.push("<div>#: message #</div>");
                template.push("<div style='text-align:center; margin-top:10px;'>");
                template.push("<button type='button' class='k-button button1'>" + text[type].button1 + "</button>");
                template.push(" ");
                template.push("<button type='button' class='k-button button2'>" + text[type].button2 + "</button>");
                template.push("</div>");
            }
            break;
        case "info":
            if (typeof options.template === "string" && options.template != "") {
                template.push(options.template); // 템플릿 제작 : 메시지 영역에 #: message # 이 반드시 존재해야 한다. 
            } else {
                template.push("<div>#: message #</div>");
            }
            break;
        case "layer":
            break;
        case "loading":
            if (typeof options.template === "string" && options.template != "") {
                template.push(options.template); // 템플릿 제작 : 메시지 영역에 #: message # 이 반드시 존재해야 한다. 
            } else {
                template.push("<div style='text-align:center; padding:10px;'>#: message #</div>");
            }
            defaults.hideTitlebar = true;
            break;
        }
        template.push("</div>");
        var html = template.join("");
        $(html).appendTo(topWin.document.body);

        var dialog = null;
        if (type == "layer") {
            dialog = $("#" + id).kendoWindow({
                visible: false,
                content: (typeof options.content === "string" ? options.content : "about:blank"),
                iframe: true
            }).data("kendoWindow");
        } else {
            alert(id);
            dialog = $("#" + id).kendoWindow({
                visible: false,
                content: {
                    template: kendo.render(kendo.template(html), [{message: (options.message || "")}])
                },
                minHeight: 0,
                iframe: false
            }).data("kendoWindow");
        }
        
        dialog.parent = self; // iframe에서 parent를 찾기 위하여 넣는다
        if (type === "layer") {
            dialog.$iframe = $("#" + id).find("iframe");
            dialog.$iframe.attr({
                name: id + "-iframe",
                id: id + "-iframe",
                webkitallowfullscreen:"",
				mozallowfullscreen:"",
				allowfullscreen:""
            });
            var $parent = dialog.$iframe.parent();
            var $clone = dialog.$iframe.clone();
            dialog.$iframe.remove();
            $parent.append($clone);
            dialog.$iframe = $("#" + id).find("iframe");
            
            dialog.$iframe.each(function() {
                var $this = $(this);
                var iframe = null;
                for(var x = 0; x < topWin.window.length; x++) {
                    try {
                        if (this.name == topWin[x].name) {
                            iframe = topWin[x];
                        }
                    } catch (e) {  
                        // iframe src가 타 사이트일경우 access가 안된다.
                    }
                }
                $this.load(function(event) {
                    iframe.$layer = dialog; // iframe 내에 $layer 변수에 dialog를 기억시킨다. $layer.close(); 를 $layer.parent 를 이용하여 parent와 소통한다
                });
                $this.unload(function(event) { // ie에서 플레시가 있을경우 레이어 닫힐 때 에러 나는 문제 해결.
                    jQuery(this.document.body).find("object").parent().empty();
                });
            });
        }
        
        defaults.open = function() {
            if (options.hideTitlebar === true) {
            	if (type != "loading") {
            		$("#" + id).closest(".k-window").addClass("design");
            	}
                $("#" + id).closest(".k-window").find(".k-window-titlebar").hide();
                $("#" + id).closest(".k-window").css("paddingTop", "0");
            }
        };
        defaults.close = function() {
            publics.visibleIeObject("visible");
            dialog.destroy();
            if (typeof options.callback === "function") {
                options.callback.call(this); 
            }
        };
        options = $.extend(true, defaults, options);

        dialog.setOptions(options);
        $("#" + id).find(".button1").click(function() {
            dialog.close();
            if (typeof options.button1 === "object" && typeof options.button1.callback === "function") {
                options.button1.callback.call(this); 
            }
        });
        $("#" + id).find(".button2").click(function() {
            dialog.close();
            if (typeof options.button2 === "object" && typeof options.button2.callback === "function") {
                options.button2.callback.call(this); 
            }
        });
        if (type !== "layer") {
				dialog.center();
		}else{
			if(typeof options.position.top === 'undefined' && typeof options.position.left === 'undefined' ){
				dialog.center();	
			}
		}        
        dialog.open();
        
        return dialog;
    };

    $.loadingIcon = function(containerId, img) {
        var $icon = null;
        if (typeof containerId === "string" && containerId.length > 0) {
            if (typeof img === "string") {
                $icon = $(img);
            } else {
                $icon = $("<div class='" + IMGLBObject.loadingbar.styleClass + "'></div>");
            }
            $("#" + containerId).html($icon);
        } else {
            $icon = typeof img === "string" ? $(img) : $("<div class='" + IMGLBObject.loadingbar.styleClass + "'></div>");
            $icon.css({
                position : "fixed",
                top : "45%",
                left : "47%",
                zIndex : "100"
            });
            $icon.appendTo(document.body);
        }
        return $icon;
    };

})(jQuery);


(function($) {
    var methods = {
        create : function(requestStyle, options) {
            var defualts = {
                "normal" : {
                    url : "",
                    parameters : "",
                    formId : "",
                    secure : "",
                    target : "_self", /* target이 iframe이면 iframe name */
                    fn : { 
                        validate : null, /* function (boolean) */
                        before : null /* function (boolean) */
                    }
                },
                "submit" : {
                    url : "",
                    parameters : "",
                    formId : "",
                    secure : "",
                    target : "_self", /* target이 iframe이면 iframe name */
                    fn : { 
                        validate : null, /* function (boolean) */
                        before : null, /* function (boolean) */
                        complete : null
                    }
                },
                "ajax" : {
                    url : "",
                    parameters : "",
                    formId : "",
                    secure : "",
                    type : "html", /* html, xml, request, json, script (url에 callback=?이포함되면 jsonp가됨,jsonp는 get 방식만 됨.) */
                    containerId : "",
                    asynchronous : false,
                    loadingIcon : null,
                    method : "post",
                    update : true,
                    onErrorAlert : false,
                    fn : { 
                        validate : null, /* function (boolean) */
                        before : null, /* function (boolean) */
                        complete : null
                    }
                },
                "popup" : {
                    url : "",
                    parameters : "",
                    formId : "",
                    secure : "",
                    winname : "",
                    popupWindow : null,
                    options : {width : 500, height : 500, position : "center" },
                    fn : { 
                        validate : null, /* function (boolean) */
                        before : null, /* function (boolean) */
                        complete : null
                    }
                },
                "layer" : {
                    url : "",
                    parameters : "",
                    formId : "",
                    secure : "",
                    options : {width : 500, height : 500, position : "center", title : "" },
                    fn : {
                        validate : null, /* function (boolean) */
                        before : null, /* function (boolean) */
                        complete : null
                    }
                },
                "script" : {
                    fn : { 
                        validate : null, /* function (boolean) */
                        exec : null
                    }
                }
            };            
            var _action = function () {
                var publics = {
                    cfg : {
                        loadingbarShow : true,
                        loadingDialog : null,
                        message : {
                            confirm : "", 
                            success : "",
                            waiting : "",
                            width : 300
                        },
                        formEmpty : false
                    },    
                    waiting : false,
                    validator : null,
                    status : function(status, target) {
                        var _super = this;
                        switch(status) {
                        case "start":
                            _super.waiting = true;
                            if (_super.cfg.loadingbarShow == true) {
                                switch(_super.cfg.requestStyle) {
                                case "submit":
                                case "layer":
                                    if (typeof _super.cfg.message.waiting === "string" && _super.cfg.message.waiting != "") {
                                        _super.cfg.loadingDialog = $.dialog("loading", {message : _super.cfg.message.waiting});
                                    }
                                    break;
                                case "ajax":
                                    if (typeof _super.cfg.message.waiting === "string" && _super.cfg.message.waiting != "") {
                                        _super.cfg.loadingDialog = $.dialog("loading", {message : _super.cfg.message.waiting});
                                    } else if (_super.cfg.update == true && _super.cfg.containerId != "") {
                                        _super.cfg.loadingIcon = $.loadingIcon(_super.cfg.containerId);
                                    } else {
                                        IMGLBObject.loadingbar.icon = $.loadingIcon();
                                    }
                                    break;
                                }
                            }
                            if (typeof target !== "undefined") {
                                setTimeout(function() {
                                    if (target == "_self" || target == "_top" || target == "_blank" || target == "_new" || target == "_parent") {
                                        _super.status("end");
                                    } else {
                                        var $target = $("#" + target);
                                        if ($target.length == 0) {
                                            $target = $("[name='" + target + "']");
                                        }
                                        if ($target.length != 1) {
                                            alert("target '" + target + "' is unclear");
                                        } else if ($target.length == 1) {
                                            $target.bind("load", function(event) {
                                                $target.unbind("load");
                                                _super.status("end");
                                            });
                                        }
                                    }
                                }, 10);
                            }
                            break;
                        case "end":
                            _super.waiting = false;
                            if (_super.cfg.loadingDialog != null) {
                                _super.cfg.loadingDialog.close();
                            }
                            if (_super.cfg.loadingIcon != null) {
                                _super.cfg.loadingIcon.remove();
                                _super.cfg.loadingIcon = null;
                            }
                            break;
                        }
                    },
                    go : function() {
                        var _super = this;
                        switch(arguments[0]) {
                        case "complete":
                            _super.status("end");
                            IMGLBObject.runningAction = null;
                            var args = Array.prototype.slice.call(arguments, 1);
                            if (_super.cfg.message.success != "") {
                                if (typeof _super.cfg.fn.complete === "function") {
                                    $.dialog("alert", {
                                        message : _super.cfg.message.success,
                                        button1 : {
                                            callback : function() {
                                                _super.cfg.fn.complete.apply(this, args);
                                            }
                                        },
                                        width : _super.cfg.message.width
                                    });
                                } else {
                                    $.dialog("alert", {
                                        message : _super.cfg.message.success,
                                        width : _super.cfg.message.width
                                    });
                                }
                            } else {
                                if (typeof _super.cfg.fn.complete === "function") {
                                    _super.cfg.fn.complete.apply(this, args);
                                }
                            }
                            break;
                        case "continue":
                            IMGLBObject.runningAction = this;
                            // run
                            switch(_super.cfg.requestStyle) {
                            case "normal":
                                if (_super.requireConfig(["url", "target"]) == false) {
                                    return;
                                }
                                _super.status("start", _super.cfg.target);
                                if (_super.cfg.formId == "") {
                                    var url = _super.getUrl(_super.cfg.url, _super.cfg.parameters, IMGLBObject.parameters);
                                    if (_super.cfg.target == "_top") {
                                        window.top.location.href = url;
                                    } else if (_super.cfg.target == "_self") {
                                        self.location.href = url;
                                    } else {
                                        window[_super.cfg.target].location.href = url;
                                    }
                                } else {
                                    var $form = $("#" + _super.cfg.formId);
                                    _super.appendParameters($form, _super.cfg.parameters, IMGLBObject.parameters);

                                    var form = $form.get(0);
                                    form.action = _super.cfg.url;
                                    form.target = _super.cfg.target;
                                    form.submit();
                                }
                                _super.status("end");
                                break;
                            case "submit":
                                if (_super.requireConfig(["url", "formId", "target"]) == false) {
                                    return;
                                }
                                var $form = $("#" + _super.cfg.formId);
                                _super.appendParameters($form, _super.cfg.parameters, IMGLBObject.parameters);
                                
                                var form = $form.get(0);
                                form.action = _super.cfg.url;
                                form.target = _super.cfg.target;
                                form.method = "post";
                                form.submit();
                                _super.status("start", _super.cfg.target);
                                break;
                            case "ajax":
                                if (_super.requireConfig(["url", "type", "method"]) == false) {
                                    return;
                                }
                                if (_super.cfg.type == "html" && _super.requireConfig(["containerId"]) == false) {
                                    return;
                                }
                                if (_super.requireFn(["complete"]) == false) {
                                    return;
                                }
                                
                                var params = "";
                                if (_super.cfg.formId == "") {
                                    if (typeof _super.cfg.parameters === "string" && _super.cfg.parameters.length > 0) {
                                        params += _super.encodingParameters(_super.cfg.parameters);
                                    }
                                    if (typeof IMGLBObject.parameters === "string" && IMGLBObject.parameters.length > 0) {
                                        if (params.length > 0) {
                                            params += "&";
                                        }
                                        params += _super.encodingParameters(IMGLBObject.parameters);
                                    }
                                } else {
                                    var $form = $("#" + _super.cfg.formId);
                                    params = $form.serialize();
                                    if (typeof _super.cfg.parameters === "string" && _super.cfg.parameters.length > 0) {
                                        if (params.length > 0) {
                                            params += "&";
                                        }
                                        params += _super.encodingParameters(_super.cfg.parameters);
                                    }
                                    if (typeof IMGLBObject.parameters === "string" && IMGLBObject.parameters.length > 0) {
                                        if (params.length > 0) {
                                            params += "&";
                                        }
                                        params += _super.encodingParameters(IMGLBObject.parameters);
                                    }
                                }
                                var _super = this;
                                $("#" + _super.cfg.containerId).show();
                                _super.status("start");
                                $.ajax({
                                    url : _super.cfg.url,
                                    data : params,
                                    type : _super.cfg.method.toUpperCase(),
                                    dataType : _super.cfg.type == "request" ? "html" : _super.cfg.type,
                                    cache : false,
                                    async : _super.cfg.asynchronous,
                                    error : function(req, status, error) {
                                        _super.status("end");
                                        if (_super.cfg.onErrorAlert == true) {
                                            alert("[" + req.statusText + "] : " + error);
                                        }
                                        if (typeof _super.cfg.fn.complete === "function") {
                                            _super.cfg.fn.complete.call(this, _super, null, {"error" : error});
                                        }
                                    },
                                    success : function(data, textStatus, jqXHR) {
                                        _super.status("end");
                                        if (_super.cfg.type == "html" && _super.cfg.update == true) {
                                            $("#" + _super.cfg.containerId).html(data);
                                        }
                                        if (typeof _super.cfg.fn.complete === "function") {
                                            _super.cfg.fn.complete.call(this, _super, data);
                                        }
                                    }
                                });
                                break;
                            case "popup":
                                if (_super.requireConfig(["url"]) == false) {
                                    return;
                                }
                                if (_super.requireOption(["width", "height", "position"]) == false) {
                                    return;
                                }
                                var posX = 0;
                                var posY = 0;
                                var height = _super.cfg.options.height;
                                var width = _super.cfg.options.width;
                                switch(_super.cfg.options.position.toUpperCase()) {
                                case "LEFTTOP":
                                    posX = 0;
                                    posY = 0;
                                    break;
                                case "LEFTMIDDLE":
                                    posX = 0;
                                    posY = Math.ceil((window.screen.height - height) / 2);
                                    break;
                                case "LEFTBOTTOM":
                                    posX = 0;
                                    posY = Math.ceil(window.screen.height - height);
                                    break;
                                case "RIGHTTOP":
                                    posX = Math.ceil(window.screen.width - width);
                                    posY = 0;
                                    break;
                                case "RIGHTMIDDLE":
                                    posX = Math.ceil(window.screen.width - width);
                                    posY = Math.ceil((window.screen.height - height) / 2);
                                    break;
                                case "RIGHTBOTTOM":
                                    posX = Math.ceil(window.screen.width - width);
                                    posY = Math.ceil(window.screen.height - height);
                                    break;
                                case "CUSTOM":
                                    posX = _super.cfg.options.left;
                                    posY = _super.cfg.options.top;
                                    break;
                                case "CENTER":
                                default:
                                    posX = Math.ceil((window.screen.width - width) / 2);
                                    posY = Math.ceil((window.screen.height - height) / 2);
                                    break;
                                }
                                var options = {
                                    left : posX,
                                    top : posY,
                                    width : width,
                                    height : height
                                };
                                _super.cfg.options = $.extend(true, _super.cfg.options, options);
                                var options = "";
                                for (var opt in _super.cfg.options) {
                                    if (options.length > 0) {
                                        options += ",";
                                    }
                                    options += opt + "=" + _super.cfg.options[opt];
                                }
                                _super.status("start");
                                var winname = _super.cfg.winname == "" ? (new Date()).getTime() : _super.cfg.winname;
                                if (_super.cfg.formId == "") {
                                    var url = _super.getUrl(_super.cfg.url, _super.cfg.parameters, IMGLBObject.parameters);
                                    _super.cfg.popupWindow = window.open(url, winname, options);
                                    _super.cfg.popupWindow.focus();
                                } else {
                                    _super.cfg.popupWindow = window.open("", winname, options);
                                    var $form = $("#" + _super.cfg.formId);
                                    _super.appendParameters($form, _super.cfg.parameters, IMGLBObject.parameters);
                                    
                                    var form = $form.get(0);
                                    form.action = _super.cfg.url;
                                    form.target = winname;
                                    form.submit();
                                    _super.cfg.popupWindow.focus();
                                }
                                _super.status("end");
                                _super.go("complete");
                                break;
                            case "layer":
                                if (_super.requireConfig(["url"]) == false) {
                                    return;
                                }
                                if (_super.requireOption(["width", "height", "position"]) == false) {
                                    return;
                                }
                                _super.$layer = $.dialog("layer", _super.cfg.options);
                                _super.status("start");
                                if (_super.cfg.formId == "") {
                                    var url = _super.getUrl(_super.cfg.url, _super.cfg.parameters, IMGLBObject.parameters);
                                    _super.$layer.$iframe.attr("src", url);
                                } else {
                                    var $form = $("#" + _super.cfg.formId);
                                    _super.appendParameters($form, _super.cfg.parameters, IMGLBObject.parameters);
                                    
                                    var form = $form.get(0);
                                    form.action = _super.cfg.url;
                                    form.target = _super.$layer.$iframe.attr("name");
                                    form.submit();
                                }
                                _super.status("end");
                                _super.go("complete");
                                break;
                            case "script":
                                if (typeof _super.cfg.fn.exec === "function") {
                                    _super.status("start");
                                    _super.cfg.fn.exec.call(this, this);
                                    _super.status("end");
                                } else {
                                    alert("undefined action.cfg.fn.exec");
                                }
                                break;
                            }
                            break;
                        default:
                            if (_super.cfg.formEmpty == true) {
                                $("#" + _super.cfg.formId).find(":input").remove();
                            }
                            if (_super.waiting == true) {
                                if (typeof _super.cfg.message.waiting === "string" && _super.cfg.message.waiting != "") {
                                    if (typeof _super.cfg.asynchronous === "undefined" || _super.cfg.asynchronous !== true) {
                                        $.dialog("alert", {message : _super.cfg.message.waiting});
                                    }
                                }
                                return;
                            }
                            if (typeof _super.cfg.fn.validate === "function") {
                                if (_super.cfg.fn.validate.call(this, _super) == false) {
                                    return;
                                }
                            } else {
                                if (_super.validator != null && _super.validator.isValid() == false) {
                                    return;
                                }
                            }
                            if (typeof _super.cfg.message.confirm === "string" && _super.cfg.message.confirm != "") {
                                $.dialog("confirm", {
                                    message : _super.cfg.message.confirm,
                                    button1  : {
                                        callback : function() {
                                            if (typeof _super.cfg.fn.before === "function") {
                                                var returnValue = _super.cfg.fn.before.call(this, _super);
                                                if (true == returnValue) {
                                                    _super.go("continue");
                                                }
                                            } else {
                                                _super.go("continue");
                                            }
                                        }
                                    },
                                    button2  : {
                                        callback : function() {
                                            return;
                                        }
                                    },
                                    width : _super.cfg.message.width
                                });
                            } else {
                                if (typeof _super.cfg.fn.before === "function") {
                                    var returnValue = _super.cfg.fn.before.call(this, _super);
                                    if (true == returnValue) {
                                        _super.go("continue");
                                    }
                                } else {
                                    _super.go("continue");
                                }
                            }
                            break;
                        }
                        return;
                    },
                    getUrl : function(url, parameters, commonParameters) {
                        if (typeof parameters === "string" && parameters.length > 0) {
                            url += url.indexOf("?") == -1 ? "?" : "&";
                            url += this.encodingParameters(parameters);
                        }
                        if (typeof commonParameters === "string" && commonParameters.length > 0) {
                            url += url.indexOf("?") == -1 ? "?" : "&";
                            url += this.encodingParameters(commonParameters);
                        }
                        
                        return url;
                    },
                    appendParameters : function($form, parameters, commonParameters) {
                        if (typeof parameters === "string" && parameters.length > 0) {
                            var params = parameters.split("&");
                            for (var index = 0; index < params.length; index++) {
                                var pair = params[index].split("=");
                                var $field = $form.find(":input").filter(function() {
                                    return this.name == pair[0];
                                });
                                if (pair.length == 2 && $field.length == 0) {
                                    $("<input type='hidden' name='" + decodeURIComponent(pair[0]) + "' value='" + decodeURIComponent(pair[1]) + "'>").appendTo($form);
                                }
                            }
                        }
                        if (typeof commonParameters === "string" && commonParameters.length > 0) {
                            var params = commonParameters.split("&");
                            for (var index = 0; index < params.length; index++) {
                                var pair = params[index].split("=");
                                var $field = $form.find(":input").filter(function() {
                                    return this.name == pair[0];
                                });
                                if (pair.length == 2 && $field.length == 0) {
                                    $("<input type='hidden' name='" + decodeURIComponent(pair[0]) + "' value='" + decodeURIComponent(pair[1]) + "'>").appendTo($form);
                                }
                            }
                        }
                    },
                    encodingParameters : function(parameters) {
                        var encoding = [];
                        var params = parameters.split("&");
                        for (var index = 0; index < params.length; index++) {
                            var pair = params[index].split("=");
                            if (pair.length == 2) {
                                encoding.push(encodeURIComponent(decodeURIComponent(pair[0])) + "=" + encodeURIComponent(decodeURIComponent(pair[1])));
                            }
                        }
                        return encoding.join("&");
                    },
                    toString : function() {
                        var indent = "    "; 
                        var str = "";
                        str += "config : { \n";
                        for (var config in this.cfg) {
                            if (typeof this.cfg[config] === "object") {
                                str += indent + config + " : { \n";
                                for (var obj in this.cfg[config]) {
                                    str += (indent + indent + obj + " : " + this.cfg[config][obj] + "\n");
                                }
                                str += indent + "}\n";
                            } else {
                                str += (indent + config + " : " + this.cfg[config] + "\n");
                            }
                        }
                        str += "}\n";
                        return str;
                    },
                    requireConfig : function(arrConfigs) {
                        for (var config = 0; config < arrConfigs.length; config++) {
                            if (typeof this.cfg[arrConfigs[config]] === "undefined" || this.cfg[arrConfigs[config]] == null || this.cfg[arrConfigs[config]] == "") {
                                alert("undefined action.cfg." + arrConfigs[config]);
                                return false;
                            }
                        }
                        return true;
                    },
                    requireFn : function(arrFns) {
                        for (var fn = 0; fn < arrFns.length; fn++) {
                            if (typeof this.cfg.fn[arrFns[fn]] === "undefined" || this.cfg.fn[arrFns[fn]] == null || typeof this.cfg.fn[arrFns[fn]] !== "function") {
                                alert("undefined action.cfg.fn." + arrFns[fn]);
                                return false;
                            }
                        }
                        return true;
                    },
                    requireOption : function(arrOptions) {
                        for (var opt = 0; opt < arrOptions.length; opt++) {
                            if (typeof this.cfg.options[arrOptions[opt]] === "undefined" || this.cfg.options[arrOptions[opt]] == null || typeof this.cfg.options[arrOptions[opt]] == "") {
                                alert("undefined action.cfg.options." + arrOptions[opt]);
                                return false;
                            }
                        }
                        return true;
                    }
                    
                }
                return publics;
            };

            if (typeof requestStyle === "undefined") {
                requestStyle = "normal";
            }
            if (typeof defualts[requestStyle] === "undefined") {
                return null;
            } else {
                var _action = new _action();
                options = $.extend(true, {requestStyle : requestStyle}, options);
                options = $.extend(true, _action.cfg, options);
                _action.cfg = $.extend(true, defualts[requestStyle.toLowerCase()], options);
                if (typeof _action.cfg.formId !== "undefined" && _action.cfg.formId != "") {
                    _action.validator = new $.validator(_action.cfg.formId);
                }
                return _action;
            }
        }
    };
    imRequest = function(requestStyle, options) {
         
        var _action = methods.create.apply(this, arguments);
        if (_action == null) {
            alert("requestStyle " + requestStyle + " does not exist on jQuery.action");
            return null;
        }
        return _action;
    };

})(jQuery);

(function($) {
    var Validator = {
        defaults : {
        },
        messages : {
            "eq" : IM_BUNDLE["Imui:Validator:Eq"], // "{_title_}{_postword_} {_value_}으로 {_verb_}"
            "le" : IM_BUNDLE["Imui:Validator:Le"], // "{_title_}{_postword_} {_value_}보다 작거나 같은 값으로 {_verb_}"
            "ge" : IM_BUNDLE["Imui:Validator:Ge"], // "{_title_}{_postword_} {_value_}보다 크거나 같은 값으로 {_verb_}"
            "lt" : IM_BUNDLE["Imui:Validator:Lt"], // "{_title_}{_postword_} {_value_}보다 작은 값으로 {_verb_}"
            "gt" : IM_BUNDLE["Imui:Validator:Gt"], // "{_title_}{_postword_} {_value_}보다 큰 값으로 {_verb_}"
            "maxlength" : IM_BUNDLE["Imui:Validator:Maxlength"], // "{_title_}{_postword_} 최대길이[{_value_}자] 이하로 {_verb_}"
            "minlength" : IM_BUNDLE["Imui:Validator:Minlength"], // "{_title_}{_postword_} 최소길이[{_value_}자] 이상으로 {_verb_}"
            "fixlength" : IM_BUNDLE["Imui:Validator:Fixlength"], // "{_title_}{_postword_} 길이[{_value_}자]로 {_verb_}"
            "maxbyte" : IM_BUNDLE["Imui:Validator:Maxbyte"], // "{_title_}{_postword_} 최대[{_value_}bytes] 이하로 {_verb_}"
            "minbyte" : IM_BUNDLE["Imui:Validator:Minbyte"], // "{_title_}{_postword_} 최소[{_value_}bytes] 이상으로 {_verb_}"
            "!space" : IM_BUNDLE["Imui:Validator:!Space"], // "{_title_}{_postword_} 공백문자를 허용하지 않습니다."
            "!null" : IM_BUNDLE["Imui:Validator:!Null"], // "{_title_}{_postword_} {_verb_}"
            "!tag" : IM_BUNDLE["Imui:Validator:!Tag"], // "{_title_}{_postword_} tag를 허용하지 않습니다"
            "!chars" : IM_BUNDLE["Imui:Validator:!Chars"], // "{_title_}{_postword_} [{_value_}] 문자를 허용하지 않습니다."
            "ssn" : IM_BUNDLE["Imui:Validator:Ssn"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "frn" : IM_BUNDLE["Imui:Validator:Frn"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "email" : IM_BUNDLE["Imui:Validator:Email"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "url" : IM_BUNDLE["Imui:Validator:Url"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "ip" : IM_BUNDLE["Imui:Validator:Ip"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "date" :IM_BUNDLE["Imui:Validator:Date"], // "{_title_}{_postword_} [{_value_}] 형식이 정확하지 않습니다."
            "regex" :IM_BUNDLE["Imui:Validator:Regex"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "!regex" :IM_BUNDLE["Imui:Validator:!Regex"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "alphabet" :IM_BUNDLE["Imui:Validator:Alphabet"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "hangul" : IM_BUNDLE["Imui:Validator:Hangul"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "number" :IM_BUNDLE["Imui:Validator:Number"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "signnumber" :IM_BUNDLE["Imui:Validator:Signnumber"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "hypennumber" :IM_BUNDLE["Imui:Validator:Hypennumber"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "commanumber" :IM_BUNDLE["Imui:Validator:Commanumber"], // "{_title_}{_postword_} 형식이 정확하지 않습니다."
            "decimalnumber" :IM_BUNDLE["Imui:Validator:Decimalnumber"] // "{_title_}{_postword_} 형식이 정확하지 않습니다."
        },
        verbs : {
            "select" : IM_BUNDLE["Imui:Validator:Select"], // "선택하십시오."
            "enter" : IM_BUNDLE["Imui:Validator:Enter"] // "입력하십시오."
        },
        postwords : {
             "!space"        : {word1 : "은", word2 : "는"}
            ,"!null"         : {word1 : "을", word2 : "를"}
            ,"!tag"          : {word1 : "은", word2 : "는"}
            ,"!chars"        : {word1 : "은", word2 : "는"}
            ,"maxlength"     : {word1 : "을", word2 : "를"}
            ,"minlength"     : {word1 : "을", word2 : "를"}
            ,"fixlength"     : {word1 : "을", word2 : "를"}
            ,"maxbyte"       : {word1 : "을", word2 : "를"}
            ,"minbyte"       : {word1 : "을", word2 : "를"}
            ,"eq"            : {word1 : "을", word2 : "를"}
            ,"le"            : {word1 : "을", word2 : "를"}
            ,"ge"            : {word1 : "을", word2 : "를"}
            ,"lt"            : {word1 : "을", word2 : "를"}
            ,"gt"            : {word1 : "을", word2 : "를"}
            ,"ssn"           : {word1 : "의", word2 : "의"}
            ,"frn"           : {word1 : "의", word2 : "의"}
            ,"email"         : {word1 : "의", word2 : "의"}
            ,"url"           : {word1 : "의", word2 : "의"}
            ,"ip"            : {word1 : "의", word2 : "의"}
            ,"date"          : {word1 : "의", word2 : "의"}
            ,"regex"         : {word1 : "의", word2 : "의"}
            ,"!regex"        : {word1 : "의", word2 : "의"}
            ,"alphabet"      : {word1 : "의", word2 : "의"}
            ,"hangul"        : {word1 : "의", word2 : "의"}
            ,"number"        : {word1 : "의", word2 : "의"}
            ,"signnumber"    : {word1 : "의", word2 : "의"}
            ,"hypennumber"   : {word1 : "의", word2 : "의"}
            ,"commanumber"   : {word1 : "의", word2 : "의"}
            ,"decimalnumber" : {word1 : "의", word2 : "의"}
        },
        getMessage : function(checker, title, verb, compareValue) {
            var postword = title.endJongsung() ? Validator.postwords[checker] ? Validator.postwords[checker].word1 : ""
                                               : Validator.postwords[checker] ? Validator.postwords[checker].word2 : "";
            var param = {
                _title_ : title,
                _postword_ : postword,
                _verb_ : Validator.verbs[verb],
                _value_ : compareValue
            };
            return Validator.messages[checker].format(param);
        },
        checker : {
            "eq" : function(elementValue, compareValue) {
                if (typeof compareValue === "number") {
                    return elementValue.length == 0 ? true : Number(elementValue.replace(",", "")) == compareValue ? true : false;
                } else { // string
                    return elementValue.length == 0 ? true : elementValue == compareValue ? true : false;
                }
            },
            "le" : function(elementValue, compareValue) {
                if (typeof compareValue === "number") {
                    return elementValue.length == 0 ? true : Number(elementValue.replace(",", "")) <= compareValue ? true : false; 
                } else { // string
                    return elementValue.length == 0 ? true : elementValue <= compareValue ? true : false;
                }
            },
            "ge" : function(elementValue, compareValue) { 
                if (typeof compareValue === "number") {
                    return elementValue.length == 0 ? true : Number(elementValue.replace(",", "")) >= compareValue ? true : false; 
                } else { // string
                    return elementValue.length == 0 ? true : elementValue >= compareValue ? true : false;
                }
            },
            "lt" : function(elementValue, compareValue) { 
                if (typeof compareValue === "number") {
                    return elementValue.length == 0 ? true : Number(elementValue.replace(",", "")) < compareValue ? true : false; 
                } else { // string
                    return elementValue.length == 0 ? true : elementValue < compareValue ? true : false;
                }
            },
            "gt" : function(elementValue, compareValue) { 
                if (typeof compareValue === "number") {
                    return elementValue.length == 0 ? true : Number(elementValue.replace(",", "")) > compareValue ? true : false; 
                } else { // string
                    return elementValue.length == 0 ? true : elementValue > compareValue ? true : false;
                }
            },            
            "maxlength" : function(elementValue, compareValue) { 
                return elementValue.length == 0 ? true : elementValue.length <= compareValue ? true : false;
            },
            "minlength" : function(elementValue, compareValue) { 
                return elementValue.length == 0 ? true : elementValue.length >= compareValue ? true : false;
            },
            "fixlength" : function(elementValue, compareValue) { 
                return elementValue.length == 0 ? true : elementValue.length == compareValue ? true : false;
            },
            "maxbyte" : function(elementValue, compareValue) { 
                return elementValue.length == 0 ? true : elementValue.getBytes() <= compareValue ? true : false; 
            },
            "minbyte" : function(elementValue, compareValue) { 
                return elementValue.length == 0 ? true : elementValue.getBytes() >= compareValue ? true : false;
            },
            "date" : function(elementValue, compareValue) { 
                if (elementValue.length == 0 || compareValue.length == 0) {
                    return true;
                }
                compareValue = compareValue.toUpperCase();
                var pattern = null;
                var date = null;
                switch(compareValue) {
                    case "YYYY.MM.DD": pattern = /^(\d{4})\.(\d{2})\.(\d{2})$/;    break;
                    case "YYYY-MM-DD": pattern = /^(\d{4})\-(\d{2})\-(\d{2})$/; break;
                    case "YYYY/MM/DD": pattern = /^(\d{4})\/(\d{2})\/(\d{2})$/; break;
                    case "YYYYMMDD"  : pattern = /^(\d{4})(\d{2})(\d{2})$/; break;
                    case "YY.MM.DD"  : pattern = /^(\d{2})\.(\d{2})\.(\d{2})$/;    break;
                    case "YY-MM-DD"  : pattern = /^(\d{2})\-(\d{2})\-(\d{2})$/; break;
                    case "YY/MM/DD"  : pattern = /^(\d{2})\/(\d{2})\/(\d{2})$/; break;
                    case "YYMMDD"    : pattern = /^(\d{2})(\d{2})(\d{2})$/;    break;
                    case "DD.MM.YYYY": pattern = /^(\d{2})\.(\d{2})\.(\d{4})$/;    break;
                    case "DD-MM-YYYY": pattern = /^(\d{2})\-(\d{2})\-(\d{4})$/;    break;
                    case "DD/MM/YYYY": pattern = /^(\d{2})\/(\d{2})\/(\d{4})$/;    break;
                    case "DDMMYYYY"  : pattern = /^(\d{2})(\d{2})(\d{4})$/;    break;
                    case "DD.MM.YY"  : pattern = /^(\d{2})\.(\d{2})\.(\d{2})$/;    break;
                    case "DD-MM-YY"  : pattern = /^(\d{2})\-(\d{2})\-(\d{2})$/;    break;
                    case "DD/MM/YY"  : pattern = /^(\d{2})\/(\d{2})\/(\d{2})$/;    break;
                    case "DDMMYY"    : pattern = /^(\d{2})(\d{2})(\d{2})$/;    break;
                }
                if (pattern == null) {
                    alert("unknown date pattern " + compareValue);
                    return false;
                }
                if (!pattern.test(elementValue)) {
                    return false;
                }
                switch(compareValue) {
                    case "YYYY.MM.DD":
                    case "YYYY-MM-DD":
                    case "YYYY/MM/DD":
                    case "YYYYMMDD":
                        date = new Date(elementValue.replace(pattern, '$2/$3/$1'));
                        return (parseInt(RegExp.$1, 10) == date.getFullYear())
                            && (parseInt(RegExp.$2, 10) == (1+date.getMonth())) 
                            && (parseInt(RegExp.$3, 10) == date.getDate());
                        break;
                    case "YY.MM.DD":
                    case "YY-MM-DD":
                    case "YY/MM/DD":
                    case "YYMMDD":
                        date = new Date(elementValue.replace(pattern, '$2/$3/$1'));
                        return (parseInt(RegExp.$1, 10) == date.getYear())
                            && (parseInt(RegExp.$2, 10) == (1+date.getMonth())) 
                            && (parseInt(RegExp.$3, 10) == date.getDate());
                        break;
                    case "DD.MM.YYYY":
                    case "DD-MM-YYYY":
                    case "DD/MM/YYYY":
                    case "DDMMYYYY":
                        date = new Date(elementValue.replace(pattern, '$2/$1/$3'));
                        return (parseInt(RegExp.$3, 10) == date.getFullYear())
                            && (parseInt(RegExp.$2, 10) == (1+date.getMonth())) 
                            && (parseInt(RegExp.$1, 10) == date.getDate());
                        break;
                    case "DD.MM.YY":
                    case "DD-MM-YY":
                    case "DD/MM/YY":
                    case "DDMMYY":
                        date = new Date(elementValue.replace(pattern, '$2/$1/$3'));
                        return (parseInt(RegExp.$3, 10) == date.getYear())
                            && (parseInt(RegExp.$2, 10) == (1+date.getMonth())) 
                            && (parseInt(RegExp.$1, 10) == date.getDate());
                        break;
                }
                return true;
            },
            "regex" : function(elementValue, compareValue) {
                if (elementValue.length == 0) {
                    return true;
                }
                var pattern = null;
                if (typeof compareValue === "string") {
                    if (compareValue.length == 0) {
                        return true;
                    }
                    pattern = new RegExp(compareValue);
                } else if (typeof compareValue === "object") {
                    pattern = compareValue;
                }
                var result = pattern.test(elementValue) ? true : false;
                return result;
            },
            "!regex" : function(elementValue, compareValue) { 
                if (elementValue.length == 0 || compareValue.length == 0) {
                    return true;
                }
                var pattern = null;
                if (typeof compareValue === "string") {
                    if (compareValue.length == 0) {
                        return true;
                    }
                    pattern = new RegExp(compareValue);
                } else if (typeof compareValue === "object") {
                    pattern = compareValue;
                }
                var result = !pattern.test(elementValue) ? true : false; 
                return result;
            },
            "!chars" : function(elementValue, compareValue) {
                var result = true;
                var chars = compareValue.split("");
                $.each(chars, function(i, c) {
                    result = elementValue.indexOf(c) > -1 ? false : true;
                    if (!result) {
                        return false; // break;
                    }
                });
                return result;
            },
            "!null" : function(elementValue, compareValue) { 
            	return compareValue == false || $.trim(elementValue).length > 0 ? true : false;  
            },
            "!space" : function(elementValue, compareValue) { 
                var pattern = /[ \t\r\n\v\f]/;
                return compareValue == false || !pattern.test(elementValue) ? true : false;
            },
            "!tag" : function(elementValue, compareValue) { 
                var pattern = /<\w+(\s?("[^"]*"|'[^']*'|[^>])+)?>|<\/\w+\s?>/gi;
                return compareValue == false || elementValue.length == 0 || !pattern.test(elementValue) ? true : false; 
            },
            "ssn" : function(elementValue, compareValue) {
                if (compareValue == false || elementValue.length == 0) {
                    return true;
                }
                var result = true;
                var pattern = /^[0-9]{13}$/;
                if (pattern.test(elementValue)) {
                    var sum = 0;
                    var ssnArray = new Array(13);
                    var chkArray = new Array( 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 );
                    for ( var y = 0; y < 13; y++ ) {
                        ssnArray[y] = parseInt( elementValue.charAt(y), 10);
                    }
                    for ( var y = 0; y < 12; y++ ) {
                        sum += ssnArray[y] * chkArray[y];
                    }
                    var rs = (11 - (sum % 11)) % 10;
                    if ( rs != ssnArray[12] ) {
                        result = false;
                    }
                } else {
                    result = false;
                }
                return result;
            },
            "frn" : function(elementValue, compareValue) { 
                if (compareValue == false || elementValue.length == 0) {
                    return true;
                }
                var result = true;
                var pattern = /^[0-9]{13}$/;
                if (pattern.test(elementValue)) {
                    var sum = 0;
                    var frnArray = new Array(13);
                    var chkArray = new Array( 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 );
                    for ( var y = 0; y < 13; y++ ) {
                        frnArray[y] = parseInt( elementValue.charAt(y), 10 );
                    }
                    for ( var y = 0; y < 12; y++ ) {
                        sum += frnArray[y] * chkArray[y];
                    }
                    if (((frnArray[7] * 10 + frnArray[8]) % 2 != 0)
                        || ((frnArray[11] != 6) && (frnArray[11] != 7) && (frnArray[11] != 8) && (frnArray[11] != 9))) {
                        result = false;
                    } else {
                        sum = 11 - (sum % 11);
                        if (sum >= 10) { 
                            sum -= 10; 
                        }
                        sum += 2;
                        if (sum >= 10) { 
                            sum -= 10; 
                        }
                        if (sum != frnArray[12]) {
                            result = false;
                        }
                    }
                } else {
                    result = false;
                }
                return result;
            },
            "email" : function(elementValue, compareValue) { 
                var pattern = /\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/;
                return compareValue == false || elementValue.length == 0 || pattern.test(elementValue) ? true : false; 
            },
            "url" : function(elementValue, compareValue) { 
                var pattern = /^(http|https|ftp|mms):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i;
                return compareValue == false || elementValue.length == 0 || pattern.test(elementValue) ? true : false; 
            },
            "ip" : function(elementValue, compareValue) { 
                var pattern = /(([0-1]?[0-9]{1,2}\.)|(2[0-4][0-9]\.)|(25[0-5]\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))/i;
                return compareValue == false || elementValue.length == 0 || pattern.test(elementValue) ? true : false; 
            },            
            "alphabet" : function(elementValue, compareValue) { 
                var pattern = /[^A-Za-z ]/;
                return compareValue == false || elementValue.length == 0 || !pattern.test(elementValue) ? true : false; 
            },
            "Hangul" : function(elementValue, compareValue) { 
                var pattern = /[^ㄱ-힣 ]/;
                return compareValue == false || elementValue.length == 0 || !pattern.test(elementValue) ? true : false; 
            },
            "number" : function(elementValue, compareValue) { 
                var pattern = /[^0-9]/;
                return compareValue == false || elementValue.length == 0 || !pattern.test(elementValue) ? true : false; 
            },
            "signnumber" : function(elementValue, compareValue) { 
                var pattern = /^([+-])?((?:\d*))?$/;
                return compareValue == false || elementValue.length == 0 || pattern.test(elementValue) ? true : false; 
            },
            "hypennumber" : function(elementValue, compareValue) { 
                var pattern = /[^0-9-]/;
                return compareValue == false || elementValue.length == 0 || !pattern.test(elementValue) ? true : false; 
            },
            "commanumber" : function(elementValue, compareValue) { 
                var pattern = /[^,0-9]|^[,]|^(\d{4,})|[,]{1}\d{0,2}$|(\d{4,})$|([,]{1}\d{0,2}[,]{1})/;
                return compareValue == false || elementValue.length == 0 || !pattern.test(elementValue) ? true : false; 
            },
            "decimalnumber" : function(elementValue, compareValue) { 
                var pattern = /[^\.0-9]|^[\.]/;
                return compareValue == false || elementValue.length == 0 || !pattern.test(elementValue) ? true : false; 
            }
        },
        events : {
            maxlength : ["maxlength", "maxbyte", "fixlength"],
            keydown : {
                "alphabet" : {
                    keyCodes : [8, 9, 13, 32, 35, 36, 37, 38, 39, 40, 46], // backspace, tab, enter, space, end, home, ←, ↑, →, ↓, delete, 한영
                    condition : [
                        "((event.keyCode >= 65) && (event.keyCode <= 90))" // 알파벳 
                    ]
                },
                "hangul" : {
                    keyCodes : [8, 9, 13, 32, 35, 36, 37, 38, 39, 40, 46, 229], // backspace, tab, enter, space, end, home, ←, ↑, →, ↓, delete, 한영
                    condition : [
                        "((event.keyCode >= 12592) && (event.keyCode <= 12687))"
                    ]
                },
                "number" : {
                    keyCodes : [8, 9, 13, 35, 36, 37, 38, 39, 40, 46], // backspace, tab, enter, end, home, ←, ↑, →, ↓, delete
                    condition : [
                        "(!event.shiftKey && (event.keyCode >= 48 && event.keyCode <= 57))",
                        "(!event.shiftKey && (event.keyCode >= 96 && event.keyCode <= 105))"
                    ]
                },
                "signnumber" : {
                    keyCodes : [8, 9, 13, 35, 36, 37, 38, 39, 40, 46], // backspace, tab, enter, end, home, ←, ↑, →, ↓, delete
                    condition : [
                        "(!event.shiftKey && (event.keyCode >= 48 && event.keyCode <= 57))", // 0 ~ 9
                        "(!event.shiftKey && (event.keyCode >= 96 && event.keyCode <= 105))", // 키패드 0 ~ 9
                        "(!event.shiftKey && (event.keyCode == 189 || event.keyCode == 107 || event.keyCode == 109))", // -, 키패드 +, 키패드 -
                        "( event.shiftKey && event.keyCode == 187)" // +
                    ]
                },
                "hypennumber" : {
                    keyCodes : [8, 9, 13, 35, 36, 37, 38, 39, 40, 46], // backspace, tab, enter, end, home, ←, ↑, →, ↓, delete
                    condition : [
                                 "(!event.shiftKey && (event.keyCode >= 48 && event.keyCode <= 57))", // 0 ~ 9
                                 "(!event.shiftKey && (event.keyCode >= 96 && event.keyCode <= 105))", // 키패드 0 ~ 9
                                 "(!event.shiftKey && (event.keyCode == 189 || event.keyCode == 109))" // -, 키패드 -
                                 ]
                },
                "commanumber" : {
                    keyCodes : [8, 9, 13, 35, 36, 37, 38, 39, 40, 46], // backspace, tab, enter, end, home, ←, ↑, →, ↓, delete
                    condition : [
                        "(!event.shiftKey && (event.keyCode >= 48 && event.keyCode <= 57))",
                        "(!event.shiftKey && (event.keyCode >= 96 && event.keyCode <= 105))"
                    ]
                },
                "decimalnumber" : {
                    keyCodes : [8, 9, 13, 35, 36, 37, 38, 39, 40, 46], // backspace, tab, enter, end, home, ←, ↑, →, ↓, delete
                    condition : [
                         "(!event.shiftKey && (event.keyCode >= 48 && event.keyCode <= 57))",
                         "(!event.shiftKey && (event.keyCode >= 96 && event.keyCode <= 105))",
                         "(!event.shiftKey && (event.keyCode == 110 || event.keyCode == 190) && ($(event.currentTarget).val().indexOf('.') == -1))" // 키패드 ., .
                    ]
                },
                "ssn" : {
                    keyCodes : [8, 9, 13, 35, 36, 37, 38, 39, 40, 46], // backspace, tab, enter, end, home, ←, ↑, →, ↓, delete
                    condition : [
                        "(!event.shiftKey && (event.keyCode >= 48 && event.keyCode <= 57))",
                        "(!event.shiftKey && (event.keyCode >= 96 && event.keyCode <= 105))"
                    ]
                }
            }
        },
        availKeys : function(event) {
            if ($.inArray(event.keyCode, event.data.keyCodes) > -1) {
                event.returnValue = true;
            } else {
                if (typeof event.data.condition !== "undefined") {
                    if (eval(event.data.condition.join(" || "))) {
                        event.returnValue = true;
                    } else {
                        event.returnValue = false;
                    }
                } else {
                    event.returnValue = false;
                }
            }
            if (event.returnValue == false && event.preventDefault) {
                event.preventDefault();
            }
        }
    };
    var methods = {
        create : function(formId, options) {
            var _validator = function () {
                var property = {
                    title : null,
                    name : null,
                    when : null,
                    message : null,
                    notExistIsNull : false, // !null 을 설정했을 때 해당 element가 존재하지 않을 경우 null 로 간주한다. 
                    data : [
                        "trim", "!null", "!space", "!tag", "number", "signnumber", "hypennumber", "commanumber", "decimalnumber", 
                        "hangul", "alphabet", "ssn", "frn", "email", "ip", "url"
                    ],
                    check : {
                        number : ["eq", "le", "ge", "lt", "gt", "maxlength", "minlength", "fixlength", "maxbyte", "minbyte"],
                        string : ["eq", "le", "ge", "lt", "gt", "date", "regex", "!regex", "!chars"],
                        object : ["eq", "le", "ge", "lt", "gt"]
                    },
                    keyPrevent : true
                };
                var objectProperty = ["name", "title"];
                var publics = {
                    option : {
                    },
                    settings : [],
                    $form : null,
                    formId : "",
                    isValidParameter : function(setting) {
                        if (typeof setting.name === "undefined") {
                            alert("'name' is required.");
                            return false;
                        }
                        if (typeof setting.name !== "string" && $.isArray(setting.name) == false) {
                            alert("name type is invalid. 'string' and 'array' is only available.");
                            return false;
                        }
                        if ($.isArray(setting.name) == true && setting.name.length < 2) {
                            alert("'name' array must be at least two.");
                            return false;
                        }
                        if (typeof setting.title === "undefined" && typeof setting.message === "undefined") {
                            alert("'title' or 'message' must be defined.");
                            return false;
                        }
                        if (typeof setting.data === "undefined" && typeof setting.check === "undefined") {
                            alert("'data' or 'check' must be defined.");
                            return false;
                        }
                        if (typeof setting.data !== "undefined") {
                            if ($.isArray(setting.data) == false) {
                                alert("data type is invalid. 'array of string' is only available.");
                                return false;
                            }
                            for (var index = 0; index < setting.data.length; index++) {
                                if ($.inArray(setting.data[index], property.data) == -1) {
                                    alert("'" + setting.data[index] + "' is not supported.");
                                    return false;
                                }
                            }
                        }
                        if (typeof setting.check !== "undefined") {
                            for (var item in setting.check) {
                                if ($.inArray(item, property.check[typeof setting.check[item]]) == -1) {
                                    alert("'" + item + ":" + typeof setting.check[item] + "' is not supported.");
                                    return false;
                                }
                                if (typeof setting.check[item] === "object") {
                                    for (var index = 0; index < objectProperty.length; index++) {
                                        if (typeof setting.check[item][objectProperty[index]] === "undefined") {
                                            alert("'" + objectProperty[index] + "' is required.");
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                        for (var item in setting) {
                            if (typeof property[item] === "undefined") {
                                alert("'" + item + "' is not supported.");
                                return false;
                            }
                        }
                        return true;
                    },
                    set : function(setting) {
                        switch (typeof setting) {
                        case "function":
                            this.settings.push(setting);
                            break;
                        case "object":
                            if (this.isValidParameter(setting) == false) {
                                return;
                            }
                            if (setting.keyPrevent !== false) {
                                this.setEvent(setting);
                            }
                            this.settings.push(setting);
                            break;
                        default :
                            alert("parameter is invalid.");
                            break;
                        }
                    },
                    checking : function($elements, elementValue, compareValue, checkItem, setting) {
                        var _super = this;
                        if (typeof setting.data !== "undefined") {
                            if ($.inArray("alphabet", setting.data) == -1 && $.inArray("hangul", setting.data) == -1) {
                                if ($.inArray("number", setting.data) > -1 || $.inArray("decimalnumber", setting.data) > -1
                                || $.inArray("commanumber", setting.data) > -1 || $.inArray("signnumber", setting.data) > -1 
                                || $.inArray("hypennumber", setting.data) > -1) {
                                    if (typeof compareValue === "string") {
                                        compareValue = Number(compareValue.replace(",", ""));
                                    }
                                }
                            }
                        }
                        if (Validator.checker[checkItem](elementValue, compareValue) == false) {
                            var element = $elements.get(0);
                            var message = _super.getMessage(element, checkItem, setting);
                            $.dialog("alert", {
                                message : message, 
                                button1 : {
                                    callback : function() {
                                        try {
                                            element.focus();
                                        } catch (e) {
                                        }
                                    }
                                }
                            });
                            return false;
                        }
                        return true;
                    },
                    checkingData : function($elements, elementValue, setting) {
                        var _super = this;
                        var patterns = {
                            "alphabet" : "A-Za-z",
                            "number" : "0-9",
                            "hangul" : "ㄱ-힣"
                        };
                        var single = [];
                        var multiple = [];
                        var c = 0;
                        for (var index = 0; index < setting.data.length; index++) {
                            if (typeof patterns[setting.data[index]] !== "undefined") {
                                c++;
                            }
                        }
                        for (var index = 0; index < setting.data.length; index++) {
                            if (c < 2 || typeof patterns[setting.data[index]] === "undefined") {
                                single.push(setting.data[index]); 
                            } else {
                                multiple.push(patterns[setting.data[index]]);
                            }
                        }
                        var result = "";
                        for (var index = 0; index < single.length; index++) {
                            var checkItem = single[index];
                            if ("trim" === checkItem) {
                                continue;
                            }
                            if (Validator.checker[checkItem](elementValue, true) == false) {
                                result = checkItem;
                                break;
                            }
                        }
                        if (result == "" && multiple.join("").length > 0 && Validator.checker["!regex"](elementValue, "[^" + multiple.join("") + "]") == false) {
                            result = "!regex";
                        }
                        if (result != "") {
                            var element = $elements.get(0);
                            var message = _super.getMessage(element, result, setting);
                            _super.alertMessage(element, message);
                            return false;
                        }
                        return true;
                    },
                    alertMessage : function(element, message) {
                        var _super = this;
                        $.dialog("alert", {
                            message : message, 
                            button1 : {
                                callback : function() {
                                    try {
                                        element.focus();
                                    } catch (e) {};
                                }
                            }
                        });
                    },
                    isValid : function() {
                        if (this.$form == null) {
                            this.$form = $("#" + this.formId); 
                        }
                        if (this.$form.length != 1) {
                            return true;
                        }
                        var _super = this;
                        for (var item in _super.settings) {
                            var setting = _super.settings[item];
                            if (typeof setting.when === "function") {
                                var result = setting.when.call();
                                if (typeof result !== "boolean" ) {
                                    alert("'when' function must return a boolean.");
                                    return false;
                                }
                                if (result == false) {
                                    continue;
                                }
                            }
                            if (typeof setting === "function") {
                                var result = setting.call();
                                if (typeof result !== "boolean" ) {
                                    alert("function must return a boolean.");
                                    return false;
                                }
                                if (result == false) {
                                    return result;
                                }
                            } else {
                                var $elements = _super.getElements(setting.name);
                                $elements = $elements.not(":disabled");
                                if ($elements.length == 0) {
                                    if (typeof setting.data !== "undefined" && $.inArray("!null", setting.data) > -1 && setting.notExistIsNull == true) {
                                        var message = _super.getMessage(null, "!null", setting);
                                        _super.alertMessage(null, message);
                                        return false;
                                    } else {
                                        continue;
                                    }
                                }
                                
                                if (typeof setting.data !== "undefined" && $.inArray("trim", setting.data) > -1) {
                                    $elements.each(function() {
                                        $(this).val($(this).val().trim());
                                    });
                                }

                                if ($.isArray(setting.name) == true) {
                                    var elementValue = _super.getElementsValues($elements);
                                    for (var checkItem in setting.check) {
                                        var compareValue = setting.check[checkItem];
                                        if (typeof compareValue === "object") {
                                            var $compareElements = _super.getElements(setting.check[checkItem].name);
                                            $compareElements = $compareElements.not(":disabled");
                                            if ($compareElements.length == 0) {
                                                continue;
                                            } else if ($compareElements.length > 1) {
                                                alert("can not compare with multiple elements.");
                                                return false;
                                            }
                                            compareValue = _super.getElementsValues($compareElements).join("");
                                        }
                                        if (_super.checking($elements, elementValue.join(""), compareValue, checkItem, setting) == false) {
                                            return false;
                                        }
                                    }
                                    if (_super.checkingData($elements, elementValue.join(""), setting) == false) {
                                        return false;
                                    }
                                } else {
                                    var result = true;
                                    $elements.each(function() {
                                        var element = this;
                                        var elementValue = "";
                                        if (element.type.toUpperCase() == "RADIO" || element.type.toUpperCase() == "CHECKBOX") {
                                            elementValue = _super.getElementsValues($elements).join("");
                                        } else {
                                            elementValue = $(element).val();
                                        }
                                        if (elementValue == null) {
                                            elementValue = "";
                                        }
                                        if (typeof setting.check !== "undefined") {
                                            for (var checkItem in setting.check) {
                                                var compareValue = setting.check[checkItem];
                                                if (typeof compareValue === "object") {
                                                    var $compareElements = _super.getElements(setting.check[checkItem].name);
                                                    $compareElements = $compareElements.not(":disabled");
                                                    if ($compareElements.length == 0) {
                                                        continue;
                                                    } else if ($compareElements.length > 1) {
                                                        alert("can not compare with multiple elements.");
                                                        result = false;
                                                        return false;
                                                    }
                                                    compareValue = _super.getElementsValues($compareElements).join("");
                                                }
                                                if (_super.checking($(element), elementValue, compareValue, checkItem, setting) == false) {
                                                    result = false;
                                                    return false;
                                                }
                                            }
                                        }
                                        if (typeof setting.data !== "undefined") {
                                            if (_super.checkingData($(element), elementValue, setting) == false) {
                                                result = false;
                                                return false;
                                            }
                                        }
                                    });
                                    if (result == false) {
                                        return result;
                                    }
                                }
                            }
                        }
                        return true;
                    },
                    getElements : function(elementName) {
                        $field = this.$form.find(":input").filter(function() {
                            if (typeof elementName === "string") {
                                return this.name == elementName; 
                            } else if ($.isArray(elementName) == true) {
                                for (var index = 0; index < elementName.length; index++) {
                                    return this.name == elementName[index];
                                }
                            }
                        });
                        return $field;
                    },
                    getElementsValues : function($element) {
                        var values = [];
                        $element.each(function() {
                            var val = "";
                            if (this.type.toUpperCase() == "RADIO" || this.type.toUpperCase() == "CHECKBOX") {
                                if (this.checked == true) {
                                    val = $(this).val();
                                }
                            } else {
                                val = $(this).val();
                            }
                            if (val == null) {
                                val = "";
                            }
                            values.push(val);
                        });
                        return values;
                    },
                    setEvent : function(setting) {
                        if (this.$form == null) {
                            this.$form = $("#" + this.formId); 
                        }
                        if (this.$form.length != 1) {
                            return;
                        }
                        var $elements = this.getElements(setting.name);
                        for (var item in setting.check) {
                            if ($.inArray(item, Validator.events.maxlength) > -1) {
                                $elements.each(function() {
                                    var element = this;
                                    if (element.tagName == "INPUT" && element.type.toUpperCase() == "TEXT") {
                                        $(element).attr("maxLength", setting.check[item]);
                                    }
                                });
                            }
                        }

                        var keydownParam = {keyCodes : [], condition : []};
                        for (var index in setting.data) {
                            if (typeof Validator.events.keydown[setting.data[index]] !== "undefined") {
                                $.merge(keydownParam.keyCodes, Validator.events.keydown[setting.data[index]].keyCodes);
                                $.merge(keydownParam.condition, Validator.events.keydown[setting.data[index]].condition);
                            }
                        }

                        if (keydownParam.keyCodes.length > 0 || keydownParam.condition.length > 0) {
                            $elements.each(function() {
                                var element = this;
                                $(element).bind("keydown", keydownParam, Validator.availKeys);
                            });
                        }
                        $elements.each(function() {
                            var element = this;
                            if (typeof setting.data !== "undefined") {
                                if ($.inArray("alphabet", setting.data) > -1) {
                                    $(element).css("ime-mode", "inactive");
                                }
                                if ($.inArray("hangul", setting.data) > -1) {
                                    $(element).css("ime-mode", "active");
                                } else {
                                    if ($.inArray("number", setting.data) > -1 
                                            || $.inArray("signnumber", setting.data) > -1 
                                            || $.inArray("hypennumber", setting.data) > -1 
                                            || $.inArray("commanumber", setting.data) > -1 
                                            || $.inArray("decimalnumber", setting.data) > -1) { 
                                        $(element).css("ime-mode", "disabled");
                                    }
                                }
                                if ($.inArray("commanumber", setting.data) > -1) {
                                    $(element).val($(element).val().toComma());
                                    $(element).bind("keyup", function(event) {
                                        var obj = event.target ? event.target : event.srcElement;    
                                        var str = obj.value.replace(/[,]/g, '');
                                        var arr = str.split('.');
                                        if (arr.length <= 2) {
                                            if (!isNaN(arr[0])) {
                                                var pattern = /([+-]?\d+)(\d{3})/;   // 정규식
                                                while (pattern.test(arr[0])) {
                                                    arr[0] = arr[0].replace(pattern, '$1,$2');
                                                }
                                            }
                                            obj.value = arr.join('.');
                                        }
                                    });
                                }
                            }
                        });
                        
                    },
                    getMessage : function(element, item, setting) {
                        if (typeof setting.message === "undefined") {
                            var params = {
                                _title_ : setting.title
                                ,_postword_ : this.getPostword(setting.title, item)
                                ,_value_ : typeof setting.check !== "undefined" && typeof setting.check[item] !== "undefined" ? setting.check[item] : ""  
                                ,_verb_ : this.getVerb(element)
                            };
                            if (typeof setting.check !== "undefined" && typeof setting.check[item] === "object") {
                                params._value_ = setting.check[item].title;
                            }
                            return Validator.messages[item].format(params);
                        } else {
                            return setting.message;
                        }
                        
                    },
                    getVerb : function(element) {
                        var verb = Validator.verbs.enter;
                        if (element != null) {
                            switch(element.type.toUpperCase()) {
                                case "CHECKBOX":
                                case "RADIO":
                                case "SELECT-ONE":
                                case "SELECT-MULTIPLE":
                                    verb = Validator.verbs.select;
                                    break;
                            }
                        }
                        return verb;
                    },
                    getPostword : function(title, checker) {
                        if (title == null) {
                            title = "";
                        }
                        return title.endJongsung() ? Validator.postwords[checker] ? Validator.postwords[checker].word1 : ""
                                                   : Validator.postwords[checker] ? Validator.postwords[checker].word2 : "";
                    }
                };
                
                return publics;
            }
            var _validator = new _validator();
            _validator.formId = formId;
            _validator.option = $.extend(true, Validator.defaults, options);
            return _validator;
        }
    };
    $.validator = function(formId, options) {
        
        var _validator = methods.create.apply(this, arguments);
        if (typeof formId !== "string" || _validator == null) {
            alert("validator does not create");
            return null;
        }
        return _validator;
    };
    IMGLBObject.validator = Validator;
})(jQuery);

(function($) {
    var methods = {
        create : function() {
            var style = function () {
                var publics = {
                    styleSheet : null,    
                    addClass : function(className, styles) {
                        var arr = []; 
                        for (style in styles) {
                            arr.push(style + ":" + styles[style]);
                        }
                        if (typeof this.styleSheet.insertRule === "function") { // all browsers, except IE before version 9
                            try {
                                this.styleSheet.insertRule(className + " {" + arr.join(";") + "}",  this.styleSheet.cssRules.length);
                            } catch (e) {
                            }
                        } else if (typeof this.styleSheet.addRule === "object") { // Internet Explorer before version 9
                            this.styleSheet.addRule(className, arr.join(";"), -1);
                        }
                    }
                }
                var $style = $("<style rel='alternate stylesheet' type='text/css'></style>"); 
                $style.appendTo("head");
                publics.styleSheet = document.styleSheets[document.styleSheets.length - 1];
                return publics;
            }
            return new style(); 
        }
    }
    $.styleClass = function() {
        return methods.create.apply(this, arguments);
    }
})(jQuery);

(function($) {
    if (!String.prototype.trim) {
        String.prototype.trim = function() {
            return this.replace(/^\s*|\s*$/g, "");
        };
    }
    if (!String.prototype.startsWith) {
        String.prototype.startsWith = function(str) {
            return (this.match("^"+str) == str);
        };
    }
    if (!String.prototype.endsWith) {
        String.prototype.endsWith = function(str) {
            return (this.match(str+"$") == str);
        };
    }
    if (!String.prototype.getBytes) {
        String.prototype.getBytes = function () {
            return this.length + (escape(this)+"%u").match(/%u/g).length-1;
        };
    }
    if (!String.prototype.relaceAll) {
        String.prototype.replaceAll = function(oldstr, newstr) {
            var pattern = new RegExp(oldstr, "g");
            return this.replace(pattern, newstr);
        };
    }
    if (!String.prototype.toComma) {
        String.prototype.toComma = function () {
            var str = this.replace(/,/g, '');
            if (!isNaN(str)) {
                var pattern = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
                var num = str.split('.');
                num[0] += '.';
                do {
                    num[0] = num[0].replace(pattern, '$1,$2');
                } while (pattern.test(num[0]));
                if (num.length > 1) {
                    return num.join('');
                } else {
                    return num[0].split('.')[0];
                }
            } else {
                return this;
            }
        };
    }
    if (!String.prototype.escapeXml) {
        String.prototype.escapeXml = function () {
            var returnValue = this;
            var chars = {"&amp;" : "&", "&lt;" : "<", "&gt;" : ">", "&#034;" : '"', "&#039;" : "'"};  
            $.each(chars, function (i, c) {
                returnValue = returnValue.replace(new RegExp(c.key, 'mg'), c.value);
            });
            return returnValue;
        };
    }
    if (!String.prototype.format) {
        String.prototype.format = function (col) {
            col = typeof col === 'object' ? col : Array.prototype.slice.call(arguments);
    
            return this.replace(/\{([^}]+)\}/gm, 
                function () {
                    return col[arguments[1]];    
                }
            );        
        };
    }
    if (!String.prototype.endJongsung) {
        String.prototype.endJongsung = function() {
            // str의 마지막글자의 받침이 있는지 검사하여. 있으면  true
            if (this == null) {
                return false;
            }
            if (this.length < 1) {
                return false;
            }
            var chr = this.substring(this.length - 1);
            var ascii = chr.charCodeAt();
            if (!((ascii >= 44032) && (ascii <= 55203))) {
                return false;
            }
            if ((ascii - 44032) % 588 % 28 == 0) {
                return false;
            } else {
                return true;
            }
        };
    }
    if (!String.prototype.encodeIfNumber) {
        String.prototype.encodeIfNumber = function() {
            // 숫자형식의 스트링을 암호화(이진수+각각을아스키문자로변환+리버스)
            if (isNaN(this)) {
                return this;
            }
            var encode = [];
            var array = parseInt(this, 10).toString(2).split("");
            var len = array.length;
            for (var i = 0; i < len; i++) {
                encode.push(String.fromCharCode(parseInt(array[i], 10) + 1 + i));
            }
            return encode.reverse().join(String.fromCharCode(1));
        };
    }
})(jQuery);

// collection reverse 
jQuery.fn.reverse = [].reverse;
