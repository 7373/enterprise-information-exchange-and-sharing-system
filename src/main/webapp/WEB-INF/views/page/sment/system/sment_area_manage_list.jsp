<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">

    <title>区域类型管理</title>
    <link rel="stylesheet" href="/js/lib/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="/css/sment/sment.server.css">
</head>
<body>
<div class="pd20 clearfix post-permissions">

    <div class="col-5">
        <div class="fl mr40">
            <h4 class="ft14"><i class="xbt-icon icon-send-dept"></i><strong>区域类型列表</strong></h4>
            <div class="" style="overflow:auto;height: 655px;width: 425px;">
                <ul id="areaTypeTree" class="ztree"></ul>
            </div>
        </div>
    </div>

    <div class="col-6 information-input-box">
        <p class="row mt5">
            <button class="btn btn-sm" id="addBtn">新增</button>
            <button class="btn btn-sm" id="editBtn">修改</button>
            <button class="btn btn-sm" id="delBtn">删除</button>
        </p>
        <div id="showArea">

        </div>
    </div>
</div>
<script id="viewTemplate" type="text/x-handlebars-template">
    <input type="hidden" id="id" value="{{id}}"/>
    <table class="table-horizontal mt15">
        <tbody>
        <tr>
            <td class="right bg-gray" width="24%">上级区域类型名称：</td>
            <td class="text-left pName">{{parentName}}</td>
        </tr>
        <tr>
            <td class="right  bg-gray">区域类型名称：</td>
            <td class="text-left">{{areaName}}</td>
        </tr>
        </tbody>
    </table>
</script>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/sment/system/sment_area_manage_list.js"></script>
</body>
</html>
