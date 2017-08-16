<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title></title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/js/lib/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="/css/syn.css">
</head>
<body>
<div style="margin-bottom: 10px;"  class="ztree">
    <div>
        <!-- 所选是： <span id="values" style="color: blue;"></span> -->
    </div>
    <input id="cityCode" type="hidden">
    <input id="type" type="hidden" value="${type }">
    <input id="indType" type="hidden" value="${indType}">
    <ul id="permisionTree"></ul>
</div>
<div class="row ml10 mt5">
    <button id="save" type="button" class="btn">保存</button>
    <button id="cancel" type="button" class="btn ml20">取消</button>

</div>

<script>
    var roleId = '${sysRole.id}';
</script>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>

<script src="/js/common/select/entcatgmutiselectCom.js"></script>
</body>
</html>