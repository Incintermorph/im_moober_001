<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



${detail.nameCard.searchCnd}<br>
${detail.nameCard.tmplatSeCodeNm}<br>
${detail.nameCard.emplyrId}<br>

<c:set  var="kendoBase" value="/egoveframework-all-in-one/js/kendoui.for.jquery.2021.3.1109.trial"/>

<!DOCTYPE html>
<html>
<head>
    <title>Popup editing</title>
    <meta charset="utf-8">
    <link href="${kendoBase}/examples/content/shared/styles/examples-offline.css" rel="stylesheet">
    <link href="${kendoBase}/styles/kendo.common.min.css" rel="stylesheet">
    <link href="${kendoBase}/styles/kendo.rtl.min.css" rel="stylesheet">
    <link href="${kendoBase}/styles/kendo.default.min.css" rel="stylesheet">
    <link href="${kendoBase}/styles/kendo.default.mobile.min.css" rel="stylesheet">
    <script src="${kendoBase}/js/jquery.min.js"></script>
    <script src="${kendoBase}/js/jszip.min.js"></script>
    <script src="${kendoBase}/js/kendo.all.min.js"></script>
    <script src="${kendoBase}/examples/content/shared/js/console.js"></script>
    <script>
        
    </script>
    
    
</head>
<body>    
    <%--
test : ${dto.callName} --%>
    <div id="example">
    <div id="grid"></div>

    <script>
        $(document).ready(function () {
            var crudServiceBaseUrl = "https://demos.telerik.com/kendo-ui/service",
                dataSource = new kendo.data.DataSource({
                    transport: {
                        read:  {
                            url: crudServiceBaseUrl + "/Products",
                            dataType: "jsonp"
                        },
                        update: {
                            url: crudServiceBaseUrl + "/Products/Update",
                            dataType: "jsonp"
                        },
                        destroy: {
                            url: crudServiceBaseUrl + "/Products/Destroy",
                            dataType: "jsonp"
                        },
                        create: {
                            url: crudServiceBaseUrl + "/Products/Create",
                            dataType: "jsonp"
                        },
                        parameterMap: function(options, operation) {
                            if (operation !== "read" && options.models) {
                                return {models: kendo.stringify(options.models)};
                            }
                        }
                    },
                    batch: true,
                    pageSize: 20,
                    schema: {
                        model: {
                            id: "ProductID",
                            fields: {
                                ProductID: { editable: false, nullable: true },
                                ProductName: { validation: { required: true } },
                                UnitPrice: { type: "number", validation: { required: true, min: 1} },
                                Discontinued: { type: "boolean" },
                                UnitsInStock: { type: "number", validation: { min: 0, required: true } }
                            }
                        }
                    }
                });

            $("#grid").kendoGrid({
                dataSource: dataSource,
                pageable: true,
                height: 550,
                toolbar: ["create"],
                columns: [
                    { field:"ProductName", title: "Product Name" },
                    { field: "UnitPrice", title:"Unit Price", format: "{0:c}", width: "120px" },
                    { field: "UnitsInStock", title:"Units In Stock", width: "120px" },
                    { field: "Discontinued", width: "120px", editor: customBoolEditor },
                    { command: ["edit", "destroy"], title: "&nbsp;", width: "250px" }],
                editable: "popup"
            });
        });

        function customBoolEditor(container, options) {
            $('<input class="k-checkbox" type="checkbox" name="Discontinued" data-type="boolean" data-bind="checked:Discontinued">').appendTo(container);
        }
    </script>
</div>


    
</body>
</html>
